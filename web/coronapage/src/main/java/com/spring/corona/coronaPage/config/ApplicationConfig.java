package com.spring.corona.coronaPage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.spring.corona.coronaPage"})
@Import({DBConfig.class})
public class ApplicationConfig {
	
}
