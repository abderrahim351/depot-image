import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ConfirmationService } from 'primeng/api';

export const REGEX_PASSWORD: RegExp =
  /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{5,30}/;

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss'],
})
export class InscriptionComponent implements OnInit {
  inscriptionForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private cmService: ConfirmationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.inscriptionForm = this.fb.group({
      nom: [null, Validators.required],
      prenom: [null, Validators.required],
      adresseEmail: [null, [Validators.required, Validators.email]],
      motDePasse: [null, this.password1()],
      motDePasse2: [null, this.password2()],
    });
  }
  inscrire() {
    console.log(this.inscriptionForm.value);
  }

  isInvalidEmail(): boolean {
    const ctr = this.inscriptionForm.get('adresseEmail');
    return ctr.invalid && ctr.hasError('email') && ctr.dirty;
  }

  isInvalidRequired(ctr: AbstractControl): boolean {
    return ctr.invalid && ctr.hasError('required') && ctr.dirty;
  }

  isInvalidPassword1(): boolean {
    const ctr = this.inscriptionForm.get('motDePasse');
    return ctr.invalid && ctr.hasError('ErrorPassword1') && ctr.dirty;
  }

  isInvalidPassword2(): boolean {
    const ctr = this.inscriptionForm.get('motDePasse2');
    return ctr.invalid && ctr.hasError('ErrorPassword2') && ctr.dirty;
  }
  password1(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value as string;

      if (!value || !REGEX_PASSWORD.test(value)) {
        return { ErrorPassword1: true };
      }
      return null;
    };
  }

  password2(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (this.inscriptionForm) {
        const ctrP1 = this.inscriptionForm.get('motDePasse');
        const value = control.value as string;
        if (ctrP1.valid && value !== ctrP1.value) {
          return { ErrorPassword2: true };
        }
      }

      return null;
    };
  }

  onSubmit() {
    if (this.inscriptionForm.valid) {
      this.http
        .post<void>('api/inscrire', this.inscriptionForm.value, {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
          }),
        })
        .toPromise().then((value) => {
          this.router.navigate(['/login']);
        }).catch(()=> {


          this.cmService.confirm({
            message: 'Cette adresse e-mail est déjà utilisé.',
            header: 'Erreur',
            icon: 'pi pi-times-circle',
            acceptVisible: false,
            rejectLabel:'OK',
            defaultFocus: 'reject',
          });

        });
    } else {
      Object.keys(this.inscriptionForm.controls).forEach((key) =>
        this.inscriptionForm.get(key).markAsDirty()
      );
    }
  }
}
