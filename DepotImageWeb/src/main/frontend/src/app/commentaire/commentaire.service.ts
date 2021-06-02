import { CommentaireDto } from './commentaire-dto';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export interface Commentaire {
  creerpar :number;
  docId :number;
  contenu :string;
  type :string;
  creerle:string;
  nom:string;
  prenom:string;
  
}


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};


@Injectable()
export class CommentaireService {

  private apiUrl = 'api/commentaires'; // URL to web api

  constructor(private http: HttpClient) { }

  listByIdDemande(idDemande: number): Promise<CommentaireDto[]> {
    return this.http.get<any>(this.apiUrl + '/demande/' + idDemande, httpOptions).toPromise();
  }

  save(idDemande: number, param: CommentaireDto): Promise<string> {
    return this.http.post<any>(this.apiUrl + '/demande/' + idDemande, param, httpOptions).toPromise();
  }

  delete(id: number): Promise<void> {
    return this.http.post<any>(this.apiUrl + '/supp', id, httpOptions).toPromise();
  }
  ajouterCommentaire(x:Commentaire){
    return this.http.post('api/commentaire/',x);
  }
  listCommentaire(x:number){
    return this.http.get('api/commentaire/'+x);
  }
  supprimerCommentaire(id : number){
    return this.http.get('api/commentaire/delete/'+id);
  }
}
