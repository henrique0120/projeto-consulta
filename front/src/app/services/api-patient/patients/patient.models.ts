export interface SavePatientRequest {
  name:string
  email:string
  phone:string
}

export interface UpdatePatientResponse {
  name:string
  email:string
  phone: string
}

  export interface UpdatePatientRequest{
    id:number
    name:string
    email:string
    phone:string
}


export interface SavePatientResponse {
  id:number
  name:string
  email:string
  phone:string
}

export interface ResponsePatientResponse {
  id:number
  name:string
  email:string
  phone:string
}

export interface ListPatientResponse {
  id:number
  name:string
  email:string
  phone:string
}

export interface DetailPatientResponse{
  id:number
  name:string
  email:string
  phone:string
}
