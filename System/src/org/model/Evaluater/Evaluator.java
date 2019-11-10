package org.model.Evaluater;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.model.Query;

public class Evaluator{
	String EventName = null;
	String EventID = null;
	String AttackerNum = null;
	String DefenderNum = null;
	String Scores = null;
	String Result=null;
	PrintWriter out;
	public Evaluator(
			String EventName ,
			String EventID ,
			String AttackerNum,
			String DefenderNum,
			String Scores,
			String Result,
			PrintWriter out) {
		this.EventName = EventName;
		this.EventID = EventID;
		this.AttackerNum = AttackerNum;
		this.DefenderNum = DefenderNum;
		this.Scores = Scores;
		this.Result=Result;
		this.out=out;
	}
	public void run() {
		String sql = "select event_id,e_name,e_Status from event where event_id="+EventID;
		ArrayList<String[]> ret =Query.runSql(3,sql);
		if(ret==null||ret.size()==0) {
			out.print("<script>alert('事件不存在或输入有误'); window.location='Evaluate.jsp'</script>");
	    	out.flush();
	    	out.close();
	    	return;
		}
		int Status = Integer.parseInt(ret.get(0)[3]);
		if(Status!=2) {
			out.print("<script>alert('事件尚未启动'); window.location='Evaluate.jsp'</script>");
	    	out.flush();
	    	out.close();
	    	return;
		}
		sql = "insert into evaluate ";
		sql +="(attackerNum,defenderNum,evaluate_result,evaluate_score)";
		sql +=" values ("+AttackerNum+","+DefenderNum+","+Result+","+Scores+","+")";
		Query.addSql(sql);
	}
}
