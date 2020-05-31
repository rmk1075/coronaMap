package com.spring.corona.coronaPage.exam;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.corona.coronaPage.PatientsInfoDAO;
import com.spring.corona.coronaPage.config.ApplicationConfig;
import com.spring.corona.coronaPage.dto.PatientsInfoDTO;

public class JDBCTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		PatientsInfoDAO dao = ac.getBean(PatientsInfoDAO.class);
		
		List<PatientsInfoDTO> result = dao.selectAll();
		for(PatientsInfoDTO patient : result) {
			System.out.println(patient);
		}
	}

}
