package com.spring.corona.coronaPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.corona.coronaPage.dto.UserTest;

// @Controller
public class TestController {
	
	/**
	 * googleMapTest
	 * @return
	 */
	@GetMapping(path = "/googleMapTest")
	public String googleMapTest() {
		return "test/googleMapTest";
	}
	

	/**
	 * plusTest
	 * @return
	 */
	@GetMapping(path = "/testPage")
	public String testPage() {
		return "test/testPage";
	}
	
	@PostMapping(path = "/testPlus")
	public String testPlus(@RequestParam(name = "val1", required = true) int val1,
			@RequestParam(name = "val2", required = true) int val2, ModelMap modelMap) {
		int result = val1 + val2;
		
		modelMap.addAttribute("val1", val1);
		modelMap.addAttribute("val2", val2);
		modelMap.addAttribute("result", result);
		return "test/testPlus";
	}
	
	/**
	 * userForm test
	 * @return
	 */
	@RequestMapping(path = "/userform", method = RequestMethod.GET)
	public String userform() {
		return "test/userform";
	}
	
	@RequestMapping(path = "/regist", method = RequestMethod.POST)
	public String regist(@ModelAttribute UserTest user) {
		System.out.println("사용자 user info");
		System.out.println(user);
		return "test/regist";
	}
}
