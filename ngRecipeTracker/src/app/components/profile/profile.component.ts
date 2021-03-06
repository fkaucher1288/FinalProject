import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FavoriteRecipe } from 'src/app/models/favorite-recipe';
import { Recipe } from 'src/app/models/recipe';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { CategoryService } from 'src/app/services/category.service';
import { CookbookService } from 'src/app/services/cookbook.service';
import { IngredientService } from 'src/app/services/ingredient.service';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  @ViewChild('recipeModalTemplate', { read: TemplateRef })
  recipeModalTemplate!: TemplateRef<any>;

  addRecipe: boolean = false;

  user?: User;
  rating?: number;
  favoriteRecipes: FavoriteRecipe[] = [];
  recipes: Recipe[] = [];
  recipe: Recipe = {
    id: 0,
    name: '',
    imageURL: '',
    active: true,
    creatorId: 1,
    isPublic: true,
    prepTime: '',
    cookTime: '',
    description: '',
    instructions: '',
    notes: '',
    ingredients: [],
    categories: [],
  };

  constructor(
    private recipeService: RecipeService,
    private cookbookService: CookbookService,
    private ingredientService: IngredientService,
    private categoryService: CategoryService,
    private authService: AuthService,
    private modal: NgbModal,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.authService
      .getUserByUserName(localStorage.getItem('username')!)
      .subscribe((result) => {
        this.user = result;
        this.authService.getAvgRating(this.user?.id!).subscribe((result) => {
          this.rating = result;
        });
        this.recipeService.getFavoriteRecipes(this.user?.id!).subscribe((result) => {
          this.favoriteRecipes = result;
        });
        this.recipeService.getUserRecipes(this.user?.id!).subscribe((result) => {
          this.recipes = result;
        });

      });
  }

  onClickAddRecipe() {
    this.modal.open(this.recipeModalTemplate).result.then((reason) => {
      console.log('close reason:', reason);

      if (reason === 'add recipe') {
        this.recipeService.create(this.recipe).subscribe({
          next: (result) => {
            console.log('recipe added', result);
          },
          error: (error) => {
            console.log('error adding recipe', error);
          },
        });
      }
    });
  }

  @ViewChild('editPageModalTemplate', { read: TemplateRef })
  editPageModalTemplate!: TemplateRef<any>;

  onClickEditPage() {
    this.modal.open(this.editPageModalTemplate).result.then((result)=>  {

      if(result == 'edit') {

        this.authService.saveUser(this.user!).subscribe({
          next: (result) => {
            console.log('profile edited', result);
          },
          error: (error) => {
            console.log('error editing profile', error);
          },
        })
      }


    })
  }

  onClickLogout(){
    this.authService.logout();
    this.router.navigateByUrl(`home`);
  }
}
