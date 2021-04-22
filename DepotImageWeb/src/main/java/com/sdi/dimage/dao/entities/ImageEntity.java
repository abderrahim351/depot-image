package com.sdi.dimage.dao.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name ;
    private String cheminFichier;
    private String typeFichier;
    private Integer tailleFichier;
    private String creeLe;
    private String type;
    private String resolution;
    private String largeur;
    private String hauteur;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String appareil;
    private String lieu;
    private String description;
    private String metadata;
    @ManyToOne
    private AbstractUtilisateurEntity creePar;
    @ManyToOne
    private DocumentEntity document;
    @OneToMany
    private Set<CommentaireEntity> commentaires;
    @OneToOne
    private ImageEntity aPartirDe ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getTypeFichier() {
        return typeFichier;
    }

    public void setTypeFichier(String typeFichier) {
        this.typeFichier = typeFichier;
    }

    public Integer getTailleFichier() {
        return tailleFichier;
    }

    public void setTailleFichier(Integer tailleFichier) {
        this.tailleFichier = tailleFichier;
    }

    public String getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(String creeLe) {
        this.creeLe = creeLe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getLargeur() {
        return largeur;
    }

    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }

    public String getHauteur() {
        return hauteur;
    }

    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    public String getAppareil() {
        return appareil;
    }

    public void setAppareil(String appareil) {
        this.appareil = appareil;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public AbstractUtilisateurEntity getCreePar() {
        return creePar;
    }

    public void setCreePar(AbstractUtilisateurEntity creePar) {
        this.creePar = creePar;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public Set<CommentaireEntity> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<CommentaireEntity> commentaires) {
        this.commentaires = commentaires;
    }

    public ImageEntity getaPartirDe() {
        return aPartirDe;
    }

    public void setaPartirDe(ImageEntity aPartirDe) {
        this.aPartirDe = aPartirDe;
    }
}
