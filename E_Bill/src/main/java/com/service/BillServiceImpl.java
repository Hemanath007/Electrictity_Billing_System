package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Bill;
import com.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepository billRepo;
	
	@Override
	public List<Bill> getNotPaidBills(int meterno){
		String status="not paid";
		return billRepo.findAllByMeternoAndStatus(meterno,status);
	}

	@Override
	public List<Bill> getAllByMeterno(int meterno) {
		return billRepo.findAllByMeterno(meterno);
	}	
}