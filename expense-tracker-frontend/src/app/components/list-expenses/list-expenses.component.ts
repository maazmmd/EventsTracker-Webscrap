import { Component, OnInit } from '@angular/core';
import { Event } from 'src/app/models/event';
import { ExpenseService } from 'src/app/services/expense.service';


@Component({
  selector: 'app-list-expenses',
  templateUrl: './list-expenses.component.html',
  styleUrls: ['./list-expenses.component.css']
})
export class ListExpensesComponent implements OnInit {

  events: Event[] = [];

  filters = {
    keyword: '',
    sortBy: 'Name'
  }

  constructor(private _expenseService: ExpenseService) { }

  ngOnInit(): void {
    this.listExpenses();
  }

  deleteExpense(id: number) {
    this._expenseService.deleteExpense(id).subscribe(
      data => {
        console.log('deleted response', data);
        this.listExpenses();
      }
    )
  }

  listExpenses() {
    this._expenseService.getExpenses().subscribe(
      data => this.events = this.filterExpenses(data)
    )
  }

  filterExpenses(expenses: Event[]) {
    return expenses.filter((e) => {
      return e.event.toLowerCase().includes(this.filters.keyword.toLowerCase());
    }).sort((a, b) => {
      if (this.filters.sortBy === 'Name') {
        return a.event.toLowerCase() < b.event.toLowerCase() ? -1: 1;
      }else if(this.filters.sortBy === 'Date') {
        return new Date(a.startDate) < new Date(b.endDate) ? -1: 1;
      }else if(this.filters.sortBy === 'Source') {
        return a.origin.toLowerCase() > b.origin.toLowerCase() ? -1: 1;
      }
    })
  }

    goToLink(website: string) {
      window.open(website, "_blank");
    }
}
