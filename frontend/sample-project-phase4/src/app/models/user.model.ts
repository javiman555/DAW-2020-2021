import { Purchase } from "./purchase.model";

export interface User {
    id?: number;
    firstName: string;
    surname: string;
    email:string;
    phoneNumber: number;
    name: string;
    roles: string[];
    purchases:Purchase[];
    image: boolean;
}