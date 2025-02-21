package com.bankmisr.service;

import com.bankmisr.controller.payload.PlotSensorDto;
import com.bankmisr.data.model.PlotSensor;
import com.bankmisr.data.repositories.PlotSensorRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SensorSimulator implements Runnable {

    private final PlotSensorDto plotSensor;
    private final PlotSensorRepository plotSensorRepository;
    private final Random random = new Random();

    public SensorSimulator(PlotSensorDto plotSensor, PlotSensorRepository plotSensorRepository) {
        this.plotSensor = plotSensor;
        this.plotSensorRepository = plotSensorRepository;
    }

    @Override
    public void run() {
        while (true) {
            // Simuler les données du capteur
            double soilMoisture = 20 + random.nextDouble() * 60; // Valeur entre 20 et 80
            double temperature = 15 + random.nextDouble() * 25; // Valeur entre 15 et 40

            // Mettre à jour les données du capteur
            plotSensor.setSoilMoisture(Double.valueOf(soilMoisture));
            plotSensor.setTemperature(Double.valueOf(temperature));

            System.out.println(plotSensor);

            plotSensorRepository.save(PlotSensorDto.toModel(plotSensor));

            try {
                Thread.sleep(60000); // Attendre 5 secondes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
