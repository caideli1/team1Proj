<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</head>

<body>
<form action="${pageContext.request.contextPath}/userservlet?method=updateUser"  method="post">

  <table width="90%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">服务中心-->个人资料</caption>
  <c:forEach var="obj" items="${sessionScope.obj}">
  
  <tr>
   <td width="15%" align="right">姓名:</td>
   <td>${(sessionScope.userinfo.realName)}</td>  
  <td> <input  type="hidden" name="tb_ownerDetail.ownerId"   value="${obj[7]}" />
  <input  type="hidden" name="tb_ownerDetail.buildingId"   value="${obj[8]}" />
  <input  type="hidden" name="tb_ownerDetail.houseUint"   value="${obj[9]}" />
  <input  type="hidden" name="tb_ownerDetail.houseFloor"   value="${obj[10]}" />
   <input  type="hidden" name="tb_ownerDetail.userId"   value="${obj[0]}" />
  </td>
   </tr>
   <tr>
   <td width="15%" align="right">所住楼栋:</td>
   <td>${obj[2]}</td>
   </tr>
      <tr>
   <td width="15%" align="right">门牌号:</td>
   <td>${obj[3]}</td>  <input type="hidden" name="houseNumber" value="${obj[3]}">
   </tr>
       <tr>
   <td width="15%" align="right">身份证号:</td>
   <td><input type="text" name="tb_ownerDetail.cardId"  class="text" style="width:154px"  value="${obj[4]}"  /></td>  
   </tr>
       <tr>
   <td width="15%" align="right">联系电话:</td>
   <td><input type="text" name="tb_ownerDetail.telephone"  class="text" style="width:154px"  value="${obj[5]}"  /></td>  
   </tr>
       <tr>
   <td width="15%" align="right">入住日期:</td>
   <td><input type="text" name="tb_ownerDetail.enterDate" class="text" style="width:154px"   value="${obj[6]}" onClick="WdatePicker()" /></td>  
   </tr>
   <tr>
   <td colspan="2" align="left">
     <input type="submit" style="margin-left:110px;width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)"  value="更 新"/>
          <input type="reset" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="取 消"/>
   </td>
   </tr>
  </c:forEach>
  </table>	
  </form>
</body>
</html>