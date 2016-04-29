<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="80%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">服务中心-->我要租车位</caption>
    <tr>
    <td colspan="3" style="font-size:15px; font-weight:bold;">
     小区空闲车位列表
    </td>
    </tr>
                  <tr class="firstRow">
				    <td width="10%">
                    车位编号</td>
                    <td width="30%" height="20">车位地址</td>
                    <td width="14%">租用状态</td>
                  </tr>
                <c:forEach var="crr" items="${requestScope.pages.data}">
                  <tr  class="cont">
				    <td bgcolor="#FFFFFF">${crr.stallNo}11</td>
                    <td>${crr.stallAdress}22</td>
                    
                    <td bgcolor="#FFFFFF">
                    <c:choose>
                    <c:when test="${crr.stallState==1}">
                        已租用
                        </c:when>
                       <c:otherwise> 
                                   空闲
                        </c:otherwise>
                        </c:choose>
                        </td>
                  </tr>
                  </c:forEach>
              
              
                  <tr>
                  </table>
</body>
</html>