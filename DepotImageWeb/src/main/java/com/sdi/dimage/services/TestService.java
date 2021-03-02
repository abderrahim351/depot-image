package com.sdi.dimage.services;

import com.sdi.dimage.dao.UtlisateurDao;
import com.sdi.dimage.utils.UtilisateurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private UtlisateurDao ut;

    public List<UtilisateurDto> getall() {
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
    public String enregistrer(UtilisateurDto utl){
        UtilisateurDto uti=this.ut.save(utl);
        return "user enregistrer";

    }
    //supprimer_utilisateur_par_son_id
    public String SupprimerUtilisateur(Integer id){
        this.ut.deleteById(id);
        return "supprimé";
    }
}









