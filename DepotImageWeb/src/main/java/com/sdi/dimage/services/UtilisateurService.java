package com.sdi.dimage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.UtilisateurEntity;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.utils.LoginModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;
import com.sdi.dimage.utils.UtilisateurModel;

import net.bytebuddy.asm.Advice.This;


@Service
public class UtilisateurService {

	@Autowired
    private UtlisateurRepository utRepository;
	
	public UtilisateurSessionDto chercherUtilisateur(LoginModel login) {
		
		Optional<AbstractUtilisateurEntity> op = utRepository.findOneByIdentifiant(login.getUsername());
		
		if (op.isEmpty()) {
			
			// TODO cas ou l'email n'existe pas.
			throw new NullPointerException();
			
		}
		
		AbstractUtilisateurEntity ut = op.get();
		
		if (!ut.getMotDePasse().equals(login.getPassword())) {
			// TODO cas mot de passe incorrecte.
			throw new NullPointerException();
		}
		
		UtilisateurSessionDto usd = new UtilisateurSessionDto();
		usd.setId(ut.getId());
		usd.setNom(ut.getNom());
		usd.setPrenoms(ut.getPrenoms());
		usd.setAdresseEmail(ut.getAdresseEmail());
		//TODO complte
		
		return usd;
	}
	 public List<AbstractUtilisateurEntity> getUser() {
	        return this.utRepository.findAll();
	    }

	//test_email
	
	public boolean test_par_email(String mail){
	    for(int i=0;i<this.utRepository.findAll().size();i++){
	        if(this.utRepository.findAll().get(i).getAdresseEmail().equals(mail)==true){
	            return true;
	        }
	    }return false ;
}
	
	
	//test_modification
	public boolean test_modification(UtilisateurEntity utl ){

	     for(int i=0;i<this.utRepository.findAll().size();i++){
	         if(this.utRepository.findAll().get(i).getAdresseEmail().equals(utl.getAdresseEmail())==true && (this.utRepository.findAll().get(i).getId())!=utl.getId()){
	             
	          return true;}
	     }return false ;
	}
	//modifier utilisateur
	/*public void modifier(UtilisateurModel utl) {
		for(int i=0; i<this.getUser().size();i++) {
		if(this.getUser().get(i).getId().equals(utl.getId())) {
			this.getUser().get(i).setIdentifiant(utl.getEmail());
			this.getUser().get(i).setAdresseEmail(utl.getEmail());
			this.getUser().get(i).setNom(utl.getNom());
			this.getUser().get(i).setPrenoms(utl.getPrenom());
			 this.utRepository.save(this.getUser().get(i));
		}
		
		}
	}*/
	public void modifier(UtilisateurModel utl) {
		for(int i=0; i<this.getUser().size();i++) {
		if(this.getUser().get(i).getId().equals(utl.getId())) {
			this.getUser().get(i).setIdentifiant(utl.getEmail());
			this.getUser().get(i).setAdresseEmail(utl.getEmail());
			this.getUser().get(i).setAdresse(utl.getAdresse());
			this.getUser().get(i).setPays(utl.getPays());
			this.getUser().get(i).setNom(utl.getNom());
			this.getUser().get(i).setPrenoms(utl.getPrenom());
			this.getUser().get(i).setVille(utl.getVille());
			this.getUser().get(i).setTelMobile(utl.getTel());
			this.getUser().get(i).setMotDePasse(utl.getPasse());
			 this.utRepository.save(this.getUser().get(i));
		}
		
		}
		
	}
	public void modifier2(UtilisateurModel utl) {
		for(int i=0; i<this.getUser().size();i++) {
		if(this.getUser().get(i).getId().equals(utl.getId())) {
			this.getUser().get(i).setIdentifiant(utl.getEmail());
			this.getUser().get(i).setAdresseEmail(utl.getEmail());
			this.getUser().get(i).setNom(utl.getNom());
			this.getUser().get(i).setRole(utl.getRole());
			this.getUser().get(i).setPrenoms(utl.getPrenom());
			 this.utRepository.save(this.getUser().get(i));
		}
		
		}
		
	}
	
	
}