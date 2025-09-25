import { Observable } from "rxjs";
import { LoginRequest, LoginResponse } from "./login.models";

export interface ILoginService{
  checkUser(request: LoginRequest): Observable<LoginResponse>
}
