// auth.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  loggedIn = false;
  users = [
    { login: 'user1', password: 'pass1', role: 'user' },
    { login: 'admin', password: 'admin', role: 'admin' },
  ];
  currentUser: { login: string; password: string; role: string } | null = null;

  constructor() {}

  logIn(login: string, password: string) {
    const user = this.users.find(
      (user) => user.login === login && user.password === password
    );
    if (user) {
      this.loggedIn = true;
      this.currentUser = user;
    }
  }

  logOut() {
    this.loggedIn = false;
    this.currentUser = null;
  }

  isLogged() {
    return this.loggedIn;
  }

  isAdmin() {
    return this.loggedIn && this.currentUser?.role === 'admin';
  }

  isUser() {
    return this.loggedIn && this.currentUser?.role === 'user';
  }
}
