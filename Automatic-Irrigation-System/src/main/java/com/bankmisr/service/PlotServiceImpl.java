package com.bankmisr.service;

import java.time.LocalDateTime;
import java.util.*;

import com.bankmisr.data.repositories.PlotSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bankmisr.common.exception.ResourceNotFoundException;
import com.bankmisr.controller.payload.PlotDto;
import com.bankmisr.data.model.Crop;
import com.bankmisr.data.model.Plot;
import com.bankmisr.data.model.PlotConfiguration;
import com.bankmisr.data.model.PlotSensor;
import com.bankmisr.data.repositories.CropRepository;
import com.bankmisr.data.repositories.PlotRepository;


@Service
public class PlotServiceImpl implements PlotService {
	

	private final PlotRepository plotRepository;
	private final CropRepository cropRepository;
	private final PlotSensorService plotSensorService;
	private final PlotSensorRepository plotSensorRepository;

	@Autowired
	public PlotServiceImpl(PlotRepository plotRepository, CropRepository cropRepository,PlotSensorService plotSensorService,PlotSensorRepository plotSensorRepository) {
		this.plotRepository = plotRepository;
		this.cropRepository = cropRepository;
		this.plotSensorService = plotSensorService;
		this.plotSensorRepository = plotSensorRepository;
	}


	public List<Plot> getAllPlots() {
		List<Plot> plots = new ArrayList<Plot>();
		plotRepository.findAll().forEach(plots::add);
		return plots;
	}

	@Override
	public Plot getPlotById(Integer id) {
		return plotRepository.findById(id).orElse(null);
	}

	public Plot getPlotById(int id) {
		return plotRepository.findById(id).orElse(null);
	}

	public Plot addNewPlot(PlotDto plotDto) {

		Plot plot = new Plot();
		plot.setLocation(plotDto.getLocation());
		plot.setArea(plotDto.getArea());
		plot.setOwnerName(plotDto.getOwnerName());
		plot.setNextIrragtionDate(LocalDateTime.now().plusMinutes(plotDto.getPlotConfiguration().getIrrigationRate()));

		PlotConfiguration plotConfiguration = new PlotConfiguration();
		Optional<Crop> crop = cropRepository.findById(plotDto.getPlotConfiguration().getCropId());
		crop.ifPresent(plotConfiguration::setCrop);
		plotConfiguration.setWaterAmount(plotDto.getPlotConfiguration().getWaterAmount());
		plotConfiguration.setIrrigationRate(plotDto.getPlotConfiguration().getIrrigationRate());
		plotConfiguration.setPlot(plot);
		plotConfiguration.setCurrentConfig(true);

		plot.setPlotConfigurations(Set.of(plotConfiguration));

		//plot sensor
		PlotSensor plotSensor = new PlotSensor();
		plotSensor.setAvailable(true);
		plot.setPlotSensor(plotSensor);

		return plotRepository.save(plot);
	}

	public Plot editPlot(PlotDto plotDto) {
		Plot plot = plotRepository.findById(plotDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Plot with id: " + plotDto.getId()));

		if (StringUtils.hasText(plotDto.getLocation())) {
			plot.setLocation(plotDto.getLocation());
		}

		if (!Objects.equals(plotDto.getArea(), plot.getArea())) {
			plot.setArea(plotDto.getArea());
		}

		if (StringUtils.hasText(plotDto.getOwnerName())) {
			plot.setOwnerName(plotDto.getOwnerName());
		}

		PlotConfiguration plotConfiguration = plot.getPlotConfigurations().stream().filter(PlotConfiguration::getCurrentConfig).toList().get(0);

		if (plotDto.getPlotConfiguration().getCropId() > 0) {
			Crop crop = cropRepository.findById(plotDto.getPlotConfiguration().getCropId()).orElseThrow(() -> new ResourceNotFoundException("crop with id: " + plotDto.getPlotConfiguration().getCropId()));
			if (!Objects.equals(plotConfiguration.getCrop().getId(), crop.getId())) {
				plotConfiguration.setCrop(crop);
			}
		}

		if (plotDto.getPlotConfiguration().getIrrigationRate() > 0) {
			plotConfiguration.setIrrigationRate(plotDto.getPlotConfiguration().getIrrigationRate());
			if (plot.getLastIrragtionDate() != null) {
				plot.setNextIrragtionDate(plot.getLastIrragtionDate().plusMinutes(plotConfiguration.getIrrigationRate()));
			} else {
				plot.setNextIrragtionDate(LocalDateTime.now().plusMinutes(plotConfiguration.getIrrigationRate()));
			}
		}

		if (plotDto.getPlotConfiguration().getWaterAmount() > 0) {
			plotConfiguration.setWaterAmount(plotDto.getPlotConfiguration().getWaterAmount());
		}

		// Update sensor availability
		if (plotDto.getPlotSensor() != null) {
			PlotSensor plotSensor = plot.getPlotSensor();
			plotSensor.setAvailable(plotDto.getPlotSensor().getAvailable());
			plotSensorService.updatePlotSensor(plotSensor);
		}

		return plotRepository.save(plot);
	}

	@Override
	public void toggleSensor(Integer plotId) {
		Plot plot = plotRepository.findById(plotId).orElseThrow(() -> new ResourceNotFoundException("Plot with id: " + plotId));
		plot.getPlotSensor().setAvailable(!plot.getPlotSensor().getAvailable());
		plotSensorRepository.save(plot.getPlotSensor());
	}

}
