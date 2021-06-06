import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageMetasComponent } from './image-metas.component';

describe('ImageMetasComponent', () => {
  let component: ImageMetasComponent;
  let fixture: ComponentFixture<ImageMetasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImageMetasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageMetasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
