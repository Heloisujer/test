<%@page import="com.kl.book.pojo.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>图书信息</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/userlist.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">图书信息管理</h1>
	<table class="list">
		<th colspan="9">
			<form action="books/findByLike"
				method="post">
				<input type="text" placeholder="按书名名搜索" name="bName"
					id="search-input" class="search-input" /> <input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td id="id-p">Id</td>
			<td>分类</td>
			<td>图书书名</td>
			<td>图书作者</td>
			<td>图书简介</td>
			<td>图书图片</td>
			<td>操作</td>
		</tr>

		  <%--<%
			if(books!=null){
				for (Book b : books) {
		%>
		
			<%
				}}
			%>--%>
			<c:forEach items="${books}" var="book">
			<tr class="value-tr">
			    <td class="value-td" id="id-p">${book.id}</td>
			    <td class="value-td">${book.kind.type}</td>
			    <td class="value-td">${book.bName}</td>
			    <td class="value-td">${book.author}</td>
			    <td class="value-td">${book.intro}</td>
			    <td class="value-td"><img class="yu" src="${book.address}" /></td>
			    <td class="value-botton">
				    <input type="button" class="update" value="修改">
			     	<input type="button" class="delete" value="删除">
			    </td>
		    <tr>
		
			</c:forEach>
		
	</table>
	<button type="button" class="add" id="add">增加</button>
	<div class="box">
		<form id="form" class="form" enctype="multipart/form-data">
			<button type="button" class="close" id="close">关闭</button>
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" class="id" />
			</div>
			<div class="box-th">
				<p>分类</p>
				<input type="text" name="type" id="type" class="type" required="required"/>
			</div>
			<div class="box-th">
				<p>图书书名</p>
				<input type="text" name="bName" id="bName" class="bName" required="required"/>
			</div>
			<div class="box-th">
				<p>图书作者</p>
				<input type="text" name="author" id="author"
					class="author" required="required"/>
			</div>
			<div class="box-th">
				<p>图书简介</p>
				<input type="text" name="intro" id="intro"
					class="intro" required="required"/>
			</div>
			<div class="box-th">
				<p>相关图片</p>
				<input type="file" name="file" id="file"
					class="file" />
			</div>
			<input type="submit" class="submit" value="提交" />
		</form>
	</div>
	<div class="box-shadow"></div>
</body>
<script type="text/javascript">
      
	//关闭窗口
	function fun(){
		$(".box").css({
			"display" : "none"
		});

		$(".box-shadow").css({
			"display" : "none"
		});

		/* 清空残留数据 */
		$("#type").val("");
		$("#id").val("");
		$("#bookName").val("");
		$("#author").val("");
		$("#intro").val("");
		$(".submit").removeClass("add_submit").removeClass("update_submit");
	}
	$("#close").click(function() {
		fun();
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
		}
		//添加操作
		//使用时将input的type改为button
	/*	$(".submit").click(function(){
      	$.ajax({
      		type:"post",
      		url:"${pageContext.request.contextPath}/books/addBook",
      		data:$("#form").serialize(),
		    dataType:'json',
      		async:true,
      		success:function(data){
      			alert(data.msg);
      			window.location.reload();
      		}
      	});
      })*/
      $("#form").submit(function(evt){
			evt.preventDefault();
			var fd = new FormData(document.getElementById("form"));
			$.ajax({
				type:"post",
				url:"books/addBook",
				data:fd,
				dataType:'json',
				encType:"multipart/form-data",
				contentType:false,
				processData:false,
				success:function(data){
					alert(data.msg);
    			    window.location.reload();
				}
			});
		})
		
	})

	/*更新按钮*/
	$(".update").click(function() {
		//获取对应的值 填入 表格中
		var id = $(this).parents("tr").find("td").eq(0).html();
		var type = $(this).parents("tr").find("td").eq(1).html();
		var bName = $(this).parents("tr").find("td").eq(2).html();
		var author = $(this).parents("tr").find("td").eq(3).html();
		var intro = $(this).parents("tr").find("td").eq(4).html();
		
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
			$("#type").val(type);
			$("#bName").val(bName);
			$("#author").val(author);
			$("#intro").val(intro);
			$("#form").submit(function(evt){
				//取消form表单的默认提交
				evt.preventDefault();
				var fd = new FormData(document.getElementById("form"));
				$.ajax({
					type:"post",
					url:"books/updateBook",
					data:fd,
					dataType:'json',
					encType:"multipart/form-data",
					contentType:false,
					processData:false,
	      		    success:function(data){
	      			    alert(data.msg);
	      			    window.location.reload();
	      		    }
				});
			})
		}
		//更新
	})
		
		
	
	/*删除按钮*/
	$(".delete").click(function(){
			var bId = $(this).parents("tr").find("td").eq(0).html();
			$.ajax({
				type:"post",
				url:"books/deleteBook?bId="+bId,
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