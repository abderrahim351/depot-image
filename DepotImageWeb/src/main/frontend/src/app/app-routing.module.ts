import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { ListeUtilisateurComponent } from './admin/liste-utilisateur/liste-utilisateur.component';
import { AuthGuard } from './login/auth-guard';
import { LoginComponent } from './login/login.component';
import { NouveauImageComponent } from './nouveau-image/nouveau-image.component';

const routes: Routes = [
  {path : '' , redirectTo : 'nouveau-image', pathMatch: 'full'} ,
  {path : 'login' , component : LoginComponent },
  {path : 'gestion-utilisateur' , component : ListeUtilisateurComponent },
  {path : 'accueil' , component : AccueilComponent , canActivate :  [AuthGuard]},
  {path : 'nouveau-image' , component : NouveauImageComponent , canActivate :  [AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
