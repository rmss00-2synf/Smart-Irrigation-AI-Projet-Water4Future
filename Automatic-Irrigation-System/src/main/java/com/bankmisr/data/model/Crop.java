package com.bankmisr.data.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity  
@Table
@Getter
@Setter
public class Crop {
	
	@Id    
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column  
	private String name;  
	
	@Column  
	private Integer irrigationRate;
	
	@Column  
	private Integer waterAmountUnit;
	
	@OneToMany(mappedBy = "crop")
    private Set<PlotConfiguration> plotConfigurations;
}
