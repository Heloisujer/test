<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="UTF-8">
	<title>修改密码</title>
	<link rel="stylesheet" type="text/css" href="css/update.css"/>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	</head>
	<body>
		<h1 class="title">修改密码</h1>
		<div class="box">
		<form action="" method="post" class="form" id="form">
			<div class="box1">
				<lable class="l">旧密码：&nbsp;&nbsp;&nbsp;</lable>
				<!--<input type="" name="" id="" value="" />-->
				<input type="password" class="text" placeholder="请填入旧密码" name="opwd" id="opwd" required="required">
			</div>
			<div class="box1">
				<lable class="l">新密码：&nbsp;&nbsp;&nbsp;</lable>
				<input type="password" class="text" placeholder="请填入新密码" name="uPwd1" id="uPwd1" required="required">
			</div>
			<div class="box1">
				<lable class="l">确认密码：</lable>
				<input type="password" class="text" placeholder="请再次输入新密码" name="uPwd2" id="uPwd2" required="required">
			</div>
			<input type="button" name="提交" class="btn submit1" value="确定" onClick="return check(form)" />
			<input type="reset" name="取消" class="btn submit2" value="取消" />
		</form>
	</div>
	</body>
	<script type="text/javascript">
    function check(form){
	    if (form.opwd.value==""){
		    alert("请输入旧密码!");form.opwd.focus();return false;
	    }
	    if (form.uPwd1.value==""){
		    alert("请输入新密码!");form.uPwd1.focus();return false;
	    }	
	    if (form.uPwd2.value==""){
		    alert("请再次确认新密码!");form.uPwd2.focus();return false;
	    }
    }
    $(function() {
		var flag = 0;
		$("#opwd").keyup(function() {
			var opwd = $("#opwd").val();
			$.ajax({
				type : "post",
				dataType : "json",
				data : {
					'opwd' : opwd
				},
				url : "users/checkPwd",
				async : true,
				success : function(data) {
					if (data.status == 0) {
						$("#msg").attr("color", "red").html(data.msg);
					} else {
						flag = 1;
						$("#msg").attr("color", "green").html(data.msg);
					}
				}
			});
		});
		$(".btn").click(function() {
			if (flag == 1) {
				$.ajax({
					type : "post",
					dataType : "json",
					data : $("#form").serialize(),
					url : "users/changePwd",
					async : true,
					success : function(data) {
						if (data.status == 1) {
							alert(data.msg);
							top.location.href = "login.jsp";
						} else {
							alert(data.msg);
							window.location.reload();
						}
					}
				});
			}
		});
	});
</script>
</html>

