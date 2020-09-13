import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SubCategory } from '../../../Models/subCategory';
import { AdminServiceService } from '../../../Services/admin-service/admin-service.service';

@Component({
  selector: 'app-subcategories',
  templateUrl: './subcategories.component.html',
  styleUrls: ['./subcategories.component.css']
})
export class SubcategoriesComponent implements OnInit {
  subCategories:SubCategory[]
  categories:SubCategory[]
  child_records_found:boolean=false;
  sub_category_details:SubCategory
  editSubCategoryForm:FormGroup
  edit:boolean=false
  searchText:string
    constructor(private adminService:AdminServiceService, private formBuilder: FormBuilder) { }
  
    ngOnInit() {
      this.editSubCategoryForm=this.formBuilder.group({
        subCategoryId:[''],
        subCategoryName:['',[Validators.required,Validators.pattern("[A-Za-z].*")]],
        category:['',[Validators.required]]
  
   
      })
      this.getSubCategories();
      
    }
  
    getSubCategories()
    {
      this.child_records_found=false;
      this.adminService.getSubCategories().subscribe(data=>{
        this.subCategories = data;
        console.log(this.subCategories)

      })
    }
    
    deleteSubCategory(subCategoryId : number)
    {
      this.child_records_found=false;
      this.adminService.deleteSubCategory(subCategoryId).subscribe(data=>{
        if(data){
          this.getSubCategories();
        }
      },err=>{
        if(err.error.errorMessage=="child records found") {
            this.child_records_found=true;
      }
      
    })
    
    }

    updateSubCategory(subCategoryId : number){
      this.edit=true;
      this.child_records_found=false;
     this.adminService.getSubCategory(subCategoryId).subscribe(data=>{
       this.sub_category_details = data;
       
       this.editSubCategoryForm.patchValue(data)

     })
    }

    editSubCategory(){
      this.child_records_found=false;
        this.edit=false;
        this.adminService.editSubCategory(this.editSubCategoryForm.value).subscribe(data=>{console.log("edited")
      if(data){
        this.getSubCategories();
      }
      })
    }
}
