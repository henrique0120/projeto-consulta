import { Component } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { RegisterRequest } from '../../services/api-register/register.models';
import { RegisterService } from '../../services/api-register/register.service';

@Component({
  selector: 'app-register',
  imports: [MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  phone = '';
  roles: string[] = ['USER'];

  constructor(private registerService: RegisterService, private router: Router) {}

  onSubmit(form: NgForm) {
    if (form.invalid) return;

    const registerRequest: RegisterRequest = {
      email: this.email,
      password: this.password,
      name: this.name,
      phone: this.phone,
      roles: this.roles
    };

    this.registerService.createUser(registerRequest).subscribe({
      next: (response) => {
        console.log('Usuário criado ', response);
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Erro ao registrar usuário:', err);
      }
    });
  }

  // ✅ método para voltar à tela de login
  goToLogin() {
    this.router.navigate(['/login']);
  }
}
