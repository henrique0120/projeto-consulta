import { Injectable } from '@angular/core';
import { IScheduleService } from './ischedule.service';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { SaveScheduleRequest, SaveScheduleResponse, ScheduleAppointmentMonthResponse } from './schedule.models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService implements IScheduleService {

  private readonly basePath = environment.apiUrl

  constructor(private http: HttpClient) { }

  save(request: SaveScheduleRequest): Observable<SaveScheduleResponse> {
    return this.http.post<SaveScheduleResponse>(`${this.basePath}schedules`, request)
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.basePath}schedules/${id}`)
  }
  listInMonth(year: number, month: number): Observable<ScheduleAppointmentMonthResponse> {
    return this.http.get<ScheduleAppointmentMonthResponse>(`${this.basePath}schedules/${year}/${month}`)
  }

}
