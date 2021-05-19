import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormGroup} from '@angular/forms';
import { Observable } from 'rxjs';
import {ACCOUNT, COMPANY, LOGIN, SAVEACCOUNT} from './paths';
import { Account } from '../models/account.model';
import {Company} from '../models/company.model';



@Injectable({
    providedIn: 'root'
  })
  export class AccountService {

  constructor(private http: HttpClient) {
  }

  getAllUsers(userId: number): Observable<Account[]> {
    return this.http.get<Account[]>(ACCOUNT + "/" + userId);
  }

  loginUser(loginForm: FormGroup) {
    return this.http.post(LOGIN, {
      username: loginForm.get('username').value,
      password: loginForm.get('password').value
    })
  }

  saveUser(accountForm: FormGroup) {
    console.log("service: " + accountForm.get('id').value + " name: " + accountForm.get('name').value);
    return this.http.post(SAVEACCOUNT, {
      id: accountForm.get('id').value,
      name: accountForm.get('name').value,
      password: accountForm.get('password').value,
      role: accountForm.get('role').value,
      balance: accountForm.get('balance').value,
      requestsAvailable: accountForm.get('requestsAvailable').value
    })
  }

  getAllCompanys(): Observable<Company[]> {
    return this.http.get<Company[]>(COMPANY);
  }
}
