let recRateId ={
    userId: 1,
    recipeId: 1
}

export interface RecipeRatingId {
    userId: number;
    recipeId: number;
}
let recRate = {
    id: 0,
    rating: 1,
}

export interface RecipeRating {
    id: RecipeRatingId;
    rating: number;
    createdOn?: Date;
}
