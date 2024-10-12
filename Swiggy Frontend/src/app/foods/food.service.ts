import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Food } from './food.model';
import jwt_decode from 'jwt-decode';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FoodService {
  token = this.authService.getToken();
  jwt_data: any;
  jwt_role: any;
  jwt_user_data: any;
  jwt_userid: any;
  food_name!: any;
  food_price: any;
  foodId!: any;
  private cartId = new BehaviorSubject<String>('');

  loadCartId(cartId: string) {
    this.cartId.next(cartId);
  }

  getCartId(): Observable<String> {
    return this.cartId.asObservable();
  }

  requestHeader = new HttpHeaders({
    Authorization: `Bearer ${this.authService.getToken()}`,
  });

  constructor(
    private authService: AuthService,
    private http: HttpClient,
    private router: Router
  ) {}

  getFoods() {
    return this.http.get('http://localhost:8081/api/v1/getfoods', {
      headers: this.requestHeader,
    });
  }

  addFood(food: Food) {
    console.log('add food called from food service');
    return this.http.post('http://localhost:8081/api/v1/addfood', food, {
      headers: this.requestHeader,
    });
  }

  addToCart(food: any) {
    let userData = this.getDecodedAccessToken(this.token);
    let userId = userData.id;
    this.foodId = food['id'];

    this.http
      .post(
        'http://localhost:8084/api/v1/cart/additem',
        { food_id: this.foodId, user_id: userId },
        { headers: this.requestHeader }
      )
      .subscribe((data: any) => {
        console.log(data, 'Cart Data');
        this.food_name = data['food'].name;
        this.food_price = data['food'].price;
        this.loadCartId(data['id']);
      });
    this.router.navigate(['cart'], {
      state: { food },
    });
    console.log('Food added in cart', food);
  }

  deleteFood(food: any) {
    this.foodId = food['id'];
    return this.http.delete(
      `http://localhost:8081/api/v1/deletefood/${this.foodId}`
    );
  }

  getDecodedAccessToken(token: string): any {
    try {
      this.jwt_data = jwt_decode(token);
      // console.log(this.jwt_data.userData, "User Data");
      this.jwt_user_data = this.jwt_data.userData;
      return this.jwt_user_data;
    } catch (Error) {
      return null;
    }
  }
}
