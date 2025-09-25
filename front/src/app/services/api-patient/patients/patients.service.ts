import { Injectable } from '@angular/core';
import { IPatientService } from './ipatients.service';
import { Observable } from 'rxjs';
import { SavePatientRequest, SavePatientResponse, UpdatePatientResponse, UpdatePatientRequest, ListPatientResponse, DetailPatientResponse } from './patient.models';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PatientsService implements IPatientService {

  private readonly basePath = environment.apiUrl

  constructor(private http: HttpClient) { }

  save(request: SavePatientRequest): Observable<SavePatientResponse> {
    return this.http.post<SavePatientResponse>(`${this.basePath}patients`, request)
  }
  update(id: number, request: UpdatePatientRequest): Observable<UpdatePatientResponse> {
    return this.http.put<UpdatePatientResponse>(`${this.basePath}patients/${id}`, request)
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.basePath}patients/${id}`)
  }
  list(): Observable<ListPatientResponse[]> {
    return this.http.get<ListPatientResponse[]>(`${this.basePath}patients`)
  }
  findById(id: number): Observable<DetailPatientResponse> {
    return this.http.get<DetailPatientResponse>(`${this.basePath}patients/${id}`)
  }
}
