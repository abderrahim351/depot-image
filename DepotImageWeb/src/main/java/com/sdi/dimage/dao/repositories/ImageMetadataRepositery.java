package com.sdi.dimage.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sdi.dimage.dao.entities.ImageMetadataEntity;


@Repository
public interface ImageMetadataRepositery  extends JpaRepository<ImageMetadataEntity,Integer>{
	
	@Query(" from ImageMetadataEntity as img where img.image.id = :idimg order by img.id ")
	List<ImageMetadataEntity> listMetadata(Integer idimg);
}
