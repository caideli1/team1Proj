<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>录入缴费</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
function cl()
{
document.getElementById('r1').value="";
document.getElementById('r2').value="";
document.getElementById('r3').value="";

}
</script>
</head>

<body>
<form action="${pageContext.request.contextPath}/userservlet?method=baocenaddsendfee" method="post">
  <!-- 此处使用隐藏域将收费人员的Id保存起来 -->
  <input type="hidden" name="tb_userCost.reveived" value="${sessionScope.userinfo.userId}" />
 
  <table width="80%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">业主缴费管理-->录入缴费</caption>
  <tr>
  <td width="30%" align="right">缴费人:</td>
   <td>
     <select name="tb_userCost.userId">
     	<c:forEach var="uer" items="${sessionScope.pagea.data}">
      		<option value="${4}">${(uer.realName)}</option>
        </c:forEach>
     </select>
   </td>   
   <td width="40%"></td>
   </tr>
   <tr>
  <td width="30%" align="right">缴费项目:</td>
   <td>
     <select name="tb_userCost.feeId">
     	<c:forEach var="item" items="${sessionScope.tbfeeItems}">
		      <option value="${item.feeId}">${(item.feeName)}</option>
		</c:forEach>
     </select>
   </td>   
   <td width="40%"></td>
   </tr>
   <tr>
   <td width="30%" align="right">缴费金额:</td>
   <td width="30%"><input type="text" name="tbhavaCost" id="r1" class="text" style="width:154px" /></td>  
   <td width="40%"><span  style="color:red; display: none;">*缴费金额必须填写，且必须为数字！</span></td>
   </tr>
      <tr>
   <td width="30%" align="right">收费人:</td>
   <td width="30%">${sessionScope.userinfo.realName} </td>  
   <td width="40%"></td>
   </tr>
     <tr>
   <td width="30%" align="right">缴费日期:</td>
   <td width="30%"><input type="text" name="tb_userCost.sendDate" id="r2" class="text" style="width:154px" onClick="WdatePicker()" /></td>  
   <td width="40%"><span  style="color:red; display: none;">*缴费日期必须填写！</span></td>
   </tr>
   <tr>
     <tr>
   <td width="30%" align="right">备注:</td>
   <td width="30%"><input type="text" name="tb_userCost.remark" id="r3" class="text" style="width:154px" /></td>  
   <td width="40%"></td>
   </tr>
   <tr>
   <td colspan="3" align="center">
     <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)" value="保 存"/>
          <input type="reset" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="取 消" onclick="cl();" />
   </td>
   </tr>
  </table>
  </form> 	
</body>
</html>