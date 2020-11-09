import {Component, OnInit} from '@angular/core';
import {LocalStorage} from 'src/app/services/localStorage';
import {Router} from '@angular/router';
import { LogService } from 'src/app/services/logService';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private storage: LocalStorage, private logService:LogService, private router: Router) {
  }

  userId: number;
  userName: string;
  isAdmin: boolean = false;
  isLoggedIn : boolean = false;

  ngOnInit(): void {
    this.userId = this.storage.getUserId();
    this.userName = this.storage.getUserName();
    if (String(this.storage.getUserRole()) == "admin") {
      this.isAdmin = true;
    }
    if (this.userName) {
      this.isLoggedIn = true;
    }

  }

  logout() {
    this.storage.clearUser()
    this.logService.logout(this.storage.getUserId()).subscribe();
  }

}
