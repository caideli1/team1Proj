<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已处理报修单</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script type="text/javascript">
//改变每页的条数
function turnPage(pageSize){
   /* window.location="${pageContext.request.contextPath}/report/1-"+pageSize; */
   window.location="${pageContext.request.contextPath}/report?method=showFinishList&pageNo=1&pageSize="+pageSize;
}
</script>
</head>

<body>
 <table width="94%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" 

class="newfont03" align="center">
  <caption class="tablestyle_title">报修单管理-->已处理报修单</caption>
  <tr class="firstRow">
   <td width="8%">编号</td>
   <td width="12%">问题类别</td> <td  width="42%">问题描述</td>
   <td  width="10%">报修人</td><td  width="10%">状态</td>
   <td>操作</td>
  </tr>
   <c:forEach  var="obj"  items="${requestScope.page.data }" varStatus="sta">
  <tr class="cont">
   <td width="8%">${obj[0]}</td>
   <td width="10%">${obj[1]}</td>
    <td  width="25%">${obj[2]}</td>
 
   <td  width="15%">
     ${obj[3] }
  
   </td>
   <td  width="10%">
        <c:choose>
                         <c:when test="${obj[4]==0 }">
                              未受理                                
                         </c:when>
                         <c:when test="${obj[4]==1 }">
                              已受理                               
                         </c:when>
                         <c:otherwise>
                               已处理  
                         </c:otherwise>
                       </c:choose>  
    </td>
    <td>
        <c:choose>
                         <c:when test="${obj[4]==0 }">
                             <a href="${pageContext.request.contextPath}/report?method=allotPeople&id=${sta.count }&pageNo=${requestScope.page.pageNo}">分配维修人员</a>                               
                         </c:when>
                          <c:when test="${obj[4]==1 }">
                             <a href="${pageContext.request.contextPath}/report?method=TakNotes&id=${sta.count }&pageNo=${requestScope.page.pageNo}">录入维修记录</a>                  
                         </c:when>
                         <c:otherwise>
                               <a href="${pageContext.request.contextPath}/report?method=ReportDetailes&id=${sta.count }&pageNo=${requestScope.page.pageNo}">维修详情</a>
                         </c:otherwise>
                       </c:choose>  
       
    </td>
    </tr>
   </c:forEach>                                          
      
   
   
   
 
 
  <tr>
    <td colspan="6"  class="right-font08">   
           <div class="showPage">
                第 <span class="right-text09">${requestScope.page.pageNo }</span> 页 |共 <span 

class="right-text09">${requestScope.page.totalPage }</span> 页 |
                每页<select name="pageSize1" onchange="turnPage(this.value)">
             <!-- // 每页条数与option中的值相同时，这一项要选中 -->
             <c:choose>
                <c:when test="${requestScope.page.pageSize==5}">
                      <option value="5" selected="selected">5</option> 
                      <option value="8">8</option>  
                      <option value="10">10</option> 
                      <option value="15">15</option>
                </c:when>
               <c:when test="${requestScope.page.pageSize==8}">
                       <option value="5">5</option> 
                      <option value="8" selected="selected">8</option>  
                      <option value="10">10</option> 
                      <option value="15">15</option>
              </c:when>
              <c:when test="${requestScope.page.pageSize==10}">
                       <option value="5">5</option> 
                       <option value="8">8</option>  
                       <option value="10" selected="selected">10</option> 
                      <option value="15">15</option>
               </c:when>
               <c:otherwise>
                      <option value="5">5</option> 
                      <option value="8" selected="selected">8</option>  
                      <option value="10">10</option> 
                      <option value="15" selected="selected">15</option> 
               
               
               </c:otherwise>
             </c:choose>
               
        
            
                </select>条 
               </div>  
                 <div class="showPage">
                 [<a href="${pageContext.request.contextPath }/report?method=showFinishList&pageNo=1" class="right-font08">首页</a> |  
                   <c:choose>
                   <c:when test="${requestScope.page.pageNo>1 }">
                      <a href="${pageContext.request.contextPath }/report?method=showFinishList&pageNo=${requestScope.page.pageNo-1}" class="right-font08">上一页</a> 
                   </c:when>
                  <c:otherwise>
                                                          上一页
                  </c:otherwise>
                </c:choose>
                 
          
            
                 | 
                 
                   
                <c:choose>
                   <c:when test="${requestScope.page.pageNo<requestScope.page.totalPage}">
                      <a href="${pageContext.request.contextPath }/report?method=showFinishList&pageNo=${requestScope.page.pageNo+1}" class="right-font08">下一页</a> 
                   </c:when>
                  <c:otherwise>
                                                          下一页
                  </c:otherwise>
                </c:choose>   
                | <a href="${pageContext.request.contextPath }/report?method=showFinishList&pageNo=${requestScope.page.totalPage}" class="right-font08">末页</a>]
                 </div>
              
                  </td>
                  </tr>
  </table>
</body>
</html>
