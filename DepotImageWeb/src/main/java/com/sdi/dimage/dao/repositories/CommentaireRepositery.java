package com.sdi.dimage.dao.repositories;

import com.sdi.dimage.dao.entities.CommentaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepositery extends JpaRepository<CommentaireEntity,Integer> {

}
