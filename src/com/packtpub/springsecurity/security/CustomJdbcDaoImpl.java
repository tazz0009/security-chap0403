package com.packtpub.springsecurity.security;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * 이 클래스는 사용자가 요청한 새로운 비밀번호로 데이터베이스에 있는 
 * 비밀번호를 업데이트할 수 있게 기본 JdbcDaoImpl 클래스를 확장한다.
 */
public class CustomJdbcDaoImpl extends JdbcDaoImpl implements IChangePassword {

	@Override
	public void changePassword(String username, String password) {
		getJdbcTemplate().update(
				"UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?",
				password, username);
	}

}
