import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './models/user.model';
import { UsersService } from './services/users.service';
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
    activatedRoute: ActivatedRoute,
    private usersService: UsersService,
    public loginService: LoginService
  ){  }

  goToProfile(){
    this.router.navigate(['/profile/', this.loginService.currentUserId()])
  }
}
