package org.model.taskView;

import org.springframework.jdbc.core.JdbcTemplate;

import system.utils.JDBCUtils;

public class TaskDeletDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	public void delete(int taskid) {
		String sql="delete from event where event_id=?";
		template.update(sql,taskid);
	}

}
