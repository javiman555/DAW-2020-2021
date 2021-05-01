import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Purchase } from '../models/purchase.model';


const BASE_URL = '/api/purchases/';

@Injectable({ providedIn: 'root' })
export class PurchasesService {

	constructor(private httpClient: HttpClient) { }

	getPurchasesUser(user_id: number | string, page: number): Observable<Purchase[]> {
		return this.httpClient.get(BASE_URL+user_id+'?numPage='+page).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Purchase[]>;
	}

	getPurchasesAdmin(page: number): Observable<Purchase[]> {
		return this.httpClient.get(BASE_URL+'?numPage='+page).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Purchase[]>;
	}

	getPurchase(id: number | string): Observable<Purchase> {
		return this.httpClient.get(BASE_URL+"purchase/" + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Purchase>;
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

	addPurchase(purchase: Purchase,user_id: number | string) {

		if (!purchase.id) {
			return this.httpClient.post('/api/users/'+user_id+'currentPurchase', purchase)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put('/api/users/'+user_id+'currentPurchase', purchase).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}


	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}