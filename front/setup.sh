#pacientes
ng g c patients/new-patient &&
ng g c patients/list-patients &&
ng g c patients/edit-patients &&
ng g c patients/components/patient-form &&
ng g c patients/patient-table &&

touch src/app/patients/patient-models.ts &&

#schedules
ng g c schedules/schedules-month &&
ng g c schedules/components/schedules-calendar &&

touch src/app/schedules/schedule.models.ts &&

#commons components
ng g c commons/components/card-header &&
ng g c commons/components/menu-bar &&
ng g c commons/components/yes-no-dialog &&

#service
ng g s services/dialog-manager &&
ng g s services/snackbar-manager &&
ng g s services/api-patient/patients/patients &&
ng g s services/api-patient/schedules/schedules &&

touch src/app/services/idialog-manager.service.ts &&
touch src/app/services/isnackbar-manager.service.ts &&
touch src/app/services/service.token.ts &&

touch src/app/services/api-patient/patients/ipatients.service.ts &&
touch src/app/services/api-patient/patients/patient.models.ts &&

touch src/app/services/api-patient/schedules/schedules.service.ts &&
touch src/app/services/api-patient/schedules/schedule.models.ts &&

npm install @angular/cdk bootstrap ngx-mask
