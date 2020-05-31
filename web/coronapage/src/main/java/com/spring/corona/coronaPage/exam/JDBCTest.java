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
		PatientsInfoDTO patient = new PatientsInfoDTO();
		
		// insert
		patient.setIdx(100);
		patient.setAge(99);
		patient.setAddress("서울시");
		patient.setGender("남성");
		patient.setLat("123.123");
		patient.setLng("123.123");
		int insertCount = dao.insert(patient);
		System.out.println("alert: " + insertCount + "건이 입력되었습니다.");
		
		insertCount = dao.insert(patient);
		System.out.println("alert: " + insertCount + "건이 입력되었습니다.");
		
		// update
//		patient.setIdx(100);
//		patient.setAddress("대구시");
//		int updateCount = dao.update(patient);
//		System.out.println("alert: " + updateCount + "건이 수정되었습니다.");
		
		// searchAll
		List<PatientsInfoDTO> result = dao.selectAll();
		for(PatientsInfoDTO p : result) {
			System.out.println(p);
		}
		
		patient = dao.selectByIdx(100);
		System.out.println(patient);
		
		System.out.println("alert: " + dao.deleteById(100) + "건이 삭제되었습니다.");
		
		System.out.println(dao.selectByIdx(100));
	}

}
