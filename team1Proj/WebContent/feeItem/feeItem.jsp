<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收费项设置</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript">


	function deleteMany(){
		  document.getElementById("feeForm").submit();
	}

	 $(document).ready(
			  function(){
				   $("#feeName").blur(
						  function(){
							  $.get("${pageContext.request.contextPath }/userservlet?method=checkName",
									  {feeNames: $(this).val()},function(msg){
										  //把服务器端返回的数据显示到span里
										  $("#feeNamess").html(msg);
									    }
									  );
							  
						  }   
				   
				   );
				   $("#createDate").blur(
							  function(){
								  $.get("${pageContext.request.contextPath }/userservlet?method=checkName",
										  {createDates: $(this).val()},function(msg){
											  //把服务器端返回的数据显示到span里
											  $("#createDatess").html(msg);
										    }
										  );
								  
							  }   
					   
					   );		
				   
				 
				   $("#fee").blur(
							  function(){
								  $.get("${pageContext.request.contextPath }/userservlet?method=checkName",
										  {fees: $(this).val()},function(msg){
											  //把服务器端返回的数据显示到span里
											  $("#feess").html(msg);
										    }
										  );
								  
							  }   
					   
					   ); 
					 
						   $("#feedate").blur(
								  function(){
									  $.get("${pageContext.request.contextPath }/userservlet?method=checkName",
											  {feedates: $(this).val()},function(msg){
												  //把服务器端返回的数据显示到span里
												  $("#feedatess").html(msg);
											    }
											  );
									  
								  }   
						   
						   );
					 
				   
			  }	  
		  );
	   
	
	 function keyPress() {   
		    var keyCode = event.keyCode;
		    event.returnValue = ((keyCode >= 48 && keyCode <= 57));   
		}   
</script>

<script language="javascript">
function aa1(){
	var t=$("#fee").val();//这个就是我们要判断的值了
	if(!isNaN(t)){
	  
	}else{
	  alert("*收费标准必须填写，且必须是数字！");
	}}
function aa(){
var t=$("#feedate").val();//这个就是我们要判断的值了
if(!isNaN(t)){
  
}else{
  alert("*收费周期必须填写，且必须是数字！");
}}

function cl()
{
document.getElementById('feeNamess').value="";
document.getElementById('feess').value="";
document.getElementById('feedatess').value="";
document.getElementById('createDatess').value="";
}
</script>
</head>
<body>

<form  action="${pageContext.request.contextPath}/userservlet?method=feeItemsdele"  id="feeForm"   method="post">
 <table width="90%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">收费项设置-->所有收费项目列表</caption>
  <tr class="firstRow">
   <td width="8%">选择</td>
   <td width="10%">编号</td> <td  width="15%">收费项目</td>
   <td  width="23%">收费标准<br/>(每月最低收费)</td><td  width="12%">收费周期<br/>(以月计)</td>
   <td  width="20%">创建日期</td><td>编辑</td>
  </tr>
  
  <c:forEach var="fees" items="${tbfeeItems}">
	  <tr class="cont">
	  <td><input type="checkbox" name="delid" value="${fees.feeId}" /></td>
	  <td>${(fees.feeId)}</td>  <td>${(fees.feeName)}</td><td>${(fees.fee)}</td><td>${(fees.feeReceDate)}</td><td>${(fees.createDate)}</td>
	  <td>   <div class="editData"> <img src="${pageContext.request.contextPath}/images/edit.gif"  style="border:none;" /><a href="${pageContext.request.contextPath}/userservlet?method=bianItems&feeId=${fees.feeId} " class="right-font08">编辑</a></div></td>
	  </tr>
  </c:forEach>
  
  <tr>
   <td colspan="7">
     <img src="${pageContext.request.contextPath}/images/delete.gif"  style="border:none; margin-left:60px; margin-right:10px;" /><a href="javascript:deleteMany();" class="right-font08"  onclick="return confirm('确定删除选中项吗？');">删除选中的收费项目</a>
   </td>
  </tr>
  </table>
  </form>
  
  
  
  
  <div style="margin-top:2px; margin-left:auto; margin-right:auto; width:90%; height:auto;">
    <fieldset style="border:1px solid #476D90">
     <legend style="color:#FFF">收费项设置-->添加收费项目</legend>
     
  <form action="${pageContext.request.contextPath}/userservlet?method=feeItemsadd" method="post">
     
      <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#464646" class="newfont03" align="center" >
  <tr>
   <td width="30%" align="right">收费项目:</td>
   <td width="30%"><input type="text" name="feeNames" id="feeName" class="text" style="width:154px" /></td>  
   <td width="40%"><span id="feeNamess">*收费项目必须填写！</span></td>
   </tr>
   <tr>
   <td width="30%" align="right">收费标准(每月最低收费):</td>
   <td width="30%"><input type="text" id="fee" name="fees" class="text" style="width:154px"  onblur="aa1();"/></td>  
   <td width="40%"><span id="feess">*收费标准必须填写,且必须为数字！</span></td>  
   </tr>
      <tr>
   <td width="30%" align="right">收费周期(以月计):</td>
   <td width="30%"><input type="text" id="feedate"name="feedates" class="text" style="width:154px" onblur="aa();"/></td>  
   <td width="40%"><span id="feedatess">*收费周期必须填写，且必须是数字！</span></td>  
   </tr>
      <tr>
   <td width="30%" align="right">创建日期</td>
   <td width="30%"><input type="text" id="createDate" name="createDates" class="text" style="width:154px" onClick="WdatePicker()" onchange="WdatePicker()"/></td>  
   <td width="40%"><span id="createDatess">*创建日期必须填写！</span></td>  
   </tr>
   <tr>
   <td colspan="3" align="center">
     <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)" value="保 存"/>
          <input type="reset" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="取 消" onclick="cl();"/>
   </td>
   </tr>
  </table>
 
 </form>
 
    </fieldset>
  </div>
</body>
</html>