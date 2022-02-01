import { Pipe, PipeTransform } from '@angular/core';
import { RecipeDetailsComponent } from '../components/recipe-details/recipe-details.component';
import { Category } from '../models/category';
import { Ingredient } from '../models/ingredient';
import { Recipe } from '../models/recipe';
import { IngredientService } from '../services/ingredient.service';

@Pipe({
  name: 'recipeFilter',
})
export class RecipeFilterPipe implements PipeTransform {
  transform(
    recipes: Recipe[],
    args: {
      categories: Category[];
      ingredients: Ingredient[];
      dummy: number;
    },
  ): Recipe[] {
    console.log(args);
    let accepted: Recipe[] = [];
    let needed = 0

    if (args.ingredients.length > 0) {
      needed++;
    }

    if (args.categories.length > 0) {
      needed++;
    }

    for (let recipe of recipes) {
      let got = 0;
console.log(recipe);
      if (args.ingredients.length > 0) {
        let ingredients = recipe.ingredients?.filter((i) => args.ingredients.filter((i2) => i2.id === i.ingredient.id)?.length > 0);

        if (ingredients?.length === args.ingredients.length) {
          got++;
        }
      }

      if (args.categories.length > 0) {
        let categories = recipe.categories?.filter((c) => {
          for (const other of args.categories) {
            if(other.id === c.id) {
              return true;
            }
          } return false;
        });


        if (categories?.length === args.categories.length) {
          got++;
        }
      }

      if (got === needed) {
        accepted.push(recipe);
      }
    }
    return accepted;

  }
}
