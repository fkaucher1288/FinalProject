import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeIngredient, RecipeIngredientId } from 'src/app/models/recipe-ingredient';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css'],
})
export class RecipeDetailsComponent implements OnInit {
  recipe: Recipe = {
    name: '',
    prepTime: '',
    cookTime: '',
    ingredients: [],
    categories: [],
  };



  id: RecipeIngredientId ={
    ingredientId: 0
  }

  ingredients: RecipeIngredient = {
    id: this.id,
    quantity: 0,
    remarks: '',
    name: ''
  };

recipeIngredients: RecipeIngredient[] = [];


  constructor(
    private recipeService: RecipeService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let id = Number.parseInt( this.route.snapshot.paramMap.get("id")!);
        this.recipeService.show(id).subscribe({
        next:(recipe)=> {this.recipe = recipe}
      })
  }
}
