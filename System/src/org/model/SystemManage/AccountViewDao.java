package org.model.SystemManage;

import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import system.utils.JDBCUtils;

public class AccountViewDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	public List<User> findAll() {
		// TODO Auto-generated method stub
		 String sql = "select * from user";
         //2.����query����
         List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

         return users;
	}
	

}
