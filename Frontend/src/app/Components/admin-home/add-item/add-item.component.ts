import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SubCategory } from '../../../Models/subCategory';
import { Item } from '../../../Models/Item';
import { AdminServiceService } from '../../../Services/admin-service/admin-service.service';


@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {
addItemForm: FormGroup
editItemForm: FormGroup
submitted: boolean=false
subCategories: SubCategory[]
selectedSubCatgeory:any
itemId:number;
select:boolean=true
item_details:Item
addedItem:boolean=false;
editedItem:boolean=false
  constructor(private formBuilder:FormBuilder, private adminService: AdminServiceService, private route:ActivatedRoute, private router: Router) { 
    this.route.params.subscribe(params => {
      
      this.itemId = params['itemId'];
      if(this.itemId !=null){
      this.select = false
     
      }
    })
  }

  ngOnInit() {
    console.log(this.select)
    this.addItemForm=this.formBuilder.group({
      itemName:['',[Validators.required,Validators.pattern("[A-Za-z].*")]],
      itemDescription:['',[Validators.required, Validators.pattern("[A-Za-z].*")]],

      itemPrice: ['',[Validators.required, Validators.min(10), Validators.max(1000)]],
      active:['',[Validators.required]],
      speciality:['',[Validators.required]],
      type:['',[Validators.required]],
      subCategory:['',Validators.required]

     
    })




    this.getSubCategories();
    if(this.select==false)
    {
      this.editItemForm=this.formBuilder.group({
       itemId:[''],
        itemName:['',[Validators.required,Validators.pattern("[A-Za-z].*")]],
        itemDescription:['',[Validators.required, Validators.pattern("[A-Za-z].*")]],
  
        itemPrice: ['',[Validators.required, Validators.min(10), Validators.max(1000)]],
        active:['',[Validators.required]],
        speciality:['',[Validators.required]],
        type:['',[Validators.required]],
        subCategory:['',[Validators.required]]
       
      })
  
        this.getItem(this.itemId)
    }
   }

   getSubCategories(){
    this.adminService.getSubCategories().subscribe(data => this.subCategories = data),
    err=>{console.log(err)}

   }
   getItem(itemId:number)
   {
    console.log(this.itemId)
     this.adminService.getItem(itemId).subscribe(data=>{
       this.item_details=data
       console.log(this.item_details)
  
       this.editItemForm.patchValue(data)
       console.log(this.editItemForm.value)
       
     })
   }
 addItem(){
 
   this.submitted=true
   if(this.addItemForm.invalid){
     return;
   }
 else{
   console.log(this.selectedSubCatgeory)
   this.adminService.addItem("pravallikakonduru17@gmail.com", this.selectedSubCatgeory, this.addItemForm.value).subscribe(
   data=>{
    this.addedItem=true
   },err=>{}
   )
   
 }

 }

 editItem(){
   
   this.adminService.editItem(this.editItemForm.value).subscribe(data=>{
     if(data)
     {
       console.log("editItem")
      this.adminService.editItem(this.editItemForm.value)
       console.log("edited")
       this.editedItem=true;
        this.router.navigate(['/admin/items']);
        
     }
   }
    )
 }


  
}
