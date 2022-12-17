package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Bill;
import com.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepository billRepo;
	
	@Override
	public List<Bill> getNotPaidBills(long meterno){
		String status="not paid";
		return billRepo.findAllByMeternoAndStatus(meterno,status);
	}

	@Override
	public List<Bill> getAllByMeterno(long meterno) {
		return billRepo.findAllByMeterno(meterno);
	}
	@Override
	public List<Bill> getAllBills() {
		return billRepo.findAll();
	}

	@Override
	public void saveBill(Bill bill) {
	this.billRepo.save(bill);
		
	}

	@Override
	public Bill getBillById(int id) {
		Optional < Bill > optional = billRepo.findById(id);
        Bill bill = null;
        if (optional.isPresent()) {
            bill = optional.get();
        } else {
            throw new RuntimeException(" Bill not found for id :: " + id);
        }
        return bill;
	}

	@Override
	public void deleteBillById(int id) {
		 this.billRepo.deleteById(id);		
	}

		
}