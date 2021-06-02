import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CurrentUser, LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  testConnexion:String;

  constructor(private fb: FormBuilder,
    private router: Router,
    private service: LoginService) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  login(): void {

    this.service
      .login({
        username: this.loginForm.get('username').value,
        password: this.loginForm.get('password').value,
      })
      .subscribe((data:CurrentUser)=>{
        console.log(data);
        this.testConnexion=data.identifiant;
       if(data.identifiant=='faux_email'){
         console.log("verifier votre adresse email et votre mot de passe");
       }
       else if(data.identifiant=='faux_passe'){
        console.log("verifier votre mot de passe");
       }
       else{
        this.router.navigate(['/accueil']);
       }
       
          
       
       
      })
      
      
      
        

  }


}
