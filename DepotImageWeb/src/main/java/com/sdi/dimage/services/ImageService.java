package com.sdi.dimage.services;


import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.dao.entities.UtilisateurEntity;
import com.sdi.dimage.dao.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepo;
    private ImageEntity img =new ImageEntity();
    private int S;
    private String ch;
    private TestService serv=new TestService();
    private UtilisateurEntity utl =new UtilisateurEntity();

    private int taille ;
    public void uplodfile(MultipartFile file) throws IOException, ImageProcessingException {
        File f =new File("C:\\Users\\abder\\OneDrive\\Desktop\\depot-image\\DepotImageWeb\\src\\main\\frontend\\src\\assets\\"+file.getOriginalFilename());
        file.transferTo(f);
        Metadata metadata = ImageMetadataReader.readMetadata(new File("C:\\Users\\abder\\OneDrive\\Desktop\\depot-image\\DepotImageWeb\\src\\main\\frontend\\src\\assets\\" + file.getOriginalFilename()));
        this.utl.setNom("jamaaoui");
        this.utl.setAdresseEmail("abderrahim@gmail");
        this.img.setCheminFichier("assets/" + file.getOriginalFilename());
        this.img.setCreePar(null);
        this.img.setDescription("azerty");
        this.img.setaPartirDe(null);
        this.img.setCommentaires(null);
        this.img.setDocument(null);
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
                        this.img.setTailleFichier(this.taille);
                        break;
                    }
                    case "File Modified Date":
                        this.img.setCreeLe(tag.getDescription());
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
    }

    //liste des images
    public List<ImageEntity> getphoto(){
       return this.imageRepo.findAll();

    }
}
