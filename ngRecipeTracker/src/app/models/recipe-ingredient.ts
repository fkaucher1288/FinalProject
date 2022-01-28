let recIngId = {
    ingredient: 0,
}

export interface RecipeIngredientId {
    recipeId?: number;
    ingredientId: number;
}

let recIng ={
    id: 0,
    quantity: 0,
    remarks: 'default',
}
export interface RecipeIngredient {
    id: RecipeIngredientId;
    quantity: number;
    remarks: string;
}
