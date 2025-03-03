//package com.bankmisr.data.dataseed;
//
//import java.time.LocalDateTime;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import com.bankmisr.data.enums.IrrigationTransactionStatus;
//import com.bankmisr.data.model.Crop;
//import com.bankmisr.data.model.IrrigationTransaction;
//import com.bankmisr.data.model.Plot;
//import com.bankmisr.data.model.PlotAlert;
//import com.bankmisr.data.model.PlotConfiguration;
//import com.bankmisr.data.model.PlotSensor;
//import com.bankmisr.data.repositories.CropRepository;
//import com.bankmisr.data.repositories.PlotAlertRepository;
//import com.bankmisr.data.repositories.PlotRepository;
//
//
//@Component
//public class IrrigationDataLoader implements CommandLineRunner {
//
//	@Autowired
//	PlotRepository plotRepository;
//
//	@Autowired
//	CropRepository cropRepository;
//
//	@Autowired
//	PlotAlertRepository plotAlertRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		loadCropData();
//
//		loadTestCase1Data();
//		loadTestCase2Data();
//		loadTestCase3Data();
//
//	}
//
//	private void loadCropData() {
//		Crop crop = new Crop();
//
//		crop.setName("tomatos");
//		crop.setIrrigationRate(Integer.valueOf(5));
//		crop.setWaterAmountUnit(Integer.valueOf(100));
//
//
//		cropRepository.save(crop);
//
//		crop = new Crop();
//		crop.setName("Sugarcane");
//		crop.setIrrigationRate(Integer.valueOf(5));
//		crop.setWaterAmountUnit(Integer.valueOf(500));
//
//		cropRepository.save(crop);
//
//		crop = new Crop();
//		crop.setName("wheat");
//		crop.setIrrigationRate(Integer.valueOf(10));
//		crop.setWaterAmountUnit(Integer.valueOf(300));
//
//		cropRepository.save(crop);
//
//		crop = new Crop();
//		crop.setName("ٌRice");
//		crop.setIrrigationRate(Integer.valueOf(10));
//		crop.setWaterAmountUnit(Integer.valueOf(600));
//
//		cropRepository.save(crop);
//	}
//
//	private void loadTestCase1Data() {
//			Plot plot = new Plot();
//
//			plot.setLocation("Giza,Kerdasa");
//			plot.setOwnerName("Ahmed abd el kareem");
//			plot.setArea(Integer.valueOf(70));
//			plot.setLastIrragtionDate(LocalDateTime.now());
//
//			//plot sensor
//			PlotSensor plotSensor = new PlotSensor();
//			plotSensor.setAvailable(Boolean.valueOf(true));
//			plot.setPlotSensor(plotSensor);
//
//			//plot configurations
//			PlotConfiguration plotConfiguration = new PlotConfiguration();
//			Crop crop = cropRepository.findByName("Sugarcane").get();
//			plotConfiguration.setCrop(crop);
//			plotConfiguration.setPlot(plot);
//			plotConfiguration.setCurrentConfig(Boolean.valueOf(true));
//			plotConfiguration.setIrrigationRate(crop.getIrrigationRate());
//			plotConfiguration.setWaterAmount(Integer.valueOf(crop.getWaterAmountUnit()*plot.getArea()));
//
//			plot.setNextIrragtionDate(LocalDateTime.now().plusMinutes(plotConfiguration.getIrrigationRate()));
//
//			plot.setPlotConfigurations(Set.of(plotConfiguration));
//
//			plotRepository.save(plot);
//	}
//
//	private void loadTestCase2Data() {
//		Plot plot = new Plot();
//
//		plot.setLocation("Dakahlia,mansoura");
//		plot.setArea(Integer.valueOf(80));
//		plot.setOwnerName("hany ahmed");
//		plot.setLastIrragtionDate(LocalDateTime.now());
//
//		//plot sensor
//		PlotSensor plotSensor = new PlotSensor();
//		plotSensor.setAvailable(Boolean.valueOf(false));
//		plot.setPlotSensor(plotSensor);
//
//		//plot configurations
//		PlotConfiguration plotConfiguration = new PlotConfiguration();
//		Crop crop = cropRepository.findByName("Sugarcane").get();
//		plotConfiguration.setCrop(crop);
//		plotConfiguration.setPlot(plot);
//		plotConfiguration.setCurrentConfig(Boolean.valueOf(true));
//		plotConfiguration.setIrrigationRate(crop.getIrrigationRate());
//		plotConfiguration.setWaterAmount(Integer.valueOf(crop.getWaterAmountUnit()*plot.getArea()));
//
//		plot.setNextIrragtionDate(LocalDateTime.now().plusMinutes(plotConfiguration.getIrrigationRate()));
//
//		plot.setPlotConfigurations(Set.of(plotConfiguration));
//
//		plotRepository.save(plot);
//	}
//
//	private void loadTestCase3Data() {
//
//		Plot plot = new Plot();
//
//		plot.setLocation("aswan,nouba");
//		plot.setArea(Integer.valueOf(60));
//		plot.setOwnerName("edrees mohamed");
//		plot.setLastIrragtionDate(LocalDateTime.now());
//
//		//plot sensor
//		PlotSensor plotSensor = new PlotSensor();
//		plotSensor.setAvailable(Boolean.valueOf(true));
//		plot.setPlotSensor(plotSensor);
//
//		//plot configurations
//		PlotConfiguration plotConfiguration = new PlotConfiguration();
//		Crop crop = cropRepository.findByName("wheat").get();
//		plotConfiguration.setCrop(crop);
//		plotConfiguration.setPlot(plot);
//		plotConfiguration.setCurrentConfig(Boolean.valueOf(true));
//		plotConfiguration.setIrrigationRate(crop.getIrrigationRate());
//		plotConfiguration.setWaterAmount(Integer.valueOf(crop.getWaterAmountUnit()*plot.getArea()));
//
//		plot.setNextIrragtionDate(LocalDateTime.now().plusMinutes(plotConfiguration.getIrrigationRate()));
//
//		plot.setPlotConfigurations(Set.of(plotConfiguration));
//
//		//plot irrigation transaction history
//		IrrigationTransaction irrigationTransaction = new IrrigationTransaction();
//		irrigationTransaction.setPlot(plot);
//		irrigationTransaction.setIrragtionDate(LocalDateTime.now().minusMinutes(5));
//		irrigationTransaction.setStatus(IrrigationTransactionStatus.FAILED);
//		irrigationTransaction.setTrials(Integer.valueOf(2));
//
//		plot.setIrrigationTransactions(Set.of(irrigationTransaction));
//
//		plotRepository.save(plot);
//
//	}
//}
