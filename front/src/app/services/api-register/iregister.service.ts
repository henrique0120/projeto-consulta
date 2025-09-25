import { Observable } from "rxjs";
import { RegisterRequest, RegisterResponse } from "./register.models";

export interface IRegisterService{
  createUser(request: RegisterRequest): Observable<RegisterResponse>
}
