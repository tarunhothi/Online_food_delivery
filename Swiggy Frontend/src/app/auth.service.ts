import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  loggedIn(){ 
    return !!localStorage.getItem('token');
  }

 getToken(){
      return JSON.parse(localStorage.getItem('token')|| 'null' || '{}');
      // return localStorage.getItem('token') || 'null' || '{}';
    
  }
}
