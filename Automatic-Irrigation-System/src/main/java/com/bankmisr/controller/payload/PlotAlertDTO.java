package com.bankmisr.controller.payload;

import com.bankmisr.data.model.IrrigationTransaction;
import com.bankmisr.data.model.PlotAlert;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PlotAlertDTO {
    private Integer id;
    private LocalDateTime creationDate;
    private Integer plot_id;
    private IrrigationTransaction irrigationTransaction;

    static public PlotAlertDTO toDTO(PlotAlert plotAlert) {
        return PlotAlertDTO.builder()
                .id(plotAlert.getId())
                .creationDate(plotAlert.getCreationDate())
                .plot_id(plotAlert.getPlot().getId())
                .irrigationTransaction(plotAlert.getIrrigationTransaction())
                .build();
    }
}
