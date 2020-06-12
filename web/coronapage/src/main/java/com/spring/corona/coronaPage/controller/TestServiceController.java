package com.spring.corona.coronaPage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.corona.coronaPage.dto.PatientsInfoDTO;
import com.spring.corona.coronaPage.service.PatientsInfoService;

@Controller
public class TestServiceController {
	@Autowired
	PatientsInfoService patientsInfoService;
	
	@GetMapping(path="/list")
	public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start, ModelMap model) {
		List<PatientsInfoDTO> list = new ArrayList<PatientsInfoDTO>();
		
		List<PatientsInfoDTO> entire = patientsInfoService.getPatientsInfo();
		int count = patientsInfoService.getCount();
		
		int idx = start;		
		while(idx < start + PatientsInfoService.LIMIT && idx < count) list.add(entire.get(idx++));
		
		int pageCount = count / PatientsInfoService.LIMIT;
		if(count % PatientsInfoService.LIMIT > 0) pageCount++;
		
		List<Integer> pageList = new ArrayList<Integer>();
		for(int i = 0; i < pageCount; i++) pageList.add(i * PatientsInfoService.LIMIT);
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageList);
		
		return "list";
	}
}
