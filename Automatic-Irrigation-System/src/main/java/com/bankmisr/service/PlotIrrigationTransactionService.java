package com.bankmisr.service;

import com.bankmisr.data.model.IrrigationTransaction;

import java.util.Set;

public interface PlotIrrigationTransactionService {
        Set<IrrigationTransaction> getPlotConfigurations(Integer plotId);
}
