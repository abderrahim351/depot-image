package com.sdi.dimage.web;

import com.drew.imaging.ImageProcessingException;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.services.ImageService;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {
    @Autowired
    private ImageService service;
//enregistrer document )
    @PostMapping("document")
    public void ajouterDocument(@RequestBody DocumentModel img, HttpServletRequest request){
        UtilisateurSessionDto user = getUserSession(request);
       this.service.enregistrerImage(img, user);

    }

    //enregistrer image
    @PostMapping("/img")
    public void upload(@RequestParam("file") MultipartFile file) throws IOException, ImageProcessingException {
        service.uplodfile(file);


    }

    @GetMapping("/img")
    public List<ImageEntity> getimg(){
        return this.service.getphoto();
    }


    private UtilisateurSessionDto getUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println(session);
        if (session != null) {
            return (UtilisateurSessionDto) session.getAttribute(ConnexionController.LOGGED_USER);

        }
        return null;
    }

}
