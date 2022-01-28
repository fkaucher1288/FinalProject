export interface RecipeReviewId {
    userId: number;
    recipeId: number;
}
export interface RecipeReview {
    id?:RecipeReviewId;
    createdOn?: Date;
    comment:string;
    active?:boolean;
}
