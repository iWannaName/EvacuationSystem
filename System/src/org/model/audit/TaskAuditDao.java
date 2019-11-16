package org.model.audit;

import org.model.Audit;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import system.utils.JDBCUtils;

public class TaskAuditDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public void audit(Audit audit){
	try {
        //1.编写sql
        String sql = "insert into audit(audit_id,event_id,ispassed,policeNum,executeTeam,suggestion)values(null,?,?,?,?,?)";
        //2.调用query方法
        template.update(sql,audit.getEvent_id(),audit.getIspassed(),audit.getPoliceNum(),audit.getExecuteTeam(),audit.getSuggestion());     
    } catch (DataAccessException e) {
        e.printStackTrace();//记录日志
        //return null;
    }
	}

}
