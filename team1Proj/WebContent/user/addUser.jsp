<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>录入新用户</title>
		<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
		<script type="text/javascript">
//定义添加用户时各种验证函数
//全局根据id获取元素的函数
function $(id) {
	return document.getElementById(id);
}

//检验用户名
function checkUserName() {
	//获取用户名
	var userName = $("user_Name").value;
	var reg = /^\w{6,12}$/;
	if (!reg.test(userName)) {
		$("lblUserName").innerHTML = "<img src='${pageContext.request.contextPath}/images/pic22.gif' />用户名由字母、数字或下划线组成，长度6-12位！";
		return false;
	}
	$("lblUserName").innerHTML = "<img src='${pageContext.request.contextPath}/images/pic21.gif' />";
	return true;
}
//检验密码
function checkPwd(){
   //获取 密码
   var user_Pwd=$("user_Pwd").value;
   	var reg = /^\w{6,12}$/;
	if (!reg.test(user_Pwd)) {
		$("lblUserPwd").innerHTML = "<img src='${pageContext.request.contextPath}/images/pic22.gif' />密码由字母、数字或下划线组成，长度6-12位！";
		return false;
	}
	$("lblUserPwd").innerHTML = "<img src='${pageContext.request.contextPath}/images/pic21.gif' />";
	return true;
}
//检验确认密码
function checkRePwd(){
   //获取确认密码
   var confirm_Pwd=$("confirm_Pwd").value;
   //如果两次密码不一致
   if(confirm_Pwd!=$("user_Pwd").value){
       $("lblRePwd").innerHTML="<img src='${pageContext.request.contextPath}/images/pic22.gif' />两次输入的密码不一致！";
       return false;
   }
   	$("lblRePwd").innerHTML = "<img src='${pageContext.request.contextPath}/images/pic21.gif' />";
	return true;
}
//检验姓名
function checkRealName(){
  if($("real_Name").value==""){
		$("lblRealName").innerHTML = "<img src='${pageContext.request.contextPath}/images/pic22.gif' />姓名不能为空！";
		return false;
	}
	$("lblRealName").innerHTML = "<img src='${pageContext.request.contextPath}/images/pic21.gif' />";
	return true;
   
}
//检测表单提交函数
function checkReg(){
   if(checkUserName()&&checkPwd()&&checkRePwd()&&checkRealName()){
      //alert("通过检验");
      return true;
   }
   //alert("未通过检验!");
   return false;
}
</script>
	</head>

	<body>
		<form action="${pageContext.request.contextPath}/userservlet?method=addUser"  onsubmit="return checkReg();" method="post">
			<!-- 此处使用隐藏域将工作人员的角色Id保存起来 -->
			<input type="hidden" name="tb_user.roleId" value="r002" />
			<table width="94%" border="0" cellpadding="3" cellspacing="1"
				bgcolor="#464646" class="newfont03" align="center">
				<caption class="tablestyle_title">
					人员管理-->工作人员添加
				</caption>
				<tr>
					<td width="20%" align="right">
						用户名:
					</td>
					<td width="30%">
						<input type="text" id="user_Name" name="tb_user.userName"
							class="text" style="width: 154px" onblur="checkUserName();" />
					</td>
					<td width="50%">
						<span id="lblUserName" style="color: red;">*请输入用户名</span>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						密码:
					</td>
					<td width="30%">
						<input type="password" id="user_Pwd" name="tb_user.userPwd" class="text"
							style="width: 154px" onblur="checkPwd();"/>
					</td>
					<td width="50%">
						<span id="lblUserPwd"  style="color: red;">*请输入密码</span>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						确认密码:
					</td>
					<td width="30%">
						<input type="password" id="confirm_Pwd" name="confirmPwd" class="text"
							style="width: 154px"  onblur="checkRePwd();" />
					</td>
					<td width="50%">
						<span id="lblRePwd" style="color: red;">*请再次输入密码</span>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						姓名:
					</td>
					<td width="30%">
						<input type="text" id="real_Name" name="tb_user.realName" class="text"
							style="width: 154px" onblur="checkRealName();" />
					</td>
					<td width="50%">
						<span id="lblRealName" style="color: red;">*请输入姓名！</span>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit"
							style="width: 63px; height: 20px; border: none; background-image: url(${pageContext.request.contextPath}/images/btnSubmit.gif)"
							value="保 存" />
						<input type="reset"
							style="width: 63px; height: 20px; border: none; background-image: url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left: 15px;"
							value="取 消" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>