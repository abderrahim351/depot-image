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
  role:string;

}
export interface modifierutl {
  id:number;
  nom: string;
  prenom: string;
  email:string;
  role:string;

}
export interface modifierutl2 {
  id:number;
  nom: string;
  prenom: string;
  email:string;

}
@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  constructor(private http: HttpClient) { }

  get_utilisateurs(x:number){
    return this.http.get('api/liste_utilisateurs/'+x);
  }

  get_inscriptions(){
    return this.http.get('api/liste_inscriptions');
  }

  activer(id: any){
    return this.http.get('api/activer/inscription/'+id);
  }

  deletutilisateur(id: any){
    return this.http.get('api/utilisateur/'+id);
  }
  ajouter_utilisateur(x:ajouterutl){
    console.log(x);
    return this.http.post('api/utilisateur',x,optionRequete);
  }
  modifier_utilisateur(x:modifierutl2){
    console.log(x);
    return this.http.post('api/utilisateur/modifer',x,optionRequete);
  }
  test_ajouter(x : any){
    return this.http.get('api/utilisateur/test/'+x);

  }
  test_modification(x : Utilisateur){
    return this.http.post('api/utilisateur/test_modification',x);

}
}
