import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { PatientsService } from '../../services/api-patient/patients/patients.service';
import { SERVICES_TOKEN } from '../../services/service.token';
import { IPatientService } from '../../services/api-patient/patients/ipatients.service';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { PatientModelForm } from '../patient-models';
import { PatientFormComponent } from '../components/patient-form/patient-form.component';

@Component({
  selector: 'app-edit-patients',
  standalone: false,
  templateUrl: './edit-patients.component.html',
  styleUrl: './edit-patients.component.css',
  providers: [
        {provide: SERVICES_TOKEN.HTTP.PATIENT, useClass: PatientsService},
        {provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService}
      ]
})
export class EditPatientsComponent implements OnInit, OnDestroy{

  private httpsubscriptions?: Subscription[] = []

  patient: PatientModelForm = {id: 0, name:'', email: '', phone: ''}

  constructor(
    @Inject(SERVICES_TOKEN.HTTP.PATIENT) private readonly httpService: IPatientService,
    @Inject(SERVICES_TOKEN.SNACKBAR) private readonly snackBarManager: ISnackbarManagerService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly router: Router
  ){}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if(!id){
      this.snackBarManager.show('Erro ao recuperar informações do paciente')
      this.router.navigate(['patients/list'])
    }
    this.httpsubscriptions?.push(this.httpService.findById(Number(id)).subscribe(data => this.patient = data))
  }

  ngOnDestroy(): void {
   this.httpsubscriptions?.forEach(s => s.unsubscribe())
  }

  onSubmitPatient(value: PatientModelForm){
    const {id, ...request} = value
    if (id) {
      this.httpsubscriptions?.push(this.httpService.update(id, request).subscribe(_ => {
        this.snackBarManager.show('Usuário atualizado com sucesso')
        this.router.navigate(['patients/list'])
      }))
      return
    }

    this.snackBarManager.show('Um erro inesperado aconteceu')
    this.router.navigate(['patients/list'])

  }


}
