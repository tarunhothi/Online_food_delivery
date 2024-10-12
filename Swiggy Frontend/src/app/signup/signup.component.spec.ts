import { ComponentFixture, inject, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClient, HttpClientModule, HttpErrorResponse} from '@angular/common/http';

import { SignupComponent } from './signup.component';
import { SignUp } from './signup.modal';
import { SignupService } from './signup.service';
import { Router } from '@angular/router';

describe('SignupService', () => {
  let signupService: SignupService;
  let http: HttpClient;
  let router: Router;
  let httpController: HttpTestingController
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [SignupService]
    });
    signupService = TestBed.inject(SignupService);
    http = TestBed.inject(HttpClient);
    router = TestBed.inject(Router);
    httpController = TestBed.inject(HttpTestingController);
  });

  afterEach(()=>{
    httpController.verify()
  })

  it('should be created', () => {
    expect(signupService).toBeTruthy();
  });

  it('signup api', () => {
    const testData = true;
    const inputData: any = {
      name: 'tarun',
      email: 'tarunhothi683@gmail.com',
      password: 'tarun123',
      mobileNo: 7227073160
    };

    signupService.registration(inputData).subscribe((data)=>{
      expect(data).toEqual(testData);
    })

    const req: any = httpController.expectOne('http://localhost:8080/api/v1/registration');

    expect(req.request.method).toEqual('POST');
    req.flush(testData);
  })

  it('signup api failed', () => {
    const emsg = 'status 500 error';
    const inputData: any = {
      name: 'tarun',
      email: 'tarunhothi683@gmail.com',
      password: 'tarun123',
      mobileNo: 7227073160
    };

    signupService.registration(inputData).subscribe(()=>{
      fail('should have fail with 500 error')
    },(err: HttpErrorResponse)=>{
      expect(err.status).toEqual(500, 'status');
      expect(err.error).toEqual(emsg, 'message');
    })

    const req: any = httpController.expectOne('http://localhost:8080/api/v1/registration');

    expect(req.request.method).toEqual('POST');
    req.flush(emsg, {status: 500, statusText: 'Server Error'});
  })
});

