import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from './signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  msg2: any = {};
  errorMsg: any = {};
  constructor(
    private router: Router,
    private http: HttpClient,
    private signupService: SignupService
  ) {}

  ngOnInit(): void {}

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    mobileNo: new FormControl('', Validators.required),
  });

  registration() {
    this.signupService.registration(this.registrationForm.value).subscribe(
      (data) => {
        this.msg2 = data;
        console.log(data, 'data');
        alert('Registration Sucessful!');
        this.registrationForm.reset();
        this.router.navigate(['/login']);
      },
      (error) => {
        if (error instanceof HttpErrorResponse) {
          this.errorMsg = error;
          console.log(this.errorMsg.error);
          alert(this.errorMsg.error);
        }
      }
    );
    // this.router.navigate(['/welcome'])
  }

  get name() {
    return this.registrationForm.get('name');
  }

  get email() {
    return this.registrationForm.get('email');
  }

  get password() {
    return this.registrationForm.get('password');
  }

  get mobileNo() {
    return this.registrationForm.get('mobileNo');
  }
}
