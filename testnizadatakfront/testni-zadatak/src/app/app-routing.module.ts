import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AccountComponent } from './components/account/account.component';
import { LogComponent } from './components/log/log.component';
import { TokenComponent } from './components/token/token.component';
import { TokenRequestComponent } from './components/tokenRequest/tokenRequest.component';
import { HomepageComponent } from './components/homepage/homepage.component';



const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomepageComponent},
  {path:'account',component:AccountComponent},
  {path:'log',component:LogComponent},
  {path:'token',component:TokenComponent},
  {path:'tokenRequest',component:TokenRequestComponent},
  {path: '**', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
