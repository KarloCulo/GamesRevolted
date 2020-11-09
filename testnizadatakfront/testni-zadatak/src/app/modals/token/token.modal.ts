import {Component, Inject} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import { TokenInfo } from 'src/app/models/tokenInfo.model';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {TokenService} from 'src/app/services/tokenService';
import {LocalStorage} from 'src/app/services/localStorage';
import {Router} from '@angular/router';

@Component({
  selector: 'app-token-modal',
  templateUrl: './token.modal.html'
})
export class TokenModal {
  selectedUserId: number;
  tokenForm = new FormGroup({
    id: new FormControl(''),
    mark: new FormControl(''),
    value: new FormControl('', Validators.required),
    beginDate: new FormControl(''),
    endDate: new FormControl('', Validators.required),
    activated: new FormControl(''),
    ownerId: new FormControl(''),
    requestId: new FormControl('')
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: TokenInfo, private router: Router, private storage: LocalStorage, private tokenService: TokenService,
    public dialogRef: MatDialogRef<TokenModal>) {
      if(data.ownerId){
        this.tokenForm.patchValue({
          id: data.id,
          mark: data.mark,
          value: data.value,
          beginDate: data.beginDate,
          endDate: data.endDate,
          activated: data.activated,
          ownerId: data.ownerId,
          requestId: data.requestId
        });
      }
      else{
        console.log(" NO DATA");
      }
      console.log(this.tokenForm);
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(): void{
    this.dialogRef.close();
    this.tokenService.saveToken(this.tokenForm).subscribe();
    window.location.reload();
    }
  
}