import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { lastValueFrom } from 'rxjs';
import {PlotSensor} from "../models/plot.model";

@Injectable({
  providedIn: 'root'
})
export class PlotSensorService {

  constructor(private http: HttpClient) { }

  async updateSensor(sensor: any): Promise<any> {
    const url = `${environment.baseURL}/api/sensor/`; // URL de l'API Spring Boot
    return await lastValueFrom(this.http.post(url, sensor));
  }

  async getSensor(sensorId: number): Promise<PlotSensor> {
    const url = `${environment.baseURL}/api/sensor/${sensorId}`;
    return await lastValueFrom(this.http.get<PlotSensor>(url));
  }
}
