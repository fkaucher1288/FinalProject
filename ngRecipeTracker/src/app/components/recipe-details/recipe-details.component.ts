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


ingredients: RecipeIngredient[] = [];
  rating: number = 5;

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

  onRatingClick(event: MouseEvent){
    let target = event.target as HTMLElement;
    let f = event.offsetX/target.clientWidth;
    this.rating = Math.round(f*5);
    this.recipeService.addRecipeRating(
      {
        id: {recipeId: this.recipe.id!},
        rating: this.rating
      }
    ).subscribe({
      next: () => console.log('ok'),
      error: (err) => console.log(err)



    })
    console.log(this.rating);


  }



}
