import { TestBed } from '@angular/core/testing';

import { PlotSensorService } from './plot-sensor.service';

describe('PlotSensorService', () => {
  let service: PlotSensorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlotSensorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
