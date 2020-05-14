import { Injectable } from '@angular/core';
import { UserModel } from 'src/app/Model/UserModels';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userModel: UserModel;

  constructor() {
    this.userModel = new UserModel();
    let flag = this.getCookie('user');

    if (flag != "") {
      Object.assign(this.userModel, (JSON.parse(flag) as UserModel));
    } else { 
      this.userModel.UserId = 2;
    }
  }

  // 获取Cookie
  private getCookie(cname :string) : string {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
      while (c.charAt(0) == ' ') {
            c = c.substring(1);
         }
         if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
         }
     }
    return "";
  } 
}
