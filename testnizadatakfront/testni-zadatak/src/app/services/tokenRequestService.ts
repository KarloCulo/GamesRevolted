import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { from, Observable } from 'rxjs';
import { TOKENREQUEST, GENERATE } from './paths';
import { TokenRequest } from 'src/app/models/tokenRequest.model';



@Injectable({
    providedIn: 'root'
  })
  export class TokenRequestService {

    constructor(private http:HttpClient){}
    getAllTokenRequests(userId: number):Observable<TokenRequest[]>{
        return this.http.get<TokenRequest[]>(TOKENREQUEST + "/" + userId);
    }

    requestGeneration(id: number){
      return this.http.post(GENERATE, id)
    }

  }