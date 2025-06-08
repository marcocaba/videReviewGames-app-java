package com.marcoMario.restController;

import com.marcoMario.iService.IPlatformService;
import com.marcoMario.model.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlatformRestController {

    @Autowired
    private IPlatformService platformService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getPlatformById")
    public Platform getPlatformById(@RequestParam("idPlatform") long idPlatform) {
        return platformService.getPlatformById(idPlatform);
    }
}
