package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.*;
import com.repository.CustomerRepository;
import com.service.*;



@Controller
public class CustomerController  {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("customer", new Customer());	
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Customer customer) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);	
		customerRepository.save(customer);	
		return "register_success";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		System.out.println("inside the password" + customer.getPassword());
		customerRepository.save(customer);
        return "redirect:/login";
	}
	@GetMapping("/users")
	public String homeUsers(@AuthenticationPrincipal CustomerUserDetails user, Model model) {
	Customer customer = customerRepository.findByEmail(user.getUsername());
		System.out.println("inside the users" + user.getFullName());
		System.out.println("inside the users password  " + customer.getPassword());
		model.addAttribute("Customer",customer);
		return "users_home";
	}
	@GetMapping("/users/showdetails")
	public String detailsUsers(@AuthenticationPrincipal CustomerUserDetails user, Model model) {
		Customer customer = customerRepository.findByEmail(user.getUsername());
		System.out.println("inside the show details" + user.getFullName());
		model.addAttribute("customer",customer);
		return "user_detail";
	}
	@PostMapping("/users/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Customer customer = customerRepository.findByCustomerid(id);     
        model.addAttribute("customer", customer);
        System.out.println("inside the showform  pass" +customer.getPassword());
        return "update_customer";
    }
//	@GetMapping("/quickpay")
//	public String quickPayViewPage(Model model) {
//		Long customerid = null;
//		model.addAttribute("cutomerid",customerid);
//		return "quickpay_form";
//	}
////	@PostMapping("/payquickpay")
////	public String payBill(@ModelAttribute(name="paybill")Long customerid, Model model) {
////		List<>
////		if()
////		
////		return "pay_form";
////	}
	@GetMapping("/knowcustomerid")
	public String knowId(Model model) {
		model.addAttribute("customer", new Customer());
		return "know_form";
	}
	@PostMapping("/knowcustomerid")
	public String knowIds(String email, Model model) {
		Customer customer = (customerRepository.findByEmail(email));
		if(customer !=null) {
			customer=customerRepository.findByEmail(email);
			String msg = "Hello! " +customer.getName() +"  Your customer id is " +"' " +customer.getCustomerid() +" '";		
			model.addAttribute("customerid", msg);
			return "know_form";
		}
		String err= "  Cannot find any customer id for this " + email;
		model.addAttribute("errorMessage",err);
		
		return "know_form";
	}
	
}