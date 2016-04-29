<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script type="text/javascript">
//改变每页的条数
function turnPage(pageSize){
   window.location="${pageContext.request.contextPath}/user/1-"+pageSize;
}
</script>
</head>

<body>
  <table width="90%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">人员管理-->用户列表</caption>
                  <tr class="firstRow">
                    <td width="28%" height="20">用户名</td>
                    <td width="34%">姓名</td>
                    <td>角色</td>
                  </tr>
            
                    
                     <c:forEach var="user" items="${sessionScope.page.data }">
                      <tr  class="cont">
                   <td bgcolor="#FFFFFF">${user.userName}</td><td>${user.realName}</td>
                   <td>
                    <c:choose>
                         <c:when test="${user.roleId=='r001' }">
                                                                   工作人员
                         </c:when>
                         <c:otherwise>
                                                                   业主 
                         </c:otherwise>
                       </c:choose> 
                       </td></tr>  
                 </c:forEach>
                  
      
                 
                 <tr>
                  <td colspan="3"  class="right-font08">
                  
                  <div class="addData"> <img src="${pageContext.request.contextPath}/images/add.gif"  style="border:none;" /><a href="${pageContext.request.contextPath}/user/addUser.jsp" class="right-font08">新增用户</a></div>
               <div class="showPage">
                第 <span class="right-text09">${sessionScope.page.pageNo }</span> 页 |共 <span class="right-text09">${sessionScope.page.totalPage }</span> 页 |
                每页<select name="pageSize" onchange="turnPage(this.value)">
            
                     <option value="5" selected="selected">5</option> 
                      <option value="8">8</option>  
                    <option value="10">10</option> 
                      <option value="15">15</option>
            
                   </select>条 -->    
               </div>  
                 <div class="showPage">
                 [<a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=1" class="right-font08">首页</a> | 
                  
                  
                <c:choose>
                   <c:when test="${sessionScope.page.pageNo>1 }">
                      <a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=${sessionScope.page.pageNo-1}" class="right-font08">上一页</a> 
                   </c:when>
                  <c:otherwise>
                                                          上一页
                  </c:otherwise>
                </c:choose>
                 
          
            
                 | 
                 
                   
                <c:choose>
                   <c:when test="${sessionScope.page.pageNo<sessionScope.page.totalPage}">
                      <a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=${sessionScope.page.pageNo+1}" class="right-font08">下一页</a> 
                   </c:when>
                  <c:otherwise>
                                                          下一页
                  </c:otherwise>
                </c:choose>   
    
           
             
                | <a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=${sessionScope.page.totalPage}" class="right-font08">末页</a>]
                 </div>
                  </td>
                  </tr>
                </table>
                
                
                <table width="90%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03" align="center" style="display:none;">
  <caption class="tablestyle_title">人员管理-->用户列表</caption>
                  <tr class="firstRow">
                    <td width="28%" height="20">用户名</td>
                    <td width="34%">姓名</td>
                    <td>角色</td>
                  </tr>
            
                    
                     <c:forEach var="user" items="${sessionScope.pagea.data }">
                      <tr  class="cont">
                   <td bgcolor="#FFFFFF">${user.userName}</td><td>${user.realName}</td>
                   <td>
                    <c:choose>
                         <c:when test="${user.roleId=='r001' }">
                                                                   工作人员
                         </c:when>
                         <c:otherwise>
                                                                   业主 
                         </c:otherwise>
                       </c:choose> 
                       </td></tr>  
                 </c:forEach>
                  
      
                 
                 <tr>
                  <td colspan="3"  class="right-font08">
                  
                  <div class="addData"> <img src="${pageContext.request.contextPath}/images/add.gif"  style="border:none;" /><a href="${pageContext.request.contextPath}/user/addUser.jsp" class="right-font08">新增用户</a></div>
               <div class="showPage">
                第 <span class="right-text09">${sessionScope.page.pageNo }</span> 页 |共 <span class="right-text09">${sessionScope.page.totalPage }</span> 页 |
                每页<select name="pageSize" onchange="turnPage(this.value)">
            
                     <option value="5" selected="selected">5</option> 
                      <option value="8">8</option>  
                    <option value="10">10</option> 
                      <option value="15">15</option>
            
                   </select>条 -->    
               </div>  
                 <div class="showPage">
                 [<a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=1" class="right-font08">首页</a> | 
                  
                  
                <c:choose>
                   <c:when test="${sessionScope.page.pageNo>1 }">
                      <a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=${sessionScope.page.pageNo-1}" class="right-font08">上一页</a> 
                   </c:when>
                  <c:otherwise>
                                                          上一页
                  </c:otherwise>
                </c:choose>
                 
          
            
                 | 
                 
                   
                <c:choose>
                   <c:when test="${sessionScope.page.pageNo<sessionScope.page.totalPage}">
                      <a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=${sessionScope.page.pageNo+1}" class="right-font08">下一页</a> 
                   </c:when>
                  <c:otherwise>
                                                          下一页
                  </c:otherwise>
                </c:choose>   
    
           
             
                | <a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=${sessionScope.page.totalPage}" class="right-font08">末页</a>]
                 </div>
                  </td>
                  </tr>
                </table>
                

</body>
</html>
