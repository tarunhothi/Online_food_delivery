import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../auth.service';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  jwt: any;
  private _isLoggedIn = new BehaviorSubject<boolean>(false);
  isLoggedIn = this._isLoggedIn.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router,
    private homeService: HomeService,
    private authService: AuthService
  ) {
    const token = localStorage.getItem('token');
    this._isLoggedIn.next(!!token);
  }

  ngOnInit(): void {}

  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  login() {
    console.log(this.loginForm.value);
    if (localStorage.getItem('token')) {
      alert('Already logged in! please logout first');
    }
    // this.http.post('http://localhost:8080/api/v1/token', this.loginForm.value).subscribe((data)=>{
    //   this.jwt = data;
    //   localStorage.setItem('token', JSON.stringify(this.jwt.token));
    //   this.router.navigate(['/food']);
    // }, );
    else {
      this.jwt = this.homeService.login(this.loginForm.value).subscribe(
        (data: any) => {
          this.jwt = data;
          this._isLoggedIn.next(true);
          localStorage.setItem('token', JSON.stringify(this.jwt.token));
          console.log(this.jwt);
          alert('Logged in successfully');
          this.router.navigate(['/food']);
        },
        (err) => {
          alert('Wrong credentials!');
          console.log(err.message);
        }
      );
    }
  }

  // (error) => {
  //   if(error instanceof HttpErrorResponse){
  //     console.log(error.error.trace.slice(21, 36));
  //     alert(error.error.trace.slice(21, 36));
  //   }
  // }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
