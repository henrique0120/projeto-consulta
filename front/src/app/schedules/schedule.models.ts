export interface ScheduleAppointementMonthModel {
  year: number
  month: number
  scheduledAppointments: PatientScheduleAppointmentModel[]
}

export interface PatientScheduleAppointmentModel {
  id: number
  day: number
  startAt: Date
  endAt: Date
  patientId: number
  patientName: string
}

export interface SaveScheduleModel {
  startAt?: Date
  endAt?: Date
  patientId?: number
}

export interface SelectPatientModel {
  id: number
  name: string
}
