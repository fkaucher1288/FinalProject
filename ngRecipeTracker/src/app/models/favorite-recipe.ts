export interface FavoriteRecipeId {
    recipeId: number;
    userId?: number;
}

export interface FavoriteRecipe {
    id: FavoriteRecipeId;
    comment: string;
    dateLastMade?: Date;
    createdAt?:Date;

}
