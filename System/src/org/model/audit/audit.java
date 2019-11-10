package org.model.audit;

import java.util.ArrayList;

import org.model.Query;

public class audit {
	String Suggestion;
	String EventID;
	String status;
	public audit(String EventID,String Suggestion,String status) {
		this.EventID=EventID;
		this.Suggestion=Suggestion;
		this.status=status;
	}
	//status 未审核0，已审1，已启动2
	public boolean saveAudit() {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		
		//判断 1事件是否存在 2事件是否已经被审核通过
		System.out.println("select e_Status from e_event where event_id="+EventID);
		ret=Query.runSql(1, "select e_Status from e_event where event_id="+EventID);
		if(ret==null||ret.size()==0) {
			System.out.println("[Audit]事件不存在");
			return false;
		}
		else if(ret.get(0)[1].equals("1")) {
			System.out.println("[Audit]事件已被审核通过");
			return false;
		}		
		else if(ret.get(0)[1].equals("2")) {
			System.out.println("[Audit]事件已启动，无需审核");
			return false;
		}
		
		//否则生成新的审核
		String sql = "select max(audit_id) from audit";
		int Audit_id0 = 1;
		ret = Query.runSql(1,sql);
		if(ret!=null&&ret.size()==1) {
			Audit_id0=Integer.parseInt(ret.get(0)[1])+1;
		}
		String Status;
		if(status.equals("通过")) {
			Status="1";
		}
		else {
			Status="0";
		}
		
		sql = "insert into audit (audit_id,event_id,suggestion,e_status) values(";
		sql+=Integer.toString(Audit_id0)+",";
		sql+=EventID+",";
		sql+=Suggestion+",";
		sql+=Status+")";
		Query.addSql(sql);
		sql="update e_event set e_Status="+Status+" where event_id="+EventID;
		Query.addSql(sql);
		System.out.println("[audit] new audit added");
		return true;
	}
}
