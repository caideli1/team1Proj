<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改收费项目</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script type="text/javascript">

function cl()
{
document.getElementById('txt1').value="";
document.getElementById('txt2').value="";
}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/userservlet?method=baocenItems" method="post">
  <!-- 此处使用隐藏域将收费项的Id保存起来 -->
  <input type="hidden" name="createDate" value="${(tbfeeItem.createDate)}">
 <input type="hidden" name="feeId" value="${tbfeeItem.feeId}" />
  <input type="hidden" name="feeItem" value="${(tbfeeItem.feeName)}" />
  <table width="70%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">收费项设置-->编辑收费项目</caption>
  <tr>
   <td width="30%" align="right">收费项目:</td>
   <td width="30%">${(tbfeeItem.feeName)}</td>  
   <td width="40%">&nbsp;</td>
   </tr>
   <tr>
   <td width="30%" align="right">收费标准(每月最低收费):</td>
   <td width="30%"><input type="text" name="cost" id="txt1" class="text" style="width:154px"  value="${(tbfeeItem.fee)}"/></td>  
   <td width="40%"><span style="color:red; display: none;">*收费标准必须填写，且必须是数字！</span></td>  
   </tr>
      <tr>
   <td width="30%" align="right">收费周期(以月计):</td>
   <td width="30%"><input type="text" name="receDate" id="txt2" class="text" style="width:154px" value="${(tbfeeItem.feeReceDate)}" /></td>  
   <td width="40%"><span style="color:red; display: none;">*收费周期必须填写，且必须是数字！</span></td>  
   </tr>
   <tr>
   <td colspan="3" align="center">
     <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)" value="保 存"/>
      <input type="button" id="clear" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="取 消" onclick="cl();"/>
   </td>
   </tr>
  </table>
  </form> 	
</body>
</html>
