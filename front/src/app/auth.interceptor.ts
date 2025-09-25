import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  const token = localStorage.getItem('token');

  const isLoginRequest = req.url.includes('/login');

  if (token && !isLoginRequest) {
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    return next(authReq).pipe(
      catchError(err => {
        if (err.status === 401) {
          localStorage.removeItem('token');
          router.navigate(['/login']);
        }
        return throwError(() => err);
      })
    );
  }

  // requisição normal (sem token, como no login)
  return next(req);
};
