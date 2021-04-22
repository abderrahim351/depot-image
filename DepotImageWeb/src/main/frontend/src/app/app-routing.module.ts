import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { AuthGuard } from './login/auth-guard';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path : '' , redirectTo : 'accueil', pathMatch: 'full'} ,
  {path : 'login' , component : LoginComponent },
  {path : 'accueil' , component : AccueilComponent , canActivate :  [AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
