import { variable } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { CurrentUser, LoginService } from 'src/app/login/login.service';
import {SericeService} from './serice.service';

@Component({
  selector: 'app-information-profil',
  templateUrl: './information-profil.component.html',
  styleUrls: ['./information-profil.component.scss']
})
export class InformationProfilComponent implements OnInit {
  profilForm: FormGroup;
 
  constructor(private loginService: LoginService,private fb: FormBuilder,private modifierservice: SericeService,private messageService: MessageService) { }
  currentUser: CurrentUser;
  a:string;
  
  ngOnInit(): void {
    this.loginService.currentUserSession().subscribe((u) => {
      console.log(u);
      this.currentUser = u;
      
    });
    this.profilForm = this.fb.group({
      nom: [this.currentUser.nom, [Validators.required]],
      prenom: [this.currentUser.prenoms, Validators.required],
      adresse: ['', Validators.required],
      email: [this.currentUser.adresseEmail, Validators.required],
      pays: ['', Validators.required],
      ville: ['', Validators.required],
      naissance: ['', Validators.required],
      tel: ['', Validators.required],
      passe: ['', Validators.required],
      passe2: ['', Validators.required]
    });
  }
  enregistrer(){
this.a= this.profilForm.get("naissance").value;
console.log(this.a);
    console.log(this.profilForm.value);
    console.log( this.profilForm.get("naissance").value);
    console.log(this.profilForm.get("passe").value);
    if(this.profilForm.get("passe").value!==this.profilForm.get("passe2").value){
      alert("verifie mot de passe ");}
      else{
        this.modifierservice.modifier_utilisateur({
  id:this.currentUser.id,
  nom: this.profilForm.get("nom").value,
  prenom:this.profilForm.get("prenom").value,
  adresse:this.profilForm.get("adresse").value,
  email:this.profilForm.get("email").value,
  pays:this.profilForm.get("pays").value,
  ville:this.profilForm.get("ville").value,
  tel:this.profilForm.get("tel").value,
  passe:this.profilForm.get("passe").value,
        }).subscribe((data:any)=>{
          console.log("modofcication termine");
          this.messageService.add({severity:'success', summary:'Service Message', detail:'Via MessageService'});
          
        })
        
      }
      
    }

    


      
      annuler(){
        this.ngOnInit();
      }
    

  }
 

