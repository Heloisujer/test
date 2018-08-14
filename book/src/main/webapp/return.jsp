<%@page import="com.kl.book.pojo.Record"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<%
	List<Record> records = (List<Record>) request.getAttribute("records");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>归还</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"
	href="css/userlist.css" />
<script type="text/javascript"
	src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">还书管理</h1>
	<table class="list">
		<th colspan="6">
			<form action="returns/findByLike"
				method="post">
				<input type="text" placeholder="按用户名搜索" name="uName"
					id="search-input" class="search-input" /> <input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td id="id-p">Id</td>
			<td>图书书名</td>
			<td>借书用户</td>
			<td>借书时间</td>
			<td>还书时间</td>
			<td>状态</td>
			<td>还书操作</td>
		</tr>

		<%
			if (records != null) {
				for (Record record : records) {
		%>
		<tr class="value-tr">
			<td class="value-td" id="id-p"><%=record.getId()%></td>
			<td class="value-td"><%=record.getbName()%></td>
			<td class="value-td"><%=record.getuName()%></td>
			<td class="value-td"><%=record.getBorrowDate()%></td>
			<td class="value-td"><%=record.getReturnDate()%></td>
			<td class="value-td"><%=record.getState()%></td>
			<td class="value-botton">
				<button type="button" class="delete">归还</button>
			</td>
		<tr>
			<%
				}
				}
			%>
		
	</table>

	<script type="text/javascript">
		/*删除按钮*/
		$(".delete").click(function(){
			var rId = $(this).parents("tr").find("td").eq(0).html();
			$.ajax({
				type:"post",
				url:"returns/returnBook?rId="+rId,
				//data:("#form").serialize(),
				dataType:'json',
				async:true,
				success:function(data){
      			    alert(data.msg);
      			    window.location.reload();
      		    }
			});
		})
		
	</script>
</html>