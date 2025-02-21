package com.bankmisr.service;

import com.bankmisr.data.model.PlotConfiguration;

import java.util.Set;

public interface PlotConfigurationService {

    Set<PlotConfiguration> getPlotConfigurations(Integer plotId);
}
