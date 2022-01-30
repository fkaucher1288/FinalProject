import { HttpClient } from '@angular/common/http';
import { convertUpdateArguments } from '@angular/compiler/src/compiler_util/expression_converter';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Ingredient } from '../models/ingredient';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private url = environment.baseUrl + 'api/ingredients/';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.url, {
      headers: this.auth.getHeaders()
    }).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'error loading ingredient' + err
          }
        );

      })
    )
  }

  show(id: number): Observable<Ingredient> {
    return this.http.get<Ingredient>(this.url + "/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'error showing ingredient' + err
          }
        );

      })
    )
  }

  create(ingredient: Ingredient): Observable<Ingredient> {
    ingredient.name = '';
    ingredient.brand = '';
    ingredient.category = '';
    ingredient.measurementUnit = '';
    return this.http.post<Ingredient>(this.url, ingredient).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => {
            'IngredientService.create(): error creating ingredient' + err
          }
        );

      })

    )


  }

  update(ingredient: Ingredient): Observable<Ingredient> {

    return this.http.put<Ingredient>(this.url + '/' + ingredient.id, ingredient).pipe(
    catchError((problem: any) => {
      console.error('IngredientService.index(): error updating ingredient');
      console.error(problem);
      return throwError(
        () => new Error(
          'IngredientService.update(): error updating ingredient'
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

  destroy(ingredient: Ingredient): Observable<void>{
    return this.http.delete<void>(this.url + "/" + ingredient.id, this.getHttpOptions()).pipe(
    catchError( (error: any)=>{
        console.log('RecipeService.destory(): error deleting recipe: ');
        console.log(error);
        return throwError(
          () => new Error(
            'RecipeService.destory(): error deleting recipe'
            )
            );


          })
          )



        }
}
