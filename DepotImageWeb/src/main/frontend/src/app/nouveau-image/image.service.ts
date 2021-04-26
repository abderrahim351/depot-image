import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface documentModel {
  titre: string;
  soustitre: string;
  description: string;
  publique:boolean;
}
@Injectable({
  providedIn: 'root'
})

export class ImageService {

  constructor(private http: HttpClient) { }

  ajouter(doc: documentModel) : Observable<any>{
    return this.http.post('api/document',doc);
  }
}
