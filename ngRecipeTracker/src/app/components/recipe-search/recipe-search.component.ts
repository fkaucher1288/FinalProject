import { ThisReceiver } from '@angular/compiler';
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
  styleUrls: ['./recipe-search.component.css'],
})
export class RecipeSearchComponent implements OnInit {
  categories: Category[] = [];
  recipes: Recipe[] = [];
  ingredients: Ingredient[] = [];
  filter: string = '';
  filterCategories: Category[] = [];
  filterIngredients: Ingredient[] = [];
  dummy = 0;

  constructor(
    private categorySvc: CategoryService,
    private recipeSvc: RecipeService,
    private ingredientSvc: IngredientService
  ) {}

  ngOnInit(): void {
    this.categorySvc.index().subscribe((result) => {
      this.categories = result;
    });

    this.ingredientSvc.index().subscribe((result) => {
      this.ingredients = result;
    });

    this.recipeSvc.index().subscribe((result) => {
      this.recipes = result;
    })
  }

  addFilter(item:any) {
    // only categories have a type
    if (item.type) {
      this.filterCategories.push(item);
      this.categories = this.categories.filter(c => c !== item) 
    } else {
      this.filterIngredients.push(item);
      this.ingredients = this.ingredients.filter(i => i !== item)
    }
    this.dummy++; // we needed a dummy variable to force our pipe to update
  }

  removeFilter(item:any) {
    if (item.type) {
      this.categories.push(item);
      this.filterCategories = this.filterCategories.filter(c => c !== item)
    } else {
      this.ingredients.push(item);
      this.filterIngredients = this.filterIngredients.filter(i => i !== item)
    }
    this.dummy++; // we needed a dummy variable to force our pipe to update
  }
}
