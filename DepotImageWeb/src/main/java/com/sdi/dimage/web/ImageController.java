package com.sdi.dimage.web;

import com.drew.imaging.ImageProcessingException;
import com.sdi.dimage.dao.entities.DocumentEntity;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.services.ImageService;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;
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

	@GetMapping("/document/img/{idDoc}")
	@ResponseBody
	public ResponseEntity<byte[]> imagePricipale(@PathVariable Integer idDoc, HttpServletRequest request) {
		
		ImageEntity img = service.getImagePrincipale(idDoc);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(img.getTypeFichier()));

		return new ResponseEntity<>(img.getContenu(), headers, HttpStatus.OK);
	}

}
