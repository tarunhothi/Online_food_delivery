import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HomeService } from './home/home.service';
import { LogoutService } from './logout/logout.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'swiggy';
  constructor(public homeService: HomeService, public logoutService: LogoutService){}

  logout(){
    this.logoutService.logout();
  }
}
