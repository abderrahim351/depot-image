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
    
    public String role(UtilisateurModel utl) {
    	
    	if(utl.getRole().equals("gestionnaire")) {
    		GestionnaireEntity gest =new GestionnaireEntity();
    		gest.setAdresseEmail(utl.getEmail());
    		gest.setIdentifiant(utl.getEmail());
    		gest.setNom(utl.getNom());
    		gest.setPrenoms(utl.getPrenom());
    		return this.addGestionnaire(gest);
    	}
    	else if(utl.getRole().equals("utilisateur")) {
    		UtilisateurEntity utlis = new UtilisateurEntity();
    		utlis.setAdresseEmail(utl.getEmail());
    		utlis.setIdentifiant(utl.getEmail());
    		utlis.setNom(utl.getNom());
    		utlis.setPrenoms(utl.getPrenom());
    		return this.adduser(utlis);
    		
    	}
    	else {
    		AdministrateurEntity admin = new AdministrateurEntity();
    		admin.setAdresseEmail(utl.getEmail());
    		admin.setIdentifiant(utl.getEmail());
    		admin.setNom(utl.getNom());
    		admin.setPrenoms(utl.getPrenom());
    		return this.addAdmin(admin);
    	}
    	}
    //ajouter utilisateurs
    public String adduser(UtilisateurEntity utl) {
        String a = this.random_password();
        utl.setMotDePasse(a);
        userRepo.save(utl);

        this.sendmail( utl.getAdresseEmail(), utl.getNom(), utl.getPrenoms(),"utilisateur",a);
        return "utilisateur enregistrer";
    }
    //ajouter gestionnaire
    public String addGestionnaire(GestionnaireEntity utl) {
        String a = this.random_password();
        utl.setMotDePasse(a);

        userRepo.save(utl);
        this.sendmail( utl.getAdresseEmail(), utl.getNom(), utl.getPrenoms(),"gestionnaire",a);
        return "gestionnaire enregistrer";
    }
    //ajouter administrateur
    public String addAdmin(AdministrateurEntity utl) {

        String a = this.random_password();
        utl.setMotDePasse(a);
        userRepo.save(utl);
        this.sendmail( utl.getAdresseEmail(), utl.getNom(), utl.getPrenoms(),"administrateur",a);
        return "administrateur enregistrer";
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

    //fonction image
    /*public void AddImage(){
        ImageEntity im=new ImageEntity();
        im.setCreePar(userRepo.getOne(1));
        imageRepo.save(im);




    }*/


   /* public List<UtilisateurDto> getall() {
        return this.ut.findAll();
    }

    public UtilisateurDto testconnexion(String email,String passe) {
        for (int i = 0; i < this.getall().size(); i++) {
            if (this.getall().get(i).getEmail().equals(email) == true && this.getall().get(i).getMot_de_passe().equals(passe)== true) {
                return this.getall().get(i);
            }

        }
        return null;
    }
    //enregistrer utilisateur
    public void enregistrer(UtilisateurDto utl){
    utl.setMot_de_passe(this.random_password());
        this.ut.save(utl);
    }


    //supprimer_utilisateur_par_son_id
    public void SupprimerUtilisateur(Integer id){
         this.userRepo.deleteById(id);
    }


    //test_email
 public boolean test_par_email(String mail){
    for(int i=0;i<this.getall().size();i++){
        if(this.getall().get(i).getEmail().equals(mail)==true){
            return true;
        }
    }return false ;
 }
 public boolean test_modification(UtilisateurDto utl ){

     for(int i=0;i<this.getall().size();i++){
         System.out.println(this.getall().get(i).getEmail()+ +this.getall().get(i).getId());
         if(this.getall().get(i).getEmail().equals(utl.getEmail())==true && (this.getall().get(i).getId())!=utl.getId()){
             System.out.println(utl.getEmail()+ +utl.getId());
             return true;
         }
     }return false ;

 }*/
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













