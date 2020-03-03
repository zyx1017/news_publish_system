package com.ssm.service;
import java.util.List;
import com.ssm.po.User;
/*
 * 用户Service层接口
 */
public interface UserService {
	/**
	 * 查询所有用户
	 * @param keywords
	 * @param userListRoleId
	 * @return
	 */
	public List<User> findUserList(String keywords, Integer userListRoleId);
	/**
	 * 通过登录名和密码查询用户
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User findUser(String loginName,String password);
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
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int editUser(User user);
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);
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
