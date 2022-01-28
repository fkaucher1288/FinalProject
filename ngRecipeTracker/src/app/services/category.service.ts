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

  private url = environment.baseUrl + 'api/recipes/ingredients/';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url).pipe(
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

  show(id: number): Observable<Category> {
    return this.http.get<Category>(this.url + "/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'error showing category' + err
          }
        );

      })
    )
  }

  create(category: Category): Observable<Category> {
    category.name = '';
    return this.http.post<Category>(this.url, category).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'CategoryService.create(): error creating category' + err
          }
        );

      })
    )
  }

  update(category: Category): Observable<Category> {
    return this.http.put<Category>(this.url + "/" + category.id, category).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'CategoryService.update(): error updating category' + err
          }
        );

      })
    )
  }

  getHttpOptions()  {
    let options = {
    headers: {
      'Authorization': 'Basic ' + this.auth.getCredentials(),
      'X-Reuested-With': 'XMLHttpRequest'
    }
  };
    return options;
  }

  delete(id: number): Observable<Category> {
    return this.http.delete<Category>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'CategoryService.delete(): error deleting category' + err
          }
        );

      })
    )
  }
}
