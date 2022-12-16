package com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.detailservice.CustomerUserDetails;
import com.model.*;
import com.repository.CustomerRepository;

@Controller
public class CustomerController  {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	@GetMapping("/customer/login")
	public String loginPage() {
		System.out.println("Inside the login controller");
		return "customer/customer_login";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("customer", new Customer());	
		return "customer/signup_form";
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
	
	@GetMapping("/customer/home")
	public String homeUsers(@AuthenticationPrincipal CustomerUserDetails user, Model model) {
		Customer customer = customerRepository.findByEmail(user.getUsername());
		model.addAttribute("Customer",customer);
		return "customer/customer_home";
	}
	
	@GetMapping("/customer/showdetails")
	public String detailsUsers(@AuthenticationPrincipal CustomerUserDetails user, Model model) {
		Customer customer = customerRepository.findByEmail(user.getUsername());
		model.addAttribute("customer",customer);
		return "customer/customer_detail";
	}
	
	@PostMapping("/customer/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Customer customer = customerRepository.findByCustomerid(id);     
        model.addAttribute("customer", customer);
        return "customer/update_customer";
    }

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