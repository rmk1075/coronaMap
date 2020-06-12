package com.spring.corona.coronaPage.exam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.corona.coronaPage.config.ApplicationConfig;
import com.spring.corona.coronaPage.dto.PatientsInfoDTO;
import com.spring.corona.coronaPage.service.PatientsInfoService;

public class CoronaPageServiceTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PatientsInfoService service = ac.getBean(PatientsInfoService.class);
		
		StringBuilder sb = new StringBuilder();
		sb.append("============전체 환자 정보 조회===============\n");
		for(PatientsInfoDTO patientsInfo : service.getPatientsInfo()) {
			sb.append(patientsInfo + "\n");
		}
		sb.append("======================================\n\n");
		
		sb.append("==========1번 환자 정보 조회===========\n");
		sb.append(service.getpatientsInfo(1) + "\n");
		sb.append("=====================================");
		
		System.out.println(sb);
	}

}
