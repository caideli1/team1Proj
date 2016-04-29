<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已受理投诉</title>
<link type="text/css" href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" />
<script type="text/javascript">
//改变每页的条数
function turnPage(pageSize){
   window.location="${pageContext.request.contextPath }/complain?method=doDoWithCom&complainpageNo=1&pageSize="+pageSize;
}
</script>
</head>

<body>
 <table width="98%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">投诉单管理-->已受理投诉</caption>
  <tr class="firstRow">
   <td width="8%">编号</td>
   <td width="42%">投诉内容</td> <td  width="12%">投诉人</td>
   <td  width="10%">投诉日期</td><td  width="10%">状态</td>
   <td>操作</td>
  </tr>
  <c:forEach var="obj" items="${complainpage.data}">
  	
		  <tr class="cont">
		   <td>${obj[0]}</td>
		   <td>${obj[1]}</td>
		   <td>${obj[2]}</td>
		   <td>${obj[3]}</td>
		   <c:choose>		   	
		   		<c:when test="${obj[4]==1 }">
		   			<td>已受理</td>
			  	    <td><a href="${pageContext.request.contextPath }/complain?method=opendoWith&complainId=${obj[0]}">录入处理结果</a></td>
		   		</c:when>		   
		   </c:choose>
		  </tr>
	</c:forEach>   
  <tr>
   <td colspan="6"  class="right-font08">   
        
               <div class="showPage" style="margin-left:80px">
                第 <span class="right-text09">${complainpage.pageNo}</span> 页 |共 <span class="right-text09">${complainpage.totalPage}</span> 页 |
                每页<select name="pageSize" onchange="turnPage(this.value)">
              <!-- 每页条数与option中的值相同时，这一项要选中 -->
              <c:choose>
              	<c:when test="${complainpage.pageSize ==5 }">
              		 <option value="5" selected="selected">5</option> 
                     <option value="8">8</option>  
                    <option value="10">10</option> 
                      <option value="15">15</option>
              	</c:when>
              	<c:when test="${complainpage.pageSize ==8 }">
              		 <option value="5">5</option> 
                      <option value="8" selected="selected">8</option>  
                    <option value="10">10</option> 
                      <option value="15">15</option>
              	</c:when>
              	<c:when test="${complainpage.pageSize ==10 }">
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
                  [<a href="${pageContext.request.contextPath }/complain?method=doAllComplain&complainpageNo=1" class="right-font08">

首页</a> | 
					<c:choose>
						<c:when test="${complainpage.pageNo>1 }">
                      		<a href="${pageContext.request.contextPath }/complain?method=doAllComplain&complainpageNo=${complainpage.pageNo-1}" class="right-font08">上一页</a> 
                   		</c:when>
						<c:otherwise>
							 上一页 
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${complainpage.pageNo<complainpage.totalPage}">
                      		<a href="${pageContext.request.contextPath }/complain?method=doAllComplain&complainpageNo=${complainpage.pageNo+1}" class="right-font08">下一页</a> 
                   		</c:when>
						<c:otherwise>
							下一页 
						</c:otherwise>
					</c:choose>                             
                 | <a href="${pageContext.request.contextPath }/complain?method=doAllComplain&complainpageNo=${complainpage.totalPage}" class="right-font08">末页</a>]
                 </div>
                  </td>
                  </tr>
  </table>
</body>
</html>
