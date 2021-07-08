import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {
  InscriptionForm: FormGroup;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.InscriptionForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      adresse: ['',[Validators.required, Validators.email]],
      passe: ['', Validators.required],
      passe2: ['', Validators.required],
    });
  }
  inscrire(){
console.log(this.InscriptionForm.value);

  }
}
