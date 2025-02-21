import { IrrigationSystemService } from 'src/app/services/irrigation-system.service';
import { PlotSensorService } from 'src/app/services/plot-sensor.service';
import { Component, OnInit } from '@angular/core';
import { Plot, PlotSensor } from 'src/app/models/plot.model';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SavePlotModalComponent } from '../details/save-plot-modal/save-plot-modal.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  preserveWhitespaces: true
})
export class HomeComponent implements OnInit {
  plots: any[] = [];
  irrigationStatus: boolean = false;
  isManualMode: boolean = false;
  selectedPlot:any

  constructor(
    private irrigationSystemService: IrrigationSystemService,
    private router: Router,
    private modalService: NgbModal,
    private plotSensorService: PlotSensorService
  ) {
  }

  ngOnInit(): void {
    this.loadPlots();
  }

  loadPlots() {
    this.irrigationSystemService.getPlots().subscribe({
      next: (plots) => {
        this.plots = plots;
        //console.log(plots)
      },
      error: (e) => console.error(e)
    });
  }

  async openPlotDetails(plot: Plot) {
    this.router.navigate([`details/${plot.id}`]);
  }

  async addPlot() {
    const modalRef = this.modalService.open(SavePlotModalComponent, {
      centered: true,
      size: 'lg'
    });

    modalRef.componentInstance.mode = 'add';
    modalRef.componentInstance.submitChangesEvent.subscribe(
      async (data: any) => {
        try {
          await this.irrigationSystemService.addPlot(data.plot);
          this.loadPlots();
          modalRef.close();
        } catch (e) {
          console.log(e);
        }
      }
    );
  }

  async toggleIrrigationStatus() {
    this.irrigationStatus = !this.irrigationStatus
    await this.irrigationSystemService.toggleIrrigationStatus(this.irrigationStatus);
    this.loadPlots();
  }

  async changeAutoManual(plot: any) {
    this.selectedPlot = plot
    if(plot.plotSensor.available){
      plot.plotSensor.available = false;
    }else{
      plot.plotSensor.available = true;
    }

    await this.plotSensorService.updateSensor(plot.plotSensor);
    this.loadPlots();
  }
}
