import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InformationProfilComponent } from './information-profil.component';

describe('InformationProfilComponent', () => {
  let component: InformationProfilComponent;
  let fixture: ComponentFixture<InformationProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InformationProfilComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InformationProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
