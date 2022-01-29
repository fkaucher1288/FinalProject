import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { RecipeListComponent } from './components/recipe-list/recipe-list.component';
import { RecipeSearchComponent } from './components/recipe-search/recipe-search.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'recipedetail', component: RecipeDetailsComponent },
  { path: 'recipes', component: RecipeListComponent },
  { path: 'search', component: RecipeSearchComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
