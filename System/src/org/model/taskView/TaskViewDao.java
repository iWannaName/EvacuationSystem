package org.model.taskView;

import java.util.List;

import org.model.Event1;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import system.utils.JDBCUtils;



public class TaskViewDao {
private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public List<Event1> findAll() {
		
	         //1.��дsql
	         String sql = "select * from event order by e_Status,DrillTime DESC";
	         //2.����query����
	         List<Event1> events = template.query(sql, new BeanPropertyRowMapper<Event1>(Event1.class));

	         return events;
	    
	}

}
