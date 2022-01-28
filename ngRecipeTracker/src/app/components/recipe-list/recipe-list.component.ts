import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {
  selected: Recipe | null = null;
  recipes: Recipe[] = [];
  router: any;

  constructor(
    private currentRoute: ActivatedRoute,
    private recipeService: RecipeService
  ) { }

  ngOnInit(): void {
    let recipeIdStr = this.currentRoute.snapshot.paramMap.get('id');
    if(!this.selected && recipeIdStr){
      let recipeId = Number.parseInt(recipeIdStr);
      if( !isNaN(recipeId)){
        this.recipeService.show(recipeId).subscribe({
          next: (recipe)=> {
            this.selected = recipe;
          },
          error:  (wrong) => {
            console.error('Recipe-DetailComponent.ngOnInit(): invalid recipeId');
            console.error(wrong);
          }
        })
      }else{
        this.router.navigateByUrl('invalidTodoId')
      }
    }
    this.reload();

  }

  reload(){
    this.recipeService.index().subscribe({
      next: (recipes) => {
        this.recipes = recipes;
      },
     error:  (wrong) => {
        console.error('Recipe-DetailComponent.reload(): Error retrieveing recipess');
        console.error(wrong);
      }
    });
    }

}

