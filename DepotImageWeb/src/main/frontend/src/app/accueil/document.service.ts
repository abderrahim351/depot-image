import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Document} from './document';
@Injectable({
  providedIn: 'root'
})
export class documentservice {

  constructor(private httpclient:HttpClient) {

  }
  getdoc(){
    return this.httpclient.get<Document>('api/documents');
  }
  supprimerdoc(idDoc : number){
    return this.httpclient.get('api/document/'+idDoc);
  }
}
