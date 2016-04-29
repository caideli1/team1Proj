<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>录入维修记录</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</head>

<body>
 <form action="${pageContext.request.contextPath}/report?method=updateRepair&reportId=${requestScope.report.reportId}&userId=${requestScope.report.userId}" method="post">
  <%-- <!-- 此处使用隐藏域将报修单的相关属性保存起来 -->
 <input type="hidden" name="tb_report.reportId" value="${requestScope.obj[0]}" />
  <input type="hidden" name="tb_report.reportState" value="2" />
    <!-- 此处使用隐藏域将维修记录单的相关属性保存起来 -->
  <input type="hidden" name="tb_repair.reportId" value="${requestScope.obj[0]}" />
    <input type="hidden" name="tb_repair.userId" value="${requestScope.obj[3]}" />
  
  
   <!-- 此处使用隐藏域将处理人的Id保存起来 -->
    <input type="hidden" name="tb_report.handlerId" value="${requestScope.obj[3]}" /> --%>
  <table width="90%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">报修单管理-->录入维修记录</caption>

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
   <td width="20%" align="right">维修人:</td>
    <td> ${requestScope.handler.realName} </td> 
   </tr>
   
    <tr>
   <td width="20%" align="right">维修总费用(元):</td>
   <td><input type="text" name="tb_repair.totalCost" class="text" style="width:154px" /></td>
   </tr>
      <tr>
   <td width="20%" align="right">维修结果:</td>
   <td><input type="text" name="tb_repair.repairResult" class="text" style="width:304px" /></td>
   </tr>
       <tr>
   <td width="20%" align="right">完成日期:</td>
   <td><input type="text" name="tb_repair.endDate" class="text" style="width:154px" onClick="WdatePicker()"/></td>
   </tr>
   <tr>
   <td colspan="3" align="center">
     <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)" value="录 入"/>
          <input type="button" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="返 回" onclick="javascript:history.go(-1);"/>
   </td>
   </tr>
  </table>
  </form> 	
</body>
</html>
