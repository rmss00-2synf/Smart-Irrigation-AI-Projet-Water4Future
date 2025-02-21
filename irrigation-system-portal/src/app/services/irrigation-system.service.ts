import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { constants } from '../constants';
import { catchError, map } from 'rxjs/operators';
import { lastValueFrom, throwError } from 'rxjs';
import { PlotSensor } from '../models/plot.model';

@Injectable({
  providedIn: 'root'
})
export class IrrigationSystemService {
  constructor(private http: HttpClient) {}

  getPlots() {
    const url = `${environment.baseURL}${constants.API.GET_PLOTS_API}`;
    return this.http.get(url).pipe(
      map((res: any) => {
        return res;
      }),
      catchError((errorRes) => {
        return throwError(errorRes);
      })
    );
  }

  getPlot(plotId: string) {
    const url = `${environment.baseURL}${constants.API.GET_PLOT_API}`;
    return this.http.get(url.replace('{plotId}', plotId)).pipe(
      map((res: any) => {
        return res;
      }),
      catchError((errorRes) => {
        return throwError(errorRes);
      })
    );
  }

  getCrops() {
    const url = `${environment.baseURL}${constants.API.GET_CROPS_API}`;
    return this.http.get(url).pipe(
      map((res: any) => {
        return res;
      }),
      catchError((errorRes) => {
        return throwError(errorRes);
      })
    );
  }

  async addPlot(plot: any): Promise<any> {
    const url = environment.baseURL + constants.API.ADD_PLOT_API;
    let res: any = await lastValueFrom(this.http.post(url, plot));
    return res;
  }

  async editPlot(plotId: any, plot: any): Promise<any> {
    const url =
      environment.baseURL +
      constants.API.EDIT_PLOT_API.replace('{plotId}', plotId);
    await lastValueFrom(this.http.patch(url, plot));
  }

  async toggleIrrigationStatus(data: any): Promise<any> {
    const url = environment.baseURL + "/api/config/changeIrrigationStatus";
    let res: any = await lastValueFrom(this.http.post(url, data));
    return res;
  }

  async getSensor(sensorId: any): Promise<any> {
    const url = `${environment.baseURL}/api/sensor/${sensorId}`;
    let res: any = await lastValueFrom(this.http.get(url.replace("{sensorId}", sensorId)));
    return res;
  }

  async updateSensor(sensor: any): Promise<any> {
    const url = environment.baseURL + "/api/sensor/";
    let res: any = await lastValueFrom(this.http.post(url, sensor));
    return res;
  }
}
