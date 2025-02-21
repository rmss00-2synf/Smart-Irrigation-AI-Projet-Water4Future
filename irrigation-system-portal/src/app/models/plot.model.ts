export interface Plot {
  id?: number;
  location: string;
  area: number;
  ownerName: string;
  nextIrragtionDate?: string;
  lastIrragtionDate?: string;
  plotAlerts?: any[];
  irrigationTransactions?: any[];
  plotConfigurations?: any[];
  plotSensor?:PlotSensor
}

export interface PlotSensor {
  id?: number;
  available: boolean;
  soilMoisture?:number
  temperature?:number
}
