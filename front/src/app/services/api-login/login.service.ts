import { Injectable } from "@angular/core";
import { ILoginService } from "./ilogin.service";
import { Observable } from "rxjs";
import { LoginRequest, LoginResponse } from "./login.models";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})

export class LoginService implements ILoginService {

  private readonly basePath = environment.apiUrl

  constructor(private http: HttpClient) { }

  checkUser(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.basePath}/login`, request);
  }

}
