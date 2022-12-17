package com.service;

import java.util.List;

import com.model.Bill;

public interface BillService {
	List<Bill> getNotPaidBills(long meterno);

	List<Bill> getAllByMeterno(long meterno);	
	
	List < Bill > getAllBills();
	
    void saveBill(Bill bill);
    
    Bill getBillById(int id);
    
    void deleteBillById(int id);
}
