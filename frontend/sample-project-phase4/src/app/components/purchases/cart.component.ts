import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { PurchasesService } from 'src/app/services/purchases.service';

import { LoginService } from 'src/app/services/login.service';
import { Dish } from 'src/app/models/dish.model';

@Component({
  selector: 'cart',
    template: `
    	<section class="hero-wrap hero-wrap-2" style="background-image:url('../assets/images/bg_5.jpg');" data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 text-center mb-5">
					<h1 class="mb-2 bread">Registro de pedidos</h1>
					<p class="breadcrumbs"><span class="mr-2"><a href="/index">Principal <i class="fa fa-chevron-right"></i></a></span> <span>Carro <i class="fa fa-chevron-right"></i></span></p>
                    <p>Hey! Echa un vistazo a los pedidos que has realizado hasta el momento.</p>
					<p>Si tu pedido ya está listo, ¡finaliza aquí tu compra!</p>
				</div>
			</div>
		</div>
	</section>
  <div *ngIf="purchase">
  <h2>{{purchase.id}}</h2>
  <h2>Platos:</h2>
  <div *ngFor="let dish of purchase.dishes" class="menus d-flex">
    <div *ngIf="dish.image"><img [src]="dishImage(dish)"style="width:400px;height:300px;"></div> 
      <div class="text">
          <div class="d-flex">
              <div class="one-half">
                  <a [routerLink]="['/dishes', dish.id]"><h3>{{dish.name}}</h3></a>
              </div>
              <div class="one-forth">
                  <span class="price"><a [routerLink]="['/dishes', dish.id]"><h3>{{dish.dishPrice}}.00€</h3></a></span>
              </div>
          </div>
      </div>
    </div>
  <p>
    <button class="btn btn-primary" (click)="gotoPay()">Pagar</button>
  </p>
  </div>
  <div *ngIf="!purchase">
    <br>
    <br>
  <h2>Nada en el Carro</h2>
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

    dishImage(dish: Dish){
      return dish.image? '/api/dishes/'+dish.id+'/image' : '/assets/images/no_image.png';
    }

}
