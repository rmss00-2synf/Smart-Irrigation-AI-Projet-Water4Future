package com.bankmisr.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity  
@Table
@Data
public class PlotSensor {

	
	@Id    
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column  
	private Boolean available;

	@Column
	private Double soilMoisture;

	@Column
	private Double temperature;

	@OneToOne(mappedBy = "plotSensor")
	@JsonBackReference
    private Plot plot;

	
}
