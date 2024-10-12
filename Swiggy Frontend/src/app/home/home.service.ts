import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Model } from './login.model';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  // loginModel!: Model
  jwt: any = {};
  private _isLoggedIn = new BehaviorSubject<boolean>(false);
  isLoggedIn = this._isLoggedIn.asObservable();

  constructor(private http: HttpClient, private router: Router) {
    const token = localStorage.getItem('token');
    this._isLoggedIn.next(!!token);
  }

  requestHeader = new HttpHeaders({
    'No-Auth': 'True',
  });

  login(loginModel: Model) {
    return this.http.post('http://localhost:8080/api/v1/token', loginModel, {
      headers: this.requestHeader,
    });
  }

  token() {
    localStorage.getItem('token');
  }
}
