export interface User {
    id?: number;
    firstName: string;
    surname: string;
    email: string;
    phoneNumber: number;
    image: boolean;
    name: string;
    encodedPassword: string;
    roles: string[];
}