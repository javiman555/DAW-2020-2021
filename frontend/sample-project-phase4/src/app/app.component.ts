import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './models/user.model';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  imgLogo = "assets/images/logo.png";
  currentUser: User;

  constructor(
    private router: Router,
    public loginService: LoginService
  ){}
}
