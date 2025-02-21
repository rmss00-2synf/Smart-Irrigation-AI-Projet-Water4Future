import { TestBed } from '@angular/core/testing';

import { SensorSimulatorService } from './sensor-simulator.service';

describe('SensorSimulatorService', () => {
  let service: SensorSimulatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SensorSimulatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
