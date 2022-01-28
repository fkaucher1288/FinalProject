let favRecId = {
    recipeId: 0
}

export interface FavoriteRecipeId {
    recipeId: number;
    userId?: number;
}
let favorRec = {
    id: 0,
    comment: "default"
}

export interface FavoriteRecipe {
    id: FavoriteRecipeId;
    comment: string;
    dateLastMade?: Date;
    createdAt?:Date;

}
