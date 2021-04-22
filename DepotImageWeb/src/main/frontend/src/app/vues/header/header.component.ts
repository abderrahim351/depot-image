import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUser, LoginService } from 'src/app/login/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {

  currentUser: CurrentUser;

  constructor(private loginService: LoginService,
    private router: Router,) { }

  ngOnInit(): void {

    this.loginService.currentUserSession().subscribe(u => this.currentUser = u);

  }

  deconnexion(){
    this.loginService.logout()
    .toPromise()
    .then(() => {
      this.router.navigate(['/login']);
    });
  }

}
