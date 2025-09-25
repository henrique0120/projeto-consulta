import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { IPatientService } from '../../services/api-patient/patients/ipatients.service';
import { SERVICES_TOKEN } from '../../services/service.token';
import { PatientsService } from '../../services/api-patient/patients/patients.service';
import { PatientModelForm } from '../patient-models';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';


@Component({
  selector: 'app-new-patient',
  standalone: false,
  templateUrl: './new-patient.component.html',
  styleUrl: './new-patient.component.css',
  providers: [
    {provide: SERVICES_TOKEN.HTTP.PATIENT, useClass: PatientsService},
    {provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService}
  ]
})
export class NewPatientComponent implements OnDestroy{
    title = 'Cadastrar paciente';

    private routeSubscription?: Subscription;

    private httpSubscription?: Subscription

    constructor(
      @Inject(SERVICES_TOKEN.HTTP.PATIENT) private readonly httpService: IPatientService,
      @Inject(SERVICES_TOKEN.SNACKBAR) private readonly  snackBarManager: ISnackbarManagerService,
      private readonly router: Router){}


  ngOnDestroy(): void {
    if (this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }

      if(this.httpSubscription){
        this.httpSubscription.unsubscribe()
      }
  }

  onSubmitPatient(value: PatientModelForm){
      const{id, ...request} = value
      this.httpSubscription = this.httpService.save(request).subscribe(_ => {
        this.snackBarManager.show('Usuário cadastrado com sucesso')
        this.router.navigate(['patients/list'])
      })
  }


}
