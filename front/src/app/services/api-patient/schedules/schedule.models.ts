export interface ScheduleAppointmentMonthResponse {
  year: number,
  month: number
  scheduledAppointments: PatientScheduleAppointementResponse[]
}

export interface PatientScheduleAppointementResponse {
  id: number
  day: number
  startAt: Date
  endAt: Date
  patientId: number
  patientName: string
}

export interface SaveScheduleResponse {
  id: number
  startAt: Date
  endAt: Date
  patientId: number
}

export interface SaveScheduleRequest {
  startAt: Date
  endAt: Date
  patientId: number
}
