import { Component, OnInit } from '@angular/core';
import { IrrigationSystemService } from 'src/app/services/irrigation-system.service';
import { PlotSensorService } from 'src/app/services/plot-sensor.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  soilMoisture: number | undefined = 0;
  temperature: number | undefined = 0;
  irrigationEnabled: boolean = false;
  sensorId: number = 1; // Remplacez par l'ID réel du capteur

  constructor(
    private irrigationSystemService: IrrigationSystemService,
    private plotSensorService: PlotSensorService
  ) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.plotSensorService.getSensor(this.sensorId).then(sensor => {
      this.soilMoisture = sensor.soilMoisture;
      this.temperature = sensor.temperature;
    });
  }

  updateSensorData() {
    const sensorData = {
      id: this.sensorId,
      soilMoisture: this.soilMoisture,
      temperature: this.temperature
    };

    this.plotSensorService.updateSensor(sensorData).then(() => {
      console.log('Sensor data updated successfully!');
      this.loadData(); // Refresh the sensor data
    });
  }
}
