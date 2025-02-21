package com.bankmisr.service;

import com.bankmisr.data.model.PlotAlert;
import com.bankmisr.data.model.PlotConfiguration;
import com.bankmisr.data.repositories.PlotAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlotAlertServiceImpl implements PlotAlertService {

    @Autowired
    private PlotAlertRepository plotAlertRepository;

    @Override
    public Set<PlotAlert> getPlotAlert(Integer plotId) {

            return plotAlertRepository.findAll().stream()
                    .filter(plotAlert -> Objects.equals(plotAlert.getPlot().getId(), plotId))
                    .collect(Collectors.toSet());

    }
}
