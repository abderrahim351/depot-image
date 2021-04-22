package com.sdi.dimage.web;
import com.drew.imaging.ImageProcessingException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

import com.sdi.dimage.dao.entities.*;
import com.sdi.dimage.dao.repositories.ImageRepository;
import com.sdi.dimage.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sdi.dimage.utils.LoginModel;
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
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/utilisateur")
	public List<AbstractUtilisateurEntity> getuser() {


		return this.service.getUser();
	}
	@GetMapping("/utilisateur1")
	public AbstractUtilisateurEntity getutl() {


		return this.service.getUser().get(0);
	}





	//ajouter utilisateur
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/utilisateur")
	public String AddUser(@RequestBody UtilisateurEntity utl) {

		return service.adduser(utl);

	}
	//ajouter gestionnaire
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/gestionnaire")
	public String AddGestionnaire(@RequestBody GestionnaireEntity utl) {

		return service.addGestionnaire(utl);

	}
	//ajouter administrateur
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/admin")
	public String AddAdministrateur(@RequestBody AdministrateurEntity utl) {

		return service.addAdmin(utl);

	}
	//supprimer utilisateur
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/utilisateur/{id}")
	public void   SupprimerUtilisateur(@PathVariable Integer id) {
		  this.service.SupprimerUtilisateur(id);

	}

	
}
