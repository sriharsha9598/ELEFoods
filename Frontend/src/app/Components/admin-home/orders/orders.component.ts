import { Component, OnInit } from '@angular/core';
import { Order } from '../../../Models/Order';
import { AdminServiceService } from '../../../Services/admin-service/admin-service.service';



@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  placedOrder:Order[];
  activeOrder:Order[];
  count:number;
  count1:number;
  emailId:string;
  updating:boolean=false
  constructor(private adminService:AdminServiceService) { }

  ngOnInit() {
    this.emailId="pravallikakonduru17@gmail.com";
    this.getOrderList(this.emailId);
    this.getActiveOrderList(this.emailId);


  }
    getOrderList(emailId: string){
      this.adminService.getOrderList(this.emailId).subscribe(data=>{
        this.placedOrder=data;
        this.count=this.placedOrder.length;
      },err=>{
        console.log(err);
      });
    }
    getActiveOrderList(emailId:string){

      this.adminService.getActiveOrderList(this.emailId).subscribe(data=>{
        this.activeOrder=data;
        this.count1=this.activeOrder.length;
      },err=>{
        console.log(err);
      });
    }


  updateAcceptStatus(orderId:number){
    this.adminService.updateOrderStatus(orderId, "Accepted").subscribe(data=>
      {
        this.updating=true
        setTimeout(() => {
          this.updating=false
        }, 2500);
        console.log("data")
        this.getActiveOrderList(this.emailId);
        this.getOrderList(this.emailId);

      })
  }
  updateRejectStatus(orderId:number){
    this.adminService.updateOrderStatus(orderId, "Rejected").subscribe(data=>
      {
        this.updating=true
        setTimeout(() => {
          this.updating=false
        }, 2500);
        console.log("data")
        this.getActiveOrderList(this.emailId);
        this.getOrderList(this.emailId);

      })
  }
  updateDeliveredStatus(orderId:number){
    this.adminService.updateOrderStatus(orderId, "Delivered").subscribe(data=>
      {
        this.updating=true
        setTimeout(() => {
          this.updating=false
        }, 2500);
        console.log("data")
        if(data){
        this.getActiveOrderList(this.emailId);
        this.getOrderList(this.emailId);
        }

      })
  }

}
