package com.bankmisr.controller.payload;

import lombok.Data;

@Data
public class PlotDto {
	
	private Integer id;

	private String location;  
	
	private Integer area;

	private String ownerName;  
	
	private PlotConfigurationDto plotConfiguration;
	private PlotSensorDto plotSensor;
}
