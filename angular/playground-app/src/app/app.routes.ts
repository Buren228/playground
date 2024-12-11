import {Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {MainpageComponent} from './mainpage/mainpage.component';

export const routes: Routes = [
  {path: 'mainpage', component: MainpageComponent},  // Главная страница
  {path: 'login', component: LoginComponent},  // Страница входа
  {path: 'register', component: RegisterComponent}  // Страница регистрации
];
