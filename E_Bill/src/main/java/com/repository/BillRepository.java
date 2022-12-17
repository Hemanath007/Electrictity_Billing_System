package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
	@Query("SELECT u FROM Bill u WHERE u.meterno = ?1")
	public List<Bill> findAllByMeterno(long meterno);
	
	public List<Bill> findAllByMeternoAndStatus(long meterno, String status);
}
