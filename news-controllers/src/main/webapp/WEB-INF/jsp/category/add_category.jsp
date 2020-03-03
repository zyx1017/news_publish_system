<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加分类</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="${pageContext.request.contextPath}/findCategoryList.action">分类列表</a></li>
    </ul>
    </div>    
    <div class="formbody">    
    <div class="formtitle"><span>添加分类</span></div>
	   <form action="${pageContext.request.contextPath}/addCategory.action" name="ff" method="post" onsubmit="return checkValue()"> 
	   <ul class="forminfo">
    <li><label style="width:150px;">分类名称（<font style="color:Red;font-weight:bolder;">*</font>）：</label><input id="categoryName" name="categoryName"  type="text" class="dfinput" /><i>&nbsp;<font style="color:Red;">${checkMsg}</font></i></li>
   	
	<i></i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认添加"/>
    &nbsp;&nbsp;<input name="" type="button" onclick="goback();"class="btn" value="返回列表"/></li>
    </ul>
    </form>  
    </div>
    <script>
    function goback(){
    	window.location.href="${pageContext.request.contextPath}/findCategoryList.action";    	
    }
    function checkValue() {		
		var str = document.getElementById("categoryName").value;
		if (str.length < 1) {
			alert("请输入分类名称");
			document.getElementById("categoryName").focus();
			return false;
		}
		$.ajax({
			url:"${pageContext.request.contextPath }/checkCategoryName.action",
			type:"post",
			//data表示发送的数据
			data:JSON.stringify({categoryName:str}),
			// 定义发送请求的数据格式为JSON字符串
			contentType:"application/json;charset=UTF-8",
			//定义回调响应的数据格式为JSON字符串，该属性可以省略
			dataType:"json",
			//成功响应的结果
			success:function(data){
				if(data!=null){
						return true;							
					}else{
						alert("名字已存在，请更改新名字")
						return false;
					}
				}
  		});
	}
</script>
</body>
</html>