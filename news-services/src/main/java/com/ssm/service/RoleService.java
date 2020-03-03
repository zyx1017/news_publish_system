package com.ssm.service;
import java.util.List;
import com.ssm.po.Role;
/*
 * 角色Service层接口
 */
public interface RoleService {
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> findRoleList();
	/**
	 * 通过角色号查询用户角色
	 * @param roleId
	 * @return
	 */
	public Role findRoleWithUsersByRoleId(Integer roleId);
}