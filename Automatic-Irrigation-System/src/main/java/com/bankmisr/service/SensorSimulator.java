package com.bankmisr.service;

import com.bankmisr.data.model.PlotSensor;
import com.bankmisr.data.repositories.PlotSensorRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SensorSimulator implements Runnable {

    private PlotSensor plotSensor;
    private PlotSensorRepository plotSensorRepository;
    private Random random = new Random();

    public SensorSimulator(PlotSensor plotSensor, PlotSensorRepository plotSensorRepository) {
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
            plotSensor.setSoilMoisture(soilMoisture);
            plotSensor.setTemperature(temperature);
            System.out.println(plotSensor);
            plotSensorRepository.save(plotSensor);

            try {
                Thread.sleep(5000); // Attendre 5 secondes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
