package com.sdi.dimage.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;

@Repository
public interface UtlisateurRepository extends JpaRepository<AbstractUtilisateurEntity,Integer> {


	Optional<AbstractUtilisateurEntity> findOneByIdentifiant(String identifiant);

}
