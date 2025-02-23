package com.bankmisr.service;

import com.bankmisr.data.model.IrrigationTransaction;
import com.bankmisr.data.repositories.IrrigationTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlotIrrigationTransactionServiceImp implements PlotIrrigationTransactionService {

    private final IrrigationTransactionRepository repository;
    @Autowired
    public PlotIrrigationTransactionServiceImp(IrrigationTransactionRepository repository) {
        this.repository = repository;
    }
    @Override
    public Set<IrrigationTransaction> getPlotConfigurations(Integer plotId) {
        return repository.findAll().stream()
                .filter(e->Objects.equals(e.getPlot().getId(),plotId)).collect(Collectors.toSet());

    }

}
