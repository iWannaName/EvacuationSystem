package org.model.SystemManage;

public class User {
	private String username;
	private String password;
	private String realname;
	private String unit;
	private String position;
	private String id;
	private String birthdate;
	private String jointime;
	private String tel;
	private String email;
	private String police_id;
	private String name;
	
	public User() {
		
	}
	public User(String username, String realname, String unit, String position, String tel, String email) {
		super();
		this.username = username;
		this.realname = realname;
		this.unit = unit;
		this.position = position;
		this.tel = tel;
		this.email = email;
	}
	public User(String username, String realname, String unit, String position, String id, String birthdate,
			String jointime, String tel, String email) {
		super();
		this.username = username;
		this.realname = realname;
		this.unit = unit;
		this.position = position;
		this.id = id;
		this.birthdate = birthdate;
		this.jointime = jointime;
		this.tel = tel;
		this.email = email;
	}
	public User(String username, String password, String id, String tel, String email) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
		this.tel = tel;
		this.email = email;
	}
//	public User(String username, String id, String tel, String email) {
//		super();
//		this.username = username;
//		this.id = id;
//		this.tel = tel;
//		this.email = email;
//	}
	public User(String username, String password, String tel, String email) {
		super();
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.email = email;
	}
	public User(String username, String password, String realname, String unit, String position, String id,
			String birthdate, String jointime, String tel, String email) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.unit = unit;
		this.position = position;
		this.id = id;
		this.birthdate = birthdate;
		this.jointime = jointime;
		this.tel = tel;
		this.email = email;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getJointime() {
		return jointime;
	}
	public void setJointime(String jointime) {
		this.jointime = jointime;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPolice_id() {
		return police_id;
	}
	public void setPolice_id(String police_id) {
		this.police_id = police_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
