package com.sdi.dimage.dao.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("UTILISATEUR")
public class UtilisateurEntity extends AbstractUtilisateurEntity {

}
