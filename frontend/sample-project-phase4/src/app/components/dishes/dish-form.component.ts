import { Component, ViewChild } from '@angular/core';
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
  <p>Image: </p>
    <img [src]="dishImage()" style="width:200px;height:150px;"><br>

    <ng-template [ngIf]="dish.id">
        <br><input type='checkbox' name='removeImage' [(ngModel)]="removeImage"> <label>Remove image</label><br>

        <label>Upload image: </label><br>  
    </ng-template>

    <input #file type='file' name='imageFile' accept=".jpg, .jpeg" />
  <p>
    <button (click)="cancel()">Cancel</button>
    <button (click)="save()">Save</button>
  </p>
  </div>
  
  `
})
export class DishFormComponent {

  newDish: boolean;
  dish: Dish;

  @ViewChild("file")
  file: any;

  removeImage:boolean;

  selectedFiles = null;
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
      this.dish = { name: '',nbuy:0,dishPrice:0, category: '',ingredients:[], image:false};
      this.newDish = true;
    }
  }


  cancel() {
    window.history.back();
  }

  save() {
    if(this.dish.image && this.removeImage){
      this.dish.image = false;
    }
    this.service.addDish(this.dish).subscribe(
      (dish: Dish) => this.uploadImage(dish),
      error => alert('Error creating new book: ' + error)
    );
  }
  
  uploadImage(dish: Dish): void {

    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.service.setDishImage(dish, formData).subscribe(
        _ => this.afterUploadImage(dish),
        error => alert('Error uploading book image: ' + error)
      );
    } else if(this.removeImage){
      this.service.deleteDishImage(dish).subscribe(
        _ => this.afterUploadImage(dish),
        error => alert('Error deleting book image: ' + error)
      );
    } else {
      this.afterUploadImage(dish);
    }
  }
  
    private afterUploadImage(dish: Dish){
      this.router.navigate(['/dishes/', dish.id]);
    }
  
    dishImage() {
      return this.dish.image ? '/api/dishes/' + this.dish.id + '/image' : '/assets/images/no_image.png';
    }

}
