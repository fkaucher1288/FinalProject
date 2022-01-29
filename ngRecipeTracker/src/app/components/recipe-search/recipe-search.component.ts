import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category';
import { Ingredient } from 'src/app/models/ingredient';
import { Recipe } from 'src/app/models/recipe';
import { CategoryService } from 'src/app/services/category.service';
import { IngredientService } from 'src/app/services/ingredient.service';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipe-search',
  templateUrl: './recipe-search.component.html',
  styleUrls: ['./recipe-search.component.css']
})
export class RecipeSearchComponent implements OnInit {

  categories: Category[] = [];
  recipes: Recipe[] = [];
  ingredients: Ingredient[] = [];
  filter: string = "";



  constructor(
   private categorySvc: CategoryService,
   private recipeSvc: RecipeService,
   private ingredientSvc: IngredientService
  ) { }

  ngOnInit(): void {

    this.categorySvc.index().subscribe(result => {

      this.categories = result;

    })

    this.ingredientSvc.index().subscribe(result => {
      this.ingredients = result;
    })

  }

}
