package com.sdi.dimage.dao.entities;


import javax.persistence.*;
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
    private DocumentEntity document;
    @ManyToOne
    private AbstractUtilisateurEntity ecritPar;
    @ManyToOne
    private ImageEntity image;


}
