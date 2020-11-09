import { Component, OnInit } from '@angular/core';
import { TokenRequestService } from 'src/app/services/tokenRequestService';
import { TokenRequest } from 'src/app/models/tokenRequest.model';
import { TokenInfo } from 'src/app/models/tokenInfo.model';
import {LocalStorage} from 'src/app/services/localStorage';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AlertModal } from 'src/app/modals/alert/alert.modal';
import { TokenModal } from 'src/app/modals/token/token.modal';


@Component({
    selector: 'app-tokenRequest',
    templateUrl: './tokenRequest.component.html',
    styleUrls: ['./tokenRequest.component.css']
  })
  export class TokenRequestComponent{
      
    tokenRequestColumns: string[] = ['id', 'userId', 'userName', 'actions'];
    tokenRequests:TokenRequest[];
    alertMessage: string;
    isAdmin: boolean;

    constructor(
      private tokenRequestService: TokenRequestService, private storage: LocalStorage, private dialog:MatDialog
    ){}

    async ngOnInit(){
      await this.tokenRequestService.getAllTokenRequests(this.storage.getUserId()).subscribe(
        res =>{
          this.tokenRequests = res;
        });

     if(this.storage.getUserRole() == "admin"){
      this.isAdmin = true;
     }
     else this.isAdmin = false;
     console.log("tok.req. is admin: " + this.isAdmin);
    }

    requestGeneration(){
      this.tokenRequestService.requestGeneration(this.storage.getUserId()).subscribe((res: boolean) => {
        if(res == true){
          this.alertMessage = "Token generation request successfully added."
          this.openAlertDialog(this.alertMessage);
        }
      },
      (error) => {
        this.alertMessage = "Token generation request failed due:" + error.error.message;
        this.openAlertDialog(this.alertMessage);
      });
    }
    
    openAlertDialog(element: string): void {
      const dialogRef = this.dialog.open(AlertModal,{
        data:element
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        });
      }

    approve(element: TokenRequest){
        var tokenInfo = new TokenInfo();
        tokenInfo.id = 0;
        tokenInfo.ownerId = element.userId;
        tokenInfo.requestId = element.id;
        this.openTokenDialog(tokenInfo);
      }
      
    openTokenDialog(element: TokenInfo): void {
      const dialogRef = this.dialog.open(TokenModal,{
        data:element
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        });
      }
  }