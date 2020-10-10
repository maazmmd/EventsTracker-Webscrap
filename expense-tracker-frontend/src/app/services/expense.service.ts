import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Event } from '../models/event';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  private getUrl: string = "http://localhost:8080/api/v1/events";
  private postWebScrapUrl: string = "http://localhost:8080/api/v1/webscrap";

  constructor(private _httpClient: HttpClient) { }

  getExpenses(): Observable<Event[]> {
    return this._httpClient.get<Event[]>(this.getUrl).pipe(
      map(response => response)
    )
  }

  saveExpense(expense: Event): Observable<Event> {
    return this._httpClient.post<Event>(this.getUrl, expense);
  }

  getExpense(id: number): Observable<Event> {
    return this._httpClient.get<Event>(`${this.getUrl}/${id}`).pipe(
      map(response => response)
    )
  }

  deleteExpense(id: number): Observable<any> {
    return this._httpClient.delete(`${this.getUrl}/${id}`, {responseType: 'text'});
  }

  startWebScraping(): Observable<any> {
    return this._httpClient.post(this.postWebScrapUrl, {responseType: 'json'});
  }
}
