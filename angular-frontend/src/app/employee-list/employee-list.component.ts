import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

employees: Employee[];

  constructor( private _employeeService: EmployeeService,
    private router:Router
    ) { }

  ngOnInit(): void {
    this.getEmployees();
    

//     this.employees=[{

//       "id": 1,
//       "firstName": "Mbrs",
//       "lastName":"mamun",
//       "email":"mamun@gmail.com"


//     }
//   ,
// {
//   "id": 2,
//       "firstName": "Naim",
//       "lastName":"Rahman",
//       "email":"n@gmail.com"
// }
// ]
  }

  private getEmployees(){
    this._employeeService.getEmployeeList().subscribe(data =>{
      this.employees=data
    })
  }
 

  updateEmployee(id:number){
        this.router.navigate(['update-employee',id]);//url comming from app.routing.module.ts file
  }
//sending to viewEmployee page
  viewEmployee(id : number){
    this.router.navigate(['view-employee',id]);
  }

  deleteEmployee(id:number){
    this._employeeService.deleteEmployee(id).subscribe(
      data =>{
       console.log(data);
       this.getEmployees();
      }
      
      
    )
  

  }
}
