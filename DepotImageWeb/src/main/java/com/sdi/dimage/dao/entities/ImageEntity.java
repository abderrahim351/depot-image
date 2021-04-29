package com.sdi.dimage.dao.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String name ;
    
	@Column(name = "contenu_fichier")
	private byte[] contenu;
	
    private String typeFichier;
    private Long tailleFichier;
    
    private LocalDateTime creeLe;
    private String type;
    private String resolution;
    private String largeur;
    private String hauteur;
    private String appareil;
    private String lieu;
    private String description;
    private String metadata;
    
    @ManyToOne
    private AbstractUtilisateurEntity creePar;
    
    // id of document
    @JoinColumn(name = "fk_id_document", referencedColumnName = "id")
    @ManyToOne
    private DocumentEntity document;
    
    @OneToMany
    private Set<CommentaireEntity> commentaires;
    
    @OneToOne
    private ImageEntity aPartirDe ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getContenu() {
		return contenu;
	}

	public void setContenu(byte[] contenu) {
		this.contenu = contenu;
	}

	public String getTypeFichier() {
        return typeFichier;
    }

    public void setTypeFichier(String typeFichier) {
        this.typeFichier = typeFichier;
    }

    public Long getTailleFichier() {
        return tailleFichier;
    }

    public void setTailleFichier(Long tailleFichier) {
        this.tailleFichier = tailleFichier;
    }

    public LocalDateTime getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(LocalDateTime creeLe) {
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
