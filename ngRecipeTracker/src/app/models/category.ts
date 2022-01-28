import { CategoryType } from "./category-type";
let cat = {
    id: 0
}
export interface Category {
    id: number;
    name?:string;
    description?:string;
    type?:CategoryType;
}
