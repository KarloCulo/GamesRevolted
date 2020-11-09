import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TOKEN, ACTIVATE, SAVETOKEN } from './paths';
import { Token } from '../models/token.model';
import {FormGroup} from '@angular/forms';
import { TokenEncrypted } from '../models/tokenEncrypted.model';


@Injectable({
    providedIn: 'root'
  })
  export class TokenService {

    constructor(private http:HttpClient){}
    
    getAllTokens(userId: number):Observable<TokenEncrypted[]>{
         return this.http.get<TokenEncrypted[]>(TOKEN + "/" + userId);
          
    }

    activateToken(mark: string, id: number){
      return this.http.post(ACTIVATE, {
        inputMark: mark,
        userId: id
      })
    }

    saveToken(tokenForm: FormGroup){
      return this.http.post(SAVETOKEN, {
        id: tokenForm.get('id').value,
        mark: tokenForm.get('mark').value,
        value: tokenForm.get('value').value,
        beginDate: tokenForm.get('beginDate').value,
        endDate: tokenForm.get('endDate').value,
        activated: tokenForm.get('activated').value,
        ownerId: tokenForm.get('ownerId').value,
        requestId: tokenForm.get('requestId').value
      })
  }
  }