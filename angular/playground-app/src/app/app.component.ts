import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainpageComponent} from './mainpage/mainpage.component';
import {RegisterComponent} from './register/register.component';

import { routes } from './app.routes';

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet, LoginComponent, MainpageComponent, RegisterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'playground-app';
}
