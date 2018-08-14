<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
    <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>主页</title>
		<link rel="stylesheet" href="css/picture.css"/>
    	<script src="js/jquery1.0.0.1.js"></script>
    	<script src="js/js.js"></script>
    	<script src="js/index.js"></script>
    	
	</head>
	 <body>
	  <div class="wrap" id="wrap">
       <div class="slide" id="slide">
           <ul>
               <li><a href="#"><img src="img/home.jpg" alt=""/></a></li>
               <li><a href="#"><img src="img/home.jpg" alt=""/></a></li>
               <li><a href="#"><img src="img/home.jpg" alt=""/></a></li>
               <li><a href="#"><img src="img/home.jpg" alt=""/></a></li>
               <li><a href="#"><img src="img/home.jpg" alt=""/></a></li>
           </ul>
           <!--左右切换按钮-->
           <div class="arrow" id="arrow">
               <a href="javascript:;" class="prev"></a>
               <a href="javascript:;" class="next"></a>
           </div>
       </div>
    </div>
    </body>
</html>