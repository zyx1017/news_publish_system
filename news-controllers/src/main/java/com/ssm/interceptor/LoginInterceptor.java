package com.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.po.User;

public class LoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
		//获得强求URL
		String url = request.getRequestURI();
		//拦截除了登陆以外的所有请求
		if(url.indexOf("/login.action") >=0){
			return true;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login_user");
		if(user!=null){
			return true;
		}
//		request.getSession().setAttribute("msg", "请先登陆再进行操作");
		request.getRequestDispatcher("/WEB-INF/jsp/skip.jsp").forward(request, response);
		return false;
	}
	
}
