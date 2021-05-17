import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Image } from './image';
import { Observable } from 'rxjs';


export interface ImageDetailsModel {
	 idDoc :number;
	 nom :string;
	 prenom :string;
	 titre :string;
	 soustitre :string;
	 creeLe :string;
	 description :string;
   idsImage: number[];
}


@Injectable({
  providedIn: 'root'
})
export class ImageDetailsService {

  constructor(private http: HttpClient) { }


  detaillePub(id:number ) : Observable<ImageDetailsModel>{
    return this.http.get<ImageDetailsModel>('api/document/details/'+id );
  }


  getImages() {
    return this.http.get<any>('assets/photos.json')
      .toPromise()
      .then(res => <Image[]>res.data)
      .then(data => { return data; });
    }

    }

