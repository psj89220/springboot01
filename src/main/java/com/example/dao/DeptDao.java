package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.Dept;

import lombok.extern.java.Log;

@Component
@Log
public class DeptDao implements InitializingBean, DisposableBean {
	
	public DeptDao() {
		log.info("###");
		log.info("### 1. DeptDao()");
		log.info("###");
	}
	
	/*
	 * DI
	 * 		1. 생성자 주입(Constructor Injection)
	 * 		2. 세터 주입(Setter Injection)
	 * 		3. 필드 주입(Field Injection)
	 * 
	 */
	
	@Autowired
	public void setDept(Dept dept) {
		log.info("###");
		log.info("### 2. setDept()");
		log.info("###");
		log.info("### " + dept);
		log.info("###");
		this.dept = dept;
	}
	
	@PostConstruct
	public void PostConstruct() {
		log.info("###");
		log.info("### 3. PostConstruct");
		log.info("###");
	}
	
	Dept dept;
	
	@Autowired										// 필드 주입
	DataSource ds;
	
	public List<Dept> selectAll() throws SQLException {
		List<Dept> list = new ArrayList<>();
		
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM city");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			list.add(new Dept(10, rs.getString("name"), "yyy"));
		}
		pstmt.close();
		rs.close();
		conn.close();
		return list;
	}
	
	@PreDestroy
	public void PreDestroy() {
		log.info("###");
		log.info("### 5. PreDestory");
		log.info("###");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("###");
		log.info("### 4. InitializingBean.afterPropertiesSet()");
		log.info("###");
		
	}

	@Override
	public void destroy() throws Exception {
		log.info("###");
		log.info("### 6. DisposableBean.destroy()");
		log.info("###");
	}
}


