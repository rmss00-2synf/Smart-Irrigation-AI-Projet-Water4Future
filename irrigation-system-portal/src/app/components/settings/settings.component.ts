import { Component, OnInit } from '@angular/core';
import { IrrigationSystemService } from 'src/app/services/irrigation-system.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {
  irrigationStatus: boolean = false
  isManualMode: boolean = false;

  constructor(private irrigationSystemService: IrrigationSystemService) {
  }

  ngOnInit(): void {
    this.loadConfig();
  }

  loadConfig() {
    //TODO
  }

  async toggleIrrigationStatus() {
    this.irrigationStatus = !this.irrigationStatus
    await this.irrigationSystemService.toggleIrrigationStatus(this.irrigationStatus);
  }
}
