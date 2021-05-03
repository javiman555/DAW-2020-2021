import {Component, Pipe, PipeTransform} from '@angular/core'
import {DomSanitizer} from '@angular/platform-browser';
import {Router} from '@angular/router'

@Component({
    selector: 'contact',
    templateUrl: './contact.component.html',
    styleUrls: ['./contact.component.css']
})

export class ContactComponent{
    constructor(private router: Router){}

    imgNoExist="assets/images/drink-6.jpg";
}

@Pipe({ name: 'safe' })
export class SafePipe implements PipeTransform {
  constructor(private sanitizer: DomSanitizer) {}
  transform(url) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
} 