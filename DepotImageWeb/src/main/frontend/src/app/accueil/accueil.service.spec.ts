import { TestBed } from '@angular/core/testing';
import { documentservice } from './document.service';

describe('AccueilService', () => {
  let service: documentservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(documentservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
