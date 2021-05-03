import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { LoginService } from 'src/app/services/login.service';
import { PurchasesService } from 'src/app/services/purchases.service';


@Component({
  template: `

	<section *ngIf="loginService.isLogged()" class="ftco-section ftco-wrap-about ftco-no-pb ftco-no-pt" style="background-image: url('https://cdn.hipwallpaper.com/i/54/42/AL9wmh.jpg');">
		<br>
    <br>
		<div class="container">
    <h1 style="color: aliceblue;text-align: center; "class="mb-2 bread">Paga tu Pedido</h1>
			<form action="/processpay" method="post" enctype="multipart/form-data" class="appointment-form">
				<h2 style="color: aliceblue;"><b>Datos personales:</b></h2>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label for="email">Nombre:</label>
							<input type="text" name="firstName" class="form-control"[(ngModel)]="purchase.firstName" id="name" placeholder="Introduce tu nombre">
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label for="email">Apellidos:</label>
							<input type="text" name="surname" class="form-control"[(ngModel)]="purchase.surname" id="surname" placeholder="Introduce tus apellidos">
						</div>
					</div>
				</div>
				<br>
				<h2 style="color: aliceblue;"><b>Datos de dirección de envío:</b></h2>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label for="email">Dirección:</label>
							<input type="text" name="address"class="form-control"[(ngModel)]="purchase.address" id="direction" placeholder="Introduce tu dirección">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2">
						<div class="form-group">
							<label for="email">Código Postal:</label>
							<input type="text" name="postalCode" class="form-control"[(ngModel)]="purchase.postalCode" id="cp" placeholder="CP">
						</div>
					</div>
					<div class="col-lg-5">
						<div class="form-group">
							<label for="email">Localidad:</label>
							<input type="text" name="city" class="form-control"[(ngModel)]="purchase.city" id="city" placeholder="Localidad">
						</div>
					</div>
					<div class="col-lg-5">
						<div class="form-group">
							<label for="email">País:</label>
							<input type="text" name="country" class="form-control"[(ngModel)]="purchase.country" id="country" placeholder="País">
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<label for="pwd">Número de teléfono:</label>
							<input type="text" name="phoneNumber" class="form-control"[(ngModel)]="purchase.phoneNumber" id="tel" placeholder="Introduce tu número de teléfono">
						</div>
					</div>
						<div class="col-md-12">
							<div class="form-group">
								<input type="submit"(click)="save(loginService.currentUser().id)" value="Pagar" class="btn btn-primary">
							</div>
						</div>
				</div>
			</form>
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
      postalCode: null,
      city: '',
      country: '',
      phoneNumber: null,
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
      (purchase: Purchase) => this.router.navigate(['paydone']),
      error => alert('Error creating new dish: ' + error)
    );

  }
}
