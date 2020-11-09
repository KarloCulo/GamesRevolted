import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-alert-modal',
  templateUrl: './alert.modal.html'
})
export class AlertModal {
  alertMessage = "";

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: string,
    public dialogRef: MatDialogRef<AlertModal>) {
      this.alertMessage = data;
    }

  onNoClick(): void {
    this.dialogRef.close();
    if(this.alertMessage == "Token successfully activated."
    || this.alertMessage == "Token generation request successfully added."){
      window.location.reload();
    }
  }
  
}