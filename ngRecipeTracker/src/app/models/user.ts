export interface User {
    id?: number;
    username: string;
    email: string;
    password: string;
    enabled?: boolean;
    role?: string;
    firstName?: string;
    lastName?: string;
    dateCreated?: Date;
    dateUpdated?: Date;
    imageURL?: string;
}
