import {Component, Inject} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import { Account } from 'src/app/models/account.model';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AccountService} from 'src/app/services/accountService';
import {LocalStorage} from 'src/app/services/localStorage';
import {Router} from '@angular/router';
import {Company} from '../../models/company.model';

@Component({
  selector: 'app-account-modal',
  templateUrl: './account.modal.html'
})
export class AccountModal {
  selectedUserId: number;
  name: string;
  companies: Company[];
  accountForm = new FormGroup({
    id: new FormControl(''),
    name: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
    balance: new FormControl('', Validators.required),
    requestsAvailable: new FormControl('', Validators.required),
    company: new FormControl(Company, Validators.required)
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any, private router: Router, private storage: LocalStorage, private accountService: AccountService,
    public dialogRef: MatDialogRef<AccountModal>) {
    if (data) {
      this.companies = data.companies;
      if (data.account.id) {
        this.accountForm.patchValue({
          id: data.account.id,
          name: data.account.name,
          password: data.account.password,
          role: data.account.role,
          balance: data.account.balance,
          requestsAvailable: data.account.requestsAvailable,
          company: data.account.company
        });
        this.selectedUserId = data.account.id;
      }
    }
      else{
        console.log(" NO DATA");
      }
      console.log(this.accountForm);
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(): void{
    console.log("Save: " + this.accountForm);
    console.log("company", this.accountForm.get('company').value);
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
