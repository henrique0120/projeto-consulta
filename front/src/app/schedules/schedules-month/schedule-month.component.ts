import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { PatientsService } from '../../services/api-patient/patients/patients.service';
import { Subscription } from 'rxjs';
import { IPatientService } from '../../services/api-patient/patients/ipatients.service';
import { IScheduleService } from '../../services/api-patient/schedules/ischedule.service';
import { SaveScheduleRequest } from '../../services/api-patient/schedules/schedule.models';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';
import { SERVICES_TOKEN } from '../../services/service.token';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { ScheduleAppointementMonthModel, SelectPatientModel, PatientScheduleAppointmentModel, SaveScheduleModel } from '../schedule.models';
import { ScheduleService } from '../../services/api-patient/schedules/schedule.service';

@Component({
  selector: 'app-schedules-month',
  standalone: false,
  templateUrl: './schedule-month.component.html',
  styleUrl: './schedule-month.component.css',
  providers: [
    { provide: SERVICES_TOKEN.HTTP.SCHEDULE, useClass: ScheduleService },
    { provide: SERVICES_TOKEN.HTTP.PATIENT, useClass: PatientsService },
    { provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService }
  ]
})
export class ScheduleMonthComponent implements OnInit, OnDestroy {

  title = 'Agendamentos';

  private subscriptions: Subscription[] = []
  private selectedDate?: Date

  monthSchedule!: ScheduleAppointementMonthModel
  patients: SelectPatientModel[] = []

  constructor(
    @Inject(SERVICES_TOKEN.HTTP.SCHEDULE) private readonly httpService: IScheduleService,
    @Inject(SERVICES_TOKEN.HTTP.PATIENT) private readonly patientHttpService: IPatientService,
    @Inject(SERVICES_TOKEN.SNACKBAR) private readonly snackbarManage: ISnackbarManagerService
  ) { }

  ngOnInit(): void {
    this.fetchSchedules(new Date());
    this.subscriptions.push(this.patientHttpService.list().subscribe(data => this.patients = data))
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe())
  }

  onDateChange(date: Date) {
    this.selectedDate = date
    this.fetchSchedules(date)
  }

  onConfirmDelete(schedule: PatientScheduleAppointmentModel) {
    this.subscriptions.push(this.httpService.delete(schedule.id).subscribe())
  }

  onSchedulePatient(schedule: SaveScheduleModel) {
    if (schedule.startAt && schedule.endAt && schedule.patientId) {
      const request: SaveScheduleRequest = { startAt: schedule.startAt, endAt: schedule.endAt, patientId: schedule.patientId }
      this.subscriptions.push(this.httpService.save(request).subscribe(() => {
        this.snackbarManage.show('Agendamento realizado com sucesso')
        if (this.selectedDate) {
          this.fetchSchedules(this.selectedDate)
        }
      }))
    }
  }

  private fetchSchedules(currentDate: Date) {
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth() + 1;
    this.subscriptions.push(this.httpService.listInMonth(year, month).subscribe(data => this.monthSchedule = data));
  }

}
