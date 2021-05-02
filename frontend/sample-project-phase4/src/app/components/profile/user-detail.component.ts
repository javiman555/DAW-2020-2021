import { Component, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UsersService } from 'src/app/services/users.service';

@Component({
    template: `
  <div *ngIf="user">
  <h2>Dish "{{user.name}}"</h2>
  <div *ngIf="user.id">
    <label>Id: </label>{{user.id}}
  </div>
  <div>
    <label>Nombre: </label>
    <input [(ngModel)]="user.firstName" placeholder="Nombre"/>
  </div>
  <div>
    <label>Apellido: </label>
    <input [(ngModel)]="user.surname" placeholder="Apellidos"/>
  </div>
  <div>
    <label>Correo electrónico: </label>
    <textarea [(ngModel)]="user.email" placeholder="Correo electrónico"></textarea>
  </div>
  <div>
    <label>Teléfono: </label>
    <input [(ngModel)]="user.phoneNumber" placeholder="Número de teléfono"/>
  </div>
  <p>Imagen: </p>
    <img [src]="userImage()" style="width:200px;height:150px;"><br>

    <ng-template [ngIf]="user.id">
        <br><input type='checkbox' name='removeImage' [(ngModel)]="removeUserImage"> <label>Borrar imagen</label><br>

        <label>Subir imagen: </label><br>  
    </ng-template>

    <input #file type='file' name='imageFile' accept=".jpg, .jpeg" />
  <p>
    <button (click)="cancel()">Cancelar</button>
    <button (click)="save()">Guardar</button>
  </p>
  </div>
  
  `
})
export class UserDetailComponent {

    newUser: boolean;
    user: User;

    @ViewChild("file")
    file: any;

    removeUserImage:boolean;

    selectedFiles = null;
    constructor(
        private router: Router, 
        activatedRoute: ActivatedRoute, 
        public service: UsersService) {

        const id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getUser(id).subscribe(
            user => this.user = user,
            error => console.error(error)
        );
        this.newUser = false;
        } else {
            this.user = { firstName: '',surname:'', email:'', phoneNumber:1 , name:'', encodedPassword:'', roles:[], newPurchase: null, purchases:[], image:false};
            this.newUser = true;
        }
    }

    cancel() {
        window.history.back();
      }
    
    save() {
        if(this.user.image && this.removeUserImage){
          this.user.image = false;
        }
        this.service.updateUser(this.user).subscribe(
          (user: User) => this.uploadUserImage(user),
          error => alert('Error updating user: ' + error)
        );
    }

    uploadUserImage(user: User): void {

        const image = this.file.nativeElement.files[0];
        if (image) {
          let formData = new FormData();
          formData.append("imageFile", image);
          this.service.setUserImage(user, formData).subscribe(
            _ => this.afterUploadImage(user),
            error => alert('Error uploading user image: ' + error)
          );
        } else if(this.removeUserImage){
          this.service.deleteUserImage(user).subscribe(
            _ => this.afterUploadImage(user),
            error => alert('Error deleting user image: ' + error)
          );
        } else {
          this.afterUploadImage(user);
        }
    }

    private afterUploadImage(user: User){
        this.router.navigate(['/dishes/']);
      }

    userImage(){
        return this.user.image? '/api/users/'+this.user.id+'/image' : '/assets/images/no_image.png';
    }
}