package com.bankmisr.data.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bankmisr.data.enums.IrrigationTransactionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity  
@Table
@Data
public class IrrigationTransaction {
	
	@Id    
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column  
	private IrrigationTransactionStatus status;  
	
	@Column  
	private LocalDateTime irragtionDate;
	
	@Column  
	private Integer trials;

	@ManyToOne
	@JsonBackReference
    @JoinColumn(name="plot_id", nullable=false)
    private Plot plot;


}
