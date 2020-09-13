import { Pipe, PipeTransform } from '@angular/core';
import { SubCategory } from '../../../Models/subCategory';

@Pipe({
  name: 'filterItems'
})
export class FilterItemsPipe implements PipeTransform {

  filteredItems: any = {}
  transform(categories: SubCategory[], filter: string) {
    for (let i = 0; i < categories.length; i++) {
      if (categories[i].subCategoryName.toLowerCase() == filter.toLowerCase())
        this.filteredItems = categories[i].items
    }

    return this.filteredItems;
  }
}
