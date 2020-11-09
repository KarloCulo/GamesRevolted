import {Component, Inject} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import { Account } from 'src/app/models/account.model';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AccountService} from 'src/app/services/accountService';
import {LocalStorage} from 'src/app/services/localStorage';
import {Router} from '@angular/router';

@Component({
  selector: 'app-account-modal',
  templateUrl: './account.modal.html'
})
export class AccountModal {
  selectedUserId: number;
  name: string;
  accountForm = new FormGroup({
    id: new FormControl(''),
    name: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
    balance: new FormControl('', Validators.required),
    requestsAvailable: new FormControl('', Validators.required)
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Account, private router: Router, private storage: LocalStorage, private accountService: AccountService,
    public dialogRef: MatDialogRef<AccountModal>) {
      if(data.id){
        this.accountForm.patchValue({
          id: data.id,
          name: data.name,
          password: data.password,
          role: data.role,
          balance: data.balance,
          requestsAvailable: data.requestsAvailable
        });
        this.selectedUserId = data.id;
      }
      else{
        console.log(" NO DATA");
      }
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(): void{
    console.log("Save: " + this.accountForm);
    this.dialogRef.close();
    this.accountService.saveUser(this.accountForm).subscribe();
    if(this.storage.getUserId() == this.selectedUserId){
      this.storage.clearUser();
      this.router.navigate(['/home']).then(() => {
        window.location.reload();
      });
    }
    else{
      window.location.reload();
    }

  }
  
}