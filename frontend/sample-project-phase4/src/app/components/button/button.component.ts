
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'button',
  template: `<div class="nav navbar-nav navbar-right">

  <ul class="nav navbar-nav">
      <li>
hola
      </li>
  </ul>

</div>

`
})
export class ButtonComponent {

  constructor(private router: Router) { }

  

  gotoPurchases() {
    this.router.navigate(['/purchases']);
}
gotoUsers() {
    this.router.navigate(['/users']);
}


}