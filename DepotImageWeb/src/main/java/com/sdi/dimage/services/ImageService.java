package com.sdi.dimage.services;


import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.DocumentEntity;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.dao.entities.ImageMetadataEntity;
import com.sdi.dimage.dao.entities.UtilisateurEntity;
import com.sdi.dimage.dao.repositories.DocumentRepositery;
import com.sdi.dimage.dao.repositories.ImageMetadataRepositery;
import com.sdi.dimage.dao.repositories.ImageRepository;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.utils.DocImgDetailsModel;
import com.sdi.dimage.utils.DocImgModel;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.transaction.Transactional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private DocumentRepositery documentRepositery;
    @Autowired
    private UtlisateurRepository utlisateurRepository;
    @Autowired
    private ImageMetadataRepositery metarepository;
    
    
    private ImageEntity img =new ImageEntity();
    private int S;
    private String ch;
    private TestService serv=new TestService();
    private UtilisateurEntity utl =new UtilisateurEntity();
    
    private int taille ;
    
    /*public void uplodfile(MultipartFile file ) throws IOException, ImageProcessingException {
        File f =new File("C:\\Users\\abder\\OneDrive\\Desktop\\depot-image\\DepotImageWeb\\src\\main\\frontend\\src\\assets\\"+file.getOriginalFilename());
        f.createNewFile();
        System.out.println(file.toString());
        file.transferTo(f);
        Metadata metadata = ImageMetadataReader.readMetadata(new File("C:\\Users\\abder\\OneDrive\\Desktop\\depot-image\\DepotImageWeb\\src\\main\\frontend\\src\\assets\\" + file.getOriginalFilename()));
        this.utl.setNom("jamaaoui");
        this.utl.setAdresseEmail("abderrahim@gmail");
        //this.img.setCheminFichier("assets/" + file.getOriginalFilename());
        this.img.setCreePar(null);
        this.img.setDescription("azerty");
        this.img.setaPartirDe(null);
        
        this.img.setCommentaires(null);
        
        this.img.setDocument(this.documentRepositery.findById(2).get());
        this.img.setLieu("kairouan");
        this.img.setMetadata("metadata");
        this.img.setResolution("500");
        this.img.setTypeFichier("image");


        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                switch (tag.getTagName()){

                       case "Image Width":

                    this.img.setLargeur(tag.getDescription());
                        break;

                    case "Image Height":
                       this.img.setHauteur(tag.getDescription());
                    break;

                    case "File Name":
                        this.img.setName(tag.getDescription());
                    break;

                    case "File Size": {
                       this.S=tag.getDescription().indexOf(" ");
                        this.ch=tag.getDescription().substring(0,this.S);
                        this.taille=Integer.parseInt(this.ch);
                        System.out.println(this.taille);
                        //this.img.setTailleFichier(this.taille);
                        break;
                    }
                    case "File Modified Date":
                        //this.img.setCreeLe(tag.getDescription());
                        break;
                    case "Detected File Type Long Name":
                        this.img.setAppareil(tag.getDescription());
                        break;
                    case "Detected File Type Name":
                        this.img.setType(tag.getDescription());
                        break;


                }

                }

            }


imageRepo.save(this.img);
    }*/
    //liste des metadata
    public List<ImageMetadataEntity> getmeta(int idimg){
       return this.metarepository.listMetadata(idimg);

    }
    //liste des images
    public List<ImageEntity> getphoto(){
       return this.imageRepo.findAll();

    }
    public List<DocumentEntity> getdoc(){
        return this.documentRepositery.findAll();
    }

    public Integer enregistrerdoc(DocumentModel document, UtilisateurSessionDto user){

        DocumentEntity doc =new DocumentEntity();
        doc.setTitre(document.getTitre());
        doc.setSousTitre(document.getSousTitre());
        doc.setDescription(document.getDescription());
        doc.setEstPublique(document.getPublique());
        doc.setType(document.getType());
        System.out.print(document.getPublique());
        doc.setStatut(null);
        //doc.setImages(null);
        //doc.setImagePrincipal(null);
        


        doc.setCreePar(utlisateurRepository.getOne(user.getId()));
        doc.setCommentaires(null);
        doc.setCreeLe(LocalDateTime.now());
       

        documentRepositery.save(doc) ;
        return doc.getId();
       


    }

    @Transactional
	public void uplodfile(Integer idDoc, List<MultipartFile> files,
			UtilisateurSessionDto userSession) throws IOException {

		DocumentEntity doc = documentRepositery.getOne(idDoc);
		AbstractUtilisateurEntity user = utlisateurRepository.getOne(userSession.getId());
		
		LocalDateTime now = LocalDateTime.now();
		
		List<ImageEntity> imageEntities = new ArrayList<>(files.size());
		
		for (int i = 0; i < files.size(); i++) {
			MultipartFile mf = files.get(i);
			ImageEntity img = new ImageEntity();
			img.setDocument(doc);
			img.setContenu(mf.getBytes());
			img.setTailleFichier(mf.getSize());
			img.setTypeFichier(mf.getContentType());
			
			img.setCreePar(user);
			img.setCreeLe(now);
			
			 try {
				Metadata metadata = ImageMetadataReader.readMetadata(new ByteArrayInputStream(mf.getBytes()));
				

				 for (Directory directory : metadata.getDirectories()) {
					 for (Tag tag : directory.getTags()) {
						 ImageMetadataEntity meta=new ImageMetadataEntity();
						 meta.setImage(img);
						 switch (tag.getTagName()){

	                       case "Image Width":

	                    	meta.setTag(tag.getTagName());
	                    	meta.setValeur(tag.getDescription());
	                    	this.metarepository.save(meta);
	                        break;
	                       case "Image Height":

		                    	meta.setTag(tag.getTagName());
		                    	meta.setValeur(tag.getDescription());
		                    	this.metarepository.save(meta);
		                        break;
	                       case "Make":

		                    	meta.setTag(tag.getTagName());
		                    	meta.setValeur(tag.getDescription());
		                    	this.metarepository.save(meta);
		                        break;
	                       case "Model":

		                    	meta.setTag(tag.getTagName());
		                    	meta.setValeur(tag.getDescription());
		                    	this.metarepository.save(meta);
		                        break;
	                       case "Date/Time":

		                    	meta.setTag(tag.getTagName());
		                    	meta.setValeur(tag.getDescription());
		                    	this.metarepository.save(meta);
		                        break;
		                        
	                      
						 }
	                  /*  case "Image Height":
	                       this.img.setHauteur(tag.getDescription());
	                    break;

	                    case "File Name":
	                        this.img.setName(tag.getDescription());
	                    break;
						 
						 meta.setTag(tag.getTagName());
						 meta.setValeur(tag.getDescription());*/
	                       
						 
						 System.out.println("================================"+tag.getTagName()+ "  =>"+tag);
					 }
				 }
				
				
				 }catch (ImageProcessingException | IOException e) {
				
				e.printStackTrace();
			}
			
			imageEntities.add(img);
			
		}

		
		imageRepo.saveAll(imageEntities);
		
		doc.setImagePrincipal(imageEntities.get(0));
		
		documentRepositery.save(doc);
		
	}

	public ImageEntity getImagePrincipale(Integer idDoc) {

		
		return documentRepositery.getOne(idDoc).getImagePrincipal();
	}
	public void supprimerdoc(int id) {
		this.documentRepositery.deleteById(id);
		
	}
	//
	public DocImgDetailsModel detailePub(int id) {
		DocImgDetailsModel detaille = new DocImgDetailsModel();
		
		DocumentEntity doc = this.documentRepositery.getOne(id);
		
		detaille.setNom(doc.getCreePar().getNom());
		detaille.setPrenom(doc.getCreePar().getPrenoms());
		detaille.setDescription(doc.getDescription());
		detaille.setTitre(doc.getTitre());
		detaille.setSoustitre(doc.getSousTitre());
		detaille.setCreeLe(doc.getCreeLe());
		
		detaille.setIdsImage(imageRepo.listImagesParDoc(id));
		
		return detaille;
		
		
	}
	public ImageEntity getImage(Integer idImg) {
		
		return this.imageRepo.getOne(idImg);
	}
	public void listeImage(int iddoc) {
		List<Integer> imageId = new ArrayList<>();
		
		
		for (ImageEntity img : this.getphoto()) {
			
			
			if( img.getDocument().getId().equals(iddoc)) {
				imageId.add(img.getId());
			}
			
			
		}
		
	}
	
}
