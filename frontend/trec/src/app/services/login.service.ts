import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Router } from '@angular/router';

const BASE_URL_LOGIN = '/api/auth';
const BASE_URL_USER = '/api/users';

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean;
    user: User;

    constructor(private http: HttpClient, private router: Router) {
        this.reqIsLogged();
    }

    reqIsLogged() {

        this.http.get(BASE_URL_USER+'/me', { withCredentials: true }).subscribe(
            response => {
                this.user = response as User;
                this.logged = true;
            },
            error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );

    }

    logIn(user: string, pass: string) {

        this.http.post(BASE_URL_LOGIN + "/login", { username: user, password: pass }, { withCredentials: true })
            .subscribe(
                (response) => this.reqIsLogged(),
                (error) => alert("Wrong credentials")
            );

    }

    logOut() {

        return this.http.post(BASE_URL_LOGIN + '/logout', { withCredentials: true })
            .subscribe((resp: any) => {
                console.log("LOGOUT: Successfully");
                this.logged = false;
                this.user = undefined;
                this.router.navigate(['/index']);
            });

    }

    isLogged() {
        return this.logged;
    }

    isAdmin() {
        return this.user && this.user.roles.indexOf('ADMIN') !== -1 ;
    }

    currentUser() {
        return this.user;
    }
    currentUserId() {
        return this.user.id;
    }

}
