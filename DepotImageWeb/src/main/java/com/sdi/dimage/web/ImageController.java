package com.sdi.dimage.web;

import com.drew.imaging.ImageProcessingException;
import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.DocumentEntity;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.services.ImageService;
import com.sdi.dimage.utils.DocImgDetailsModel;
import com.sdi.dimage.utils.DocImgModel;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;

import net.bytebuddy.asm.Advice.This;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/api")
public class ImageController extends AbstractController{
	@Autowired
	private ImageService service;

	//enregistrer document )
	@PostMapping("document")
	public Integer ajouterDocument(@RequestBody DocumentModel img,
			HttpServletRequest request) {
		UtilisateurSessionDto user = getUserSession(request);
		return this.service.enregistrerdoc(img, user);
	}

   
    
  

	//enregistrer image
	@PostMapping("/document/{idDoc}/upload")
	public void fileUpload(@PathVariable Integer idDoc,  @RequestParam("files") List<MultipartFile> files, HttpServletRequest request)
			throws IOException, ImageProcessingException {
	
		if (files.size()>0) {
			UtilisateurSessionDto user = getUserSession(request);
			
			this.service.uplodfile(idDoc, files, user);
		}
		
	}

	@GetMapping("/img")
	public List<ImageEntity> getimg() {
		return this.service.getphoto();
	}
	@GetMapping("/doc/{id}")
	public void supdoc(@PathVariable int id) {
		 this.service.supprimerdoc(id);
	}
	//return image principale
	@GetMapping("/document/img/{idDoc}")
	@ResponseBody
	public ResponseEntity<byte[]> imagePrincipale(@PathVariable Integer idDoc, HttpServletRequest request) {
		
		ImageEntity img = service.getImagePrincipale(idDoc);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(img.getTypeFichier()));

		return new ResponseEntity<>(img.getContenu(), headers, HttpStatus.OK);
	}
	
	
	//return image 
	@GetMapping("/img/{idImg}")
	@ResponseBody
	public ResponseEntity<byte[]> image(@PathVariable Integer idImg, HttpServletRequest request) {
		
		ImageEntity img = service.getImage(idImg);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(img.getTypeFichier()));

		return new ResponseEntity<>(img.getContenu(), headers, HttpStatus.OK);
	}
	
	
	@GetMapping("/doc")
	public List<DocImgModel> getdoc() {
		ArrayList<DocImgModel> l =new ArrayList<DocImgModel>(); 
		
		for(int i=0 ;i<this.service.getdoc().size();i++) {
			DocImgModel aux =new DocImgModel();
			aux.setNom(this.service.getdoc().get(i).getCreePar().getNom());
			aux.setPrenom(this.service.getdoc().get(i).getCreePar().getPrenoms());
			aux.setTitre(this.service.getdoc().get(i).getSousTitre());
			aux.setDescription(this.service.getdoc().get(i).getDescription());
			aux.setIdDoc(this.service.getdoc().get(i).getId());
			l.add(aux);
		}
		return l;
		
		
	}
	//detaill publication
	@GetMapping("/document/details/{id}")
	public 	DocImgDetailsModel detailePub(@PathVariable int id) {
		return this.service.detailePub(id);
	}
	

}
