import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TreeNode } from 'primeng/api';

export interface MetadataModel {
  directory : string;
  tag : string;
  valeur : string;
}

export interface GroupeMetadataModel {
  titreGroupe : string;
  tags: MetadataModel[];
}

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};
@Injectable()
export class ImageMetasService {

  private apiUrl = 'api/img/metas'; // URL to web api

  constructor(private http: HttpClient) { }

  metadatas(idImg: number): Promise<TreeNode[]> {
    return this.http.get<any>(this.apiUrl + '/' + idImg, httpOptions).toPromise();
  }
}
