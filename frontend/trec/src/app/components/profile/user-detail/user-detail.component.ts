import { Component, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UsersService } from 'src/app/services/users.service';

@Component({
    template: `
    <div class="row">
      <div class="col-md-4 col-sm-6 col-xs-12">
        <img [src]="userImage()" style="width:200px;height:150px;"><br>
      </div>

      <div *ngIf="user" class="col-md-8 col-sm-6 col-xs-12 personal-info">
        <!-- <form action="/edituser" method="post" enctype="multipart/form-data" class="appointment-form"> -->
          <div class="form-group">
            <label style="color: white" class="col-md control-label">Nombre:</label>
            <div class="col-md-8">
              <input [(ngModel)]="user.firstName" placeholder="Nombre"/>
            </div>
          </div>
          <div class="form-group">
            <label style="color: white" class="col-md control-label">Apellidos:</label>
            <div class="col-md-8">
            <input [(ngModel)]="user.surname" placeholder="Apellidos"/>
            </div>
          </div>
          <div class="form-group">
            <label style="color: white" class="col-md control-label">Correo electrónico:</label>
            <div class="col-md-8">
              <textarea [(ngModel)]="user.email" placeholder="Correo electrónico"></textarea>
            </div>
          </div>
          <div class="form-group">
            <label style="color: white" class="col-md control-label">Teléfono</label>
            <div class="col-md-8">
            <input [(ngModel)]="user.phoneNumber" placeholder="Número de teléfono"/>
            </div>
          </div>

          <ng-template [ngIf]="user.id">
            <br><input type='checkbox' name='removeImage' [(ngModel)]="removeUserImage"> <label style="color: white">Borrar imagen</label><br>
            <label style="color: white">Subir imagen: </label><br>
            <input #file type='file' name='imageFile' accept=".jpg, .jpeg" />
          </ng-template>
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
              <input class="btn btn-primary" type="submit" value="Guardar cambios" (click)="save()"/>
              <input class="btn btn-secondary" type="submit" value="Cancelar cambios" (click)="cancel()"/>
            </div>
          </div>
        <!-- </form> -->
      </div>
    </div>
  
  `,
  selector: "user-detail"
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
        return this.user?.image? '/api/users/'+this.user?.id+'/image' : '/assets/images/no_image.png';
    }
}