package com.sdi.dimage.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdi.dimage.dao.entities.CommentaireEntity;
import com.sdi.dimage.dao.repositories.CommentaireRepositery;
import com.sdi.dimage.dao.repositories.DocumentRepositery;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.utils.CommentaireModel;
@Service
public class CommentaireService {
	
	@Autowired
	private UtlisateurRepository utRepository;
	@Autowired
	private CommentaireRepositery commentaireRepo;
	@Autowired
	private DocumentRepositery docRepo;
	
	public void ajouterCommentaire(CommentaireModel com) {
		LocalDateTime now = LocalDateTime.now();
		CommentaireEntity commentaire=new CommentaireEntity();
		
		commentaire.setContenu(com.getContenu());
		commentaire.setEcritLe(now);
		commentaire.setDocument(this.docRepo.getOne(com.getDocId()));
		commentaire.setEcritPar(this.utRepository.getOne(com.getCreerpar()));
		commentaire.setType(com.getType());
		this.commentaireRepo.save(commentaire);
	}	
	

	public List<CommentaireModel> listeCommentaires(Integer idDoc){
		List<CommentaireEntity> l=new ArrayList<>();
		List<CommentaireModel> l1=new ArrayList<>();
		l=this.commentaireRepo.listCommentaires(idDoc);
		for (int i = 0; i < l.size(); i++) {
			CommentaireEntity a=l.get(i);
			 CommentaireModel aux=new CommentaireModel();
			aux.setId(a.getId());
			aux.setCreerpar(a.getEcritPar().getId());
			aux.setDocId(a.getDocument().getId());
			aux.setContenu(a.getContenu());
			aux.setType(a.getType());
			aux.setCreerle(a.getEcritLe());
			aux.setNom(a.getEcritPar().getNom());
			aux.setPrenom(a.getEcritPar().getPrenoms());
			l1.add(aux);
		}
		return l1;
	}
	public void modifier(CommentaireModel commentaire) {
	CommentaireEntity com = this.commentaireRepo.getOne(commentaire.getId());
	com.setContenu(commentaire.getContenu());
	this.commentaireRepo.save(com);
	}
	public void supprimerCommentaire(int id) {
		this.commentaireRepo.deleteById(id);
	}
	}
