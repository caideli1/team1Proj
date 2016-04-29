<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
    <script type="text/javascript">
//改变每页的条数
function turnPage(pageSize){
   window.location="${pageContext.request.contextPath}/page/1-"+pageSize;
}
</script>
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
                <c:forEach var="page" items="${requestScope.pages.data}">
                  <tr  class="cont">
				    <td bgcolor="#FFFFFF">${page.stallNo}</td>
                    <td>${page.stallAdress}</td>
                    
                    <td bgcolor="#FFFFFF">
                    <c:choose>
                    <c:when test="${page.stallState==1}">
                        <span style="color:red;">已租用</span>
                        </c:when>
                       <c:otherwise> 
                                   空闲
                        </c:otherwise>
                        </c:choose>
                        </td>
                  </tr>
                  </c:forEach>
              
              
                  <tr>
                  <td colspan="3"  class="right-font08">
                 
     <div class="showPage">
                第 <span class="right-text09">${requestScope.pages.pageNo}</span> 页 |共 <span 

class="right-text09">${requestScope.pages.totalPage}</span> 页 |
                每页<select name="pageSize" onchange="turnPage(this.value)">
              <!-- 每页条数与option中的值相同时，这一项要选中 -->
            
                     <option value="5" selected="selected">5</option> 
                      <option value="8">8</option>  
                    <option value="10">10</option> 
                      <option value="15">15</option>
                     
               
                </select>条
               </div> 
                 <div class="showPage">
                 [<a href="${pageContext.request.contextPath}/userservlet?method=restCar&pageNo=1" class="right-font08">

首页</a> |
                 <c:choose>
                 <c:when test="${requestScope.pages.pageNo>1}">
                    <a href="${pageContext.request.contextPath}/userservlet?method=restCar&pageNo=${requestScope.pages.pageNo-1}" 

class="right-font08">上一页</a> 
     </c:when>
                 <c:otherwise>
                                                          上一页 
                 </c:otherwise>
                 </c:choose>|
                  <c:choose>
                
                  <c:when test="${requestScope.pages.pageNo<requestScope.pages.totalPage}">
                    <a href="${pageContext.request.contextPath}/userservlet?method=restCar&pageNo=${requestScope.pages.pageNo+1}" 

class="right-font08">下一页</a> 
                  </c:when>
                 <c:otherwise>
                                                          下一页 
                                   
                                                          
                   </c:otherwise>
                 </c:choose>
                | <a href="${pageContext.request.contextPath}/userservlet?method=restCar&pageNo=${requestScope.pages.totalPage}" 

class="right-font08">末页</a>]
         
                 </div>
                  </td>
                  </tr>
                </table>
</body>
</html>
