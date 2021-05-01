import { Routes, RouterModule } from '@angular/router';
import { DishDetailComponent } from './components/dishes/dish-detail.component';
import { DishFormComponent } from './components/dishes/dish-form.component';
import { DishListComponent } from './components/dishes/dish-list.component';
import { IndexComponent } from './components/index/index.component';


const appRoutes = [
    { path: '', component: IndexComponent},
    { path: 'dishes', component: DishListComponent },
    { path: 'dishes/new', component: DishFormComponent },
    { path: 'dishes/:id', component: DishDetailComponent },
    { path: 'dishes/edit/:id', component: DishFormComponent }
]

export const routing = RouterModule.forRoot(appRoutes);