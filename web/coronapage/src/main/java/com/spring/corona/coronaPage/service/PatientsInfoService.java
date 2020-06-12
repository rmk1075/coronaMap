package com.spring.corona.coronaPage.service;

import java.util.List;

import com.spring.corona.coronaPage.dto.PatientsInfoDTO;

public interface PatientsInfoService {
	// TODO: ?
	public static final int LIMIT = 5;
	
	public List<PatientsInfoDTO> getPatientsInfo();
	public PatientsInfoDTO getpatientsInfo(Integer idx);
	public int getCount();
}
