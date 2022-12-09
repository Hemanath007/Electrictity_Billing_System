package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BillController {

	@GetMapping("/billcalculator")
	public String calculatorpage(Model model) {
		model.addAttribute("billCalculator", new BillCalculator());
		return "bill/billcalculation";
	}
	
	@PostMapping("/calBill")
	public String calMethod(BillCalculator billCalculator, Model model) {
		float cost = billCalculator.billCalci();		
		String c = "Your bill amount is : " +cost;
		model.addAttribute("billCalculator", new BillCalculator());
		model.addAttribute("cost",c);
		return "bill/billcalculation";
	}
	@GetMapping("/quickpay")
	public String quickPayViewPage(Model model) {
		Long customerid = null;
		model.addAttribute("cutomerid",customerid);
		return "quickpay_form";
	}
	@PostMapping("/payquickpay")
	public String payBill(@ModelAttribute(name="paybill")Long customerid, Model model) {
		
		
		return "pay_form";
	}
}
