import { inject } from '@angular/core';
import { CanActivateFn,Router } from '@angular/router';
export const authGuard:CanActivateFn=()=>localStorage.getItem('sc360-token')?true:inject(Router).parseUrl('/login');
