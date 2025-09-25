import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { IPatientService } from '../../services/api-patient/patients/ipatients.service';
import { SERVICES_TOKEN } from '../../services/service.token';
import { PatientsService } from '../../services/api-patient/patients/patients.service';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { PatientModelTable } from '../patient-models';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';


@Component({
  selector: 'app-list-patients',
  standalone: false,
  templateUrl: './list-patients.component.html',
  styleUrl: './list-patients.component.css',
  providers: [
      {provide: SERVICES_TOKEN.HTTP.PATIENT, useClass: PatientsService},
      { provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService }
    ]
})
export class ListPatientsComponent implements OnInit, OnDestroy {

  title = 'Pacientes cadastrados';

  private httpSubscriptions: Subscription[] = []

  patients: PatientModelTable[] = []

  constructor(
    @Inject(SERVICES_TOKEN.HTTP.PATIENT) private readonly httpService: IPatientService,
    @Inject(SERVICES_TOKEN.SNACKBAR) private readonly snackBarManager: ISnackbarManagerService,
    private readonly router: Router
  ) { }

  ngOnInit(): void {
    this.httpSubscriptions.push(this.httpService.list().subscribe(data => this.patients = data))
  }
  ngOnDestroy(): void {
    this.httpSubscriptions.forEach(s => s.unsubscribe())
  }

  update(patient: PatientModelTable) {
    this.router.navigate(['patients/edit-patient', patient.id])
  }

  delete(patient: PatientModelTable) {
    this.httpSubscriptions.push(
      this.httpService.delete(patient.id).subscribe(_ => this.snackBarManager.show(`O paciente ${patient.name} foi excluido com sucesso`))
    )
  }

}
