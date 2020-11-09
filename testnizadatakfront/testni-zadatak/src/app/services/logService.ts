import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LOG, LOGOUT } from './paths'



@Injectable({
    providedIn: 'root'
  })
  export class LogService {

    constructor(private http:HttpClient){}
    getAllLogs():Observable<any>{
        return this.http.get(LOG)
    }

    logout(userId: number){
      return this.http.post(LOGOUT, userId);
    }
  }