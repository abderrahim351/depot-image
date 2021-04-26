package com.sdi.dimage.utils;

public class UtilisateurSessionDto {

    private Integer id;
    private String identifiant;
    private String nom;
    private String prenoms;
    private String civilite;
    private String adresseEmail;
    
    private String groupe;

	@Override
	public String toString() {
		return "UtilisateurSessionDto{" +
				"id=" + id +
				", identifiant='" + identifiant + '\'' +
				", nom='" + nom + '\'' +
				", prenoms='" + prenoms + '\'' +
				", civilite='" + civilite + '\'' +
				", adresseEmail='" + adresseEmail + '\'' +
				", groupe='" + groupe + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getAdresseEmail() {
		return adresseEmail;
	}

	public void setAdresseEmail(String adresseEmail) {
		this.adresseEmail = adresseEmail;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
    
    

	
}
