package com.sdi.dimage.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageProcessingException;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.services.ImageService;
import com.sdi.dimage.utils.DocImgDetailsModel;
import com.sdi.dimage.utils.DocImgModel;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.MetaTreeNodeModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;

@RestController
@RequestMapping("/api")
public class ImageController extends AbstractController{
	@Autowired
	private ImageService service;
	
	
	@GetMapping("/documents")
	public List<DocImgModel> documents() {
		
		return this.service.documents();
				
	}
	
	//metadata image publication
	@GetMapping("/img/metas/{id}")
	public 	List<MetaTreeNodeModel> metadatas(@PathVariable int id) {
		return this.service.metadatas(id);
	}
	

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
	
	

	//detaill publication
	@GetMapping("/document/details/{id}")
	public 	DocImgDetailsModel detailePub(@PathVariable int id) {
		return this.service.detailePub(id);
	}

	

}
