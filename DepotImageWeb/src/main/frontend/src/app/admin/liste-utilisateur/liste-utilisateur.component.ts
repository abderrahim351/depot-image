import { Component, OnInit } from '@angular/core';

import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { AdminServiceService } from "../admin-service.service";
import { Utilisateur } from "./utilisateur";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
interface role {
  name: string
}



@Component({
  selector: 'app-liste-utilisateur',
  templateUrl: './liste-utilisateur.component.html',
  styles: [`
        :host ::ng-deep .p-dialog .product-image {
            width: 150px;
            margin: 0 auto 2rem auto;
            display: block;
        }
    `],
  styleUrls: ['./liste-utilisateur.component.scss']
})
export class ListeUtilisateurComponent implements OnInit {
  roles:role[];
  
  selectedrole:role;
  b1:boolean=false;
  
  checked:boolean;
  utilisateurForm: FormGroup;
  sex: string;
  modifier: boolean;
  utilisateurDialog: boolean;
  utilisateurs: Utilisateur[];
  utl: Utilisateur

  b: boolean;
  

 
  submitted: boolean;

  constructor(private fb: FormBuilder, private messageService: MessageService, private confirmationService: ConfirmationService, private service: AdminServiceService) { }

  ngOnInit() {
    this.roles=[
      {name:"admin"},
      {name:"gestionnaire"},
      {name:"utilisateur"}

    ];
    this.initUtiliateurForm();

    this.service.get_utilisateurs().subscribe((data: any) => {

      this.utilisateurs = data;
      console.log(this.utilisateurs);
      this.modifier = false;
      this.utl=null;
      for(let i=0;i<this.utilisateurs.length;i++){
        this.utilisateurs.values['b']=false;
      }
    })
   
  }
  initUtiliateurForm() {
    this.utilisateurForm = this.fb.group({
      nom: ['', Validators.required],
      prenoms: ['', Validators.required],
      email: ['', Validators.email],
      role: ["utilisateur", Validators.required],


    });

  }
  initialiserModification(utl: Utilisateur) {
    this.utilisateurForm = this.fb.group({
      nom: [utl.nom, Validators.required],
      prenoms: [utl.prenoms, Validators.required],
      email: [utl.adresseEmail, Validators.email],
      role: [, Validators.required],
      


    })
  }

  ajouter() {
    if (this.modifier == false) {
      this.service.test_ajouter(this.utilisateurForm.get('email').value).subscribe((data: boolean) => {
        this.b = data;
        if (this.b == true) {
          alert('utilisateur existe');
        }
        
        else {
          console.log(this.utilisateurForm.get('role').value["name"]);
          this.service.ajouter_utilisateur({
            nom: this.utilisateurForm.get('nom').value,
            prenom: this.utilisateurForm.get('prenoms').value,
            email: this.utilisateurForm.get('email').value,
            role: this.utilisateurForm.get('role').value["name"],
            
            
          }).subscribe((data: any) => {
            console.log(data)
            this.utilisateurDialog=false;
            this.ngOnInit();
            
          })
        }

      });

    }
    else {
      this.utl.nom = this.utilisateurForm.get('nom').value,
        this.utl.prenoms = this.utilisateurForm.get('prenoms').value,
        this.utl.adresseEmail = this.utilisateurForm.get('email').value,
        this.service.test_modification(this.utl).subscribe((data:boolean)=>{
          if(data==true){
            alert("cette email existe deja pour un autre utilisateur");
          }
          else{this.service.modifier_utilisateur({
            id:this.utl.id,
            nom:this.utl.nom,
            prenom:this.utl.prenoms,
            email:this.utl.adresseEmail,
            role:this.utilisateurForm.get('role').value["name"],
          }).subscribe((data: any) => {
            console.log(data);
            this.utilisateurDialog=false;
            this.ngOnInit();
          })
        }
        })
        
    }
    
  }
  openNew() {
   
    this.submitted = false;
    this.utilisateurDialog = true;
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
        this.ngOnInit();
        this.checked=false;


    }
  })}

  editutl(util: Utilisateur) {
    this.modifier = true;
    this.initialiserModification(util);
    this.utilisateurDialog = true;
    this.utl = util;
    console.log(this.utl);

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





