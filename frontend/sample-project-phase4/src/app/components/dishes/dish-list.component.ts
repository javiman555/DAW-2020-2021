import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dish } from 'src/app/models/dish.model';
import { DishesService } from 'src/app/services/dishes.service';
import { LoginService } from 'src/app/services/login.service';
import { PurchasesService } from 'src/app/services/purchases.service';
import { DishDetailComponent } from './dish-detail.component';

@Component({
  /* template: `

    <h2>Dishes</h2>
    <ul class="items">
      <h2>Desayuno</h2>
      <li *ngFor="let dish of dishes1">
        <a [routerLink]="['/dishes', dish.id]">{{dish.name}}</a>
        <button *ngIf="loginService.isLogged()" (click)="addDishPurchase(dish.id)">Pedir</button>
      </li>
      <h2>Comida</h2>
      <li *ngFor="let dish of dishes2">
        <a [routerLink]="['/dishes', dish.id]">{{dish.name}}</a>
        <button *ngIf="loginService.isLogged()" (click)="addDishPurchase(dish.id)">Pedir</button>
      </li>
      <h2>Cena</h2>
      <li *ngFor="let dish of dishes3">
        <a [routerLink]="['/dishes', dish.id]">{{dish.name}}</a>
        <button *ngIf="loginService.isLogged()" (click)="addDishPurchase(dish.id)">Pedir</button>
      </li>
    </ul>


    <button *ngIf="loginService.isLogged()" (click)="newDish()">New book</button>
  ` */
  templateUrl: './dish-list.component.html'
})
export class DishListComponent implements OnInit {

  dishes1: Dish[];
  dishes2: Dish[];
  dishes3: Dish[];
  purchase: any;

  constructor(private router: Router,
    private service: DishesService,
    private purchaseService: PurchasesService,
    public loginService: LoginService) { }

  ngOnInit() {

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

  addDishPurchase(dish_id: number){
    this.purchaseService.addDishPurchase(dish_id,this.loginService.currentUser().id).subscribe(
      purchase => this.purchase = purchase,
      error => console.log(error)
    );
  }

  dishImage(dish: Dish){
    return dish.image? '/api/dishes/'+dish.id+'/image' : '/assets/images/no_image.png';
  }

}
