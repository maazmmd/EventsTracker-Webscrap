import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { RouterModule, Routes } from "@angular/router";
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { WebScrappingComponent } from './components/web-scrapping/web-scrapping.component';
import { ListExpensesComponent } from './components/list-expenses/list-expenses.component';
import { AddExpenseComponent } from './components/add-expense/add-expense.component';

const routers: Routes = [
  {path: 'webscrap', component: WebScrappingComponent},
  {path: 'events', component: ListExpensesComponent}, //expenses
  {path: 'addevent', component: AddExpenseComponent}, //addexpense
  {path: 'editevent/:id', component: AddExpenseComponent}, //editexpense
  {path: '', redirectTo: '/webscrap', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    WebScrappingComponent,
    ListExpensesComponent,
    AddExpenseComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routers)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
