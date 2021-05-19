import {Deserializable} from './deserializable.model';
import {Account} from './account.model';

export class Company implements Deserializable{
  id: number;
  name: string;
  balance: string;
  users: Account[];

  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }

  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
