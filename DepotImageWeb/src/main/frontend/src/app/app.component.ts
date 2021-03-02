import { Component } from '@angular/core';
import {AdminServiceService} from './admin/admin-service.service';
import {Utilisateur} from './admin/liste-utilisateur/utilisateur';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'DepotImageWeb';
  utilisateurs : Utilisateur[] ;
  constructor(private serv: AdminServiceService) {
  }
  ngOnInit(){
    this.serv.get_utilisateurs().subscribe((data:any)=>{
      this.utilisateurs=data;
    });
  }
}
