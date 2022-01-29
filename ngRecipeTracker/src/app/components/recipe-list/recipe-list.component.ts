import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css'],
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [];

  constructor(private recipeService: RecipeService, private router: Router) {}

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.recipeService.index().subscribe({
      next: (recipes) => {
        console.log(recipes);
        this.recipes = recipes;
      },
      error: (wrong) => {
        console.error(
          'Recipe-DetailComponent.reload(): Error retrieveing recipess'
        );
        console.error(wrong);
      },
    });
  }


}
