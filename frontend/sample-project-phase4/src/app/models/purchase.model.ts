import { Dish } from "./dish.model";

export interface Purchase {
	id?: number;
	firstName: string;
	surname: string;
	address: string;
	postalCode: number;
	city: string;
	country: string;
	phoneNumber: number;
	price: number;
	dishes: Dish[];	
}
