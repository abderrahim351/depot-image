package com.sdi.dimage.dao.repositories;

import com.sdi.dimage.dao.entities.ImageEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
	
	@Query("select img.id from ImageEntity as img where img.document.id = :idDoc ")
	List<Integer> listImagesParDoc(Integer idDoc);

}
