import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditCardListComponent } from './credit-card-list.component';
import { CardService } from '../service/card-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CreditCard } from '../model/credit-card.model';

describe('CreditCardListComponent', () => {
  let component: CreditCardListComponent;
  let fixture: ComponentFixture<CreditCardListComponent>;
  let testCards: CreditCard[] = [
    {
      id: 1,
      name: 'John',
      cardNumber: 1234567890,
      limits: 345,
      balance: 56.8
    }
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      declarations: [ CreditCardListComponent ],
      providers: [ CardService ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditCardListComponent);
    component = fixture.componentInstance;
    component.creditCards = [
      {
        id: 1,
        name: 'John',
        cardNumber: 1234567890,
        limits: 345,
        balance: 56.8
      }
    ];
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should test the data is rendered in the table', () => {
    expect(component.creditCards).toEqual(testCards);
    fixture.detectChanges();

    fixture.whenStable().then(() => {
      fixture.detectChanges();

      let headerRows = fixture.nativeElement.querySelectorAll('th');
      expect(headerRows.length).toBe(4);

      //Header row
      expect(headerRows[0].innerHTML).toBe('Name');
      expect(headerRows[1].innerHTML).toBe('Card Number');
      expect(headerRows[2].innerHTML).toBe('Limit');
      expect(headerRows[3].innerHTML).toBe('Balance');

      let dataRows = fixture.nativeElement.querySelectorAll('td');
      expect(dataRows.length).toBe(4);
      
      //Data rows
      expect(dataRows[0].innerHTML).toBe('John');
      expect(dataRows[1].innerHTML).toBe('1234567890');
      expect(dataRows[2].innerHTML).toBe('£ 345');
      expect(dataRows[3].innerHTML).toBe('£ 56.8 ');
    });
  });
});
