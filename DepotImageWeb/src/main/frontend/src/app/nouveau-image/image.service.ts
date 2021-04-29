import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FileUpload } from 'primeng/fileupload';
import { Observable } from 'rxjs';

export interface documentModel {

  titre: string;
  sousTitre: string;
  description: string;
  publique:boolean;
  
}
export interface imageModel {

   image : File; 
  id:number;
  
}

@Injectable({
  providedIn: 'root'
  
})

export class ImageService {
  
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}

  constructor(private http: HttpClient) { }

  ajouter(doc: documentModel) : Observable<any>{
    return this.http.post('api/document',doc);
  }
  
  
uploadimg(img : imageModel) : Observable<any>{
  console.log(img);
    
  return this.http.post('api/img',img);
}

}

