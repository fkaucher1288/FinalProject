import { CategoryType } from "./category-type";

export interface Category {
    id: number;
    name?:string;
    description?:string;
    type?:CategoryType;
}
