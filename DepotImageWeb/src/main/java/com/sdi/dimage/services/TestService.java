package com.sdi.dimage.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.AdministrateurEntity;
import com.sdi.dimage.dao.entities.DocumentEntity;
import com.sdi.dimage.dao.entities.GestionnaireEntity;
import com.sdi.dimage.dao.entities.UtilisateurEntity;
import com.sdi.dimage.dao.repositories.CommentaireRepositery;
import com.sdi.dimage.dao.repositories.DocumentRepositery;
import com.sdi.dimage.dao.repositories.ImageRepository;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.utils.UtilisateurModel;


@Service
public class TestService {

    @Autowired
    private  JavaMailSender javaMailSender;
    @Autowired
    private  DocumentRepositery  doc;
    @Autowired
        private UtlisateurRepository userRepo;
    @Autowired
        private ImageRepository imageRepo;
    @Autowired
    private CommentaireRepositery commentairerepo ;
//fonctions utilisateur
    public DocumentEntity test() {
    	return doc.findById(2).get();
    }

    //listes des utilisateurs
    public List<AbstractUtilisateurEntity> getUser() {
    	System.out.print(this.userRepo.findAll());
        return this.userRepo.findAll();
    }




    public void addgest() {


        GestionnaireEntity ue = new GestionnaireEntity();

        ue.setNom("G1");

        userRepo.save(ue);


    }
    //supprimer_utilisateur_par_son_id
    public void SupprimerUtilisateur(Integer id){
        this.userRepo.deleteById(id);
    }



   
   
   
 //generer mot de passe
    public String random_password(){
        Random r =new Random() ;
        String S ;
        S= String.valueOf(r.nextInt(((999999-100000)+1)+100000));
       return S ;
    }

    public  void sendmail( String to , String nom , String prenom,String type , String a  ){
        System.out.println("envoie d'email");

      SimpleMailMessage  msj=new SimpleMailMessage();
      msj.setFrom("depotimage2@gmail.com");
      msj.setTo(to);
      msj.setSubject("Inscription");

      msj.setText("Bonjour "+nom+" "+prenom+  " dans notre equipe depot image \n vous etes un "+type+ "\n votre mot de passe est "+a);
      javaMailSender.send(msj);
    System.out.println("email envoyer");
    }




    }













