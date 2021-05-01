import { Routes, RouterModule } from '@angular/router';
import { DishDetailComponent } from './components/dishes/dish-detail.component';
import { DishFormComponent } from './components/dishes/dish-form.component';
import { DishListComponent } from './components/dishes/dish-list.component';
import { ErrorComponent } from './components/error/error.component';
import { IndexComponent } from './components/index/index.component';
import { LoginComponent } from './components/login/login.component';
import { CartComponent } from './components/purchases/cart.component';
import { PurchaseDetailComponent } from './components/purchases/purchase-detail.component';
import { PurchaseFormComponent } from './components/purchases/purchase-form.component';
import { PurchaseListComponent } from './components/purchases/purchase-list.component';
import { UserDetailComponent } from './components/users/user-detail.component';


const appRoutes = [
    { path: '', component: IndexComponent},
    { path: 'dishes', component: DishListComponent },
    { path: 'dishes/new', component: DishFormComponent },
    { path: 'dishes/:id', component: DishDetailComponent },
    { path: 'dishes/edit/:id', component: DishFormComponent },
    { path: '', redirectTo: 'dishes', pathMatch: 'full' },
    { path: 'users', component: DishListComponent },
    { path: 'purchases', component: PurchaseListComponent },
    { path: 'purchases/new', component: PurchaseFormComponent },
    { path: 'purchases/:id', component: PurchaseDetailComponent },
    { path: 'cart', component: CartComponent },
    { path: 'register', component: LoginComponent},
    { path: 'error', component: ErrorComponent},
    { path: 'index', component: IndexComponent},
    { path: 'users/:id', component: UserDetailComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
