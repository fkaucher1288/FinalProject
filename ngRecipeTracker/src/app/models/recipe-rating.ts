export interface RecipeRatingId {
    userId: number;
    recipeId: number;
}

export interface RecipeRating {
    id: RecipeRatingId;
    rating: number;
    createdOn: Date;
}
