import { CommentaireDto } from './commentaire-dto';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

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

}
