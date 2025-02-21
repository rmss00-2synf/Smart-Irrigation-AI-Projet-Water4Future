package com.bankmisr.service;

import com.bankmisr.data.model.PlotAlert;

import java.util.Set;

public interface PlotAlertService {
    Set<PlotAlert> getPlotAlert(Integer plotId);
}
