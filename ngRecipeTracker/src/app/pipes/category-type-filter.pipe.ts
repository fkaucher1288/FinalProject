import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../models/category';

@Pipe({
  name: 'categoryTypeFilter',
})
export class CategoryTypeFilterPipe implements PipeTransform {
  transform(
    categories: Category[],
    args: {
      is?: string;
      isNot?: string;
      dummy?:number; 
    }
  ): Category[] {
    let accepted: Category[] = [];

    console.log(categories, args);

    if (args.is) {
      for (const category of categories) {
        if (category.type?.name === args.is) {
          accepted.push(category);
        }
      }
    } else if (args.isNot) {
      for (const category of categories) {
        if (category.type?.name !== args.isNot) {
          accepted.push(category);
        }
      }
    }

    return accepted;
  }
}
