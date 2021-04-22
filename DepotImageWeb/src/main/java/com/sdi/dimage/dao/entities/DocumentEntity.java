package com.sdi.dimage.dao.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Table(name = "document")

public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDateTime creeLe;
    private String titre;
    private String sousTitre;
    private String description;
    private Boolean estPublique;
    private String statut;
    @OneToMany
    private Set<ImageEntity> images;
    @OneToOne
    private ImageEntity imagePrincipal;
    @OneToMany
    private Set<CommentaireEntity> commentaires;
    @ManyToOne
    private AbstractUtilisateurEntity creePar;

}
