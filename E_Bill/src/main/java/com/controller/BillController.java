package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BillController {

	@GetMapping("/billcalculator")
	public String calculatorpage() {
		System.out.println("inside the calculator");
		return "billcal";
	}
}
