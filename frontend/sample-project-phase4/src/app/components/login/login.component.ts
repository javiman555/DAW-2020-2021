import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UsersService } from 'src/app/services/users.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'register',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  imgLogo = "assets/images/logo.png";

  newUser: boolean;
  user: User;

  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    public loginService: LoginService,
    private service: UsersService
    ) {

    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      service.getUser(id).subscribe(
        user => this.user = user,
        error => console.error(error)
      );
      this.newUser = false;
    } else {
      this.user = { firstName: '',surname:'', email:'', phoneNumber:1 , name:'', encodedPassword:'', roles:[], newPurchase: null, purchases:[], image:false};
      this.newUser = true;
    }
  }

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logIn(user, pass);
  }

  logOut() {
    this.loginService.logOut();
  }
  saveUser(event: any, user: string, pass: string,phoneNumber:number,email:string,firstName:string,surname:string) {
    event.preventDefault();

    this.user = {firstName: firstName,surname:surname, email:email, phoneNumber:phoneNumber , name:user, encodedPassword:pass, roles:['USER'], newPurchase: null, purchases:[], image:false}
    this.service.addUser(this.user).subscribe(
      (user: User) => this.router.navigate(['/index']),
      error => alert('Error creating new user: ')
    );
  }
}
