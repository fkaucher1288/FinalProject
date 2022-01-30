import { Ingredient } from "./ingredient";


export interface RecipeIngredientId {
    recipeId?: number;
    ingredientId: number;
}

export interface RecipeIngredient {
    ingredient: Ingredient;
    quantity: number;
    remarks: string;
    name: Ingredient;
}
