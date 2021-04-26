import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NouveauImageComponent } from './nouveau-image.component';

describe('NouveauImageComponent', () => {
  let component: NouveauImageComponent;
  let fixture: ComponentFixture<NouveauImageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NouveauImageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NouveauImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
