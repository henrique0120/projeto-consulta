import { AfterViewInit, Component, EventEmitter, Inject, Input, OnChanges, OnDestroy, Output, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { YesNoDialogComponent } from '../../commons/components/yes-no-dialog/yes-no-dialog.component';
import { IDialogManagerService } from '../../services/idialog-manager.service';
import { SERVICES_TOKEN } from '../../services/service.token';
import { PatientModelTable } from '../patient-models';
import { DialogManagerService } from '../../services/dialog-manager.service';
import { CustomPaginator } from './custom-paginator';

@Component({
  selector: 'app-patient-table',
  standalone: false,
  templateUrl: './patient-table.component.html',
  styleUrl: './patient-table.component.css',
  providers: [
    { provide: SERVICES_TOKEN.DIALOG, useClass: DialogManagerService },
    { provide: MatPaginatorIntl, useClass: CustomPaginator }
  ]
})
export class PatientTableComponent implements AfterViewInit, OnChanges, OnDestroy {

  @Input() patients: PatientModelTable[] = []

  dataSource!: MatTableDataSource<PatientModelTable>

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  displayedColumns: string[] = ['name', 'email', 'phone', 'actions']

  private dialogManagerServiceSubscriptions?: Subscription

  @Output() onConfirmDelete = new EventEmitter<PatientModelTable>()

  @Output() onRequestUpdate = new EventEmitter<PatientModelTable>()

  constructor(
    @Inject(SERVICES_TOKEN.DIALOG) private readonly dialogManagerService: IDialogManagerService,
  ) { }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['patients'] && this.patients) {
      this.dataSource = new MatTableDataSource<PatientModelTable>(this.patients)
      if (this.paginator) {
        this.dataSource.paginator = this.paginator
      }
    }
  }
  ngOnDestroy(): void {
    if (this.dialogManagerServiceSubscriptions) {
      this.dialogManagerServiceSubscriptions.unsubscribe()
    }
  }

  formatPhone(phone: string) {
    return `( ${phone.substring(0, 2)} ) ${phone.substring(2, 7)} - ${phone.substring(7)}`
  }

  update(patient: PatientModelTable) {
    this.onRequestUpdate.emit(patient)
  }

  delete(patient: PatientModelTable) {
    this.dialogManagerService.showYesNoDialog(
      YesNoDialogComponent,
      { title: 'Exclusão de paciente', content: `Confirma a exclusão do paciente ${patient.name}` }
    )
      .subscribe(result => {
        if (result) {
          this.onConfirmDelete.emit(patient)
          const updatedList = this.dataSource.data.filter(c => c.id !== patient.id)
          this.dataSource = new MatTableDataSource<PatientModelTable>(updatedList)
        }
      })
  }

}

