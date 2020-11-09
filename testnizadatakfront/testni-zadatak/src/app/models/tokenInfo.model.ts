
export class TokenInfo{
    id : number;
    mark : string;
    value : number;
    beginDate : Date;
    endDate : Date;
    activated : boolean;
    ownerId: number;
    requestId: number;

    constructor(obj?: any) {
        Object.assign(this, obj);
    }
}