


export interface RecipeIngredientId {
    recipeId?: number;
    ingredientId: number;
}

export interface RecipeIngredient {
    id: RecipeIngredientId;
    quantity: number;
    remarks: string;
    name: string;
}
