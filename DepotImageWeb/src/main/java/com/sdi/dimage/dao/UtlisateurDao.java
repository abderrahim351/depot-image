package com.sdi.dimage.dao;

import com.sdi.dimage.utils.UtilisateurDto;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UtlisateurDao extends JpaRepository<UtilisateurDto,Integer> {


}
