import {Token} from "./token.model"

export class TokenEncrypted{
    key: string;
    token:  Token;

    constructor(obj?: any) {
        Object.assign(this, obj);
    }
}