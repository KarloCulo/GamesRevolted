import { Component, OnInit } from '@angular/core';
import { LogService } from 'src/app/services/logService';



@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogComponent implements OnInit{

    logColumns: string[] = ['userName', 'description', 'time'];
    logs:any[];
    constructor(
      private logService:LogService,
    ){}

    ngOnInit(){
      this.logService.getAllLogs().subscribe(
        res =>{
          this.logs = res;
        }
      )
    }
}