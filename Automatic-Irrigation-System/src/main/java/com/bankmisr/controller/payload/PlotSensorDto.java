package com.bankmisr.controller.payload;

import com.bankmisr.data.model.PlotSensor;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class PlotSensorDto {
    private Boolean available;
    private Integer id;
    private Double soilMoisture;
    private Double temperature;

    static public PlotSensorDto toDto(PlotSensor sensor) {
        PlotSensorDto dto = new PlotSensorDto();
        dto.setId(sensor.getId());
        dto.setAvailable(sensor.getAvailable());
        dto.setSoilMoisture(sensor.getSoilMoisture());
        dto.setTemperature(sensor.getTemperature());
        return dto;
    }

    static public PlotSensor toModel(PlotSensorDto dto) {
        PlotSensor model = new PlotSensor();
        model.setId(dto.getId());
        model.setAvailable(dto.getAvailable());
        model.setSoilMoisture(dto.getSoilMoisture());
        model.setTemperature(dto.getTemperature());
        return model;
    }
}
