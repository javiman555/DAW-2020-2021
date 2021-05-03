import {Component} from '@angular/core'
import {Router} from '@angular/router'

@Component({
    selector: 'paydone',
    templateUrl: './paydone.component.html',
    styleUrls: ['./paydone.component.css']
})

export class PaydoneComponent{
    constructor(private router: Router){}
    imgCompra = "assets/images/compra.jpg";
    goToIndex(){
        this.router.navigate(['/index']);
    }
}