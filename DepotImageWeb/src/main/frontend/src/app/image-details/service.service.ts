import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Image } from './image';
@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http: HttpClient) { }
  detaille_pub(id:number ){
    return this.http.get('api/detaille/'+id );
  }


  getImages() {
    return this.http.get<any>('assets/photos.json')
      .toPromise()
      .then(res => <Image[]>res.data)
      .then(data => { return data; });
    }

    get_liste_images(idDoc:number){
      return this.http.get<any>('api/document/img/'+idDoc);
      
    }
    
    }

