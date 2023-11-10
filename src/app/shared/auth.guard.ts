import { CanActivateFn } from '@angular/router';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  let authService = inject(AuthService);
  let router = inject(Router);

  if (!authService.loggedIn) {
    console.log('Not logged in');
    router.navigate(['login']);
    return false;
  }

  if (authService.isAdmin()) {
    console.log('You are admin');
    return true;
  } else {
    console.log('You are not admin');
    return true; // Allow non-admin users to access the route
  }
};
