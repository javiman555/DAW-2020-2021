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
    <h2>Dishes</h2>
    <ul class="items">
      <h2>Desayuno</h2>
      <li *ngFor="let dish of dishes1">
        <a [routerLink]="['/dishes', dish.id]">{{dish.name}}</a>
      </li>
      <h2>Comida</h2>
      <li *ngFor="let dish of dishes2">
        <a [routerLink]="['/dishes', dish.id]">{{dish.name}}</a>
      </li>
      <h2>Cena</h2>
      <li *ngFor="let dish of dishes3">
        <a [routerLink]="['/dishes', dish.id]">{{dish.name}}</a>
      </li>
    </ul>
    <button *ngIf="loginService.isLogged()" (click)="newDish()">New book</button>
  `
})
export class DishListComponent implements OnInit {

  dishes: Dish[];
  dishes1: Dish[];
  dishes2: Dish[];
  dishes3: Dish[];


  constructor(private router: Router, private service: DishesService, public loginService: LoginService) { }

  ngOnInit() {
    this.service.getDishes().subscribe(
      dishes => this.dishes = dishes,
      error => console.log(error)
    );
    this.service.getDishesByCategory('Desayuno').subscribe(
      dishes => this.dishes1 = dishes,
      error => console.log(error)
    );
    this.service.getDishesByCategory('Comida').subscribe(
      dishes => this.dishes2 = dishes,
      error => console.log(error)
    );
    this.service.getDishesByCategory('Cena').subscribe(
      dishes => this.dishes3 = dishes,
      error => console.log(error)
    );
  }

  newDish() {
    this.router.navigate(['/dishes/new']);
  }
}
