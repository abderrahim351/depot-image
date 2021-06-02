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
		GestionnaireEntity utl = new GestionnaireEntity();
		utl.setIdentifiant("aymen@rien.do");
		utl.setAdresseEmail("aymen@rien.do");
		utl.setPrenoms("Aymen");
		utl.setNom("JAMAAOUI");
		return service.addGestionnaire(utl);

	}

	//afficher tous les utilisateurs
	@GetMapping("/liste_utilisateurs/{id}")
	public List<AbstractUtilisateurEntity> getuser(@PathVariable Integer id) {

		return this.ultservice.getUser(id);
	}

	//ajouter  utilisateur
	@PostMapping("/utilisateur")
	public void addUser(@RequestBody UtilisateurModel utl) {

		ultservice.addUser(utl);

	}

	//supprimer utilisateur
	@GetMapping("/utilisateur/{id}")
	public void SupprimerUtilisateur(@PathVariable Integer id) {
		this.ultservice.SupprimerUtilisateur(id);

	}

	//tester l'existance d'une email
	@GetMapping("/utilisateur/test/{email}")
	public boolean testParEemail(@PathVariable String email) {
		return this.ultservice.testParEemail(email);
	}

	//tester l'existance d'une email cas de modification
	@PostMapping("/utilisateur/test_modification")
	public boolean testModification(@RequestBody UtilisateurEntity utl) {
		return this.ultservice.testModification(utl);
	}

	//modifier utilisateur
	@PostMapping("/utilisateur/modifer")
	public void modifier(@RequestBody UtilisateurModel utl) {
		this.ultservice.modifier(utl);
	}

	//completer information
	@PostMapping("/utilisateur/modifer2")
	public void modifierInformations(@RequestBody UtilisateurModel utl) {
		this.ultservice.modifierInformations(utl);
	}
}
