package com.bankmisr.controller.payload;

import com.bankmisr.data.model.IrrigationTransaction;
import com.bankmisr.data.model.Plot;
import com.bankmisr.data.model.PlotAlert;
import com.bankmisr.data.model.PlotConfiguration;
import com.bankmisr.service.PlotAlertServiceImpl;
import com.bankmisr.service.PlotConfigurationServiceImpl;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import static com.bankmisr.controller.payload.PlotConfigurationDto.toDto;

@Data
@Builder
public class PlotDto {


	private Integer id;

	private String location;  
	
	private Integer area;

	private String ownerName;

	private LocalDateTime nextIrragtionDate;

	private LocalDateTime lastIrragtionDate;

	private PlotConfigurationDto plotConfiguration;
	private Set<PlotConfigurationDto> plotConfigurations;
	private Set<PlotAlertDTO> plotAlerts;
	private Set<IrrigationTransactionDTO> irrigationTransactions;
	private PlotSensorDto plotSensor;

}
