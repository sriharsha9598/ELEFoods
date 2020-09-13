import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AdminServiceService } from '../../../Services/admin-service/admin-service.service';
import { Category } from '../../../Models/Category';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
categories:Category[]
child_records_found:boolean=false;
editable:boolean=false
editCategoryForm: FormGroup
category_details:Category
searchText:string
  constructor(private adminService:AdminServiceService, private formBuilder:FormBuilder) { }

  ngOnInit() {
    this.editCategoryForm=this.formBuilder.group({
      categoryId:[''],
      categoryName:['',[Validators.required,Validators.pattern("[A-Za-z].*")]]
 
    })
    this.getCategories();
  }

  getCategories()
  {
    this.child_records_found=false;
    this.editable=false
    this.adminService.getCategories("pravallikakonduru17@gmail.com").subscribe(data=>{
      this.categories = data;
   
      console.log(this.categories)
    })
  }

  deleteCategory(categoryId : number)
  {
    this.child_records_found=false;
    this.editable=false
    console.log(categoryId)
    this.adminService.deleteCategory(categoryId).subscribe(data=>{
      if(data){
        this.getCategories();
      }
    },err=>{
      if(err.error.errorMessage=="child records found") {
          this.child_records_found=true;
    }}

    
    )
  
  }
  editCategory(categoryId:number){
    this.editable=true;
    this.child_records_found=false;
    console.log(categoryId)
    this.adminService.getCategory(categoryId).subscribe(data=>
      {
        this.category_details=data
        console.log(data)
        this.editCategoryForm.patchValue(data);
      })

  }
  updateCategory()
  {
    this.editable=false;
    this.adminService.editCategory("pravallikakonduru17@gmail.com",this.editCategoryForm.value).subscribe(
      data=>{console.log("edited")
      if(data){
        this.getCategories();
      }
    }
    )
  }
}
