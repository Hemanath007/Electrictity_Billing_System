package com.service;

import java.util.List;

import com.model.Bill;

public interface BillService {
	List<Bill> getNotPaidBills(int meterno);

	List<Bill> getAllByMeterno(int meterno);	
	
	List < Bill > getAllBills();
	
    void saveBill(Bill bill);
    
    Bill getBillById(int id);
    
    void deleteBillById(int id);
}
