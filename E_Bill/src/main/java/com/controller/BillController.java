package com.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@PostMapping("/pay")
	public String payPage(@ModelAttribute("newbill") Bill newbill,Model model) {
		System.out.println("Inside the pay =" +newbill.toString());
		model.addAttribute("bill",newbill);
		return "bill/pay_form";
	}
	@PostMapping("/paypaid")
	public String payPagePaid(@ModelAttribute("newbill")Bill bill,Model model) {
		System.out.println(bill.toString()); 
		bill.setStatus("Paid");
		billService.saveBill(bill);
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
		model.addAttribute("newbill",bill);
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