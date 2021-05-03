import {Component} from '@angular/core'
import {ActivatedRoute, Router} from '@angular/router'
import { User } from 'src/app/models/user.model';
import { LoginService } from 'src/app/services/login.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
    selector: 'profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})

export class ProfileComponent{

    user: User;

    constructor(private router: Router,
        activatedRoute: ActivatedRoute,
        public service: UsersService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getUser(id).subscribe(
            user => this.user = user,
            error => console.error(error)
            );
        }
    }

    goToEdit(){
        document.getElementById("edit").className = "tab-pane fade in active show";
        document.getElementById("linkEdit").className = "nav-link active";

        document.getElementById("dishes").className = "tab-pane fade";
        document.getElementById("linkDishes").className = "nav-link";

        document.getElementById("purchases").className = "tab-pane fade";
        document.getElementById("linkPurchases").className = "nav-link";
    }

    goToDishes(){
        document.getElementById("edit").className = "tab-pane fade";
        document.getElementById("linkEdit").className = "nav-link";

        document.getElementById("dishes").className = "tab-pane fade in active show";
        document.getElementById("linkDishes").className = "nav-link active";

        document.getElementById("purchases").className = "tab-pane fade";
        document.getElementById("linkPurchases").className = "nav-link";
    }

    goToPurchases(){
        document.getElementById("edit").className = "tab-pane fade";
        document.getElementById("linkEdit").className = "nav-link";

        if(!this.loginService.isAdmin()){
            document.getElementById("dishes").className = "tab-pane fade";
            document.getElementById("linkDishes").className = "nav-link";
        }


        document.getElementById("purchases").className = "tab-pane fade in active show";
        document.getElementById("linkPurchases").className = "nav-link active";
    }
}