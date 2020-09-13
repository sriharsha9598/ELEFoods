import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AdminServiceService } from '../../../Services/admin-service/admin-service.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {
  addCategoryForm:FormGroup
  submitted:boolean=false
  added:boolean=false
  constructor(private formBuilder:FormBuilder, private adminService: AdminServiceService) { }

  ngOnInit() {
    this.addCategoryForm=this.formBuilder.group({
      categoryName:['',[Validators.required,Validators.pattern("[A-Za-z].*")]]
 
    })
    this.getSubCategories();
   }

   getSubCategories(){
    this.adminService.getSubCategories().subscribe(),
    err=>{console.log(err)}

   }
 addCategory(){
  var input = document.getElementById("input")
   this.submitted=true
   if(this.addCategoryForm.invalid){
     return;
   }
 else{
 
   this.adminService.addCategory("pravallikakonduru17@gmail.com",  this.addCategoryForm.value).subscribe(
   data=>{
   this.added=true
   },err=>{})
   
 }

 }


}
