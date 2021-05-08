package com.sdi.dimage.utils;

public class DocImgModel {
	private String nom;
    private String prenom;
    private String titre;
    private String description ;
    private Integer IdDoc;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIdDoc() {
		return IdDoc;
	}
	public void setIdDoc(Integer idDoc) {
		IdDoc = idDoc;
	}

}
