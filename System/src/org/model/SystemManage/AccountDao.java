package org.model.SystemManage;

import org.model.Event1;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import system.utils.JDBCUtils;

public class AccountDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	public User findUserById(String userid) {
		// TODO Auto-generated method stub		
		String sql="select * from user where police_id=?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),userid);
		
	}

	
	

}
