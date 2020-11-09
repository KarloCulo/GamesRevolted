import { logging } from 'protractor';

export class TokenRequest{
    id : number;
    userId : number;
    userName : string;

    
    constructor(obj?: any) {
        Object.assign(this, obj);
    }
}