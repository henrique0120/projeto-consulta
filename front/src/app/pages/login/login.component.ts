import { Component } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { LoginService } from '../../services/api-login/login.service';
import { LoginRequest } from '../../services/api-login/login.models';
import { LocalStorageService } from '../../local-storage.service';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  email = ''
  password = ''

  constructor(private loginService: LoginService, private localStorageService: LocalStorageService,
    private AuthService: AuthService, private Router: Router
  ) {}


  onSubmit(form: NgForm) {
    if (form.invalid) return;

    const loginRequest: LoginRequest = {
      email: this.email,
      password: this.password
    };

    this.loginService.checkUser(loginRequest).subscribe({
      next: (response) => {
        console.log('Login com sucesso:', response);
        this.AuthService.saveToken(response.token);
        this.Router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Erro ao fazer login:', err);
      }
    });
  }

}
