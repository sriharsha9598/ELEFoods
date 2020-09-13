import { Component, OnInit } from '@angular/core';
import { Item } from '../../../Models/Item';
import { MergeArrayPipe } from '../../../Pipes/AdminPipes/merge-array.pipe';
import { Router } from '@angular/router';
import { AdminServiceService } from '../../../Services/admin-service/admin-service.service';

@Component({
  selector: 'app-items-admin',
  templateUrl: './items-admin.component.html',
  styleUrls: ['./items-admin.component.css']
})
export class ItemsAdminComponent implements OnInit {
  searchtext:string=""
  items:Item[]
  actives:string[]=[]
  value:string
  temp:any
  status:boolean
  child_records_found:boolean =false;
  mergeArray:MergeArrayPipe=new MergeArrayPipe();
  searchText:string
  newArray:any[]
    constructor(private adminService: AdminServiceService, private router:Router) { }
  
    ngOnInit() {
      this.getItems();
    
   
    }
    getItems()
    {
      this.adminService.getItems("pravallikakonduru17@gmail.com").subscribe(
        data=>{
          this.items=data
                }
  
      )
  }
  edit(itemId:number){
    this.router.navigate(['/admin/addItem',itemId])
  }
  
  delete(itemId : number){
  
    this.adminService.deleteItem(itemId).subscribe(data=>{this.getItems();},err=>{
      if(err.error.errorMessage=="child records found") {
        this.child_records_found=true;}
      
    })
    
  }
  updateActiveStatus(itemId:number){
  
    this.actives=[];
    var values = (<HTMLInputElement>document.getElementById(itemId.toString()+"-active")).value
    alert(values+" input")
  
   this.adminService.updateActiveStatus(itemId,values).subscribe(data=>{
    if(data==true){
    values="active"
    }
    else{
    values="not-active"
   console.log(data+" "+values)
    }
    // alert("check"+values)

     this.getItems();
    })
  }
}

