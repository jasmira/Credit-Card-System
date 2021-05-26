import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CreditCard } from '../model/credit-card.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/cards';
  }

  // HTTP GET call to the GET Rest Api on the backend server.
  // returns a list of CreditCard type objects from the DB
  public findAllCards(): Observable<CreditCard[]> {
    return this.http.get<CreditCard[]>(this.url);
  }

  // HTTP POST call to the POST Rest Api on the backend server.
  // saves the object of type CreditCard in the DB
  public saveCard(creditCard: CreditCard) {
    return this.http.post(this.url, creditCard);
  }
}