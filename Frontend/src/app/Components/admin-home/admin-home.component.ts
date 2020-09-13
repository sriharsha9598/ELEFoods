import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

home(){
  this.router.navigate(['/admin/dashboard']);
}
getOrders()
{
  this.router.navigate(['/admin/orders']);
}

addItem(){
  this.router.navigate(['/admin/addItem']);
}
addCategory(){
  this.router.navigate(['/admin/addCategory']);
}
addSubCategory(){
  this.router.navigate(['/admin/addSubCategory']);
}
getCategories(){
  this.router.navigate(['/admin/categories']);
}
getSubCategories(){
  this.router.navigate(['/admin/subCategories']);
}
getItems()
{
  this.router.navigate(['/admin/items']);
}
roles()
{
  this.router.navigate(['/admin/r&p']);

}
logout(){
  localStorage.email=""
  this.router.navigate([''])
}
}



