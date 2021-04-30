import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Dish } from 'src/app/models/dish.model';
import { DishesService } from 'src/app/services/dishes.service';

import { LoginService } from 'src/app/services/login.service';

@Component({
    template: `
  <div *ngIf="dish">
  <h2>dish "{{dish.name}}"</h2>
  <h2>categoria: "{{dish.category}}"</h2>
  <h2>precio: "{{dish.dishPrice}}"</h2>
  <h2>Ingredientes:</h2>
      <li *ngFor="let ingredient of dish.ingredients">
        <h3>{{ingredient.name}}</h3>
      </li>
  <p>
    <button *ngIf="loginService.isLogged() && loginService.isAdmin()" (click)="removeDish()">Remove</button>
    <button *ngIf="loginService.isLogged() && loginService.isAdmin()" (click)="editDish()">Edit</button>
    <br>
    <button (click)="gotoDishes()">All Books</button>
  </p>
  </div>`
})
export class DishDetailComponent {

    dish: Dish;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: DishesService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getDish(id).subscribe(
            dish => this.dish = dish,
            error => console.error(error)
        );
    }

    removeDish() {
        const okResponse = window.confirm('Do you want to remove this dish?');
        if (okResponse) {
            this.service.removeDish(this.dish).subscribe(
                _ => this.router.navigate(['/dishes']),
                error => console.error(error)
            );
        }
    }

    editDish() {
        this.router.navigate(['/dishes/edit', this.dish.id]);
    }

    gotoDishes() {
        this.router.navigate(['/dishes']);
    }
}
