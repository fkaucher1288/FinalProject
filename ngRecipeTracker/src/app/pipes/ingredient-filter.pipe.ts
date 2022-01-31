import { Pipe, PipeTransform } from '@angular/core';
import { Ingredient } from '../models/ingredient';

@Pipe({
  name: 'ingredientFilter',
})
export class IngredientFilterPipe implements PipeTransform {
  transform(ingredients: Ingredient[], filter: string): Ingredient[] {
    let filtered: Ingredient[] = [];

    filter = filter.toLowerCase();
    ingredients.forEach((ingredient) => {
      if (ingredient.name?.toLowerCase().includes(filter)) {
        filtered.push(ingredient);
      }
    });

    return filtered;
  }
}
