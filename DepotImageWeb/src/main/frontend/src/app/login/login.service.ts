import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, ReplaySubject } from 'rxjs';
import { shareReplay, tap, catchError } from 'rxjs/operators';

export interface LoginModel {
  username: string;
  password: string;
}

export interface CurrentUser {
  identifiant: string;
  nom: string;
  id:number;
  prenoms: string;
  civilite: string;
  adresseEmail: string;
  groupe: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private currentUserState = new ReplaySubject<CurrentUser | null>(1);
  private authenticated = false;

  constructor(private http: HttpClient) { }

  login(login: LoginModel) : Observable<any>{
    return this.http.post('api/login',login);
  }

  currentUserSession(): Observable<CurrentUser | null> {
    return this.currentUserState.asObservable();
    
  }

  connexion(): Observable<CurrentUser | null> {

    if ( !this.authenticated) {
      return this.http.get<CurrentUser>('api/user-session').pipe(
        catchError(() => {
          return of(null);
        }),
        tap((userSession: CurrentUser | null) => {
          this.updateCurrentUserState(userSession);
        }),
        shareReplay()
      );
    }
    return this.currentUserSession();
  }

  private updateCurrentUserState(userSession: CurrentUser | null): void {
    if (userSession) {
      this.currentUserState.next(userSession);
      this.authenticated = true;
    } else {
      this.currentUserState.next(null);
      this.authenticated = false;
    }
  }

  private fetch(): Observable<CurrentUser> {
    return ;
  }

  logout() : Observable<any>{
    const ob  = this.http.post('api/logout',{})
      ob.subscribe(()=>this.updateCurrentUserState(null));
    return ob;
  }

}


