package com.sdi.dimage.web;

import com.drew.imaging.ImageProcessingException;
import com.sdi.dimage.dao.entities.DocumentEntity;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.services.ImageService;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/api")
public class ImageController {
    @Autowired
    private ImageService service;
//enregistrer document )
    @PostMapping("document")
    public Integer ajouterDocument(@RequestBody DocumentModel img, HttpServletRequest request){
        UtilisateurSessionDto user = getUserSession(request);
      return this.service.enregistrerdoc(img, user);

    }

    //enregistrer image
   @PostMapping("/img")
	public void fileUpload(@RequestParam("file") MultipartFile file) throws IOException, ImageProcessingException
	{
		this.service.uplodfile(file );
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
