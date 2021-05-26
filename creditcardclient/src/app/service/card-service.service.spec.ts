import { TestBed, inject } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController
} from "@angular/common/http/testing";
import { HttpClient } from '@angular/common/http';
 
import { CreditCard } from "../model/credit-card.model";
import { CardService } from './card-service.service';

describe('CardService', () => {
  let cardservice: CardService;
  let httpTestingController: HttpTestingController;
  let httpClient: HttpClient;
  let baseUrl = "http://localhost:8080/cards";
  let creditCard: CreditCard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);

    creditCard = {
      id: 2,
      name: "John",
      cardNumber: 1234567890,
      limits: 345,
      balance: 45.67
    };
  });

  beforeEach(inject([CardService], (service: CardService) => {
    cardservice = service;
  }));

  it('should be created', () => {
    expect(cardservice).toBeTruthy();
  });

  it("Test get api call to get list of existing cards", () => {
    let result!: CreditCard[];
    cardservice.findAllCards().subscribe(t => {
      result = t;
    });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: baseUrl
    });

    req.flush([creditCard]);

    expect(result[0]).toEqual(creditCard);
  });

  it("Test post api call to save card data", () => {
    cardservice.saveCard(creditCard).subscribe();

    let req = httpTestingController.expectOne({ method: "POST", url: baseUrl });
    expect(req.request.body).toEqual(creditCard);
  });
});
