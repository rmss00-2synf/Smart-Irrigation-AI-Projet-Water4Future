package com.bankmisr.controller;

import com.bankmisr.data.model.PlotSensor;
import com.bankmisr.service.PlotSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
@CrossOrigin(origins = "http://localhost:4200")
public class SensorController {

    @Autowired
    private PlotSensorService plotSensorService;

    @GetMapping("/{sensorId}")
    public PlotSensor getSensorData(@PathVariable int sensorId) {
        return plotSensorService.getPlotSensor(sensorId);
    }

    @PostMapping("/")
    public PlotSensor updateSensorData(@RequestBody PlotSensor plotSensor) {
        return plotSensorService.updatePlotSensor(plotSensor);
    }
}
