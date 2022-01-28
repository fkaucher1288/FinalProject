let cook = {
    title:'Cookbook Default',
    author: 'Default',
    description: 'Default',
    imageURL: 'DefaultImgURL'

}

export interface Cookbook {
    id?:number;
    title:string;
    author:string;
    description:string;
    imageURL:string;
}
