import { Component } from '@angular/core';
import { CardService } from '../service/card-service.service';
import { CreditCard } from '../model/credit-card.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-credit-card-form',
  templateUrl: './credit-card-form.component.html',
  styleUrls: ['./credit-card-form.component.scss']
})
export class CreditCardFormComponent {
  creditCard: CreditCard;

  constructor(private cardService: CardService, private router: Router) {
      this.creditCard = new CreditCard();
  }

  onAdd() {
    //validate that card number doesnt exceed length limit of 19
    let cardNumber = this.creditCard.cardNumber;
    if( cardNumber.toString().length > 19) {
      alert("Credit Card number exceeds length limit: 19");
      return;

    } else {
      // If card number meets the length requirements, 
      // set the balance of new credit card to 0 and 
      // save the card
      this.creditCard.balance = 0.0;
      this.cardService.saveCard(this.creditCard).subscribe(
      result => {
        alert("Credit Card created successfully.");
        this.refreshCards()
      },
      error => {
        alert("Invalid credit card number. Please try again");
        console.log("Credit card not valid according to Luhn 10.", error.message);
      });
    }
  }

  // routes to the /listcards route and displays all the credit cards
  refreshCards() {
    this.router.navigate(['/listcards']);
  }
}