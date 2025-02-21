package com.bankmisr.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.bankmisr.data.model.PlotAlert;

public interface PlotAlertRepository extends JpaRepository<PlotAlert, Integer>
{  
}
