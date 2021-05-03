import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { PurchasesService } from 'src/app/services/purchases.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  template: `
    <h2>Purchases</h2>
    <ul *ngIf="loginService.isLogged()" class="items">
      <li *ngFor="let purchase of purchasesInScreen">
        <a style="color: white;" [routerLink]="['/purchases', purchase.id]">Factura {{purchase.id}}</a>
      </li>
    </ul>
    <button *ngIf="loginService.isLogged() && purchasesLastCall != 0" style="background-color:#e52b34; color: white" (click)="moreContent()">Cargar más</button>
    <!-- <button *ngIf="loginService.isLogged()" (click)="newPurchase()">New book</button> -->
  `,
  selector: "purchase-list"
})
export class PurchaseListComponent implements OnInit {

  page = -1;
  index = 0;
  purchasesInScreen: Purchase[];
  purchasesLastCall: number;


  constructor(private router: Router,
    private service: PurchasesService,
    public loginService: LoginService) { }

  ngOnInit() {
    this.purchasesInScreen = [];
    this.moreContent();
  }

  newPurchase() {
    this.router.navigate(['/purchases/new']);
  }

  moreContent(){
    this.page = this.page + 1;
    if(this.loginService.isAdmin()){
      this.service.getPurchasesAdmin(this.page).subscribe(
        purchases => { purchases.content.forEach(p => {
            this.purchasesInScreen[this.index] = p;
            this.index++;
          });
          this.purchasesLastCall = purchases.content.length;
          console.log(this.purchasesInScreen);
        },
        error => console.log(error)
      );
    } else {
      console.log(this.page);
      this.service.getPurchasesUser(this.loginService.user.id, this.page).subscribe(
        purchases => { purchases.content.forEach(p => {
            this.purchasesInScreen[this.index] = p;
            this.index++;
          });
          this.purchasesLastCall = purchases.content.length;
          console.log(this.purchasesInScreen);
        },
        error => console.log(error)
      );
    }
  }
}
