export interface Recipe {
    id?: number;
    dateCreated?: Date;
    active?:boolean;
    creatorId?:number;
    isPublic?:boolean;
    name: string;
    prepTime: string;
    cookTime: string;
}
