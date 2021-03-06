<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维修详情</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.6.min.js" type="text/javascript"></script>
 <script type="text/javascript">
     $(function () {
        //alert("jquery框架已加载！");
            $("#btnSend").click(function () {
               //参数数据
                var account = "zslr";
                var password = "888888";
                var mobile = $("#txtPhone").val();
                var content = $("#txtContent").val();
                var AddSign = "N";
                $.ajax({
                    type: "GET",
                    url: "http://www.smswst.com/api/httpapi.aspx?action=send&account=" + account + "&password=" + password + "&mobile=" + mobile + "&content=" + content + "&sendTime=&AddSign=" + AddSign,
                    success: function (msg) {
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
		<caption class="tablestyle_title">报修单管理-->维修详情</caption>

		<tr>

			<td>
				<fieldset style="border:1px solid #476D90">
					<legend>报修单信息</legend>
					<table width="100%" border="0" cellpadding="3" cellspacing="1"
						bgcolor="#464646" class="newfont03" align="center">
						<tr>
							<td width="20%" align="right">业主:</td>
							<td>${requestScope.user.realName} </td>
						</tr>
						<tr>
							<td width="20%" align="right">问题类别:</td>
							<td>${requestScope.report.reportName}</td>
						</tr>
						<tr>
							<td width="20%" align="right">问题描述:</td>
							<td>${requestScope.report.question}</td>
						</tr>
						<tr>
							<td width="20%" align="right">报修日期:</td>
							<td>${requestScope.report.reportDate}</td>
						</tr>
						<tr>
							<td width="20%" align="right">报修备注:</td>
							<td>${requestScope.report.reamark}</td>
						</tr>
						<tr>
							<td width="20%" align="right">报修单状态:</td>
							<td>已处理</td>
						</tr>
					</table>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset style="border:1px solid #476D90">
					<legend>维修单信息</legend>
					<table width="100%" border="0" cellpadding="3" cellspacing="1"
						bgcolor="#464646" class="newfont03" align="center">
						<tr>
							<td width="20%" align="right">问题描述:</td>
							<td>${requestScope.report.question}</td>
						</tr>
						<tr>
							<td width="20%" align="right">报修日期:</td>
							<td>${requestScope.report.reportDate}</td>
						</tr>
						<tr>
							<td width="20%" align="right">维修人:</td>
							<td>${requestScope.handler.realName}</td>
						</tr>
						<tr>
							<td width="20%" align="right">维修总费用(元):</td>
							<td>${requestScope.repair.totalCost}</td>
						</tr>
						<tr>
							<td width="20%" align="right">维修结果:</td>
							<td>${requestScope.repair.repairResult}</td>
						</tr>
						<tr>
							<td width="20%" align="right">完成日期:</td>
							<td>${requestScope.repair.endDate}</td>
						</tr>
					</table>
			</td>

		</tr>
	</table>
	
	<table width="96%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">贴心服务：将维修结果发短信通知业主</caption>

     <tr>
            <td  width="18%">
                输入业主手机号码：
            </td>
            <td>
                <input id="txtPhone" type="text"   size="18"   />
            </td>
        </tr>
        <tr>
            <td>
                输入要发送的内容：
            </td>
            <td>

                <input id="txtContent" type="text"   size="45" /> (<span style="color:red">格式：内容【发送人签名】,如:你好！【张文】</span>)
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input id="btnSend" type="button" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="发  送" />
                &nbsp; &nbsp; &nbsp;
                	<input type="button"
			style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;"
			value="返 回" onclick="javascript:history.go(-1);" />
            </td>
        </tr>

  </table>
</body>
</html>

