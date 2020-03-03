package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.UserDao;
import com.ssm.po.User;
import com.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User> findUserList(String keywords, Integer userListRoleId) {
		return userDao.selectUserList(keywords, userListRoleId);
	}

	public User findUser(String loginName, String password) {
		return userDao.findUser(loginName, password);
	}

	public User getUserByUserId(Integer userId) {
		return userDao.getUserByUserId(userId);
	}

	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	public int editUser(User user) {
		return userDao.updateUser(user);
	}

	public int addUser(User user) {
		return userDao.addUser(user);
	}

	public int delUser(Integer userId) {
		return userDao.delUser(userId);
	}

	public int setUserStatus(User user) {
		return userDao.setUserStatus(user);
	}

}
