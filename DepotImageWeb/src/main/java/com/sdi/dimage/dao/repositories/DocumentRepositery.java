package com.sdi.dimage.dao.repositories;

import com.sdi.dimage.dao.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepositery extends JpaRepository<DocumentEntity,Integer> {
}
