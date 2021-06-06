package com.sdi.dimage.dao.entities;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "image_metadata")
public class ImageMetadataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String directory;
    private String tag ;
    private String valeur ;
    @JsonIgnore
    @OneToOne
    private ImageEntity image ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public ImageEntity getImage() {
		return image;
	}
	public void setImage(ImageEntity image) {
		this.image = image;
	}
    


}
