<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>楼栋信息</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script type="text/javascript">
//改变每页的条数
function turnPage(pageSize){
   window.location="${pageContext.request.contextPath}/lzf?method=getBuild&pageNo=1&pageSize="+pageSize;
}
</script>
</head>
<body>
<table width="90%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">基础数据-->楼栋信息列表</caption>
                  <tr class="firstRow">
				    <td width="8%">
                    楼栋编号</td>
                    <td width="20%" height="20">楼栋名称</td>
                    <td width="8%">楼层数</td>
                    <td width="8%">单元数</td>
                    <td width="10%">房屋套数</td>
                    <td width="10%">入住套数</td>
                    <td>开发商联系信息</td>
                  </tr>
              
                   <c:forEach  var="bl"  items="${requestScope.buildPage.data}">
	                  <tr  class="cont">
					   <td bgcolor="#FFFFFF">${bl[0]}</td>
	                   <td bgcolor="#FFFFFF">${bl[1]}</td>
	                   <td bgcolor="#FFFFFF">${bl[2]}</td>
	                   <td bgcolor="#FFFFFF">${bl[3]}</td>
	                   <td bgcolor="#FFFFFF">${bl[4]}</td>
	                   <td bgcolor="#FFFFFF">${bl[5]}</td>
	                   <td bgcolor="#FFFFFF">${bl[6]}</td>
	                  </tr>
                  </c:forEach>
                  
                  
                  <tr>
                   <td colspan="8"  class="right-font08">
            <div class="showPage">
                第 <span class="right-text09">${buildPage.pageNo}</span> 页 |共 <span 

class="right-text09">${buildPage.totalPage}</span> 页 |
                每页<select name="pageSize" onchange="turnPage(this.value)">
              <!-- 每页条数与option中的值相同时，这一项要选中 -->
             
              <c:choose>
              	<c:when test="${buildPage.pageSize ==5 }">
              		 <option value="5" selected="selected">5</option> 
                      <option value="8">8</option>  
                    <option value="10">10</option> 
                      <option value="15">15</option>
              	</c:when>
              	<c:when test="${buildPage.pageSize ==8 }">
              		 <option value="5">5</option> 
                      <option value="8" selected="selected">8</option>  
                    <option value="10">10</option> 
                      <option value="15">15</option>
              	</c:when>
              	<c:when test="${buildPage.pageSize ==10 }">
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
                 [<a href="${pageContext.request.contextPath }/lzf?method=getBuild&pageNo=1" class="right-font08">

首页</a> | 
					<c:choose>
						<c:when test="${buildPage.pageNo>1 }">
                      		<a href="${pageContext.request.contextPath }/lzf?method=getBuild&pageNo=${buildPage.pageNo-1}" class="right-font08">上一页</a> 
                   		</c:when>
						<c:otherwise>
							 上一页 
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${buildPage.pageNo<buildPage.totalPage}">
                      		<a href="${pageContext.request.contextPath }/lzf?method=getBuild&pageNo=${buildPage.pageNo+1}" class="right-font08">下一页</a> 
                   		</c:when>
						<c:otherwise>
							下一页 
						</c:otherwise>
					</c:choose>                             
                 | <a href="${pageContext.request.contextPath }/lzf?method=getBuild&pageNo=${buildPage.totalPage}" class="right-font08">末页</a>]
                 </div>
                  </td>
                  </tr>
                  
                </table>
</body>
</html>
