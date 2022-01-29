import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
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
  };

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
