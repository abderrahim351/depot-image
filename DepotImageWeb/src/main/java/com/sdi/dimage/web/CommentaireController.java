package com.sdi.dimage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdi.dimage.dao.entities.CommentaireEntity;
import com.sdi.dimage.services.CommentaireService;
import com.sdi.dimage.utils.CommentaireModel;


@RestController
@RequestMapping("/api")
public class CommentaireController {
	@Autowired
	private CommentaireService service;
	//ajouterCommentaire
	@PostMapping("/commentaire")
	public void ajouterCommentaire(@RequestBody CommentaireModel com) {
		 this.service.ajouterCommentaire(com);
	}
	//modifier commentaire
	@PostMapping("/commentaire/modifier")
	public void modifierCommentaire(@RequestBody CommentaireModel com) {
		 this.service.modifierCommentaire(com);
	}
	@GetMapping("/commentaire/{idDoc}")
	public List<CommentaireModel> listeCommentaires(@PathVariable Integer idDoc) {
		return this.service.listeCommentairesDoc(idDoc);
	}
@GetMapping("/commentaire/delete/{id}")
public void SupprimerCommentaire(@PathVariable Integer id) {
	 this.service.supprimerCommentaire(id);
}
//liste de commentaire d'une image
@GetMapping("/commentaire/image/{idImg}")
public List<CommentaireModel> listeCommentairesImage(@PathVariable Integer idImg) {
	return this.service.listeCommentairesImg(idImg);
}
//ajouter commentaire pour une image 
@PostMapping("/commentaire/image")
public void ajouterCommentaireImage(@RequestBody CommentaireModel com) {
	 this.service.ajouterCommentaireImg(com);
}

}
