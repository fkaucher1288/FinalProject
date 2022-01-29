import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = environment.baseUrl;

  constructor(private http: HttpClient) {}

  getHeaders() {
    return {
      Authorization: `Basic ${this.getCredentials()}`,
      "X-Requested-With": "XMLHttpRequest"
    }
  }

  login(username: string, password: string) {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    // create request to authenticate credentials
    return this.http.get(this.url + 'auth', httpOptions).pipe(
      tap((res) => {
        localStorage.setItem('credentials', credentials);
        return res;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.login(): Error logging in.')
        );
      })
    );
  }

  register(user: User) {
    // create request to register a new account
    return this.http.post(this.url + 'register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.register(): error registering user.')
        );
      })
    );
  }

  logout() {
    localStorage.removeItem('credentials');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username: string, password: string) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    //return localStorage.getItem('credentials');
    //make sure to get rid of this hard coded line outta here!
    return 'd29sZmdhbmdQdWNrOndvbGZnYW5nUHVjaw==';
  }
}
