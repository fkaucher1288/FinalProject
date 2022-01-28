let rec ={
    name: 'default',
    prepTime: 'Default Prep time',
    cookTime: 'Default Cook time',
}

export interface Recipe {
    id?: number;
    imageURL?: string;
    dateCreated?: Date;
    active?:boolean;
    creatorId?:number;
    isPublic?:boolean;
    name: string;
    prepTime: string;
    cookTime: string;
}
