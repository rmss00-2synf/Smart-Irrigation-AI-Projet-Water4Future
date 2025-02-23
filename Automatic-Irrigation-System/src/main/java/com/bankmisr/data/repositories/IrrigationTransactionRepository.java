package com.bankmisr.data.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.bankmisr.data.model.IrrigationTransaction;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrigationTransactionRepository extends JpaRepository<IrrigationTransaction, Integer>
{

	@Query("SELECT i FROM IrrigationTransaction i WHERE i.status = 1 AND i.trials < :trials")
	Set<IrrigationTransaction> findFailedIrrigationTransactions(@Param("trials") int trials); 
	
	
}
