<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>First.jsp</title>
    <style type="text/css">
			html,body{
				width: 100%;
	            height: 100%;
				background: #eff3f6; 
			}
			.form {
				position: relative;
				top:10% ;
				left: 30%;
			}
			.form .file {
				border: none;
				width: 200px;
				height: 30px;
				outline: none;
				text-align: center;
				}
				.sub {
					/*display: block;*/
					width: 60px;
					height: 25px;
					line-height: 25px;
					text-align: center;
					border-radius: 4px;
					font-size: 15px;
					color: #fff;
					background-color: cornflowerblue;
					border: none;
					cursor: pointer;
					}
		</style>

  </head>
  
  <body>
		<form action="users/poi" method="post" enctype="multipart/form-data" class="form">
			<input type="file" name="file" id="file" required="required" class="file"/>
			<input type="submit" value="提交" class="sub"/>
		</form>
  </body>
</html>

