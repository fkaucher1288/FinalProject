import { RecipeIngredient } from "./recipe-ingredient";


export interface Recipe {
    id?: number;
    name: string;
    imageURL?: string;
    dateCreated?: Date;
    active?:boolean;
    creatorId?:number;
    isPublic?:boolean;
    prepTime: string;
    cookTime: string;
    description?: string;
    instructions?: string;
    notes?: string;
    ingredient?: RecipeIngredient;
}
