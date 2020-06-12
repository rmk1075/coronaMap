package com.spring.corona.coronaPage.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.spring.corona.coronaPage.dto.PatientsInfoDTO;

@Repository
public class PatientsInfoDAO {
	private NamedParameterJdbcTemplate jdbc; // jdbc template를 사용한다 - template의 이미 작성되어 있는 query를 사용
	private SimpleJdbcInsert jdbcInsert; // jdbc insert
	private RowMapper<PatientsInfoDTO> rowMapper = BeanPropertyRowMapper.newInstance(PatientsInfoDTO.class);
	private String tableName = "patientsInfo";
	
	// sql query
	private static final String SELECT_ALL = "SELECT * FROM PATIENTSINFO ORDER BY IDX";
	private static final String UPDATE = "UPDATE PATIENTSINFO SET ADDRESS = :address WHERE IDX = :idx";
	private static final String SELECT_BY_IDX = "SELECT * FROM PATIENTSINFO WHERE IDX = :idx";
	private static final String DELETE_BY_IDX = "DELETE FROM PATIENTSINFO WHERE IDX = :idx";
	
	public PatientsInfoDAO(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(tableName);
	}
	
	public List<PatientsInfoDTO> selectAll() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper); // sql, bind하는 부분...?, query의 결과를 DTO에 mapping - 저장하기 위한 부분 
	}
	
	public PatientsInfoDTO selectByIdx(Integer idx) {
		try {
			Map<String, ?> params = Collections.singletonMap("idx", idx);
			return jdbc.queryForObject(SELECT_BY_IDX, params, rowMapper);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	// for practice - insert, update, delete
	public int insert(PatientsInfoDTO patient) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(patient);
		try {
			return jdbcInsert.execute(params);				
		} catch (DuplicateKeyException e) {
			System.out.println("이미 해당 idx의 데이터가 존재합니다.");
			return 0;
		}
	}
	
	public int update(PatientsInfoDTO patient) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(patient);
		return jdbc.update(UPDATE, params);
	}
	
	public int deleteById(Integer idx) {
		Map<String, ?> params = Collections.singletonMap("idx", idx);
		return jdbc.update(DELETE_BY_IDX, params);
	}
}
