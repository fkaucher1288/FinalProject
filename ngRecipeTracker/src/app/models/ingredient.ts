let Ingredient = {
    name: 'Default',
    brand: 'Default',
    measurementUnit: 'Default',
    category: 'Default',
}

export interface Ingredient {
    id:number;
    name?:string;
    brand?:string;
    measurementUnit?:string;
    category?: string;
}
