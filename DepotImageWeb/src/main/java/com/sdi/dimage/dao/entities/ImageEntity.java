package com.sdi.dimage.dao.entities;


import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    @OneToMany(mappedBy = "image",cascade = CascadeType.REMOVE)
    private Set<ImageMetadataEntity> metadata;
    @ManyToOne
    private AbstractUtilisateurEntity creePar;
    
    public Set<ImageMetadataEntity> getMetadata() {
		return metadata;
	}

	public void setMetadata(Set<ImageMetadataEntity> metadata) {
		this.metadata = metadata;
	}

	// id of document
    @JoinColumn(name = "fk_id_document", referencedColumnName = "id")
    @ManyToOne
    private DocumentEntity document;
    
    @OneToMany
    private Set<CommentaireEntity> commentaires;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

    

}
