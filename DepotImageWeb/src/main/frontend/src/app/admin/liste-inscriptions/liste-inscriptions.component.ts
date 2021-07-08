import { Component, OnInit } from '@angular/core';

import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { AdminServiceService } from "../admin-service.service";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CurrentUser, LoginService } from 'src/app/login/login.service';
import { Utilisateur } from '../liste-utilisateur/utilisateur';
interface role {
  name: string
}



@Component({
  selector: 'app-liste-inscriptions',
  templateUrl: './liste-inscriptions.component.html',
  styles: [`
        :host ::ng-deep .p-dialog .product-image {
            width: 150px;
            margin: 0 auto 2rem auto;
            display: block;
        }
    `],
  styleUrls: ['./liste-inscriptions.component.scss']
})


export class ListeInscriptionsComponent implements OnInit {

  roles:role[];
  x:CurrentUser;
  selectedrole:role;
  b1:boolean=false;

  checked:boolean;
  sex: string;
  modifier: boolean;
  utilisateurDialog: boolean;
  utilisateurs: Utilisateur[];
  utl: Utilisateur

  b: boolean;



  submitted: boolean;

  constructor(private fb: FormBuilder, private messageService: MessageService, private confirmationService: ConfirmationService, private service: AdminServiceService , private logs:LoginService) { }

  ngOnInit() {
    this.roles=[
      {name:"admin"},
      {name:"gestionnaire"},
      {name:"utilisateur"}

    ];
    this.logs.currentUserSession().subscribe((data)=>{
      this.x=data;

    })
    this.service.get_inscriptions().subscribe((data: any) => {

      this.utilisateurs = data;
      this.modifier = false;
      this.utl=null;
      for(let i=0;i<this.utilisateurs.length;i++){
        this.utilisateurs.values['b']=false;

      }


    })

  }






  deleteSelectedProducts() {
    this.confirmationService.confirm({
      message: 'vous étes sure de supprimer cette utilisateur?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        for(let i=0 ;i<this.utilisateurs.length;i++){
          if(this.utilisateurs[i].b==true){
            this.service.deletutilisateur(this.utilisateurs[i].id).subscribe((data:any)=>{
              console.log(data);


            })
          }


        }this.messageService.add({severity:'success', summary: 'Successful', detail: 'Utilisateur Supprimé', life: 3000});

        location.reload();



    }
  })}

  editutl(util: Utilisateur) {

    this.service.activer(util.id).subscribe((data) => {
      console.log("test");

      this.ngOnInit();
    });
    this.messageService.add({ severity: 'success', summary: 'Inscription validé', detail: 'utilisateur validé', life: 3000 });


  }

  deleteutl(utl: Utilisateur) {
    this.confirmationService.confirm({
      message: 'Vous étes sure de supprimer Mr ' + utl.nom + " " + utl.prenoms + '?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        console.log(utl.id);
        this.service.deletutilisateur(utl.id).subscribe((data) => {
          console.log("test");

          this.ngOnInit();
        })
        this.messageService.add({ severity: 'success', summary: 'Suppression avec succés', detail: 'utilisateur supprime', life: 3000 });

      }
    });
  }

  hideDialog() {
    this.utilisateurDialog = false;
    this.submitted = false;
    this.ngOnInit();
  }

  selectiontous(){
    if(this.checked==true){
    for(let i=0 ; i<this.utilisateurs.length;i++){
      console.log("azerty");

      this.utilisateurs[i].b=true;
      console.log(this.utilisateurs[i].b);
    }}
    else{
      for(let i=0 ; i<this.utilisateurs.length;i++){
        console.log("azerty");
        console.log(this.utilisateurs.values["b"]);
        this.utilisateurs[i].b=false;
    }
  }}
f1(b:boolean){
  console.log("test");
 console.log(b);
 if(b==false){
   this.checked=false;
 }


    }



  }





