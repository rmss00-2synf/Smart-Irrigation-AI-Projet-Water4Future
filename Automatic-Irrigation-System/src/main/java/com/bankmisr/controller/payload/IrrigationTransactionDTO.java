package com.bankmisr.controller.payload;

import com.bankmisr.data.enums.IrrigationTransactionStatus;
import com.bankmisr.data.model.IrrigationTransaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class IrrigationTransactionDTO {

    private Integer id;
    private IrrigationTransactionStatus status;
    private LocalDateTime irragtionDate;
    private Integer trials;
    private Integer plot_id;

    static public IrrigationTransactionDTO toDTO(IrrigationTransaction irrigationTransaction) {
        return IrrigationTransactionDTO.builder()
                .id(irrigationTransaction.getId())
                .status(irrigationTransaction.getStatus())
                .irragtionDate(irrigationTransaction.getIrragtionDate())
                .trials(irrigationTransaction.getTrials())
                .plot_id(irrigationTransaction.getPlot().getId())
                .build();
    }
}
