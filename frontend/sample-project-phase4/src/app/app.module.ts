import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { routing } from './app.routing';

import { DishDetailComponent } from './components/dishes/dish-detail.component';
import { DishFormComponent } from './components/dishes/dish-form.component';
import { DishListComponent } from './components/dishes/dish-list.component';
import { LoginComponent } from './components/login/login.component';
import { PurchaseDetailComponent } from './components/purchases/purchase-detail.component';
import { PurchaseFormComponent } from './components/purchases/purchase-form.component';
import { PurchaseListComponent } from './components/purchases/purchase-list.component';
import { CartComponent } from './components/purchases/cart.component';

@NgModule({
  declarations: [
    AppComponent,
    DishDetailComponent,
    DishFormComponent,
    DishListComponent,
    PurchaseDetailComponent,
    PurchaseFormComponent,
    PurchaseListComponent,
    CartComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routing],
  bootstrap: [AppComponent]
})
export class AppModule { }
