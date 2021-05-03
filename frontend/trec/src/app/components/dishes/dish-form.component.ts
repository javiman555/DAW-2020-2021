import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Dish } from 'src/app/models/dish.model';
import { DishesService } from 'src/app/services/dishes.service';
import { LoginService } from 'src/app/services/login.service';


@Component({
  template: `
  <section class="hero-wrap hero-wrap-2" style="background-image:url('../assets/images/bg_5.jpg');" data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 text-center mb-5">
					<h1 class="mb-2 bread">Añade platos</h1>
				</div>
			</div>
		</div>
	</section>

  <section *ngIf="loginService.isAdmin()" class="ftco-section ftco-no-pt ftco-no-pb" style="background-image: url('https://cdn.hipwallpaper.com/i/54/42/AL9wmh.jpg');text-align: center;">
    <div *ngIf="dish">
    <h2>Plato "{{dish.name}}"</h2>
    <div *ngIf="dish.id">
      <label>Id: </label>{{dish.id}}
    </div>
    <div class="form-group">
			<label class="col-md control-label"style="color:white;">Nombre del plato:</label>
			<div class="col"style="text-align: center;">
        <input [(ngModel)]="dish.name" placeholder="nombre"/>
      </div>
    </div>
    <div class="form-group">
			<label class="col-md control-label"style="color:white;">Categoría:</label>
        <input [(ngModel)]="dish.category" placeholder="categoria"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-md control-label"style="color:white;">Precio:</label>
      <div  class="col"style="text-align: center;">
        <textarea [(ngModel)]="dish.dishPrice" placeholder="precio"></textarea>
      </div>
    </div>
    <p style="color:white;">Imagen: </p>
      <img [src]="dishImage()" style="width:200px;height:150px;"><br>

      <ng-template [ngIf]="dish.id">
          <br><input type='checkbox' name='removeImage' [(ngModel)]="removeDishImage"> <label style="color:white;">Remove image</label><br>

          <label>Subir imagen: </label><br>  
      </ng-template>

      <input #file type='file' name='imageFile' accept=".jpg, .jpeg" />
    <p>
      <button (click)="cancel()">Cancelar</button>
      <button class="btn btn-primary"(click)="save()">Guardar</button>
    </p>
    <br>
  </section>
  `
})
export class DishFormComponent {

  newDish: boolean;
  dish: Dish;

  @ViewChild("file")
  file: any;

  removeDishImage:boolean;

  selectedFiles = null;
  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    public loginService: LoginService,
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
    if(this.dish.image && this.removeDishImage){
      this.dish.image = false;
    }
    this.service.addDish(this.dish).subscribe(
      (dish: Dish) => this.uploadDishImage(dish),
      error => alert('Error creating new book: ' + error)
    );
  }
  
  uploadDishImage(dish: Dish): void {

    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.service.setDishImage(dish, formData).subscribe(
        _ => this.afterUploadImage(dish),
        error => alert('Error uploading book image: ' + error)
      );
    } else if(this.removeDishImage){
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
