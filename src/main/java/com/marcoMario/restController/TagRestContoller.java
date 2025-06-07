package com.marcoMario.restController;
import com.marcoMario.iService.ITagService;
import com.marcoMario.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagRestContoller {

    @Autowired
    private ITagService tagService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getTagById")
    public Tag getTagById(@RequestParam("idTag") long idTag) {
        return tagService.getTagById(idTag);
    }


}
