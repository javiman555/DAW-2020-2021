import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Purchase } from '../models/purchase.model';


const BASE_URL = '/api/purchases/';

@Injectable({ providedIn: 'root' })
export class DishesService {

	constructor(private httpClient: HttpClient) { }

	getPurchasesUser(user_id: number | string): Observable<Purchase[]> {
		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Purchase[]>;
	}

	getPurchasesAdmin(): Observable<Purchase[]> {
		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Purchase[]>;
	}

	getPurchasesId(): Observable<number[]> {
		return this.httpClient.get(BASE_URL+"id").pipe(
			catchError(error => this.handleError(error))
		) as Observable<number[]>;
	}

	getPurchasesPrice(): Observable<number[]> {
		return this.httpClient.get(BASE_URL+"price").pipe(
			catchError(error => this.handleError(error))
		) as Observable<number[]>;
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}