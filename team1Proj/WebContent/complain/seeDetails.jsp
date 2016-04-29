 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浏览处理详情</title>
<link type="text/css" href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath }/js/jquery-1.6.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//alert("jquery框架已加载！");
		$("#btnSend")
				.click(
						function() {
							//参数数据
							var account = "zslr";
							var password = "888888";
							var mobile = $("#txtPhone").val();
							var content = $("#txtContent").val();
							var AddSign = "N";
							$
									.ajax({
										type : "GET",
										url : "http://www.smswst.com/api/httpapi.aspx?action=send&account="
												+ account
												+ "&password="
												+ password
												+ "&mobile="
												+ mobile
												+ "&content="
												+ content
												+ "&sendTime=&AddSign="
												+ AddSign,
										success : function(msg) {
											//alert(msg);
										}
									});
						});
	});
</script>
</head>

<body>


	<table width="96%" border="0" cellpadding="3" cellspacing="1"
		bgcolor="#464646" class="newfont03" align="center">
		<caption class="tablestyle_title">投诉单管理-->处理详情</caption>
		<tr>
			<td width="20%" align="right">投诉人:</td>
			<td>${requestScope.obj[0]}</td>
		</tr>
		<tr>
			<td width="20%" align="right">投诉内容:</td>
			<td>${requestScope.obj[1]}</td>
		</tr>

		<tr>
			<td width="20%" align="right">投诉日期:</td>
			<td>${requestScope.obj[2]}</td>
		</tr>
		<tr>
			<td width="20%" align="right">投诉备注:</td>
			<td>
			<c:choose>
				<c:when test="${obj[3]==null}">无备注</c:when>
				<c:otherwise>${obj[3]}</c:otherwise>
			</c:choose>
			</td>
		</tr>

		<tr>
			<td width="20%" align="right">处理人:</td>
			<td>${obj[4]}</td>
		</tr>
		<tr>
			<td width="20%" align="right">完成日期:</td>
			<td>${obj[5]}</td>
		</tr>
		<tr>
			<td width="20%" align="right">投诉单状态:</td> 
			<td>已处理</td> 
		</tr>
	</table>

	<table width="96%" border="0" cellpadding="4" cellspacing="1"
		bgcolor="#464646" class="newfont03" align="center">
		<caption class="tablestyle_title">贴心服务：将处理结果发短信通知业主</caption>

		<tr>
			<td width="18%">输入业主手机号码：</td>
			<td><input id="txtPhone" type="text" size="18" /></td>
		</tr>
		<tr>
			<td>输入要发送的内容：</td>
			<td><input id="txtContent" type="text" size="45" />(<span style="color:red">格式：内容【发送人签名】,如:你好！【张文】</span>)</td>
		</tr>
		<tr>
			<td colspan="2"><input id="btnSend" type="button"
				style="width:63px; height:20px; border:none; background-image:url(${contextPath}/images/btnSubmit.gif); margin-left:15px;"
				value="发  送" /> &nbsp; &nbsp; &nbsp; <input type="button"
				style="width:63px; height:20px; border:none; background-image:url(${contextPath}/images/btnSubmit.gif); margin-left:15px;"
				value="返 回" onclick="javascript:history.go(-1);" /></td>
		</tr>

	</table>
</body>
</html>