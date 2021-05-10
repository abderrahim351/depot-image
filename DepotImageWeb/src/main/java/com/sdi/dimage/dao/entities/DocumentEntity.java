package com.sdi.dimage.dao.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "document")

public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    private LocalDateTime creeLe;
    private String titre;
    @Column(name ="sous_titre" )
    private String sousTitre;
    private String description;
    private Boolean estPublique;
    private String statut;
    
    @JoinColumn(name = "id_image_principal", referencedColumnName = "id" )
    @OneToOne
    private ImageEntity imagePrincipal;
    
    @OneToMany
    private Set<CommentaireEntity> commentaires;
    @JoinColumn(name = "cree_par_id", referencedColumnName = "id")
    @ManyToOne()
    private AbstractUtilisateurEntity creePar;
    
    @OneToMany(mappedBy = "document",cascade = CascadeType.REMOVE)
    private List<ImageEntity> images = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(LocalDateTime creeLe) {
        this.creeLe = creeLe;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEstPublique() {
        return estPublique;
    }

    public void setEstPublique(Boolean estPublique) {
        this.estPublique = estPublique;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public ImageEntity getImagePrincipal() {
        return imagePrincipal;
    }

    public void setImagePrincipal(ImageEntity imagePrincipal) {
        this.imagePrincipal = imagePrincipal;
    }

    public Set<CommentaireEntity> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<CommentaireEntity> commentaires) {
        this.commentaires = commentaires;
    }

    public AbstractUtilisateurEntity getCreePar() {
        return creePar;
    }

    public void setCreePar(AbstractUtilisateurEntity creePar) {
        this.creePar = creePar;
    }

    public List<ImageEntity> getImages() {
		return images;
	}
    
    public void setImages(List<ImageEntity> images) {
		this.images = images;
	}

}
