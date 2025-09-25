import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service'

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private localStorageService: LocalStorageService) {}

  private readonly TOKEN_KEY = 'token';

   saveToken(token: string): void {
   this.localStorageService.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return this.localStorageService.getItem(this.TOKEN_KEY);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    // Aqui você pode incluir verificação de expiração no futuro
    return !!token;
  }

  logout(): void {
    this.localStorageService.removeItem(this.TOKEN_KEY);
  }

}
