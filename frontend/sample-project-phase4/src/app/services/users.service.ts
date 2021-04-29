import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Dish } from '../models/dish.model';
import { User } from '../models/user.model';
import { Purchase } from '../models/purchase.model';


const BASE_URL = '/api/users/';

@Injectable({ providedIn: 'root' })
export class DishesService {

	constructor(private httpClient: HttpClient) { }

	getUsers(): Observable<User[]> {
		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<User[]>;
	}
	
	getUser(id: number | string): Observable<User> {
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<User>;
	}

	getRecomendedDishes(user_id: number | string): Observable<Dish[]> {
		return this.httpClient.get(BASE_URL+user_id+"/dishes").pipe(
			catchError(error => this.handleError(error))
		) as Observable<Dish[]>;
	}

	getCurrentPurchase(user_id: number | string): Observable<Purchase> {
		return this.httpClient.get(BASE_URL + user_id+"/currentPurchase").pipe(
			catchError(error => this.handleError(error))
		) as Observable<Purchase>;
	}

	newPurchase(user_id: number | string) {

			return this.httpClient.post(BASE_URL+user_id+"/currentPurchase",null)
				.pipe(
					catchError(error => this.handleError(error))
				);

	}
	newPurchaseDone(user_id: number | string, purchase: Purchase) {

		if (!purchase.id) {
			return this.httpClient.post(BASE_URL+user_id+"/currentPurchase",null)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL+user_id+"/currentPurchase", purchase).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

	addDishPurchase(user_id: number | string, dish_id: number | string) {

		return this.httpClient.put(BASE_URL+user_id+"/currentPurchase/dishes/"+dish_id,null).pipe(
			catchError(error => this.handleError(error))
		);
	}

	addUser(user: User) {
		return this.httpClient.post(BASE_URL,user).pipe(
			catchError(error => this.handleError(error))
		);
	}

	updateUser(user: User) {
		return this.httpClient.put(BASE_URL + user.id, user).pipe(
			catchError(error => this.handleError(error))
		);
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}