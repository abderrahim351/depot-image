import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { SelectItem } from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';
import { acceuilservice } from './acceuilservice';
import { Product } from './product';
import { Document } from './document';
import { documentservice } from './document.service';
import { Router } from '@angular/router';
import { CurrentUser, LoginService } from '../login/login.service';
@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss'],
})
export class AccueilComponent implements OnInit {
  products: Product[];
  documents: Document[];

  sortOptions: SelectItem[];

  sortOrder: number;

  sortField: string;
  currentUser: CurrentUser;


  constructor(
    private loginService: LoginService,
    private docservice: documentservice,
    private router: Router
  ) {}

  ngOnInit() {

    this.loginService.currentUserSession().subscribe((u) => {

      this.currentUser = u;
      this.load();
    });

  }


  load(){
    this.docservice.getdoc().subscribe((data: any) => {
      this.documents = data;
      console.log(this.documents);
    });
  }

  isEditable(document: Document) : boolean{
    return document.creeParId === this.currentUser.id;
  }

  onSortChange(event) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    } else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }

  afficherDocument(idDoc: number) {
    this.router.navigateByUrl('image/' + idDoc);
  }
}
