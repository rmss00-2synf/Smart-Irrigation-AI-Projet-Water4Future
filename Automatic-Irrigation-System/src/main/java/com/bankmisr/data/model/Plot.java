package com.bankmisr.data.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;


@Entity  
@Table
@Data
public class Plot {
	
	@Id    
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column  
	private String location;  
	
	@Column  
	private Integer area;

	@Column  
	private String ownerName;  
  
	@Column  
	private LocalDateTime nextIrragtionDate;
	
	@Column  
	private LocalDateTime lastIrragtionDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private PlotSensor plotSensor;

}
