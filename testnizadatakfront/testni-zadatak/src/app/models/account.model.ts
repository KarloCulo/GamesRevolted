import {Deserializable} from "./deserializable.model";
import {Company} from './company.model';

export class Account implements Deserializable{
    id : number;
    name : string;
    password : string;
    role : string;
    balance : number;
    requestsAvailable : number;
    company: Company;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
      }

    constructor(obj?: any) {
        Object.assign(this, obj);
    }
}
