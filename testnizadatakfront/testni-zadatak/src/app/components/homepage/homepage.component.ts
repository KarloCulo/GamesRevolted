import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import {LocalStorage} from 'src/app/services/localStorage';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private http: HttpClient, private storage: LocalStorage) {
  }

  ngOnInit() {
  }

}
