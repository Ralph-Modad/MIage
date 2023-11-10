// login.component.ts
import { Component } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [`./login.component.css`],
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  logIn(login: string, password: string) {
    this.authService.logIn(login, password);
    if (this.authService.isLogged()) {
      this.router.navigate(['/home']); // navigate to home page
    }
  }
}
