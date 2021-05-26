import { ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { CreditCardFormComponent } from './credit-card-form.component';
import { CardService } from '../service/card-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

describe('CreditCardFormComponent', () => {
  let component: CreditCardFormComponent;
  let fixture: ComponentFixture<CreditCardFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ 
        HttpClientTestingModule, 
        RouterTestingModule, 
        FormsModule 
      ],
      declarations: [ CreditCardFormComponent ],
      providers: [ CardService ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditCardFormComponent);
    component = fixture.componentInstance;
    component.creditCard = {
      id: 1,
      name: 'John',
      cardNumber: 1234567890,
      limits: 345,
      balance: 56.8
    },
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render input elements', () => {
    const compiled = fixture.debugElement.nativeElement;
    const nameInput = compiled.querySelector('input[id="name"]');
    const cardNumberInput = compiled.querySelector('input[id="cardnumber"]');
    const limitsInput = compiled.querySelector('input[id="limits"]');

    expect(nameInput).toBeTruthy();
    expect(cardNumberInput).toBeTruthy();
    expect(limitsInput).toBeTruthy();
  });
});
