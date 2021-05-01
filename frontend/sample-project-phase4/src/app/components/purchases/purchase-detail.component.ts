import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { PurchasesService } from 'src/app/services/purchases.service';

import { LoginService } from 'src/app/services/login.service';

@Component({
    template: `
  <div *ngIf="purchase">
  <h2>factura: "{{purchase.id}}"</h2>
  <h2>nombre: "{{purchase.firstName}}"</h2>
  <h2>apellido: "{{purchase.surname}}"</h2>
  <h2>Platos:</h2>
      <li *ngFor="let dish of purchase.dishes">
        <h3>{{dish.name}}</h3>
      </li>
  <p>

    <button (click)="gotoPurchases()">All Books</button>
  </p>
  </div>`
})
export class PurchaseDetailComponent {

    purchase: Purchase;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: PurchasesService,
        public loginService: LoginService) {

        const id = activatedRoute.snapshot.params['id'];
        service.getPurchase(id).subscribe(
            purchase => this.purchase = purchase,
            error => console.error(error)
        );
    }

    gotoPurchases() {
        this.router.navigate(['/purchases']);
    }
}
