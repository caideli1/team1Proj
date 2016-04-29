<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缴费查询</title>
<link type="text/css" href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
//改变每页的条数
function turnPage(pageSize){
   window.location="${pageContext.request.contextPath}/usercost?method=searchUsercost&feepageNo=1&pageSize="+pageSize;
}
function deleteMany(){
	document.getElementById("feeForm").submit();
}

</script>

</head>

<body>

<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">业主缴费管理-->缴费查询</caption>
  <tr>
  <td colspan="9">
   <div style="margin-top:2px; margin-left:auto; margin-right:auto; width:100%; height:auto;">
  <fieldset style="border:1px solid #476D90; width:">
     <legend>高级搜索-->查找缴费明细</legend>
      <div class="newfont03">
    <form action="${pageContext.request.contextPath }/usercost?method=searchUsercost&feepageNo=1" method="post">
       <span style="float:left; margin-left:2px">
         业主姓名：<input type="text" name="realName" class="text"  value="${fee[0]}" style="width:120px" />
         </span>
        <span style="float:left; margin-left:2px">
         缴费项目：
        <select name="feeItemId">  
         	<option value="0" select="selected">全部</option>             
         	<c:forEach var="obj" items="${list }">       	       		
         			<option value="${obj.feeId}" >${obj.feeName}</option>        		        			      		                
         	</c:forEach>             	    	
     	</select>
        </span>
            <span style="float:left; margin-left:8px">
            缴费日期：
            从<input type="text" name="beginDate" class="text" style="width:144px" value="${begin}"  onClick="WdatePicker()" />&nbsp;到&nbsp;<input type="text" name="endDate" class="text" style="width:144px"  value="${end}"  onClick="WdatePicker()" />
            </span>
            <span  style="float:left; margin-left:10px">
                 <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath }/images/btnSubmit.gif)" value="查 找"/>
            </span>
            </form>
      </div>
</fieldset>
</div>
  </td>
  </tr>
                  <tr class="firstRow">
                    <td width="8%">选择</td>
				    <td width="10%">业主姓名</td>
                    <td width="19%" height="20">所住楼栋</td>
                    <td width="11%">联系电话</td>
                    <td width="9%" height="20">缴费项目</td>
                    <td width="8%">缴费金额</td>
                    <td width="8%">收费人</td>
                    <td width="8%">缴费日期</td>
                    <td width="18%">备注</td> 
                  </tr>
                  <tr id="aa"></tr>
                  <form action="${pageContext.request.contextPath }/usercost?method=delete" id="feeForm" method="post">  	               	  
                  <c:forEach var="fee" items="${complainpage.data}">
                  	<tr  class="cont">
	                  <td><input type="checkbox" name="delid" value="${fee[8]}"/></td>
					  <td bgcolor="#FFFFFF">${fee[0]}</td>
	                  <td>${fee[1]}</td>
	                  <td>${fee[2]}</td>
	                  <td bgcolor="#FFFFFF">${fee[3]}</td>
	                  <td>${fee[4]}</td>
	                  <td>${fee[5]}</td>
	                  <td>${fee[6]}</td>
	                  <td>${fee[7]}</td>
	                </tr>                
                  </c:forEach>                 
                 
                  <tr>
                  <td colspan="9"  class="right-font08">
                    <img src="${pageContext.request.contextPath }/images/delete.gif"  style="border:none; margin-left:60px; margin-right:10px;" /><a href="javascript:deleteMany();" class="right-font08"  onclick="return confirm('确定删除选中项吗？');">删除选中的条目</a>
                 </form>
                    
                <div class="showPage">
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
                  [<a href="${pageContext.request.contextPath }/usercost?method=searchUsercost&feepageNo=1" class="right-font08">

首页</a> | 
					<c:choose>
						<c:when test="${complainpage.pageNo>1 }">
                      		<a href="${pageContext.request.contextPath }/usercost?method=searchUsercost&feepageNo=${complainpage.pageNo-1}" class="right-font08">上一页</a> 
                   		</c:when>
						<c:otherwise>
							 上一页 
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${complainpage.pageNo<complainpage.totalPage}">
                      		<a href="${pageContext.request.contextPath }/usercost?method=searchUsercost&feepageNo=${complainpage.pageNo+1}" class="right-font08">下一页</a> 
                   		</c:when>
						<c:otherwise>
							下一页 
						</c:otherwise>
					</c:choose>                             
                 | <a href="${pageContext.request.contextPath }/usercost?method=searchUsercost&feepageNo=${complainpage.totalPage}" class="right-font08">末页</a>]
                 </div>
                  </td>
                  </tr>
  </table>
</body>
</html>

