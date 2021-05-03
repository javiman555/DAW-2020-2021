import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { PurchasesService } from 'src/app/services/purchases.service';

import { LoginService } from 'src/app/services/login.service';

@Component({
    template: `

<section *ngIf="purchase" class="hero-wrap hero-wrap-2" style="background-image: url('../assets/images/bg_5.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-center">
            <div class="col-md-9 text-center mb-5">
                <h1 class="mb-2 bread">Factura: "{{purchase.id}}"</h1>
            </div>
        </div>
    </div>
</section>

<section style="background-image: url('https://cdn.hipwallpaper.com/i/54/42/AL9wmh.jpg');">
<br>
<div *ngIf="purchase" class="container">
    <div class="row">

            <h3 style="color: white">Nombre:  </h3><h3 style="color: white">  {{purchase.firstName}}</h3>
    </div>
        <div class="row">

            <h3 style="color: white">Apellido:  </h3><h3 style="color: white">  {{purchase.surname}}</h3>
    </div>
        <div class="row">
            <h3 style="color: white">Platos:  </h3>
            <div *ngFor="let dish of purchase.dishes">
                <h3 style="color: white">  {{dish.name}}  /  </h3>
            </div> 
    </div>
</div>
        <div class="col-md-6">
            <p>

                <button class="btn btn-primary" (click)="goToProfile()">Volver a "Perfil"</button>
            </p>
        </div>

</section>

`
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
    goToProfile(){
      this.router.navigate(['/profile/', this.loginService.currentUserId()])
    }
}
