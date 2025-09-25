import { InjectionToken } from "@angular/core";
import { IPatientService } from "./api-patient/patients/ipatients.service";
import { ISnackbarManagerService } from "./isnackbar-manager.service";
import { IDialogManagerService } from "./idialog-manager.service";
import { IScheduleService } from "./api-patient/schedules/ischedule.service";

export const SERVICES_TOKEN = {
  HTTP: {
    PATIENT: new InjectionToken<IPatientService>('SERVICES_TOKEN.HTTP.PATIENT'),
    SCHEDULE: new InjectionToken<IScheduleService>('SERVICES_TOKEN.HTTP.SCHEDULE'),
},
SNACKBAR: new InjectionToken<ISnackbarManagerService>('SERVICES_TOKEN.SNACKBAR'),
DIALOG: new InjectionToken<IDialogManagerService>('SERVICES_TOKEN.DIALOG')
}
