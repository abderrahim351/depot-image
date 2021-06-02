import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { CurrentUser, LoginService } from 'src/app/login/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class HeaderComponent implements OnInit {
  currentUser: CurrentUser;

  items: MenuItem[] = [];

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {

    this.loginService.currentUserSession().subscribe((u) => {
      console.log(u);
      this.currentUser = u;
      this.createItems();
    });


  }

  createItems() {

    if(this.currentUser){

      this.items = [
        {
          label: 'Accueil',
          icon: 'pi pi-fw pi-home',
          routerLink: ['/accueil'],
          routerLinkActiveOptions: { match: ['/accueil'] },


        },
        {
          label: 'Nouvelle publication',
          icon: 'pi pi-fw pi-image',
          routerLink: ['/nouvelle-image'],
          routerLinkActiveOptions: { match: ['/nouvelle-image'] },
        },
/*        {
          label: 'Images',
          icon: 'pi pi-fw pi-pencil',
          items: [
            {
              label: 'Right',
              icon: 'pi pi-fw pi-align-right',
            },
            {
              label: 'Center',
              icon: 'pi pi-fw pi-align-center',
            },
            {
              label: 'Justify',
              icon: 'pi pi-fw pi-align-justify',
            },
          ],
        },*/
        {
          label: 'Utilisateurs',
          icon: 'pi pi-fw pi-users',
          routerLink: ['/gestion-utilisateur'],
          routerLinkActiveOptions: { match: ['/gestion-utilisateur'] },
          visible:this.isAdmin(),
        },
        {
          label: 'Bonjour  '+this.currentUser.prenoms+' '+this.currentUser.nom,
          icon: 'pi pi-fw pi-user',
          items: [
            {
              label: ' profil',
              icon: 'pi pi-user',
              
            },
            {
              label: 'vos informations',
              icon: 'pi pi-user-edit',
              routerLink: ['/information-profil'],
              routerLinkActiveOptions: { match: ['/information-profil'] },
              
            },
            
            {
              label: 'Déconnexion',
              icon: 'pi pi-fw pi-sign-out',
              command : ($event) => this.deconnexion($event),
            },
          ],
        },
      ];
    }else {
      this.items = [
        {
          label: 'Connexion',
          icon: 'pi pi-fw pi-sign-in',
          routerLink: ['/login'],
          routerLinkActiveOptions: { match: ['/login'] },
        },
      ];
    }

  }

  deconnexion($event) {
    this.loginService
      .logout()
      .toPromise()
      .then(() => {
        this.ngOnInit();
        this.router.navigate(['/login']);
      });
  }
  isAdmin(){
    if (this.currentUser.groupe=="utilisateur" ){
      return false ;}
      else{
        return true;
      }
    }
  }

