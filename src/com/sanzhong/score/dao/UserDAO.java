package com.sanzhong.score.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sanzhong.score.pojo.User;

@Component
public class UserDAO extends BaseDAO{

	@SuppressWarnings("unchecked")
	public List<User> findByName(String account) throws Exception {
		String sql = "select id,name,pass,account,reg_time,is_valid from user where account=?";
		List<User> list_user = query(sql,new Object[]{account},User.mapRow(User.class));
		return list_user;
	}
}
