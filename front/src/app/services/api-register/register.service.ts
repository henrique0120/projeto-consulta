import { Observable } from "rxjs";
import { IRegisterService } from "./iregister.service";
import { RegisterRequest, RegisterResponse } from "./register.models";
import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class RegisterService implements IRegisterService {

  private readonly basePath = environment.apiUrl

  constructor(private http: HttpClient) { }

  createUser(request: RegisterRequest): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(`${this.basePath}/register`, request);
  }


}
