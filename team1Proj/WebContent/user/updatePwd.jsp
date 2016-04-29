<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script type="text/javascript"  src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript">
/* $(document).ready(
		  function(){
			   $("#userPassword").blur(
					  function(){
						if($("#userPassword").val()!=$("#userOldPwd").val()){
							$("#lblMsg").html("原始密码不正确，请重新输入！");
							//$("#userPassword").focus();
							return false;
						}
						 
						$("#lblMsg").html("");
						return  true;
					  }   
			   
			   );
			   
			  
			   $("#confirmPwd").blur(
						  function(){
							if($("#confirmPwd").val()!=$("#userNewPwd").val()){
								$("#lblPwd").html("确认密码和新密码不一致，请重新输入！");
								//$("#confirmPwd").focus();
								return false;
							}
							 
							$("#lblPwd").html("");
							return  true;
						  }   
				   
				   );
			   
			   
			  
		  }	  
	  ); */



  function $(id){
  return document.getElementById(id);
  }
   //检验输入的原始密码是否正确
    function checkOldPwd(){
        if($("userOldPwd").value!=$("userPassword").value){
             $("lblMsg").innerHTML="原始密码不正确，请重新输入！";
              $("userPassword").value="";
             $("userPassword").focus();
               return false;
        }
          $("lblMsg").innerHTML="";
          return true;
      
    }
    //验证确认密码和新密码是否一致
     function checkConfirmPwd(){
         if($("confirmPwd").value!=$("userNewPwd").value){
                    $("lblPwd").innerHTML="确认密码和新密码不一致，请重新输入！";
                    $("confirmPwd").value="";
                   $("confirmPwd").focus();
                   return false;        
         }
          $("lblPwd").innerHTML="";
          return true; 
          }
 	   
	   function checkForm(){
           if(checkOldPwd()&&checkConfirmPwd()&&$("userNewPwd").value!=""&&$("userNewPwd").value!=null){
        	
        	 alert("密码修改成功，返回登陆页面！");
        	
        	
        	     //window.location="${pageContext.request.contextPath }/login.jsp";
              // location.href="${pageContext.request.contextPath }/login.jsp";
               
              
             return true;

           }
        return false;
 }   
<%--   $(document).ready(
    function(){
    	$("form").submit( function () {
    		if(checkOldPwd()&&checkConfirmPwd()){
    			  <%
    	           	request.setAttribute("para","0");
    	           	%>
    	           	window.location="${pageContext.request.contextPath }/login.jsp";
    	           	return true;
    		}return false;
    		} ); 
	 
	 
 }
 
 
 ); --%>
  
   
</script>

</head>

<body>
<form action="${pageContext.request.contextPath}/Property?method=updatePwd&userId=${sessionScope.userinfo.userId}" method="post"  onsubmit="return checkForm();" >
  <!-- 此处使用隐藏域将登录用户的Id保存起来 -->
 <input type="hidden" name="tb_user.userId" value="${sessionScope.userinfo.userId}" />
   <!-- 此处使用隐藏域保存登录用户的原始密码 -->
  <input type="hidden" id="userOldPwd" name="userPwd" value="${sessionScope.userinfo.userPwd}" />
  <table width="80%" border="0" cellpadding="2" cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">安全设置-->修改密码(<span style="color:#FF0026">为保证系统安全，请先输入您的原始密码</span>)</caption>

   <tr>
   <td width="30%" align="right">输入原始密码:</td>
   <td> <input type="password" id="userPassword" name="userPwd" class="text" style="width:159px"    onblur="checkOldPwd();" /> 
     <span id="lblMsg"  style="color:red"></span>
    </td>  
   </tr>
    <tr>
   <td width="30%" align="right">输入新密码:</td>
   <td><input type="password"  id="userNewPwd" name="tb_user.userPwd" class="text" style="width: 159px;" /></td>  
   </tr>
     <tr>
      <td width="30%" align="right">确认新密码:</td>
   <td><input type="password" id="confirmPwd" class="text" style="width: 159px;" onblur="checkConfirmPwd();" name="newPWd" /> 
     <span id="lblPwd"  style="color:red"></span></td>  
   </tr>
  <tr> 
   <td colspan="2" align="center">
           <input type="submit" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif)" value="保 存"/>
          <input type="reset" style="width:63px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btnSubmit.gif); margin-left:15px;" value="重 填"/>
   </td>
   </tr>
  </table>
  </form> 	
</body>
</html>

