package com.service;

import java.util.List;

import com.model.Bill;

public interface BillService {
	List<Bill> getAllBills(Long customerid);	
}
