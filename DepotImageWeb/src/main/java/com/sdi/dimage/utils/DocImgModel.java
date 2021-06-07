package com.sdi.dimage.utils;

import java.time.LocalDateTime;

public class DocImgModel {
	
	
	private Integer idDoc;
	
	private String nom;
	private String prenom;
	private String titre;
	private String sousTitre;
	private LocalDateTime creeLe;
	private String description;
	private String type;
	private Integer creeParId;
	
	public String getSousTitre() {
		return sousTitre;
	}
	public void setSousTitre(String sousTitre) {
		this.sousTitre = sousTitre;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getIdDoc() {
		return idDoc;
	}
	public void setIdDoc(Integer idDoc) {
		this.idDoc = idDoc;
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
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getSoustitre() {
		return sousTitre;
	}
	public void setSoustitre(String soustitre) {
		this.sousTitre = soustitre;
	}
	public LocalDateTime getCreeLe() {
		return creeLe;
	}
	public void setCreeLe(LocalDateTime creeLe) {
		this.creeLe = creeLe;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCreeParId() {
		return creeParId;
	}
	public void setCreeParId(Integer creeParId) {
		this.creeParId = creeParId;
	}

	

}
