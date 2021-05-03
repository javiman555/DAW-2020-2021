import { RouterModule } from '@angular/router';

import { CartComponent } from './components/purchases/cart.component';
import { ContactComponent } from './components/contact/contact.component';
import { DishDetailComponent } from './components/dishes/dish-detail.component';
import { DishFormComponent } from './components/dishes/dish-form.component';
import { DishListComponent } from './components/dishes/dish-list.component';
import { ErrorComponent } from './components/error/error.component';
import { IndexComponent } from './components/index/index.component';
import { LoginComponent } from './components/login/login.component';
import { PaydoneComponent } from './components/paydone/paydone.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PurchaseDetailComponent } from './components/purchases/purchase-detail.component';
import { PurchaseFormComponent } from './components/purchases/purchase-form.component';
import { PurchaseListComponent } from './components/purchases/purchase-list.component';
import { TeamComponent } from './components/team/team.component';

const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'cart', component: CartComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'dishes', component: DishListComponent },
    { path: 'dishes/new', component: DishFormComponent },
    { path: 'dishes/:id', component: DishDetailComponent },
    { path: 'dishes/edit/:id', component: DishFormComponent },
    { path: 'error', component: ErrorComponent },
    { path: 'index', component: IndexComponent },
    { path: 'paydone', component: PaydoneComponent },
    { path: 'profile/:id', component: ProfileComponent },
    { path: 'purchases', component: PurchaseListComponent },
    { path: 'purchases/new', component: PurchaseFormComponent },
    { path: 'purchases/:id', component: PurchaseDetailComponent },
    { path: 'register', component: LoginComponent },
    { path: 'team', component: TeamComponent },

    { path: '**', component: ErrorComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
