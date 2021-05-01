import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utilisateur } from './liste-utilisateur/utilisateur';
const optionRequete = {
  headers: new HttpHeaders({ 
    'Access-Control-Allow-Origin':'*'
  })
};
export interface ajouterutl {
  nom: string;
  prenom: string;
  email:string;
  date_naissance:string;
  sex:string;
  passe:any;
}
@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  constructor(private http: HttpClient) { }
  // tslint:disable-next-line:typedef
  get_utilisateurs(){
    return this.http.get('api/utilisateur',optionRequete);
  }
  // tslint:disable-next-line:typedef
  deletutilisateur(id: any){
    return this.http.get('api/utilisateur/'+id);
  }
  ajouter_utilisateur(x:ajouterutl){
    console.log(x);
    return this.http.post('api/test/utilisateur/enregistrer',x,optionRequete);
  }
  modifier_utilisateur(x:Utilisateur){
    console.log(x);
    return this.http.post('api/test/utilisateur/enregistrer',x,optionRequete);
  }
  test_ajouter(x : any){
    return this.http.get('api/test/utilisateur/test_par_email/'+x);
    
  }
  test_modification(x : Utilisateur){
    return this.http.post('api/test/utilisateur/test_modification',x);

}
}
