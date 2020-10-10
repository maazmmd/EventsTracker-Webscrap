import { Component, OnInit } from '@angular/core';
import { Event } from 'src/app/models/event';
import { ExpenseService } from 'src/app/services/expense.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-expense',
  templateUrl: './add-expense.component.html',
  styleUrls: ['./add-expense.component.css']
})
export class AddExpenseComponent implements OnInit {

  event: Event = new Event();

  constructor(private _expenseService: ExpenseService,
              private _router: Router,
              private _activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const isIdPresent = this._activatedRoute.snapshot.paramMap.has('id');
    if (isIdPresent) {
        const id = +this._activatedRoute.snapshot.paramMap.get('id');
        this._expenseService.getExpense(id).subscribe(
          data => this.event = data
        )
    }
  }

  saveExpense() {
    this._expenseService.saveExpense(this.event).subscribe(
      data => {
        console.log('response', data);
        this._router.navigateByUrl("/events");
      }
    )
  }

  deleteExpense(id: number) {
    this._expenseService.deleteExpense(id).subscribe(
      data => {
        console.log('deleted response', data);
        this._router.navigateByUrl('/events');
      }
    )
  }

}
