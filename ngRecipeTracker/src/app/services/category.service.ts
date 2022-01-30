import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../models/category';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url = environment.baseUrl + 'api/category';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url, {headers: this.auth.getHeaders()}).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'error loading category' + err
          }
        );

      })
    )
  }

}
