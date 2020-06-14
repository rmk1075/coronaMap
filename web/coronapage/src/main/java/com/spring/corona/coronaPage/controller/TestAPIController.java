package com.spring.corona.coronaPage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.corona.coronaPage.dto.PatientsInfoDTO;
import com.spring.corona.coronaPage.service.PatientsInfoService;

@RestController
@RequestMapping(path="/apiTest")
public class TestAPIController {
	@Autowired
	PatientsInfoService patientsInfoService;
	
	@GetMapping
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<PatientsInfoDTO> list = new ArrayList<PatientsInfoDTO>();
		
		List<PatientsInfoDTO> entire = patientsInfoService.getPatientsInfo();
		int count = patientsInfoService.getCount();
		
		int idx = start;		
		while(idx < start + PatientsInfoService.LIMIT && idx < count) list.add(entire.get(idx++));
		
		int pageCount = count / PatientsInfoService.LIMIT;
		if(count % PatientsInfoService.LIMIT > 0) pageCount++;
		
		List<Integer> pageList = new ArrayList<Integer>();
		for(int i = 0; i < pageCount; i++) pageList.add(i * PatientsInfoService.LIMIT);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageList);
		
		// TODO:
		System.out.println("request");
		
		return map;
	}
}
