package com.sdi.dimage.web;

import com.drew.imaging.ImageProcessingException;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.services.ImageService;
import com.sdi.dimage.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {
    @Autowired
    private ImageService service;

    //enregistrer image
    @PostMapping("/img")
    public void upload(@RequestParam("file") MultipartFile file) throws IOException, ImageProcessingException {
        service.uplodfile(file);


    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/img")
    public List<ImageEntity> getimg(){
        return this.service.getphoto();
    }
}
