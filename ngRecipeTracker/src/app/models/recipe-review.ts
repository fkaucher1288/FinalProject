let recRevId = {
    userId: 0,
    recipeId: 0
}

export interface RecipeReviewId {
    userId: number;
    recipeId: number;
}
let recRev ={
    comment: 'default',
}

export interface RecipeReview {
    id?:RecipeReviewId;
    createdOn?: Date;
    comment:string;
    active?:boolean;
}
