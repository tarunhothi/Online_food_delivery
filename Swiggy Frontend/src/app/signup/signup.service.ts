import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  constructor(private http: HttpClient) {}

  registration(registartionForm: any) {
    return this.http.post(
      'http://localhost:8080/api/v1/registration',
      registartionForm
    );
  }
}
