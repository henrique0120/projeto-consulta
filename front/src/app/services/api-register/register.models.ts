export interface RegisterRequest {
  name: string;
  password: string;
  email: string;
  phone: string;
  roles: string[];
}

export interface RegisterResponse {
  id: number;
  name: string;
  password: string;
  email: string;
  phone: string;
  roles: string[];
}
