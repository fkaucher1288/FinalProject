import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FavoriteRecipe } from '../models/favorite-recipe';
import { Recipe } from '../models/recipe';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class RecipeService {

private url = environment.baseUrl + 'api/recipes';

  constructor(
    private http: HttpClient,
    private auth: AuthService,
  ) { }


getHttpOptions()  {
  let options = {
  headers: {
    'Authorization': 'Basic ' + this.auth.getCredentials(),
    'X-Requested-With': 'XMLHttpRequest'
  }
};
  return options;
}

index(): Observable<Recipe[]>{
  return this.http.get<Recipe[]>(this.url, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(
          'RecipeService.index(): error deleting todo'
        )
      )
    })
  );
}

create(recipe: Recipe): Observable<Recipe>{
  return this.http.post<Recipe>(this.url, recipe, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('KABOOM');
    })
  );

}

update(recipe: Recipe): Observable<Recipe>{
//add
  return this.http.put<Recipe>(this.url + "/" + recipe.id, recipe, this.getHttpOptions()).pipe(
    catchError( (error: any)=>{
      console.log('RecipeService.update(): error updating recipe: ');
      console.log(error);
      return throwError(
        () => new Error(
          'RecipeService.update(): error updating recipe'
        )
      )
    }
  )
  );
}


destroy(recipeId: number): Observable<void>{
  return this.http.delete<void>(this.url + "/" + recipeId, this.getHttpOptions()).pipe(
  catchError( (error: any)=>{
      console.log('RecipeService.destory(): error deleting recipe: ');
      console.log(error);
      return throwError(
        () => new Error(
          'RecipeService.destory(): error deleting recipe'
        )
      )
    }
  )
  );
}

show(recipeId: number): Observable<Recipe>{
  return this.http.get<Recipe>(this.url + "/" + recipeId, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(
          'RecipeService.show(): error showing recipe with id'
        )
      )
    })
  );
}
getFavoriteRecipes(userId: number): Observable<FavoriteRecipe[]>{
    return this.http.get<FavoriteRecipe[]>(this.url + "/favorites/" + userId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'RecipeService.getFavoriteRecipes(): error getting favorite recipes'
          )
        )
      })
    )
}

getUserRecipes(userId: number): Observable<Recipe[]>{
  return this.http.get<Recipe[]>(this.url + "/userrecipes/" + userId, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(
          'RecipeService.getFavoriteRecipes(): error getting favorite recipes'
        )
      )
    })
  )
}
}
