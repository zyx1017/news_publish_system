<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- <script type="text/javascript" >
   	
    top.location="login.jsp";
    
   
    </script> -->
    <%
    	request.setAttribute("msg", "请先登陆再操作");
    	request.getRequestDispatcher("../../login.jsp").forward(request, response);
    %>