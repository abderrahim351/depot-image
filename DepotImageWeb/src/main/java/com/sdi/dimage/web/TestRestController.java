package com.sdi.dimage.web;

import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.AdministrateurEntity;
import com.sdi.dimage.dao.entities.GestionnaireEntity;
import com.sdi.dimage.dao.entities.UtilisateurEntity;
import com.sdi.dimage.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/test")
public class TestRestController {

   @Autowired
    private TestService service;





    @Autowired
    private JavaMailSender emailSender;
    @GetMapping("/email")
    public void sendSimpleMessage() {

      // service.sendmail("abderrahimjamaaoui4@gmail.com");

    }
 //enregistrer image
 //@CrossOrigin(origins = "http://localhost:4200")



    /* @GetMapping("/image")
     public String testAddImage() {
         service.AddImage();
         return  "image_ajoutés";
     }*/


    //tester connexion
   /* @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/connexion")
    public UtilisateurDto getparemail(@RequestParam String email, String passe) {
        return this.service.testconnexion(email, passe);
    }

    //enregistrer utilisateur
    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/utilisateur/enregistrer")
    public void enregistrer(@RequestBody UtilisateurDto util) {
        this.service.enregistrer(util);
    }

    //modifier utilisateur
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/utilisateur/modifier")
    public void modifier(@RequestBody UtilisateurDto util) {
        this.service.enregistrer(util);

    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/utilisateur/test_par_email/{email}")
    public boolean test_par_mail(@PathVariable String email) {
      return   this.service.test_par_email(email);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/utilisateur/test_modification")
    public boolean test_modification(@RequestBody UtilisateurDto utl) {
        return   this.service.test_modification(utl);
    }*/
}



