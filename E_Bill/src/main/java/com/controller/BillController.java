package com.controller;

import java.util.Iterator;
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
		model.addAttribute("bill", new Bill());
		return "bill/quickpay_form";
	}
	@PostMapping("/payquickpay")
	public String payBill(@ModelAttribute(name="paybill")Long customerid, Model model) {
//		List<Bill> b= billRepo.findAllByMeterno(10);
//		Bill newb=new Bill();	
//		System.out.println("This is bill detaisl = "+b.iterator());		
//		model.addAttribute("meterno",newb.getMeterno());
		
		return "pay_form";
	}
	@PostMapping("/upayquickpay")
	public String upayBill(@ModelAttribute(name="bill")Bill bill, Model model) {
		int meterno=bill.getMeterno();
		System.out.println("this is meter no = " +meterno);
		List<Bill> b=billRepo.findAllByMeterno(bill.getMeterno());
		System.out.println("This is bill finded meterno = "+b.toString());
	
		Iterator<Bill> i = b.iterator();
		while(i.hasNext()) {
			Bill e=i.next();
			if((e.getStatus()).equals("Not paid")) {
				System.out.println(e.getMeterno()); 
				System.out.println(e.getAmount()); 
				System.out.println(e.getMonth());
			}
			}
//		List<Bill> c=(billRepo.findAllByMeternoAndStatus(bill.getMeterno(),"not paid"));
//		for(Object temp:c) {
//			System.out.println("inside for each "+temp);
//		}
//		Iterator<Bill> i = c.iterator();
//		while(i.hasNext()) {
//			Bill e=i.next(); 
//			System.out.println(e.getMeterno()); 
//			System.out.println(e.getAmount()); 
//			System.out.println(e.getMonth());
//			}
		
//		model.addAttribute("meterno",newb.getMeterno());
		
		return "bill/pay_form";
	}
}
