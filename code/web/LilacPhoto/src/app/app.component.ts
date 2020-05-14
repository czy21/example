import { Component } from '@angular/core';
import { UserService } from './serviec/UserService/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private user: UserService) { 
    console.log(this.user.userModel);
  }

  title = 'haimatijiaqi';
  // tslint:disable-next-line: use-lifecycle-interface
  ngOnInit() {
    setTimeout(() => {      
      console.log(document.getElementById('body').offsetHeight);
      document.getElementById('page_main').style.height = 
      String(document.getElementById('body').offsetHeight - 50)+'px';
      // console.log(abc);
    }, 0);
  }
}
