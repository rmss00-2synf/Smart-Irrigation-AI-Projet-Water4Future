package com.bankmisr.controller.payload;

import lombok.Data;

@Data
public class PlotConfigurationDto{

	private Integer cropId;
	
	private Integer irrigationRate;
	
	private Integer waterAmount;

}
