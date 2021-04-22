package com.sdi.dimage.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.utils.LoginModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;

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
		//TODO complte
		
		return usd;
	}

	
	
}
