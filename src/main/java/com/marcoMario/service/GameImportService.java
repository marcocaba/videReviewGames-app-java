package com.marcoMario.service;

import com.marcoMario.model.Achievement;
import com.marcoMario.model.Game;
import com.marcoMario.model.Platform;
import com.marcoMario.repository.AchievementRepository;
import com.marcoMario.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class GameImportService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AchievementRepository achievementRepository;


    private final String API_KEY ="8dd82acdb7a4483596921d73e78128e4";
    private final String BASE_URL = "https://api.rawg.io/api/games";

    public void importGames(int maxPages){
        RestTemplate restTemplate = new RestTemplate();

        for (int page = 1; page <= maxPages;page++){
            String url = BASE_URL + "?key=" + API_KEY + "&page=" + page;

            try {
                String response = restTemplate.getForObject(url, String.class);
                JSONObject json = new JSONObject(response);
                JSONArray results = json.getJSONArray("results");

                for (int i=0;i<results.length();i++){
                    JSONObject game = results.getJSONObject(i);

                    Game newGame = new Game();

                    newGame.setId(game.getLong("id"));
                    newGame.setName(game.getString("name"));
                    newGame.setDescription(game.getString("description"));
                    newGame.setReleased(game.getString("released"));
                    newGame.setTba(game.getBoolean("tba"));
                    newGame.setWebsite(game.getString("website"));

                    importAchievements(newGame, restTemplate);

                    importPlatforms(newGame,json);

                    gameRepository.save(newGame);
                }

            }catch (Exception e){
                System.err.println("Error en la pagina "+page+ ": " +e.getMessage());
            }
        }
    }

    private void importAchievements(Game newGame, RestTemplate restTemplate){
        String achievementsUrl = BASE_URL + "/" + newGame.getId() + "/achievements?key=" + API_KEY;

        try{
            String achievementsResponse = restTemplate.getForObject(achievementsUrl, String.class);
            JSONObject achievementsJson = new JSONObject(achievementsResponse);
            JSONArray achievementsArray = achievementsJson.getJSONArray("results");

            for (int j = 0; j < achievementsArray.length(); j++){
                JSONObject achievementJson = achievementsArray.getJSONObject(j);

                Achievement achievement = new Achievement();
                achievement.setId(achievementJson.getInt("id"));
                achievement.setName(achievementJson.getString("name"));
                achievement.setDescription(achievementJson.optString("description", ""));
                achievement.setImage(achievementJson.optString("image", null));
                achievement.setPercent(achievementJson.getString("percent"));
                achievement.setGame(newGame);

                newGame.getAchievements().add(achievement);
            }

        }catch (Exception e){
            System.err.println("Error importando logros para el juego "+newGame.getName()+ " con ID: "+newGame.getId()+": "+e.getMessage());
        }
    }

    private void importPlatforms(Game newGame, JSONObject json){
        try {
            JSONArray platformsArray = json.getJSONArray("platforms");

            if (platformsArray != null) {
                for (int p = 0; p < platformsArray.length(); p++) {
                    JSONObject platformEntry = platformsArray.getJSONObject(p);
                    JSONObject platformObj = platformEntry.getJSONObject("platform");

                    Platform platform = new Platform();

                    platform.setId(platformObj.getInt("id"));
                    platform.setSlug(platformObj.getString("slug"));
                    platform.setName(platformObj.getString("name"));
                    platform.setRelease_at(platformObj.optString("released_at", null));

                    if (platformEntry.has("requirements")) {
                        JSONObject requirements = platformEntry.getJSONObject("requirements");
                        platform.setRequirementsMinimum(requirements.optString("minimum", null));
                        platform.setRequirementsRecommended(requirements.optString("recommended", null));
                    }
                    platform.setGame(newGame);

                    newGame.getPlatforms().add(platform);
                }
            }
        }catch (Exception e){
            System.err.println("Error importando plataformas para el juego "+newGame.getName()+ " con ID: "+newGame.getId()+": "+e.getMessage());
        }
    }

}
