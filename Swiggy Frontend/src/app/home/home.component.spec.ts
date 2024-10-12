import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../auth.service';

import { HomeComponent } from './home.component';
import { HomeService } from './home.service';

describe('HomeService', () => {
  let homeService: HomeService;
  let authService: AuthService;
  let http: HttpClient;
  let router: Router;
  let httpController: HttpTestingController
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [HomeService, AuthService]
    });
    homeService = TestBed.inject(HomeService);
    authService = TestBed.inject(AuthService);
    http = TestBed.inject(HttpClient);
    router = TestBed.inject(Router);
    httpController = TestBed.inject(HttpTestingController);
  });

  afterEach(()=>{
    httpController.verify()
  })

  it('should be created', () => {
    expect(homeService).toBeTruthy();
  });

  it('login api', () => {
    const testData = true;
    const inputData: any = {
      email: 'tarunhothi683@gmail.com',
      password: 'tarun123'
    };

    homeService.login(inputData).subscribe((data)=>{
      expect(data).toEqual(testData);
    })

    const req: any = httpController.expectOne('http://localhost:8080/api/v1/token');

    expect(req.request.method).toEqual('POST');
    req.flush(testData);
  })

  it('login api failed', () => {
    const emsg = 'status 500 error';
    const inputData: any = {
      email: 'tarunhothi683@gmail.com',
      password: 'tarun123'
    };

    homeService.login(inputData).subscribe(()=>{
      fail('should have fail with 500 error')
    },(err: HttpErrorResponse)=>{
      expect(err.status).toEqual(500, 'status');
      expect(err.error).toEqual(emsg, 'message');
    })

    const req: any = httpController.expectOne('http://localhost:8080/api/v1/token');

    expect(req.request.method).toEqual('POST');
    req.flush(emsg, {status: 500, statusText: 'Server Error'});
  })
});