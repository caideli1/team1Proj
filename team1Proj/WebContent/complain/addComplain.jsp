<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分配处理人员</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</head>

<body>
 <form action="${pageContext.request.contextPath}/userservlet?method=addcomplain" method="post">
  <!-- 此处使用隐藏域将投诉人的Id保存起来 -->
		<input type="hidden" name="tb_complain.userId"
			value="${sessionScope.userinfo.userId}" />
		<!-- 此处使用隐藏域保存投诉单的初始状态值  -->
		<input type="hidden" name="complainState" value="0" />
 <input type="hidden" name="the_complainendDate" value=""/>
  <input type="hidden" name="ComplainResult" value="未处理" />
    <input type="hidden" name="doWithId" value="1" />
 <table width="85%" border="0" cellpadding="2" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">服务中心-->我要投诉</caption>
    <tr>
   <td width="18%" align="right">投诉内容:</td>
   <td><input type="text" name="tb_complain.complainText" class="text" style="width:304px" /></td>  
   </tr>
     <tr>
      <td width="18%" align="right">投诉备注:</td>
   <td><input type="text" name="tb_complain.reamark" class="text" style="width:304px" /></td>  
   </tr>
   
   <tr>
      <td width="18%" align="right">投诉日期:</td>
   <td><input type="text" name="tb_complain.complainDate" class="text" style="width:154px" onClick="WdatePicker()" /></td>  
   </tr>
   
   <tr>
   <td colspan="3" align="center">
     <input type="submit" style="width:63px; height:20px; border:none; background-image:url(images/btnSubmit.gif)" value="录 入"/>
          <input type="button" style="width:63px; height:20px; border:none; background-image:url(images/btnSubmit.gif); margin-left:15px;" value="返 回"  onclick="javascript:history.go(-1);" />
   </td>
   </tr>
  </table>
  </form> 	
</body>
</html>

