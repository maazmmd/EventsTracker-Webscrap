import { Component, OnInit } from '@angular/core';
import { Event } from 'src/app/models/event';
import { ExpenseService } from 'src/app/services/expense.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-web-scrapping',
    templateUrl: './web-scrapping.component.html',
    styleUrls: ['./web-scrapping.component.css']
})
export class WebScrappingComponent {

    event: Event = new Event();

    constructor(private _expenseService: ExpenseService,
                private _router: Router,
                private _activatedRoute: ActivatedRoute) { }

    startWebScrapURLs() {
        this._expenseService.startWebScraping().subscribe(
            data => {
                //console.log('response', data);
                this._router.navigateByUrl("/events");
            }
        )
    }
}
