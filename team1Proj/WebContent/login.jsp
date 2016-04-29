<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站管理员登陆</title>
<script type="text/javascript"  src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>

 
 <script type="text/javascript">
 
 jQuery(document).ready(
		 
	      function (){
	    	  
	    	  jQuery("#sbmt").click(
	    		 function (){
	    			  
	    			   jQuery.get("${pageContext.request.contextPath }/Property?method=checkName",
	    			{username:jQuery("#uname").val(),uesrpwd:jQuery("#uesrpwd").val()},function (inf){
	    				if(inf=="0"){
	    					alert("用户名不能为空");
	    				}
	    				if(inf=="1"){
	    					alert("密码不能为空");
	        				}
	    				if(inf=="2"){
	    					alert("用户名不存在");
	        				}
	    				if(inf=="3"){
	    					alert("密码错误");
	        				}
	    				if(inf=="4"){
	    					
	    					jQuery("#frm").submit();
	    					//response.sendRedirect("${pageContext.request.contextPath }/public/index.jsp");
	    				
	    					window.location.href="public/index.jsp";
	    					 
	        				}
	    				
	    			}		
	    			);     			    			     			  			 
	    		 }    		     		 
	       	 );  	   	        	  
	      }	            
	       );
   /*  $(document).ready(
      function (){
    	 $("#submit").click(
    		 function (){
    			$.get("${pageContext.request.contextPath }/Property?method=checkName",
    			{username:$("#uname").val(),userpwd:$("#uesrpwd").val()},function (inf){
    				//把服务器返回的数据显示到span
    				if(inf=="0"){
    				alert("用户名不能为空");
    				}
    				if(inf=="1"){
    					alert("用户名不存在");
        				}
    				if(inf=="2"){
    					alert("密码不能为空");
        				}
    				if(inf=="3"){
    					alert("密码错误");
        				}
    				
    			}		
    			);     			    			     			  			 
    		 }    		     		 
       	 );  	   	        	  
      }	            
       ); */
</script> 


<script language="JavaScript">
function correctPNG()
{
    var arVersion = navigator.appVersion.split("MSIE")
    var version = parseFloat(arVersion[1])
    if ((version >= 5.5) && (document.body.filters)) 
    {
       for(var j=0; j<document.images.length; j++)
       {
          var img = document.images[j]
          var imgName = img.src.toUpperCase()
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
          {
             var imgID = (img.id) ? "id='" + img.id + "' " : ""
             var imgClass = (img.className) ? "class='" + img.className + "' " : ""
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
             var imgStyle = "display:inline-block;" + img.style.cssText 
             if (img.align == "left") imgStyle = "float:left;" + imgStyle
             if (img.align == "right") imgStyle = "float:right;" + imgStyle
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle
             var strNewHTML = "<span " + imgID + imgClass + imgTitle
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
             img.outerHTML = strNewHTML
             j = j-1
          }
       }
    }    
}
window.attachEvent("onload", correctPNG);
function $(id){
 return document.getElementById(id);
}
</script>


<link href="images/skin.css" rel="stylesheet" type="text/css"  />
<body>

<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="85%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right" valign="top"><img src="images/logo.png" width="279" height="68" /></td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>1- 地区商家信息网门户站建立的首选方案...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>2- 一站通式的整合方式，方便用户使用...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>3- 强大的后台系统，管理内容易如反掌...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td width="30%" height="40"><img src="images/icon-demo.gif" width="16" height="16"><a href="http://www.865171.cn" target="_blank" class="left_txt3"> 使用说明</a> </td>
                      <td width="35%"><img src="images/icon-login-seaver.gif" width="16" height="16"><a href="http://www.865171.cn" class="left_txt3"> 在线客服</a></td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">后台管理登录</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21">
              
              <table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
                    
                    <form  method="post" id="frm">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">用户名：&nbsp;&nbsp; </span></td>
                            <td  width="14%"  height="38"  class="top_hui_text"><input type="text" id="uname"  name="userName" class="editbox4" value="" size="26"  onfocus="clearText();" /> 
                            <span  id="info1"></span>   </td>
                      
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td  width="14%" height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" id="uesrpwd" size="26" name="userPwd"  onfocus="clearText();"  />
                            <span  id="info2"></span>
                            </td>
                         <td   height="38"  class="top_hui_text" align="left"> </td>
                          </tr>
                      <tr>
                            
                            <td width="10%" height="35"  align="center"><input name="Submit" type="button" class="button" id="sbmt" value="登 录" /> </td>
                            <td width="37%" class="top_hui_text"><input name="cs" type="button" class="button" id="cs" value="取 消" onClick="showConfirmMsg1()" /></td>
                          </tr>
                        </table>
                        <br />
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2012-2016 湖北理工学院计算机学院网络工程（1）班蔡得利版权所有</span></td>
      </tr>
    </table></td>
  </tr>
</table>




</body>
<%-- <script> 
//取出传回来的参数error并与yes比较
  var errori ='<%=request.getParameter("error")%>';
  if(errori=='0'){
   alert("用户名不能为空");
  }
  else if(errori=='1'){
	   alert("密码不能为空");
	  }else{
		  if(errori=='4'){
			   alert("用户名不存在");
			   }else{
				   if(errori=='3'){
					   alert("密码错误");
					  }
				   
			   }
		  
		  
	  }
  
 
  
</script> --%>
</html>
