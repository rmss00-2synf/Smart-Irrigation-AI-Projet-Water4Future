package com.bankmisr.service;

import com.bankmisr.data.model.PlotConfiguration;
import com.bankmisr.data.repositories.PlotConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlotConfigurationServiceImpl implements PlotConfigurationService {

    @Autowired
    private PlotConfigurationRepository plotConfigurationRepository;

    @Override
    public Set<PlotConfiguration> getPlotConfigurations(Integer plotId) {
        return plotConfigurationRepository.findAll().stream()
                .filter(plotConfiguration -> plotConfiguration.getPlot().getId() == plotId)
                .collect(Collectors.toSet());
    }
}
