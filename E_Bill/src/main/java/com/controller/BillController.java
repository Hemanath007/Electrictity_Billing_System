package com.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.calculation.BillCalculator;
import com.model.Bill;
import com.model.Customer;
import com.service.BillService;

@Controller
public class BillController {
	
	@Autowired
	private BillService billService;

	@GetMapping("/billcalculator")
	public String calculatorpage(Model model) {
		model.addAttribute("billCalculator", new BillCalculator());
		return "bill/billcalculation";
	}
	
	@PostMapping("/calBill")
	public String calMethod(BillCalculator billCalculator, Model model) {
		float cost = billCalculator.billCalci();		
		String c = "Bill amount is : " +cost;
		model.addAttribute("billCalculator", new BillCalculator());
		model.addAttribute("cost",c);
		return "bill/billcalculation";
	}
	
	@GetMapping("/quickpay")
	public String quickPayViewPage(Model model) {
		return "bill/quickpay_form";
	}
	@GetMapping("/pay/{id}")
	public String payPagea(@PathVariable(value = "id")long meterno,Model model) {
		System.out.println("Inside the pay =" +meterno);
		List<Bill> bill=billService.getNotPaidBills(meterno);
			
		model.addAttribute("bill",bill);
		model.addAttribute("meterno",meterno);
		return "bill/pay_form";
	}
	
	@GetMapping("/paypaid/{id}")
	public String payPagePaid(@PathVariable(value = "id")long meterno,Model model) {
		System.out.println("Inside the paypaid =" +meterno);
		List<Bill> bill=billService.getNotPaidBills(meterno);
		Iterator<Bill> i = bill.iterator();		
		while(i.hasNext()) {
			Bill e=i.next();
			e.setStatus("Paid");
			billService.saveBill(e);
		}
		System.out.println("Updated");
		model.addAttribute("status", "paid");
		return "bill/pay_form";
	}
	
	@PostMapping("/quickpay")
	public String payBill(long meterno, Model model) {
		List<Bill> bill=billService.getNotPaidBills(meterno);
		if(bill.isEmpty()) {
			model.addAttribute("error", "cannot find any due for "+meterno);
			return "bill/quickpay_form";
		}
		Iterator<Bill> i = bill.iterator();
		int total=0;
		while(i.hasNext()) {
			Bill e=i.next();
			total= total + e.getAmount();
		}		
		model.addAttribute("bill",bill);
		model.addAttribute("meterno",meterno);
		model.addAttribute("total",total);
		return "bill/quickpay_form";
	}
	
	@PostMapping("/customer/uquickpay")
	public String customerPayBill(long meterno, Model model) {
		List<Bill> b=billService.getNotPaidBills(meterno);
		if(b.isEmpty()) {
			model.addAttribute("error","You(" +meterno +") have no Due");
			return "bill/customer_pay";
		}
		Iterator<Bill> i = b.iterator();
		int total=0;
		while(i.hasNext()) {
			Bill e=i.next();
			total= total + e.getAmount();
		}		
		model.addAttribute("bill",b);
		model.addAttribute("total",total);
		return "bill/customer_pay";
	}
	@GetMapping("/customer/billhistory")
	public String billHistory(long meterno, Model model) {
		List<Bill> b=billService.getAllByMeterno(meterno);
		if(b.isEmpty()) {
			model.addAttribute("error","You(" +meterno +") have no Bill History");
			return "bill/histroy";
		}		
		model.addAttribute("billhistory",b);	
		return "bill/histroy";
	}
}