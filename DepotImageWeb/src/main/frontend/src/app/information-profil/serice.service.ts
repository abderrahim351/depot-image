import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
export interface modifierutl {
  id:number;
  nom: string;
  prenom: string;
  adresse:string;
  email:string;
  pays: string;
  ville:string;
  tel:string;
  passe:string;
  
}
@Injectable({
  providedIn: 'root'
})

export class SericeService {

  constructor(private http: HttpClient) { }
  modifier_utilisateur(x:modifierutl){
    return this.http.post('api/utilisateur/modifer',x);
  }
}
function optionRequete(arg0: string, x: modifierutl, optionRequete: any) {
  throw new Error('Function not implemented.');
}

