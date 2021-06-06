package com.sdi.dimage.dao.repositories;

import com.sdi.dimage.dao.entities.CommentaireEntity;
import com.sdi.dimage.utils.CommentaireModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepositery extends JpaRepository<CommentaireEntity,Integer> {
	@Query(" from CommentaireEntity as com where com.document.id = :idDoc")
	List<CommentaireEntity> listCommentairesDoc(Integer idDoc);
	@Query(" from CommentaireEntity as com where com.image.id = :idImg")
	List<CommentaireEntity> listCommentairesImg(Integer idImg);
	

}
