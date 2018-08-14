<%@page import="com.kl.book.pojo.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%
	List<User> users = (List<User>) request.getAttribute("users");
	%>
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户信息管理</title>
<base href="<%=basePath%>">
<link rel="stylesheet"
	href="css/userlist.css" />
<script type="text/javascript"
	src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">用户信息</h1>
	<table class="list">
		<th colspan="6">
			<form action="users/findByLike"
				method="post">
				<input type="text" placeholder="按用户名搜索" name="uName"
					id="search-input" class="search-input" /> <input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td id="id-p">Id</td>
			<td>用户名</td>
			<td class="pswd">密码</td>
			<td>联系方式</td>
			<td>Email</td>
			<td>姓名</td>
			<td>操作</td>
		</tr>
		<%--<%
			if (users != null) {
				for (User u : users) {
		%>
		<tr class="value-tr">
			<td class="value-td" id="id-p"><%=u.getId()%></td>
			<td class="value-td"><%=u.getuName()%></td>
			<td class="value-td pswd"><%=u.getuPwd()%></td>
			<td class="value-td"><%=u.getPhone()%></td>
			<td class="value-td"><%=u.getEmail()%></td>
			<td class="value-td"><%=u.getName()%></td>
			<td class="value-botton">
				<button type="button" class="update">修改</button>
				<button type="button" class="delete">删除</button>
			</td>
		<tr>
			<%
				}
				}
			%>
			--%>
		<c:forEach  items="${users}" var="user">
		<tr class="value-tr">
			<td class="value-td" id="id-p">${user.id}</td>
			<td class="value-td">${user.uName}</td>
			<td class="value-td pswd">${user.uPwd}</td>
			<td class="value-td">${user.phone}</td>
			<td class="value-td">${user.email}</td>
			<td class="value-td">${user.name}</td>
			<td class="value-botton">
				<button type="button" class="update">修改</button>
				<button type="button" class="delete">删除</button>
			</td>
		<tr>
		</c:forEach>
	</table>
	<button type="button" class="add" id="add">增加</button>
	<div class="box">
		<form id="form">
			<button type="button" class="close" id="close">关闭</button>
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" class="id"  />
			</div>
			<div class="box-th">
				<p>用户</p>
				<input type="text" name="uName" id="uName" class="uName" required="required" />
			</div>
			<div class="box-th pswd" >
				<p>密码</p>
				<input type="password" name="upwd" id="upwd" class="upwd" />
			</div>
			<div class="box-th">
				<p>手机</p>
				<input type="text" name="phone" id="phone" class="phone" required="required" />
			</div>
			<div class="box-th">
				<p>邮箱</p>
				<input type="text" name="email" id="email" class="email" required="required" />
			</div>
			<div class="box-th">
				<p>姓名</p>
				<input type="text" name="name" id="name" class="name" required="required" />
			</div>
			<input type="submit" class="submit" value="提交" />
		</form>
	</div>
	<div class="box-shadow"></div>
</body>
<script type="text/javascript">
	$("#close").click(function() {
		$(".box").css({
			"display" : "none"
		});

		$(".box-shadow").css({
			"display" : "none"
		});

		/* 清空残留数据 */
		$("#id").val("");
		$("#uName").val("");
		$("#upwd").val("");
		$("#phone").val("");
		$("#email").val("");
		$("#name").val("");
		$(".submit").removeClass("add_submit").removeClass("update_submit");
	});

	/*添加按钮*/
	$("#add").click(function() {
		if ($(".box").css("display") == "none") {
			/*先让添加框显示*/
			$(".box").css({
				"display" : "block"
			});

			$(".submit").attr({
				"value" : "添加"
			});

			$(".submit").addClass("add_submit");

			$(".box-shadow").css({
				"display" : "block"
			});
			//添加
			$("#form").submit(function(evt){
				//取消form表单的默认提交
				evt.preventDefault();
				$.ajax({
					type:"post",
					url:"users/addUser",
					data:$('#form').serialize(),
					dataType:'json',
					async:true,
					success:function(data){
						alert(data.msg);
						window.location.reload();
					}
			});
		})
		
		}

	})

	//点击修改按钮
	$(".update").click(function() {
		//获取值
         var id = $(this).parents("tr").find("td").eq(0).html();
         var uName = $(this).parents("tr").find("td").eq(1).html();
         var uPwd = $(this).parents("tr").find("td").eq(2).html();
         var phone = $(this).parents("tr").find("td").eq(3).html();
         var email = $(this).parents("tr").find("td").eq(4).html();
         var name = $(this).parents("tr").find("td").eq(5).html();
         
		if ($(".box").css("display") == 'none') {

			$(".box").css({
				"display" : "block"
			});

			$(".submit").attr({
				"value" : "修改"
			});

			$(".submit").addClass("update_submit");

			/* 阴影 */
			$(".box-shadow").css({
				"display" : "block"
			});

			//先给前端修改页面显示：修改数据
			$("#id").val(id);
			$("#uName").val(uName);
			$("#uPwd").val(uPwd);
			$("#phone").val(phone);
			$("#email").val(email);
			$("#name").val(name);
			//修改
			$("#form").submit(function(evt){
     	        evt.preventDefault();
     	         $.ajax({
     	        	type:"post",
     		        url:"users/updateUser",
     		        data:$("#form").serialize(),
     		        dataType:"json",
     		        success:function(data){
     			        alert(data.msg);
     			        window.location.reload();
     		        }
     	      });
           })
		}
	})

	//删除
	$(".delete").click(function(){
			var uId = $(this).parents("tr").find("td").eq(0).html();
			$.ajax({
				type:"get",
				url:"users/deleteUser?uId="+uId,
				dataType:'json',
				async:true,
				success:function(data){
      			    alert(data.msg);
      			    //window.location.reload();
      		    }
			});
		})
	
	
	
	
</script>
</html>