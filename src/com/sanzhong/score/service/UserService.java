package com.sanzhong.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanzhong.score.dao.UserDAO;
import com.sanzhong.score.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	public User getUser(String account) throws Exception{
		List<User> l_user = userDao.findByName(account);
		if(l_user.isEmpty()||l_user==null){
			return null;
		}
		User user = l_user.get(0);
		return user;
	}
}
