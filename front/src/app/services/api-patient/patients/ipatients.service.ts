import { Observable } from 'rxjs'
import {DetailPatientResponse, ListPatientResponse, SavePatientRequest, SavePatientResponse, UpdatePatientResponse} from './patient.models'

export interface IPatientService {
    save(request: SavePatientRequest): Observable<SavePatientResponse>

    update(id: number, request: UpdatePatientResponse): Observable<UpdatePatientResponse>

    delete(id:number): Observable<void>

    list(): Observable<ListPatientResponse[]>

    findById(id: number): Observable<DetailPatientResponse>
}
