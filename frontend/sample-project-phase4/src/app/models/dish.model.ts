import { Ingredient } from "./ingredient.model";

export interface Dish {
	id?: number;
	name: string;
	category: string;
	nbuy: number;
	dishPrice: number;
	ingredients: Ingredient[];
	image: boolean;
}
