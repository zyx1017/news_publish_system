package com.ssm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.po.Role;
import com.ssm.po.User;
import com.ssm.service.RoleService;
import com.ssm.service.UserService;

@Controller
public class UserController {
		//依赖注入
		@Autowired
		private UserService userService;
		@Autowired
		private RoleService roleService;
		//查询所有状态的全体用户
		@RequestMapping(value="/findUserList.action")
		public String findUserList(String keywords,Integer userListRoleId,Model model){
//			System.out.println("aaa");
			// 获取角色列表
			List<Role> roleList = roleService.findRoleList();
			model.addAttribute("roleList", roleList);
			// 获取用户列表
			List<User> userList=userService.findUserList(keywords,userListRoleId);
			model.addAttribute("userList", userList);
			model.addAttribute("keywords", keywords);
			model.addAttribute("userListRoleId", userListRoleId);
			return "user/user_list";
		}
		
		//跳转添加页面
		@RequestMapping("toAddUser.action")
		public String toAddUser(Model model){
			List<Role> roleList = roleService.findRoleList();
			model.addAttribute("roleList", roleList);
			return "user/add_user";
		}
		
		//添加用户
		@PostMapping("addUser.action")
		public String addUser(User user,Model model){
			List<Role> roleList = roleService.findRoleList();
			model.addAttribute("roleList", roleList);
			model.addAttribute("user", user);
			//检查用户是否已经存在
			User checkUser = userService.getUserByLoginName(user.getLoginName());
			if(checkUser!=null){
			
				model.addAttribute("checkUserLoginNameMsg", "用户名已存在，注册失败");
				return "user/add_user";
			}else{
				Date date = new Date();
				user.setRegisterTime(date);
				user.setStatus("2");
				int flag = userService.addUser(user);
				if(flag >0){
					return "redirect:findUserList.action";
				}else{
					model.addAttribute("checkUserLoginNameMsg", "注册失败,请重新尝试");
					return "user/add_user";
				}
			}	
		}
		
		//跳转修改页面
		@RequestMapping("toEditUser.action")
		public String toEditUser(Integer userId,Model model){
			
			User user = userService.getUserByUserId(userId);
			if(user!=null){
				model.addAttribute("user", user);
				List<Role> roleList = roleService.findRoleList();
				model.addAttribute("roleList", roleList);
				return "user/edit_user";
			}else{
				return "redirect:findUserList.action";
			}
		}
		
		//修改用户
		@PostMapping("/editUser.action")
		public String editUser(User user,Model model){
			Date date = new Date();
			user.setRegisterTime(date);
			user.setStatus("2");
			int flag = userService.editUser(user);
			if(flag >0){
				System.out.println("aa");
				return "redirect:findUserList.action";
			}else{
				System.out.println("bb");
				List<Role> roleList = roleService.findRoleList();
				model.addAttribute("roleList", roleList);
				model.addAttribute("user", user);
				model.addAttribute("checkUserLoginNameMsg", "修改失败，请稍后再试");
				return "user/edit_user";
			}
		}
		
		//删除用户
		@RequestMapping("delUser.action")
		@ResponseBody
		public User delUser(@RequestBody User user,Model model){
			
			int flag = userService.delUser(user.getUserId());
			if(flag >0){
				return user;
			}else{
				//设置userId为0
				user.setUserId(0);
				return user;
			}
		}
		
		//禁用用户（更新status字段值 为'3'，前台页面中通过ajax方式调用此方法）
		@RequestMapping(value = "/disableUser.action")
		@ResponseBody
		public User disableUser(@RequestBody User user, Model model) {
			int rows = userService.setUserStatus(user);
			if (rows>0) {
				return user;
			}else{
				//此处设置userId为0，只是作为操作失败的标记用
				user.setUserId(0);
				return user;
			}
		}
		//启用用户（更新status字段值 为'2'，前台页面中通过ajax方式调用此方法）
		@RequestMapping(value = "/enableUser.action")
		@ResponseBody
		public User enableUser(@RequestBody User user, Model model) {
			int rows = userService.setUserStatus(user);
			if (rows>0) {
				return user;
			}else{
				//此处设置userId为0，只是作为操作失败的标记用
				user.setUserId(0);
				return user;
			}
		}
		
		//用户登录
		@RequestMapping(value="login.action",method=RequestMethod.POST)
		public String login(String loginName,String password,Model model,HttpSession session){
			User user = userService.findUser(loginName, password);
			session.removeAttribute("msg");
			if(user != null){
				if(user.getStatus().equals("2")){
					//用户正常使用
					session.setAttribute("login_user", user);
					return "main";
				}else{
					model.addAttribute("msg", "用户使用异常，请联系管理员");
					return "../../login";
				}
			}else{
				model.addAttribute("msg", "用户名错误或密码错误，请重新登陆");
				return "../../login";
			}
		}
		
		//退出登陆
		@RequestMapping("logout.action")
		public String logout(HttpSession session){
			session.invalidate();
			return "../../login";
		}
}
