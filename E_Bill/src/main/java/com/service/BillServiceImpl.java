package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Bill;
import com.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepository billRepository;
	
	@Override
	public List<Bill> getAllBills(Long customerid){
		return billRepository.findByCustomerid(customerid);
	}	
}