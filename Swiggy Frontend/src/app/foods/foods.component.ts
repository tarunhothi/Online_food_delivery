import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
  HttpRequest,
} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FoodService } from './food.service';
import jwt_decode from 'jwt-decode';
import { Food } from './food.model';

@Component({
  selector: 'app-foods',
  templateUrl: './foods.component.html',
  styleUrls: ['./foods.component.css'],
})
export class FoodsComponent implements OnInit {
  ngOnInit(): void {
    this.getFood();
  }

  foodData: any;
  // private foodName!: any;
  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService,
    private foodService: FoodService
  ) {}

  requestHeader = new HttpHeaders({
    Authorization: `Bearer ${this.authService.getToken()}`,
  });

  token = this.authService.getToken();
  jwt_data: any;
  jwt_role: any;
  jwt_user_data: any;
  jwt_userid: any;
  showButton: boolean = false;

  getDecodedAccessToken(token: string): any {
    try {
      console.log(jwt_decode(token), 'Decoded token');
      this.jwt_data = jwt_decode(token);
      // console.log(this.jwt_data.userData, "User Data");
      this.jwt_user_data = this.jwt_data.userData;
      return this.jwt_user_data;
    } catch (Error) {
      return null;
    }
  }

  addFoodForm = new FormGroup({
    name: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
  });

  getFood() {
    this.foodService.getFoods().subscribe(
      async (data: any) => {
        console.log(data);
        this.foodData = await data;
        console.log('Food Data', this.foodData);
      },
      (err) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 304) {
            this.router.navigate(['/']);
          }
        }
      }
    );
    let role = this.getDecodedAccessToken(this.token);
    this.jwt_role = role.role;
    let userid = this.getDecodedAccessToken(this.token);
    this.jwt_userid = userid.id;
    console.log('User Id', this.jwt_userid);

    if (this.jwt_role === 'ROLE_ADMIN') {
      this.showButton = true;
    }
    // this.getDecodedAccessToken(this.token);
  }

  get name() {
    return this.addFoodForm.get('name');
  }

  get price() {
    return this.addFoodForm.get('price');
  }

  // userid(){
  //   return this.jwt_userid
  // }

  addFood() {
    this.foodService.addFood(this.addFoodForm.value).subscribe(
      (data) => {
        console.log(data);
      },
      (err) => console.log(err, 'Error')
    );
    let ref = document.getElementById('cancel');
    ref?.click();
    alert(`${this.addFoodForm.value.name} added successfully`);
    this.addFoodForm.reset();

    this.getFood();
  }

  deleteFood(food: Food) {
    if (confirm(`Are you sure you want to delete ${food.name}?`)) {
      this.foodService.deleteFood(food).subscribe((data) => {
        console.log('deleted successfully', data);
      });
      console.log(food, 'Food test');

      alert(`${food.name} deleted successfully!`);
      this.getFood();
    } else {
      console.log('Thing was not saved to the database.');
    }
  }

  updateFood(food: any) {
    console.log('Food from update method', '----->', food);
    return food;
  }

  cart(food: Food) {
    this.foodService.addToCart(food);
  }
}
