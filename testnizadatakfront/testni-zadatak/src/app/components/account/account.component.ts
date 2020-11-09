import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/services/accountService';
import { Account } from 'src/app/models/account.model';
import {LocalStorage} from 'src/app/services/localStorage';
import { AccountModal } from 'src/app/modals/account/account.modal';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';



@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit{

    accountColumns: string[] = ['name', 'password', 'role', 'balance', 'requestsAvailable', 'actions'];
    accounts:Account[];
    userRole : String;
    value: Date;
    isAdmin: boolean;

    constructor(
      private accountService:AccountService, private storage: LocalStorage, private dialog:MatDialog
    ){}

    async ngOnInit(){
      await this.accountService.getAllUsers(this.storage.getUserId()).subscribe(
        res =>{
          this.accounts = res;
        }
      );
     if(this.storage.getUserRole() == "admin"){
      this.isAdmin = true;
     }
     else this.isAdmin = false;
     console.log("account is admin: " + this.isAdmin);
    }
    
    createNew(): void{
      this.openDialog(new Account());
    }


    openDialog(element): void{
      const dialogRef = this.dialog.open(AccountModal,{
          data:element
      });

      dialogRef.afterClosed().subscribe(result => {
         console.log('The dialog was closed');
      });
    }
}
