import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../models/category';

@Pipe({
  name: 'categoryFilter',
})
export class CategoryFilterPipe implements PipeTransform {
  transform(
    categories: Category[],
    args: {
      name: string;
      filter: string;
    }
  ): Category[] {
    let name = args.name;
    let filter = args.filter;

    filter = filter.toLowerCase();

    let filtered: Category[] = [];
    categories.forEach((category) => {
      if (
        category.type?.name === name &&
        category.name?.toLowerCase().includes(filter)
      ) {
        filtered.push(category);
      }
    });

    return filtered;
  }
}
