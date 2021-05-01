import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { LoginService } from 'src/app/services/login.service';
import { PurchasesService } from 'src/app/services/purchases.service';


@Component({
  template: `
  <div *ngIf="loginService.isLogged()">
  <div *ngIf="purchase">
  <div *ngIf="purchase.id">
    <label>Id: </label>{{purchase.id}}
  </div>
  <div>
    <label>Nombre: </label>
    <input [(ngModel)]="purchase.firstName" placeholder="nombre"/>
  </div>
  <div>
    <label>Apellidos: </label>
    <input [(ngModel)]="purchase.surname" placeholder="categoria"/>
  </div>
  <div>
    <label>Telefono: </label>
    <textarea [(ngModel)]="purchase.phoneNumber" placeholder="precio"></textarea>
  </div>

  <p>
    <button (click)="cancel()">Cancel</button>
    <button (click)="save(loginService.currentUser().id)">Save</button>
  </p>
  </div>
  </div>
  `
})
export class PurchaseFormComponent {

  newPurchase: boolean;
  purchase: Purchase;
  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    private service: PurchasesService,
    public loginService: LoginService) {

    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      service.getPurchase(id).subscribe(
        purchase => this.purchase = purchase,
        error => console.error(error)
      );
      this.newPurchase = false;
    } else {
      this.purchase = { firstName: '',
      surname: '',
      address: '',
      postalCode: 0,
      city: '',
      country: '',
      phoneNumber: 0,
      price: 0,
      dishes: []	};
      this.newPurchase = true;
    }
  }

  cancel() {
    window.history.back();
  }

  save(user_id: number | string) {
    this.service.addPurchase(this.purchase,user_id).subscribe(
      (purchase: Purchase) => this.router.navigate(['/purchases/', purchase.id]),
      error => alert('Error creating new dish: ' + error)
    );

  }
}
