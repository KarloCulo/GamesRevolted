import {Deserializable} from "./deserializable.model";
import {Account} from "./account.model"

export class Token implements Deserializable{
    id : number;
    mark : string;
    value : number;
    beginDate : Date;
    endDate : Date;
    activated : boolean;
    owner: Account;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
      }

    constructor(obj?: any) {
        Object.assign(this, obj);
    }
}