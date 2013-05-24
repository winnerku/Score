package com.sanzhong.score.pojo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class User extends BaseBean implements Serializable {

	private Long id;
	private String name;
	private String pass;
	private String account;
	private String reg_time;
	private Integer is_valid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public Integer getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}
	public List<Role> getRoles() {
		return null;
	}
	
}
