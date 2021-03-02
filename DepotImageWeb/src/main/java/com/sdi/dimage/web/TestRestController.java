package com.sdi.dimage.web;

import com.sdi.dimage.services.TestService;
import com.sdi.dimage.utils.UtilisateurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/test")
public class TestRestController {

    @Autowired
    private TestService service ;

    @GetMapping("/utilisateur")
    public List<UtilisateurDto> getuser(){
        return this.service.getall();


    }

    @GetMapping("/connexion")
    public UtilisateurDto getparemail( @RequestParam String email,String passe) {
        return this.service.testconnexion(email,passe);
    }
    @PostMapping("/utilisateur/enregistrer")
    public String enregistrer(@RequestBody UtilisateurDto util){
       return this.service.enregistrer(util);


    }

    @GetMapping("/utilisateur/supprimer/{id}")
    public String SupprimerUtilisateur(@PathVariable Integer id ){
      return   this.service.SupprimerUtilisateur(id);

    }
}




