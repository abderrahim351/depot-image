package com.sdi.dimage.utils;

import java.time.LocalDateTime;

public class CommentaireModel {
	private Integer id;
	private Integer creerpar;
	private Integer docId;
	private Integer imgId;
	private String contenu;
	private String type;
	private LocalDateTime creerle;
	private String nom;
	private String prenom;
	
	
	public Integer getId() {
		return id;
	}
	public LocalDateTime getCreerle() {
		return creerle;
	}
	public void setCreerle(LocalDateTime creerle) {
		this.creerle = creerle;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public Integer getCreerpar() {
		return creerpar;
	}
	public void setCreerpar(Integer creerpar) {
		this.creerpar = creerpar;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	
	
}
