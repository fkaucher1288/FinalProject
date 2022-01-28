import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cookbook } from '../models/cookbook';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CookbookService {

  private url = environment.baseUrl + 'api/user/cookbook';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<Cookbook[]> {
    return this.http.get<Cookbook[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'error loading cookbook' + err
          }
        );

      })
    )
  }

  show(id: number): Observable<Cookbook> {
    return this.http.get<Cookbook>(this.url + "/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'error showing cookbook' + err
          }
        );

      })
    )
  }

  create(cookbook: Cookbook): Observable<Cookbook> {
    cookbook.title = '';
    cookbook.description = '';
    cookbook.author = '';
    cookbook.imageURL = '';
    return this.http.post<Cookbook>(this.url, cookbook).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'CookbookService.create(): error creating cookbook' + err
          }
        );

      })

    )


  }

  update(cookbook: Cookbook): Observable<Cookbook> {

    return this.http.put<Cookbook>(this.url + '/' + cookbook.id, cookbook).pipe(
    catchError((problem: any) => {
      console.error('CookbookService.index(): error updating cookbook');
      console.error(problem);
      return throwError(
        () => new Error(
          'CookbookService.update(): error updating cookbook'
        )
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

  destroy(cookbook: Cookbook): Observable<void>{
    return this.http.delete<void>(this.url + "/" + cookbook.id, this.getHttpOptions()).pipe(
    catchError( (error: any)=>{
        console.log('CookbookService.destory(): error deleting cookbook: ');
        console.log(error);
        return throwError(
          () => new Error(
            'CookbookService.destory(): error deleting cookbook'
            )
            );


          })
          )



        }




}
