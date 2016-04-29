<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分配维修人员</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
</head>

<body>
 <form action="${pageContext.request.contextPath}/report?method=updateAllot&reportId=${requestScope.report.reportId}" method="post">
  <!-- 此处使用隐藏域将报修单的Id保存起来 -->
 <input type="hidden" name="tb_report.reportId" value="${requestScope.report.reportId}" />
  <table width="60%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">报修单管理-->分配维修人员</caption>
  <tr>
   <td width="20%" align="right">业主:</td>
   <td>${requestScope.user.realName}</td>  
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
   <td width="20%" align="right">维修人:</td>
   <td>
   
   
     <select name="tb_report.handlerId">
     <c:forEach   var="worker"  items="${requestScope.workers }">
     
     <option value="${worker.userId}" >${worker.realName}</option>
     </c:forEach>
            
     </select>
   </td>  
   </tr>
 <tr>
   <td colspan="3" align="center">
     <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)" value="更 新"/>
          <input type="button" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="返 回" onclick="javascript:history.go(-1);"/>
   </td>
   </tr>
  </table>
  </form> 	
</body>
</html>
