package com.sdi.dimage.dao.entities;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "commentaire")
public class CommentaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;
    private String contenu;
    private LocalDateTime ecritLe;
    @ManyToOne
    @JsonIgnore
    private DocumentEntity document;
    @ManyToOne
    private AbstractUtilisateurEntity ecritPar;
    @ManyToOne
    @JsonIgnore
    private ImageEntity image;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public LocalDateTime getEcritLe() {
		return ecritLe;
	}
	public void setEcritLe(LocalDateTime ecritLe) {
		this.ecritLe = ecritLe;
	}
	public DocumentEntity getDocument() {
		return document;
	}
	public void setDocument(DocumentEntity document) {
		this.document = document;
	}
	public AbstractUtilisateurEntity getEcritPar() {
		return ecritPar;
	}
	public void setEcritPar(AbstractUtilisateurEntity ecritPar) {
		this.ecritPar = ecritPar;
	}
	public ImageEntity getImage() {
		return image;
	}
	public void setImage(ImageEntity image) {
		this.image = image;
	}


}
