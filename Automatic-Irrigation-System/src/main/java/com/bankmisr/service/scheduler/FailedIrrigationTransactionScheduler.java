package com.bankmisr.service.scheduler;

import java.time.LocalDateTime;
import java.util.Set;
import javax.transaction.Transactional;

import com.bankmisr.service.PlotAlertServiceImpl;
import com.bankmisr.service.PlotConfigurationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bankmisr.common.util.Constants;
import com.bankmisr.data.enums.IrrigationTransactionStatus;
import com.bankmisr.data.model.IrrigationTransaction;
import com.bankmisr.data.model.PlotAlert;
import com.bankmisr.data.model.PlotConfiguration;
import com.bankmisr.data.repositories.IrrigationTransactionRepository;
import com.bankmisr.service.PlotSensorIntegration;

@Component
@Transactional
public class FailedIrrigationTransactionScheduler implements Constants{

	private static final Logger log = LoggerFactory.getLogger(FailedIrrigationTransactionScheduler.class);

	@Autowired
	private IrrigationTransactionRepository irrigationTransactionRepository;

	@Autowired
	private PlotConfigurationServiceImpl plotConfigurationService;

	@Autowired
	private PlotAlertServiceImpl plotAlertService;

	@Autowired
	private PlotSensorIntegration plotSensorIntegration;

	@Value("${irrigation.failed.transactions.trials}")
	private int irrigationFailedTransactionTrials;

	@Scheduled(fixedRate = 60000)
	public void ExecuteIrrigationTransactions() {

		Set<IrrigationTransaction> failedIrrigationTransactions = irrigationTransactionRepository.findFailedIrrigationTransactions(irrigationFailedTransactionTrials);

		if (failedIrrigationTransactions.isEmpty()) {
			log.info("No Failed Transactions to handle");
		} else {
			failedIrrigationTransactions.forEach(irrigationTransaction -> {
				//call sensor
				boolean irrigationExecutionResult = plotSensorIntegration.executePlotIntegration(irrigationTransaction);

				if (irrigationExecutionResult) {
					irrigationTransaction.setStatus(IrrigationTransactionStatus.SUCCEDED);
					irrigationTransaction.setTrials(Integer.valueOf(0));
					irrigationTransaction.getPlot().setLastIrragtionDate(LocalDateTime.now());
					PlotConfiguration plotConfiguration = plotConfigurationService.getPlotConfigurations(irrigationTransaction.getPlot().getId()).stream().filter(PlotConfiguration::getCurrentConfig).toList().get(0);
					irrigationTransaction.getPlot().setNextIrragtionDate(LocalDateTime.now().plusMinutes(plotConfiguration.getIrrigationRate()));

				} else {
					irrigationTransaction.setTrials(Integer.valueOf(irrigationTransaction.getTrials() + 1));

					if (irrigationTransaction.getTrials() == irrigationFailedTransactionTrials) {

						PlotAlert plotAlert = new PlotAlert();
						plotAlert.setCreationDate(LocalDateTime.now());
						plotAlert.setPlot(irrigationTransaction.getPlot());
						plotAlert.setIrrigationTransaction(irrigationTransaction);

//						plotAlert.getIrrigationTransaction(irrigationTransaction.getPlot().getId())
//						irrigationTransaction.getPlot().getPlotAlerts().add(plotAlert);
					}
				}
				irrigationTransactionRepository.save(irrigationTransaction);
			});
		}
	}

}
