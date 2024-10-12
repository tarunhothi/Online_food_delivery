import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FoodService } from '../foods/food.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private foodService: FoodService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.profile();
  }

  token = this.authService.getToken();
  userData: any;
  name: any;
  email: any;
  mobileNo: any;

  async profile() {
    if (this.token) {
      this.userData = await this.foodService.getDecodedAccessToken(this.token);
      console.log(this.userData);
      this.name = this.userData.name;
      this.email = this.userData.email;
      this.mobileNo = this.userData.mobileNo;
    } else {
      alert('Please register or login to see the profile!');
      this.router.navigate(['/login']);
    }
  }
}
