package com.service;

import java.util.List;

import com.model.Bill;

public interface BillService {
	List<Bill> getNotPaidBills(int meterno);

	List<Bill> getAllByMeterno(int meterno);	
	
	
}
