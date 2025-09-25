import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewPatientComponent } from './patients/new-patient/new-patient.component';
import { ListPatientsComponent } from './patients/list-patients/list-patients.component';
import { EditPatientsComponent } from './patients/edit-patients/edit-patients.component';
import { PatientTableComponent } from './patients/patient-table/patient-table.component';
import { ScheduleMonthComponent } from './schedules/schedules-month/schedule-month.component';
import { CardHeaderComponent } from './commons/components/card-header/card-header.component';
import { MenuBarComponent } from './commons/components/menu-bar/menu-bar.component';
import { YesNoDialogComponent } from './commons/components/yes-no-dialog/yes-no-dialog.component';
import { HttpClientModule, provideHttpClient, withFetch} from '@angular/common/http';
import { provideNgxMask} from 'ngx-mask';
import { FormsModule } from '@angular/forms';
import { MatInputModule} from '@angular/material/input'
import {MatButtonModule} from '@angular/material/button'
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import {MatDialogModule} from '@angular/material/dialog'
import { MatFormFieldModule } from '@angular/material/form-field';
import { PatientFormComponent } from './patients/components/patient-form/patient-form.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ScheduleCalendarComponent } from './schedules/components/schedules-calendar/schedule-calendar.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { MatSelectModule } from '@angular/material/select';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { HomeComponent } from './pages/home/home.component';
import { NavbarComponent } from './commons/components/navbar/navbar.component';
import { BannerComponent } from './commons/components/banner/banner.component';
import { ContentComponent } from './commons/components/content/content.component';
import { TextComponent } from './commons/components/content/text/text.component';
import { CardComponent } from './commons/components/content/card/card.component';
import {ControlPageComponent} from './pages/control-page/control-page.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component'


@NgModule({
  declarations: [
    AppComponent,
    NewPatientComponent,
    ListPatientsComponent,
    EditPatientsComponent,
    PatientTableComponent,
    ScheduleCalendarComponent,
    CardHeaderComponent,
    MenuBarComponent,
    YesNoDialogComponent,
    PatientFormComponent,
    ScheduleMonthComponent,
    HomeComponent,
    NavbarComponent,
    BannerComponent,
    ContentComponent,
    TextComponent,
    CardComponent,
    ControlPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatMenuModule,
    MatDialogModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatTooltipModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatTimepickerModule,
    MatSelectModule
  ],
  providers: [
    provideClientHydration(withEventReplay()),
    provideHttpClient(withFetch()),
    provideNgxMask({})
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
