package com.sdi.dimage.dao.entities;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
@Inheritance
@DiscriminatorColumn
public abstract class AbstractUtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @Column(name = "identifiant")
    private String identifiant;
    private String nom;
    private String prenoms;
    private String civilite;
    @Column(name = "date_naissance")
    private String dateNaissance;
    private String adresseEmail;
    private String telMobile;
    private String telFix;
    private String adresse;
@Column(name = "mot_de_passe")
    private String motDePasse;
	private String codePostale;

	public List<DocumentEntity> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentEntity> documents) {
		this.documents = documents;
	}

	private String ville;
	private String pays;
	@OneToMany(mappedBy = "creePar",cascade = CascadeType.REMOVE)
    private List<DocumentEntity> documents = new ArrayList<>();
	
    
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    public String getTelMobile() {
        return telMobile;
    }

    public void setTelMobile(String telMobile) {
        this.telMobile = telMobile;
    }

    public String getTelFix() {
        return telFix;
    }

    public void setTelFix(String telFix) {
        this.telFix = telFix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
