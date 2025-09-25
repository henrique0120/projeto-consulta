import { Routes } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { authGuard } from './auth.guard';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full' },
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent}

];
