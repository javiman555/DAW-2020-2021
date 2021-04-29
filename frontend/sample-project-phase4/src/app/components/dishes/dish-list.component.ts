import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dish } from 'src/app/models/dish.model';
import { DishesService } from 'src/app/services/dishes.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  template: `
    <h2>Dishes</h2>
    <ul class="items">
      <li *ngFor="let dish of dishes">
        <a [routerLink]="['/dishes', dish.id]">{{dish.name}}</a>
      </li>
    </ul>
    <button *ngIf="loginService.isLogged()" (click)="newDish()">New book</button>
  `
})
export class DishListComponent implements OnInit {

  dishes: Dish[];

  constructor(private router: Router, private service: DishesService, public loginService: LoginService) { }

  ngOnInit() {
    this.service.getDishes().subscribe(
      dishes => this.dishes = dishes,
      error => console.log(error)
    );
  }

  newDish() {
    this.router.navigate(['/dishes/new']);
  }
}
