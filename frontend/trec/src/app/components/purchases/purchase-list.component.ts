import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Purchase } from 'src/app/models/purchase.model';
import { PurchasesService } from 'src/app/services/purchases.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  template: `
    <h2>Purchases</h2>
    <div *ngIf="loginService.isLogged()">
    <div *ngIf="purchasesInScreen; else nopurchases">
    <ul   class="items">
      <li *ngFor="let purchase of purchasesInScreen">
        <a style="color: white;" [routerLink]="['/purchases', purchase.id]">Factura {{purchase.id}}</a>
      </li>
    </ul>
    
    <button *ngIf="loginService.isLogged() && increment != 0" style="background-color:#e52b34; color: white" (click)="moreContent()">Cargar más</button>
    </div>
    <ng-template #nopurchases>
      <b style="color: white">No ha realizado ningún pedido.</b>
    </ng-template>
    </div>
  `,
  selector: "purchase-list"
})
export class PurchaseListComponent implements OnInit {

  page = -1;
  index = 0;
  increment: number;
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
    this.increment = 0;
    this.page = this.page + 1;
    if(this.loginService.isAdmin()){
      this.service.getPurchasesAdmin(this.page).subscribe(
        purchases => { purchases.forEach(p => {
          if (p != null){
            this.purchasesInScreen[this.index] = p;
            this.index++;
            this.increment++;
          }
          });
          
          console.log(this.purchasesInScreen);
        },
        error => console.log(error)
      );
    } else {
      console.log(this.page);
      this.service.getPurchasesUser(this.loginService.user.id, this.page).subscribe(
        purchases => { purchases.forEach(p => {
          if (p != null){
            this.purchasesInScreen[this.index] = p;
            this.index++;
            this.increment++;
          }
          });
          this.purchasesLastCall = purchases.length;
          console.log(this.purchasesInScreen);
        },
        error => console.log(error)
      );
    }
  }
}
