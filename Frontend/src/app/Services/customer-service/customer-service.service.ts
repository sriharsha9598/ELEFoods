import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from '../../Models/Item';
import { SubCategory } from '../../Models/subCategory';
import { Order } from '../../Models/Order';
import { CarryBox } from '../../Models/CarryBox';
import { Address } from '../../Models/Address';

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {

  constructor(private http:HttpClient) { }

  getABranchItems(branchId:number){
    return this.http.get<Item[]>("http://localhost:8094/customer/getABranchItems/"+branchId);
  }

  searchItems(branchId:number,searchText:string){
    return this.http.get<Item[]>("http://localhost:8094/customer/searchItems/"+branchId+"/"+searchText);
  }

  getABranchCategories(branchId:number){
    return this.http.get<SubCategory[]>("http://localhost:8094/customer/getABranchSubCategories/"+branchId);
  }

  trackAnOrder(orderId:number){
    return this.http.get<Order[]>("http://localhost:8094/customer/trackAnOrder/"+orderId);
  }

  getCarryBoxDetails(emailId:string){
    return this.http.get<CarryBox>("http://localhost:8094/customer/getACarryBoxDetails/"+emailId);
  }

  getMyOrders(emailId:string){
    return this.http.get<Order[]>("http://localhost:8094/customer/getAnUserOrders/"+emailId);
  }  

  getMyAddresses(emailId:string){
    return this.http.get<Address[]>("http://localhost:8094/customer/getAnUserAddresses/"+emailId);
  }  
  
  addItemToCarryBox(emailId:string, itemId:number){
    return this.http.post<boolean>("http://localhost:8094/customer/addAnItemToCarryBox/"+emailId+"/"+itemId,"");
  }

  addANewAddress(emailId:string, address:Address){
    return this.http.post<boolean>("http://localhost:8094/customer/addANewAddress/"+emailId,address);
  }

  placeOrder(emailId:string, branchId:number, addressId:number){
    return this.http.post<boolean>("http://localhost:8094/customer/placeANewOrder/"+emailId+"/"+branchId+"/"+addressId,"");
  }

  updateItemInCarryBox(emailId:string, itemId:number,quantity:number){
    return this.http.put<boolean>("http://localhost:8094/customer/updateACarryBoxItem/"+emailId+"/"+itemId+"/"+quantity,"");
  }

  deleteACarryBoxItem(emailId:string,itemId:number){
    return this.http.delete<boolean>("http://localhost:8094/customer/deleteACarryBoxItem/"+emailId+"/"+itemId);
  }

  clearTheCarryBox(emailId:string){
    return this.http.delete<boolean>("http://localhost:8094/customer/clearACarryBox/"+emailId);
  }
}
