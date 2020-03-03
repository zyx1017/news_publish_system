package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.po.User;

public interface UserDao {
		
		/*
		 * 查询所有用户
		 */
		public List<User> selectUserList(@Param("keywords") String keywords, @Param("userListRoleId") Integer userListRoleId);
		/**
		 * 通过用户名密码查询用户
		 * @param loginName
		 * @param password
		 * @return
		 */
		public User findUser(@Param("loginName") String loginName,@Param("password") String password);
		
		/**
		 * 通过id查询用户
		 * @param userId
		 * @return
		 */
		public User getUserByUserId(Integer userId);
		
		/**
		 * 通过用户名查询用户
		 * @param loginName
		 * @return
		 */
		public User getUserByLoginName(String loginName);
		/**
		 * 增加用户
		 * @param user
		 * @return
		 */
		public int addUser(User user);
		/**
		 * 更新用户
		 * @param user
		 * @return
		 */
		public int updateUser(User user);
		/**
		 * 删除用户
		 * @param userId
		 * @return
		 */
		public int delUser(Integer userId);
		/**
		 * 改变用户状态
		 * @param user
		 * @return
		 */
		public int setUserStatus(User user);
}
