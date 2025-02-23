package com.bankmisr.controller.payload;

import com.bankmisr.data.model.PlotConfiguration;
import com.bankmisr.data.model.PlotSensor;
import lombok.Data;

@Data
public class PlotConfigurationDto{

	private Integer cropId;
	
	private Integer irrigationRate;
	
	private Integer waterAmount;

	private Boolean currentConfig;

	static public PlotConfigurationDto toDto(PlotConfiguration configuration){
		PlotConfigurationDto dto = new PlotConfigurationDto();
		dto.setCropId(configuration.getId());
		dto.setIrrigationRate(configuration.getIrrigationRate());
		dto.setWaterAmount(configuration.getWaterAmount());
		dto.setCurrentConfig(configuration.getCurrentConfig());
		return dto;
	}
}
