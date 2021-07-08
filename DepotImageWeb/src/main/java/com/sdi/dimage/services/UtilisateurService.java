package com.sdi.dimage.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.AdministrateurEntity;
import com.sdi.dimage.dao.entities.GestionnaireEntity;
import com.sdi.dimage.dao.entities.UtilisateurEntity;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.utils.InscriptionModel;
import com.sdi.dimage.utils.LoginModel;
import com.sdi.dimage.utils.UtilisateurModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;

@Service
public class UtilisateurService {

	@Autowired
	private UtlisateurRepository utRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@PostConstruct
	private void postConstruct() {

		if (utRepository.count() == 0) {
			AdministrateurEntity adm = new AdministrateurEntity();
			adm.setIdentifiant("admin@dimage.tn");
			adm.setAdresseEmail("admin@dimage.tn");
			adm.setPrenoms("Abderrahim");
			adm.setNom("JAMAAOUI");
			adm.setMotDePasse("Azerty1?");
			adm.setActif(true);
			utRepository.save(adm);
		}

	}

	public void addUser(UtilisateurModel utl) {

		AbstractUtilisateurEntity user = null;

		if (utl.getRole().equals("gestionnaire")) {

			user = new GestionnaireEntity();
		}
		else if (utl.getRole().equals("utilisateur")) {
			user = new UtilisateurEntity();

		}
		else {
			user = new AdministrateurEntity();
		}

		user.setAdresseEmail(utl.getEmail());
		user.setIdentifiant(utl.getEmail());
		user.setNom(utl.getNom());
		user.setPrenoms(utl.getPrenom());
		user.setActif(false);
		String password = RandomStringUtils.randomAlphanumeric(8);

		user.setMotDePasse(password);
		utRepository.save(user);

		this.sendmail(user.getAdresseEmail(), user.getNom(), user.getPrenoms(),
				utl.getRole(), password);
	}

	public void sendmail(String to, String nom, String prenom, String type,
			String a) {
		System.out.println("envoie d'email");

		SimpleMailMessage msj = new SimpleMailMessage();
		msj.setFrom("depotimage2@gmail.com");
		msj.setTo(to);
		msj.setSubject("Inscription");

		msj.setText("Bonjour " + nom + " " + prenom
				+ " dans notre equipe depot image \n vous etes un " + type
				+ "\n votre mot de passe est " + a);
		javaMailSender.send(msj);
		System.out.println("email envoyer");
	}

	public UtilisateurSessionDto chercherUtilisateur(LoginModel login) {

		Optional<AbstractUtilisateurEntity> op = utRepository
				.findOneByIdentifiant(login.getUsername());

		if (op.isEmpty()) {

			// TODO cas ou l'email n'existe pas.

			UtilisateurSessionDto utl = new UtilisateurSessionDto();
			utl.setIdentifiant("faux_email");
			return utl;

		}

		AbstractUtilisateurEntity ut = op.get();

		if (!ut.getMotDePasse().equals(login.getPassword())) {
			// TODO cas mot de passe incorrecte.
			UtilisateurSessionDto utl = new UtilisateurSessionDto();
			utl.setIdentifiant("faux_passe");
			return utl;
		}
		
		if (!ut.getActif()) {
			// TODO cas mot de passe incorrecte.
			UtilisateurSessionDto utl = new UtilisateurSessionDto();
			utl.setIdentifiant("inactif");
			return utl;
		}

		UtilisateurSessionDto usd = new UtilisateurSessionDto();
		usd.setId(ut.getId());
		usd.setNom(ut.getNom());
		usd.setPrenoms(ut.getPrenoms());
		usd.setAdresseEmail(ut.getAdresseEmail());
		usd.setIdentifiant(login.getUsername());

		if (ut instanceof AdministrateurEntity) {
			usd.setGroupe("administrateur");
		}
		else if (ut instanceof GestionnaireEntity) {
			usd.setGroupe("gestionnaire");
		}
		else {
			usd.setGroupe("utilisateur");
		}

		return usd;
	}

	public List<AbstractUtilisateurEntity> getUser(Integer id) {
		return this.utRepository.listUser(id);
	}

	public List<AbstractUtilisateurEntity> liste_inscriptions() {
		return this.utRepository.liste_inscriptions();
	}

	//supprimer_utilisateur_par_son_id
	public void SupprimerUtilisateur(Integer id) {
		this.utRepository.deleteById(id);
	}

	//supprimer_utilisateur_par_son_id
	public void activer(Integer id){
  	  AbstractUtilisateurEntity user = utRepository.getOne(id);
  	  user.setActif(true);
  	  this.utRepository.save(user);
  	  
  	  String mail = String.format("Bonjour %s %s  "
  	  		+ "\n\nBienvenue sur notre plateforme de dépôt d’image. "
  	  		+ "Votre inscription est validé par les administrateurs. "
  	  		+ "\n\n Cordialement", user.getNom(), user.getPrenoms());
  	  
  	  
      this.sendmailActivation( user.getAdresseEmail(), mail);

      
  	  
    }

	public void sendmailActivation(String to, String mail) {
		System.out.println("envoie d'email");

		SimpleMailMessage msj = new SimpleMailMessage();
		msj.setFrom("depotimage2@gmail.com");
		msj.setTo(to);
		msj.setSubject("Inscription");

		msj.setText(mail);
		javaMailSender.send(msj);
		System.out.println("email envoyer");
	}

	//test_email

	public boolean testParEemail(String mail) {
		for (int i = 0; i < this.utRepository.findAll().size(); i++) {
			if (this.utRepository.findAll().get(i).getAdresseEmail()
					.equals(mail) == true) {
				return true;
			}
		}
		return false;
	}

	//test_modification
	public boolean testModification(UtilisateurEntity utl) {

		for (int i = 0; i < this.utRepository.findAll().size(); i++) {
			if (this.utRepository.findAll().get(i).getAdresseEmail()
					.equals(utl.getAdresseEmail()) == true
					&& (this.utRepository.findAll().get(i).getId()) != utl
							.getId()) {

				return true;
			}
		}
		return false;
	}

	public void modifierInformations(UtilisateurModel utl) {
		AbstractUtilisateurEntity user = utRepository.getOne(utl.getId());
		user.setIdentifiant(utl.getEmail());
		user.setAdresseEmail(utl.getEmail());
		user.setAdresse(utl.getAdresse());
		user.setPays(utl.getPays());
		user.setNom(utl.getNom());
		user.setPrenoms(utl.getPrenom());
		user.setVille(utl.getVille());
		user.setTelMobile(utl.getTel());
		user.setMotDePasse(utl.getPasse());
		this.utRepository.save(user);

	}

	public void modifier(UtilisateurModel utl) {

		AbstractUtilisateurEntity user = utRepository.getOne(utl.getId());
		user.setIdentifiant(utl.getEmail());
		user.setAdresseEmail(utl.getEmail());
		user.setNom(utl.getNom());
		user.setPrenoms(utl.getPrenom());
		this.utRepository.save(user);

	}

	public void inscrire(InscriptionModel utl) {
		AbstractUtilisateurEntity user = new UtilisateurEntity();
		user.setIdentifiant(utl.getAdresseEmail());
		user.setAdresseEmail(utl.getAdresseEmail());
		user.setNom(utl.getNom());
		user.setPrenoms(utl.getPrenom());
		user.setMotDePasse(utl.getMotDePasse());
		user.setActif(false);
		this.utRepository.save(user);

	}

}