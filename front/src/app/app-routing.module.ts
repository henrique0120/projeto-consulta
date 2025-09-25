import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditPatientsComponent } from './patients/edit-patients/edit-patients.component';
import { NewPatientComponent } from './patients/new-patient/new-patient.component';
import { ListPatientsComponent } from './patients/list-patients/list-patients.component';
import { ScheduleMonthComponent } from './schedules/schedules-month/schedule-month.component';
import { HomeComponent } from './pages/home/home.component';
import { ControlPageComponent } from './pages/control-page/control-page.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // redireciona para login
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'control', component: ControlPageComponent },
  { path: 'patients/edit-patient/:id', component: EditPatientsComponent, data:{title:'Atualizar paciente'} },
  { path: 'patients/new-patient', component: NewPatientComponent, data:{title:'Cadastrar paciente'} },
  { path: 'patients/list', component: ListPatientsComponent, data:{title:'Pacientes cadastrados'} },
  { path: 'schedules/month', component: ScheduleMonthComponent, data:{title:'Agendamentos'} },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
