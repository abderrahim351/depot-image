package com.sdi.dimage.web;
import com.drew.imaging.ImageProcessingException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

import com.sdi.dimage.dao.entities.*;
import com.sdi.dimage.dao.repositories.ImageRepository;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.services.TestService;
import com.sdi.dimage.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sdi.dimage.utils.LoginModel;
import com.sdi.dimage.utils.UtilisateurModel;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UtilisateurController {
	@Autowired
	private TestService service;
	@Autowired
	private UtilisateurService ultservice;
	@Autowired
	private ImageRepository imageRepo;
	

	
	@GetMapping("au1")
	public String adduser1() {
		UtilisateurEntity utl = new UtilisateurEntity();
		utl.setIdentifiant("aymen@rien.do"); 
		utl.setAdresseEmail("aymen@rien.do");
		utl.setPrenoms("Aymen");
		utl.setNom("JAMAAOUI");
		return service.adduser(utl);

	}
	

	//afficher tous les utilisateurs
	@GetMapping("/utilisateur")
	public List<AbstractUtilisateurEntity> getuser() {


		return this.service.getUser();
	}
	
	//ajouter  utilisateur
		@PostMapping("/utilisateur")
		public void AddUser(@RequestBody UtilisateurModel utl) {

			 service.role(utl);

		}

	//supprimer utilisateur
	@GetMapping("/utilisateur/{id}")
	public void   SupprimerUtilisateur(@PathVariable Integer id) {
		  this.service.SupprimerUtilisateur(id);

	}
	
	//tester l'existance d'une email
	@GetMapping("/utilisateur/test/{email}")
	public boolean test_par_mail(@PathVariable String email) {
	      return   this.ultservice.test_par_email(email);
	    }

	//tester l'existance d'une email cas de modification
	@PostMapping("/utilisateur/test_modification")
	public boolean test_modification(@RequestBody UtilisateurEntity utl) {
	      return   this.ultservice.test_modification(utl);
	    }
	//modifier utilisateur
		@PostMapping("/utilisateur/modifer")
		public void modifier(@RequestBody UtilisateurModel utl) {
		          this.ultservice.modifier2(utl);
		    }
		//completer information
				@PostMapping("/utilisateur/modifer2")
				public void modofier(@RequestBody UtilisateurModel utl) {
				          this.ultservice.modifier(utl);
				    }		
}
