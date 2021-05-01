import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  constructor(private http: HttpClient) { }
  // tslint:disable-next-line:typedef
  get_utilisateurs(){
    return this.http.get('http://localhost:8080/api/test/utilisateur');
  }
  // tslint:disable-next-line:typedef
  deletutilisateur(id: any){
    return this.http.get('http://localhost:8080/api/test/utilisateur/supprimer/'+id);
  }
}
