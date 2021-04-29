import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { LoginComponent } from './components/login/login.component';
import { DishListComponent } from './components/dishes/dish-list.component';
import { DishFormComponent } from './components/dishes/dish-form.component';
import { DishDetailComponent } from './components/dishes/dish-detail.component';

@NgModule({
  declarations: [AppComponent, DishDetailComponent, DishListComponent, DishFormComponent, LoginComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule, routing],
  bootstrap: [AppComponent]
})
export class AppModule { }
