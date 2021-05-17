import { TestBed } from '@angular/core/testing';

import { ImageDetailsService } from './image-details.service';

describe('ServiceService', () => {
  let service: ImageDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
