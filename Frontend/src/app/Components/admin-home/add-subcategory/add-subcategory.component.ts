import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Category } from '../../../Models/Category';
import { AdminServiceService } from '../../../Services/admin-service/admin-service.service';

@Component({
  selector: 'app-add-subcategory',
  templateUrl: './add-subcategory.component.html',
  styleUrls: ['./add-subcategory.component.css']
})
export class AddSubcategoryComponent implements OnInit {
added:boolean=false
  addSubCategoryForm:FormGroup
  submitted:boolean=false
  categories:Category[]
  selectedCategory:number
 
  constructor(private formBuilder:FormBuilder, private adminService: AdminServiceService) { }

  ngOnInit() {
    this.addSubCategoryForm=this.formBuilder.group({
      subCategoryName:['',[Validators.required,Validators.pattern("[A-Za-z].*")]],
      category:['',[Validators.required]]

 
    })
    this.getCategories();
   }

   getCategories(){
    this.adminService.getCategories("pravallikakonduru17@gmail.com").subscribe(
      data=>{this.categories = data}
    ),
    err=>{console.log(err)}

   }
 addSubCategory(){
  var input = document.getElementById("input")

   this.submitted=true
   if(this.addSubCategoryForm.invalid){
     return;
   }
 else{console.log(this.addSubCategoryForm.value)
 
   this.adminService.addSubCategory(this.selectedCategory,this.addSubCategoryForm.value).subscribe(
   data=>{

     this.added=true
   },err=>{})
   
 }

 }
 change(){
  alert(1)
 }
}
