import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Dish } from 'src/app/models/dish.model';
import { DishesService } from 'src/app/services/dishes.service';


@Component({
  template: `
  <div *ngIf="dish">
  <h2>Dish "{{dish.name}}"</h2>
  <div *ngIf="dish.id">
    <label>Id: </label>{{dish.id}}
  </div>
  <div>
    <label>Nombre: </label>
    <input [(ngModel)]="dish.name" placeholder="nombre"/>
  </div>
  <div>
    <label>Categoria: </label>
    <input [(ngModel)]="dish.category" placeholder="categoria"/>
  </div>
  <div>
    <label>Precio: </label>
    <textarea [(ngModel)]="dish.dishPrice" placeholder="precio"></textarea>
  </div>

  <p>
    <button (click)="cancel()">Cancel</button>
    <button (click)="save()">Save</button>
  </p>
  </div>`
})
export class DishFormComponent {

  newDish: boolean;
  dish: Dish;

  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    private service: DishesService) {

    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      service.getDish(id).subscribe(
        dish => this.dish = dish,
        error => console.error(error)
      );
      this.newDish = false;
    } else {
      this.dish = { name: '',nbuy:0,dishPrice:0, category: '',ingredients:[]};
      this.newDish = true;
    }
  }
  

  cancel() {
    window.history.back();
  }

  save() {
    this.service.addDish(this.dish).subscribe(
      (dish: Dish) => this.router.navigate(['/dishes/', dish.id]),
      error => alert('Error creating new dish: ' + error)
    );

  }
}
