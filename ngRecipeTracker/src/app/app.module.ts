import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './services/auth.service';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { RecipeSearchComponent } from './components/recipe-search/recipe-search.component';
import { NavigatorComponent } from './components/navigator/navigator.component';
import { FormsModule } from '@angular/forms';
import { RecipeListComponent } from './components/recipe-list/recipe-list.component';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { CategoryFilterPipe } from './pipes/category-filter.pipe';
import { IngredientFilterPipe } from './pipes/ingredient-filter.pipe';
import { RecipeFilterPipe } from './pipes/recipe-filter.pipe';
import { CategoryTypeFilterPipe } from './pipes/category-type-filter.pipe'
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    RecipeDetailsComponent,
    RecipeSearchComponent,
    NavigatorComponent,
    RecipeListComponent,
    CategoryFilterPipe,
    IngredientFilterPipe,
    RecipeFilterPipe,
    CategoryTypeFilterPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModalModule,
  ],
  providers: [AuthService],
  bootstrap: [AppComponent],
})
export class AppModule {}
