import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormGroup, FormControl} from '@angular/forms';
import {AccountService} from 'src/app/services/accountService';
import {LocalStorage} from 'src/app/services/localStorage';
import {Account} from 'src/app/models/account.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  errormessage: String;

  constructor(private router: Router, private accountService: AccountService,
              private storage: LocalStorage) {
  }

  ngOnInit() {
  }

  loginUser(loginForm: FormGroup) {
    this.accountService.loginUser(loginForm).subscribe((response: Account) => {
        console.log(response);
        this.storage.setUserId(response.id);
        this.storage.setUserName(response.name);
        this.storage.setUserRole(response.role);
        this.router.navigate(['/home']).then(() => {
          window.location.reload();
        });
        
      },
      (error) => {
        console.log(error);
        this.errormessage = error.error.message;
        console.log(this.errormessage);
      })
  }

}
