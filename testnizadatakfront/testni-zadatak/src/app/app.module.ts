import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginComponent} from './components/login/login.component';
import {LocalStorage} from './services/localStorage';
import {NavbarComponent} from './components/navbar/navbar.comoponent';
import { HomepageComponent } from './components/homepage/homepage.component';
import { AccountComponent } from './components/account/account.component';
import { AccountService } from './services/accountService';
import { AccountModal } from './modals/account/account.modal';
import { AlertModal } from './modals/alert/alert.modal';
import { TokenModal } from './modals/token/token.modal';
import { LogComponent } from './components/log/log.component';
import { LogService } from './services/logService';
import { TokenComponent } from './components/token/token.component';
import { TokenService } from './services/tokenService';
import { TokenRequestComponent } from './components/tokenRequest/tokenRequest.component';
import { TokenRequestService } from './services/tokenRequestService';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MaterialModule } from './material/material.module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    HomepageComponent,
    AccountComponent,
    AccountModal,
    AlertModal,
    TokenModal,
    LogComponent,
    TokenComponent,
    TokenRequestComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTableModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    BrowserAnimationsModule,
    NgbModule,
    MaterialModule,
  ],
  providers: [LocalStorage, LogService, AccountService, TokenService, TokenRequestService, AccountModal, AlertModal, TokenModal],
  entryComponents:[AccountModal, AlertModal, TokenModal],
  bootstrap: [AppComponent]
})
export class AppModule { }
