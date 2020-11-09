import {Injectable, Inject} from '@angular/core';
import {LOCAL_STORAGE, StorageService} from 'ngx-webstorage-service';


@Injectable()
export class LocalStorage{
  STORAGE_KEY = 'userName';
  STORAGE_ID = 'userId';
  STORAGE_ROLE = 'userRole'

  username: String;
  id: number;
  userRole : String;

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService){
  }

  public clearUser() {
    this.storage.clear();
  }

  public setUserName(user: String){
    this.storage.set(this.STORAGE_KEY, user);
  }

  public setUserId(id: number){
    this.storage.set(this.STORAGE_ID, id);
  }

  public setUserRole(role: String){
    this.storage.set(this.STORAGE_ROLE, role);
  }

  public getUserRole(){
    return this.storage.get(this.STORAGE_ROLE);
  }

  public getUserName(){
    return this.storage.get(this.STORAGE_KEY);
  }

  public getUserId(){
    return this.storage.get(this.STORAGE_ID);
  }

}
