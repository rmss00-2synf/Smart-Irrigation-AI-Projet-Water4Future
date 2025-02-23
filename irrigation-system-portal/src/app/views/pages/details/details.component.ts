import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IrrigationSystemService } from 'src/app/services/irrigation-system.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Plot } from 'src/app/models/plot.model';
import { SavePlotModalComponent } from './save-plot-modal/save-plot-modal.component';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
  preserveWhitespaces: true
})
export class DetailsComponent implements OnInit {
  active = 1; // Afficher le premier onglet par défaut
  plot: Plot = {
    id: 0,
    location: '',
    area: 0,
    ownerName: '',
    nextIrragtionDate: '',
    lastIrragtionDate: '',
    plotAlerts: [],
    irrigationTransactions: [],
    plotConfigurations: []
  };

  currentPlotConfiguration: any;

  constructor(
    private irrigationSystemService: IrrigationSystemService,
    private route: ActivatedRoute,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.loadPlot();
  }

  loadPlot() {
    const plotId: any = this.route.snapshot.paramMap.get('plotId');
    this.irrigationSystemService.getPlot(plotId).subscribe({
      next: (plot: Plot) => {
        console.log(plot)
        this.plot = plot;
        this.currentPlotConfiguration = this.plot?.plotConfigurations?.filter(
          (plotConfiguration) => {
            console.log(plotConfiguration)
            return plotConfiguration.currentConfig == true;
          }
        )[0];
      },
      error: (e: any) => console.error(e)
    });
  }

  formatDate(date: any) {
    if (!date) return '';
    const parts = date.split('T');
    if (parts.length < 2) return parts[0];
    return `${parts[0]} ${parts[1]?.split('.')[0]}`;
  }

  editPlot() {
    const modalRef = this.modalService.open(SavePlotModalComponent, {
      centered: true,
      size: 'lg'
    });
    modalRef.componentInstance.plot = this.plot;
    modalRef.componentInstance.mode = 'edit';
    modalRef.componentInstance.submitChangesEvent.subscribe(
      async (data: any) => {
        try {
          const plotId: any = this.route.snapshot.paramMap.get('plotId');
          await this.irrigationSystemService.editPlot(plotId, data.plot);
          this.loadPlot();
          modalRef.close();
        } catch (e) {
          console.log(e);
        }
      }
    );
  }

  // Fonction pour obtenir la classe de couleur en fonction du statut
  getStatusColorClass(status: string): string {
    switch (status.toLowerCase()) {
      case 'succeded':
        return 'text-green-500';
      case 'failed':
        return 'text-red-500';
      default:
        return 'text-gray-500';
    }
  }

    protected readonly console = console;
}
