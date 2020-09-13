import { Pipe, PipeTransform } from '@angular/core';
import { Item } from '../../../Models/Item';

@Pipe({
  name: 'filterCarryBoxItems'
})
export class FilterCarryBoxItemsPipe implements PipeTransform {

  transform(carryBoxItems:Item[], itemId:number) {
    for(let i=0;i<carryBoxItems.length;i++){
      if(carryBoxItems[i].itemId==itemId)
        return carryBoxItems[i].quantity
    }
    return 0;
  }
}
