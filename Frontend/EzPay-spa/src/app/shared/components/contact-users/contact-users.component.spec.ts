import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactUsersComponent } from './contact-users.component';

describe('ContactUsersComponent', () => {
  let component: ContactUsersComponent;
  let fixture: ComponentFixture<ContactUsersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactUsersComponent]
    });
    fixture = TestBed.createComponent(ContactUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
