import { Component, OnInit } from '@angular/core';
import { CreditCard } from '../model/credit-card.model';
import { CardService } from '../service/card-service.service';

@Component({
  selector: 'app-credit-card-list',
  templateUrl: './credit-card-list.component.html',
  styleUrls: ['./credit-card-list.component.scss']
})
export class CreditCardListComponent implements OnInit {

  creditCards: CreditCard[];

  constructor(private cardService: CardService) {  }

  ngOnInit() {
    // get all the cards from DB on ngInit and store it in the list of credit cards
    this.cardService.findAllCards().subscribe((data: CreditCard[]) => {
      this.creditCards = data;
    });
  }
}