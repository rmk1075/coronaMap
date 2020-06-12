package com.spring.corona.coronaPage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.corona.coronaPage.dao.PatientsInfoDAO;
import com.spring.corona.coronaPage.dto.PatientsInfoDTO;
import com.spring.corona.coronaPage.service.PatientsInfoService;

@Service
public class PatientsInfoServiceImpl implements PatientsInfoService {
	@Autowired
	PatientsInfoDAO patientsInfoDao;

	@Override
	@Transactional
	public List<PatientsInfoDTO> getPatientsInfo() {
		return patientsInfoDao.selectAll();
	}

	@Override
	@Transactional
	public PatientsInfoDTO getpatientsInfo(Integer idx) {
		return patientsInfoDao.selectByIdx(idx);
	}

	@Override
	@Transactional
	public int getCount() {
		return patientsInfoDao.selectAll().size();
	}
	
	
}
