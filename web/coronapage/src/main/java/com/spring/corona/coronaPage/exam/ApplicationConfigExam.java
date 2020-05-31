package com.spring.corona.coronaPage.exam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class ApplicationConfigExam {
	// @Bean
	public BeanTest beanTest() {
		return new BeanTest();
	}
}
