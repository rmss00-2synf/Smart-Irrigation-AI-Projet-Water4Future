package com.bankmisr.service;

import com.bankmisr.data.model.PlotSensor;
import com.bankmisr.data.repositories.PlotSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PlotSensorService {


    private final PlotSensorRepository plotSensorRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10); // Ajustez la taille du pool

    @Autowired
    public PlotSensorService(PlotSensorRepository plotSensorRepository) {
        this.plotSensorRepository = plotSensorRepository;
    }

    @PostConstruct
    public void init() {
        List<PlotSensor> allSensors = plotSensorRepository.findAll();
        allSensors.forEach(plotSensor -> {
            SensorSimulator simulator = new SensorSimulator(plotSensor, plotSensorRepository);
            executorService.execute(simulator);
        });
    }

    public PlotSensor getPlotSensor(Integer sensorId){
        return plotSensorRepository.findById(sensorId).get();
    }

    public PlotSensor updatePlotSensor(PlotSensor plotSensor){
        return plotSensorRepository.save(plotSensor);
    }
}
