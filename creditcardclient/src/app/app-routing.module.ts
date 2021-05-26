import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreditCardFormComponent } from './credit-card-form/credit-card-form.component';
import { CreditCardListComponent } from './credit-card-list/credit-card-list.component';

const routes: Routes = [
  { path: 'listcards', component: CreditCardListComponent },
  { path: 'addcard', component: CreditCardFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
