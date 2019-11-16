package org.model;

public class Audit {
	String audit_id;
	String event_id;
	String ispassed;
	int policeNum;
	String executeTeam;
	String suggestion;
	
	public String getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(String audit_id) {
		this.audit_id = audit_id;
	}
	
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	
	
	public String getIspassed() {
		return ispassed;
	}
	public void setIspassed(String ispassed) {
		this.ispassed = ispassed;
	}
	public int getPoliceNum() {
		return policeNum;
	}
	public void setPoliceNum(int policeNum) {
		this.policeNum = policeNum;
	}
	public String getExecuteTeam() {
		return executeTeam;
	}
	public void setExecuteTeam(String executeTeam) {
		this.executeTeam = executeTeam;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	
}
