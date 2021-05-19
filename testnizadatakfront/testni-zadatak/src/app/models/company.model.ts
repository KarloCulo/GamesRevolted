import {Deserializable} from './deserializable.model';

export class Company implements Deserializable{
  id : number;
  name : string;
  balance: string;

  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }

  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
