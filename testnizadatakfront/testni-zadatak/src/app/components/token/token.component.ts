import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/services/tokenService';
import { Token } from 'src/app/models/token.model';
import { TokenInfo } from 'src/app/models/tokenInfo.model';
import {LocalStorage} from 'src/app/services/localStorage';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AlertModal } from 'src/app/modals/alert/alert.modal';
import { TokenModal } from 'src/app/modals/token/token.modal';
import { TokenEncrypted } from 'src/app/models/tokenEncrypted.model';
import * as CryptoJS from "crypto-js";


@Component({
  selector: 'app-token',
  templateUrl: './token.component.html',
  styleUrls: ['./token.component.css']
})
export class TokenComponent implements OnInit{

    tokenColumns: string[] = ['mark', 'value', 'beginDate', 'endDate', 'activated', 'owner', 'actions'];
    tokens: Token[];
    inputMark: string;
    alertMessage: string;
    isAdmin: boolean;

    constructor(
      private tokenService:TokenService, private storage: LocalStorage, private dialog: MatDialog
    ){}

    async ngOnInit(){
     this.tokenService.getAllTokens(this.storage.getUserId()).subscribe(
       res =>{

        //DECRYOTING TOKEN MARK
         this.tokens = res.map((tokenEncrypted: TokenEncrypted) => {
          var token = tokenEncrypted.token;
          var encryptedCipherText = token.mark;
          var parsedBase64Key = CryptoJS.enc.Base64.parse(tokenEncrypted.key);
          var decryptedData = CryptoJS.AES.decrypt(encryptedCipherText, parsedBase64Key, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
        token.mark = decryptedData.toString(CryptoJS.enc.Utf8);
        return token;
        })

       }
     );

      if(this.storage.getUserRole() == "admin"){
          this.isAdmin = true;
      }
      else this.isAdmin = false;
      
    }

    activate(){
      this.tokenService.activateToken(this.inputMark, this.storage.getUserId()).subscribe((res: boolean) => {
          if(res == true){
            this.alertMessage = "Token successfully activated."
            this.openAlertDialog(this.alertMessage);
          }
        },
        (error) => {
          this.alertMessage = "Token activation failed due:" + error.error.message;
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
      
    edit(element: Token){
      var tokenInfo = new TokenInfo();
      tokenInfo.id = element.id;
      tokenInfo.mark = element.mark;
      tokenInfo.activated = element.activated;
      tokenInfo.beginDate = element.beginDate;
      tokenInfo.endDate = element.endDate;
      tokenInfo.value = element.value;
      tokenInfo.ownerId = element.owner.id;
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