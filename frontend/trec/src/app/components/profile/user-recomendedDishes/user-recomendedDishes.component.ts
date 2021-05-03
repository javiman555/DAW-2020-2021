import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Dish } from 'src/app/models/dish.model';
import { DishesService } from 'src/app/services/dishes.service';
import { PurchasesService } from 'src/app/services/purchases.service';
import { UsersService } from 'src/app/services/users.service';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/models/user.model';

@Component({
    templateUrl: "./user-recomendedDishes.component.html",
    selector: "user-recomended"
  })

  export class RecomendedDishListComponent{

    dishes: Dish[];
    user: User;
    purchase: any;
  
    constructor(
         private router:Router, 
         activatedRoute: ActivatedRoute,
         private service: DishesService,
         private purchaseService: PurchasesService,
         private usersService: UsersService,
         public loginService: LoginService) { 
  
     
  
      this.user = this.loginService.currentUser();

      const id = activatedRoute.snapshot.params['id'];
      // console.log(id);
      this.usersService.getRecomendedDishes(id).subscribe(
        dishes => this.dishes = dishes,
        error =>  console.log(error)
      );
    }

    addDishPurchase(dish_id: number){
        this.purchaseService.addDishPurchase(dish_id,this.loginService.currentUser().id).subscribe(
          purchase => this.purchase = purchase,
          error => console.log(error)
        );
    }

    dishImage(dish: Dish){
      return dish?.image? '/api/dishes/'+ dish?.id+'/image' : '/assets/images/no_image.png';
    }

    goToDish(dish_id: number){
      this.router.navigate(['/dishes', dish_id]);
    }
  }