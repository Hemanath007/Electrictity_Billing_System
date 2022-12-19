package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Bill;
import com.service.BillService;

@Controller
public class AdminController {

    @Autowired
    private BillService billService;
    
	@GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin/admin_login";
    }
     
    @GetMapping("/admin/home")
    public String viewAdminHomePage(Model model) {
    	 model.addAttribute("listBills", billService.getAllBills());
    	 
    	 return "admin/admin_home";
    }
    
    @GetMapping("/admin/showNewBillForm")
    public String showNewBillForm(Model model) {
        Bill bill= new Bill();
        model.addAttribute("bill", bill);
        return "bill/new_bill";
    }

    @PostMapping("/admin/saveBill")
    public String saveBill(@ModelAttribute("bill") Bill bill) {
        billService.saveBill(bill);
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {   
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        return "bill/update_bill";
    }

    @GetMapping("admin/deleteBill/{id}")
    public String deleteBill(@PathVariable(value = "id") int id) { 
        this.billService.deleteBillById(id);
        return "redirect:/admin/home";
    }
}
