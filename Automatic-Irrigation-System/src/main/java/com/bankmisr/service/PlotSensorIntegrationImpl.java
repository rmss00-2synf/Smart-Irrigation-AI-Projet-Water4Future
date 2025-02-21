package com.bankmisr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bankmisr.data.model.IrrigationTransaction;
import com.bankmisr.data.model.PlotConfiguration;
import com.bankmisr.service.scheduler.IrrigationTransactionScheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PlotSensorIntegrationImpl implements PlotSensorIntegration{

	@Autowired
	private PlotConfigurationServiceImpl plotConfigurationService;
	private static final Logger log = LoggerFactory.getLogger(IrrigationTransactionScheduler.class);

	@Override
	public boolean executePlotIntegration(IrrigationTransaction irrigationTransaction) {

		boolean isSensorAvailable = irrigationTransaction.getPlot().getPlotSensor().getAvailable();

        log.info("Executing Irrigation for plot: {}", irrigationTransaction.getPlot().getId());

		PlotConfiguration plotConfiguration = plotConfigurationService.getPlotConfigurations(irrigationTransaction.getPlot().getId()).stream().filter(PlotConfiguration::getCurrentConfig).toList().get(0);

        log.info("Executing Irrigation With Water Amount: {}", plotConfiguration.getWaterAmount());

		if(isSensorAvailable) {
            log.info("Irrigation process Succeeded for plot: {}", irrigationTransaction.getPlot().getId());
		}else {
            log.info("Irrigation process Failed for plot: {}", irrigationTransaction.getPlot().getId());
		}

		return isSensorAvailable;
	}

}
