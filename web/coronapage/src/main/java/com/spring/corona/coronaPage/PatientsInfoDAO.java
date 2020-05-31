package com.spring.corona.coronaPage;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.corona.coronaPage.dto.PatientsInfoDTO;

@Repository
public class PatientsInfoDAO {
	private NamedParameterJdbcTemplate jdbc; // jdbc template를 사용한다 - template의 이미 작성되어 있는 query를 사용
	private RowMapper<PatientsInfoDTO> rowMapper = BeanPropertyRowMapper.newInstance(PatientsInfoDTO.class);
	
	private final String SELECT_ALL = "SELECT * FROM PATIENTSINFO";
	
	public PatientsInfoDAO(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<PatientsInfoDTO> selectAll() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper); // sql, bind하는 부분...?, query의 결과를 DTO에 mapping - 저장하기 위한 부분 
	}
}
