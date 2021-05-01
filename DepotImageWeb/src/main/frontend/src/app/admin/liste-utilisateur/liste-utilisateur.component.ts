import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { ProductService } from './productservice';
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
  products: Product[];

  product: Product;

  selectedProducts: Product[];

  submitted: boolean;

  constructor(private fb: FormBuilder, private messageService: MessageService, private confirmationService: ConfirmationService, private service: AdminServiceService) { }

  ngOnInit() {
    this.roles=[
      {name:"admin"},
      {name:"gesrtionnaire"},
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
      prenom: ['', Validators.required],
      email: ['', Validators.email],
      date: ['', Validators.required],
      role: ['', Validators.required],


    });

  }
  initialiserModification(utl: Utilisateur) {
    this.utilisateurForm = this.fb.group({
      nom: [utl.nom, Validators.required],
      prenom: [utl.prenoms, Validators.required],
      email: [utl.adresseEmail, Validators.email],
      date: [utl.dateNaissance, Validators.required],
      sex: [utl.sex, Validators.required],


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
          this.service.ajouter_utilisateur({
            nom: this.utilisateurForm.get('nom').value,
            prenom: this.utilisateurForm.get('prenom').value,
            email: this.utilisateurForm.get('email').value,
            date_naissance: this.utilisateurForm.get('date').value,
            sex: this.utilisateurForm.get('sex').value,
            passe: "aaaa"
          }).subscribe((data: any) => {
            console.log(data);
            this.utilisateurDialog=false;
            this.ngOnInit();
            
          })
        }

      });

    }
    else {
      this.utl.nom = this.utilisateurForm.get('nom').value,
        this.utl.prenoms = this.utilisateurForm.get('prenom').value,
        this.utl.adresseEmail = this.utilisateurForm.get('email').value,
        this.utl.dateNaissance = this.utilisateurForm.get('date').value,
        this.utl.sex = this.utilisateurForm.get('sex').value,
        this.service.test_modification(this.utl).subscribe((data:boolean)=>{
          if(data==true){
            alert("cette email existe deja pour un autre utilisateur");
          }
          else{this.service.modifier_utilisateur(this.utl).subscribe((data: any) => {
            console.log(data);
            this.utilisateurDialog=false;
            this.ngOnInit();
          })
        }
        })
        
    }
    
  }
  openNew() {
    this.product = {};
    this.submitted = false;
    this.utilisateurDialog = true;
  }

  deleteSelectedProducts() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected products?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        for(let i=0 ;i<this.utilisateurs.length;i++){
          if(this.utilisateurs[i].b==true){
            this.service.deletutilisateur(this.utilisateurs[i].id).subscribe((data:any)=>{
              console.log(data);
              this.ngOnInit();
              
            })
          }

        }this.messageService.add({severity:'success', summary: 'Successful', detail: 'Products Deleted', life: 3000});


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





