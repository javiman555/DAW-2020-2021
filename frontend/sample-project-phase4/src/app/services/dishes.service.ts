import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Dish } from '../models/dish.model';


const BASE_URL = '/api/dishes/';

@Injectable({ providedIn: 'root' })
export class DishesService {

	constructor(private httpClient: HttpClient) { }

	getDishes(): Observable<Dish[]> {
		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Dish[]>;
	}

	getDishesByCategory(category: string): Observable<Dish[]> {
		return this.httpClient.get(BASE_URL+"category"+category).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Dish[]>;
	}
	
	getDish(id: number | string): Observable<Dish> {
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Dish>;
	}

	addDish(dish: Dish) {

		if (!dish.id) {
			return this.httpClient.post(BASE_URL, dish)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + dish.id, dish).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

	removeDish(dish: Dish) {
		return this.httpClient.delete(BASE_URL + dish.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

	updateDish(dish: Dish) {
		return this.httpClient.put(BASE_URL + dish.id, dish).pipe(
			catchError(error => this.handleError(error))
		);
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}