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
	
	@PostMapping("/payquickpay")
	public String payBill(@ModelAttribute(name="paybill")Long customerid, Model model) {
		return "pay_form";
	}
	
	@GetMapping("/quickpay")
	public String quickPayViewPage(Model model) {
		return "bill/quickpay_form";
	}
	@PostMapping("/pay")
	public String payPage(Model model) {
		return "bill/pay_form";
	}
	
	@PostMapping("/quickpay")
	public String upayBill(long meterno, Model model) {
		
		List<Bill> b=billService.getNotPaidBills(meterno);
		if(b.isEmpty()) {
			model.addAttribute("error", "cannot find any due");
			return "bill/quickpay_form";
		}
		Iterator<Bill> i = b.iterator();
		int total=0;
		while(i.hasNext()) {
			Bill e=i.next();
			total= total + e.getAmount();
		}		
		model.addAttribute("bill",b);
		model.addAttribute("total",total);
		return "bill/quickpay_form";
	}

}