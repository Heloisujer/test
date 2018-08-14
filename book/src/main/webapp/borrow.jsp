<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>借书</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/borrow.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>
<body>
	<h1 class="title">借书管理</h1>
	<div class="box">
		<form action="borrows/borrow" id="form" method="post">
			<div class="box-th">
				<p>书本名字</p>
				<input type="text" name="bName" placeholder="请输入书本名字" required="required"/>
			</div>
			<div class="box-th">
				<p>用户</p>
				<input type="text" name="uName" placeholder="请输入用户" value="${msg1.user.name}" required="required" readonly="readonly"/>
			</div>
			<div class="box-th">
				<p>借书日期</p>
				<input type="date" name="borrowDate" required="required"/>
			</div>
			<div class="box-th">
				<p>还书日期</p>
				<input type="date" name="returnDate" required="required"/>
			</div>
			<input type="submit" class="submit" value="提交订单" />
		</form>
	</div>
</body>
<script type="text/javascript">
	/*借书*/
	if("${msg.msg}"!="" && "${msg.msg}"!=null){
		alert("${msg.msg}");
	}
		
</script>
</html>



