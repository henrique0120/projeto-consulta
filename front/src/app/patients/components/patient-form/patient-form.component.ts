import { Component, Input, Output, EventEmitter, } from '@angular/core';
import { PatientModelForm } from '../../patient-models';
import { SERVICES_TOKEN } from '../../../services/service.token';

import { DialogManagerService } from '../../../services/dialog-manager.service';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-patient-form',
  standalone: false,
  templateUrl: './patient-form.component.html',
  styleUrl: './patient-form.component.css',
  providers: [{provide: SERVICES_TOKEN.DIALOG, useClass: DialogManagerService}]
})
export class PatientFormComponent {

  @Input()
  patient: PatientModelForm = { id: 0, name: '', email: '', phone: '' }

  @Output()
  patientSubmited = new EventEmitter<PatientModelForm>();

  onSubmit(_: NgForm) {
    this.patientSubmited.emit(this.patient)
  }

}
