import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { PurchasesService } from 'src/app/services/purchases.service';

import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'cart',
    template: `
  <div *ngIf="purchase">
  <h2>{{purchase.id}}</h2>
  <h2>Platos:</h2>
      <li *ngFor="let dish of purchase.dishes">
        <h3>{{dish.name}}</h3>
      </li>
  <p>

    <button (click)="gotoPay()">All Books</button>
  </p>
  </div>
  <div>
  <h2>Nada:</h2>
  </div>
  `
})
export class CartComponent {

    purchase: Purchase;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: PurchasesService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getCurrentPurchase(this.loginService.currentUserId()).subscribe(
            purchase => this.purchase = purchase,
            error => console.error(error)
        );
    }

    gotoPay() {
        this.router.navigate(['purchases/new']);
    }
}
