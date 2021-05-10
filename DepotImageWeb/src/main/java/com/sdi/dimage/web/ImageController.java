package com.sdi.dimage.web;

import com.drew.imaging.ImageProcessingException;
import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.DocumentEntity;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.services.ImageService;
import com.sdi.dimage.utils.DocImgModel;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;

import net.bytebuddy.asm.Advice.This;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
	public void fileUpload(@PathVariable Integer idDoc,  @RequestParam("image-file") MultipartFile imageFile, HttpServletRequest request)
			throws IOException, ImageProcessingException {
	
		UtilisateurSessionDto user = getUserSession(request);
		
		this.service.uplodfile(idDoc, imageFile, user);
	}

	@GetMapping("/img")
	public List<ImageEntity> getimg() {
		return this.service.getphoto();
	}
	@GetMapping("/doc/{id}")
	public String supdoc(@PathVariable int id) {
		return this.service.supprimerdoc(id);
	}
	//return image
	@GetMapping("/document/img/{idDoc}")
	@ResponseBody
	public ResponseEntity<byte[]> imagePricipale(@PathVariable Integer idDoc, HttpServletRequest request) {
		
		ImageEntity img = service.getImagePrincipale(idDoc);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(img.getTypeFichier()));

		return new ResponseEntity<>(img.getContenu(), headers, HttpStatus.OK);
	}
	@GetMapping("/doc")
	public ArrayList<DocImgModel> getdoc() {
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

}
