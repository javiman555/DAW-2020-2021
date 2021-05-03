import {Component} from '@angular/core'
import {Router} from '@angular/router'

@Component({
    selector: 'team',
    templateUrl: './team.component.html',
    styleUrls: ['./team.component.css']
})

export class TeamComponent{
    constructor(private router: Router){}
}