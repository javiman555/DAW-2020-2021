import { Purchase } from "./purchase.model";

export interface User {
    id?: number;
    firstName: string;
    surname: string;
    email:string;
    phoneNumber: number;
    name: string;
    encodedPassword: string;
    roles: string[];
    purchases: Purchase[];
    newPurchase: Purchase;
    image: boolean;
}