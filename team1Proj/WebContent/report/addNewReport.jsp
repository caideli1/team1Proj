<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>录入报修单</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</head>

<body>
	<form action="${pageContext.request.contextPath}/userservlet?method=addNewreport" method="post">
		<!-- 此处使用隐藏域将报修人的Id保存起来 -->
		<input type="hidden" name="tb_report.userId" value="${sessionScope.userinfo.userId}" />
		<!-- 此处使用隐藏域保存报修单的初始状态值  -->
		<input type="hidden" name="tb_report.reportState" value="0" />
		<input type="hidden" name="tb_report.handlerId" value="1" />

		<table width="85%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#464646" class="newfont03" align="center">
			<caption class="tablestyle_title">服务中心-->我要报修</caption>

			<tr>
				<td width="20%" align="right">问题类别:</td>
				<td><select name="tb_report.reportName">
						<option value="水电">水电</option>
						<option value="生活(服务)">生活(服务)</option>
							<option value="家居装修">家居装修</option> 
				</select></td>
			</tr>
			<tr>
				<td width="20%" align="right">问题描述:</td>
				<td><input type="text" name="tb_report.question" class="text"
					style="width:254px" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">报修备注:</td>
				<td><input type="text" name="tb_report.reamark" class="text"
					style="width:254px" /></td>
			</tr>

			<tr>
				<td width="20%" align="right">报修日期:</td>
				<td><input type="text" name="tb_report.reportDate" class="text"
					style="width:154px" onClick="WdatePicker()" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)"
					value="录 入" /> <input type="button"
					style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;"
					value="返 回" onclick="javascript:history.go(-1);" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
