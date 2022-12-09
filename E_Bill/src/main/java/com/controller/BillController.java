package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Bill;
import com.repository.BillRepository;

@Controller
public class BillController {
	
	@Autowired
	private BillRepository billRepo;

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
		List<Bill> b= billRepo.findAllByMeterno(10);
		Bill newb=new Bill();	
		System.out.println("This is bill detaisl = "+b.iterator());		
		model.addAttribute("meterno",newb.getMeterno());
		return "bill/quickpay_form";
	}
	@PostMapping("/payquickpay")
	public String payBill(@ModelAttribute(name="paybill")Long customerid, Model model) {
		
		
		return "pay_form";
	}
}
