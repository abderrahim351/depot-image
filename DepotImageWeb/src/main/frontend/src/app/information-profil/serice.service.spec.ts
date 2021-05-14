import { TestBed } from '@angular/core/testing';

import { SericeService } from './serice.service';

describe('SericeService', () => {
  let service: SericeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SericeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
