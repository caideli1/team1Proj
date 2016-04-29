<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉单管理--录入处理结果</title>
<link type="text/css" href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/datePicker/WdatePicker.js"></script>
</head>

<body>
 <form action="${pageContext.request.contextPath }/complain?method=addDoWith" method="post">
  <!-- 此处使用隐藏域将投诉单的Id保存起来 -->
 <input type="hidden" name="tb_complain.complainId" value="${sessionScope.complainId}" />
  <table width="90%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03 " align="center">
  <caption class="tablestyle_title">投诉单管理-->录入处理结果</caption>

   <tr>
   <td width="20%" align="right">投诉人:</td>
   <td>${obj[0]}</td>  
   </tr>
    <tr>
   <td width="20%" align="right">投诉内容:</td>
   <td>${obj[1]}</td>  
   </tr>
   <tr>
      <td width="20%" align="right">投诉日期:</td>
   <td>${obj[2]}</td>  
   </tr>
     <tr>
   <td width="20%" align="right">处理人:</td>
   <td>${sessionScope.userinfo.realName }</td>  
   </tr>

      <tr>
   <td width="20%" align="right">处理结果:</td>
   <td><input type="text" name="tb_complain.ComplainResult" class="text" style="width:304px" /></td>
   </tr>
       <tr>
   <td width="20%" align="right">完成日期:</td>
   <td><input type="text" name="tb_complain.endDate" class="text" style="width:154px" onClick="WdatePicker()"/></td>
   </tr>
   <tr>
   <td colspan="3" align="center">
     <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath }/images/btnSubmit.gif)" value="录 入"/>
          <input type="button" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath }/images/btnSubmit.gif); margin-left:15px;" value="返 回" onclick="javascript:history.go(-1);"/>
   </td>
   </tr>
  </table>
  </form> 	
</body>
</html>
