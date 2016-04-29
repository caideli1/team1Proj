<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物业管理系统后台</title>
<link href="${pageContext.request.contextPath }/images/skin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/left.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/js/prototype.lite.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/moo.fx.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/moo.fx.pack.js" type="text/javascript"></script>

<script type="text/javascript">
 function logout(){
    if(confirm("确定要退出吗？")){
        window.location="${pageContext.request.contextPath }/Property?method=logOut";
    }
 }
 /* function  op(){
	  alert("nihao");
	   String value=request.getAttribute("para");
	   alert(value);
	   if(value==0){
		   window.location="${pageContext.request.contextPath }/login.jsp"; 
	   }
	 
	 
 }  */


  
</script>
</head>


<body>
 
         <c:choose>
					    <c:when test="${sessionScope.userinfo.roleId=='r001' }">
					    
					          <div id="top" style="width:100%;height:64px;padding:0px;">

<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" 
class="admin_topbg">
  <tr>
    <td width="45%" height="64"><img src="${pageContext.request.contextPath }/images/logoS2.gif" width="262" 

height="64"></td>
    <td width="55%" valign="top"   align="left"><table width="100%" border="0" cellspacing="0" 

cellpadding="0">
      <tr>
        <td width="72%" height="38" class="admin_txt" valign="middle">
        <span 

style="color:red;font-weight:bold">${sessionScope.userinfo.realName } </span> 您好,感谢登录使用本系统！
    <span   id="bottomMsg" style="margin-right:18px;"></span>
                <script type="text/javascript">
         	var now=new Date();
		 //获取年
		 var year=now.getFullYear();
		 //获取月
		var month=now.getMonth()+1;
		//获取日
		var date=now.getDate();
		var week=["星期天","星期一","星期二","星期三","星期四","星期五","星期六"];
	     var day=now.getDay();
		 var todayInfo="今天是："+year+"年"+month+"月"+date+"日   "+
		    week[day];
		 document.getElementById("bottomMsg").innerHTML=todayInfo;
         </script>
<a href="#" target="_self" onClick="logout();"><img 

src="${pageContext.request.contextPath }/images/out.gif" alt="安全退出" width="46" height="20" border="0" align="middle"></a>
</td>
        <td   width="18%">&nbsp;</td>
     <td width="10%">&nbsp;</td>  
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
        </div>
	<!-- 内容开始 -->
	<div id="cont"
		style="width:100%;height:auto; background-color:red;padding:0px;margin-top:2px;">
		<!-- 左侧div开始 -->
		<div id="left"
			style="float:left; width:18%; height:568px;margin-top:3px;padding:0px;border-right:1px solid  #777E82;">
			<table width="100%" height="280" border="0" cellpadding="0"
				cellspacing="0" bgcolor="#EEF2FB">
				<tr>
					<td width="182" valign="top">
						<!-- 内容层 -->
						<div id="container">
					
					
					
					
					
					
					
							<h1 class="type">
								<a href="javascript:void(0)">系统管理</a>
							</h1>
							<div class="content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
											width="182" height="5" />
										</td>
									</tr>
								</table>
								<ul class="MM">
									<li><a href="${pageContext.request.contextPath }/userservlet?method=listUser&pageNo=1&pageNo2=1" target="main">人员管理</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/userservlet?method=feeItems" target="main">收费项设置</a>
									</li>
								</ul>
							</div>
							<h1 class="type">
								<a href="javascript:void(0)">基础数据</a>
							</h1>
							<div class="content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
											width="182" height="5" />
										</td>
									</tr>
								</table>
								<ul class="MM">
									<li><a href="${pageContext.request.contextPath}/lzf?method=getBuild&pageNo=1" target="main">楼栋信息</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/lzf?method=getOwner&pageNo=1" target="main">业主信息</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/carstall?method=getCarStallInfo&carpageNo=1" target="main">车位信息</a>
									</li>
								</ul>
							</div>
							<h1 class="type">
								<a href="javascript:void(0)">报修单管理</a>
							</h1>
							<div class="content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
											width="182" height="5" />
										</td>
									</tr>
								</table>
								<ul class="MM">
									<li><a href="${pageContext.request.contextPath }/report?method=showReportList&pageNo=1" target="main">所有报修</a>
									</li>
									<li><a href="${pageContext.request.contextPath }/report?method=showUnacceptList&pageNo=1" target="main">未受理报修</a>
									</li>
									<li><a href="${pageContext.request.contextPath }/report?method=showAcceptList&pageNo=1" target="main">已受理报修</a>
									</li>
									<li><a href="${pageContext.request.contextPath }/report?method=showFinishList&pageNo=1" target="main">已处理报修</a>
									</li>
								</ul>
							</div>
							<h1 class="type">
								<a href="javascript:void(0)">投诉单管理</a>
							</h1>
							<div class="content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="../images/menu_topline.gif"
											width="182" height="5" />
										</td>
									</tr>
								</table>
								<ul class="MM">
									<li><a href="${pageContext.request.contextPath}/complain?method=doAllComplain&complainpageNo=1" target="main">所有投诉</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/complain?method=doUnDoWith&complainpageNo=1"
										target="main">未受理投诉</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/complain?method=doDoWithCom&complainpageNo=1"
										target="main">已受理投诉</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/complain?method=doFinishCom&complainpageNo=1"
										target="main">已处理投诉</a>
									</li>
								</ul>
							</div>
							<h1 class="type">
								<a href="javascript:void(0)">业主缴费管理</a>
							</h1>
							<div class="content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
											width="182" height="5" />
										</td>
									</tr>
								</table>
								<ul class="MM">
									<li><a href="${pageContext.request.contextPath}/userCost/addSendFee.jsp"
										target="main">录入缴费</a>
									</li>
									<li><a href="${pageContext.request.contextPath }/usercost?method=searchUsercost&feepageNo=1&feeItemId=0"
										target="main">缴费查询</a>
									</li>


								</ul>
							</div>
						</div>
						<h1 class="type">
							<a href="javascript:void(0)">缴费金额统计</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
										width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li><a href="${pageContext.request.contextPath }/fee?method=getFee" target="main">按项目统计</a>
								</li>
							</ul>
						</div>
						
<!-- /fee?method=getFee -->
	<!-- /userCost/totalReport.jsp	 -->			
						<%-- <h1 class="type">
							<a href="javascript:void(0)">服务中心</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
										width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li><a
									href="${pageContext.request.contextPath }/user/findInfoById/${session.user.userId}"
									target="main">个人资料</a>
								</li>
								<li><a href="${pageContext.request.contextPath }/carStall/selectOne"
									target="main">我要租车位</a>
								</li>
								<li><a href="${pageContext.request.contextPath }/report/preAddReport"  target="main">我要报修</a>
								</li>
								<li><a href="${pageContext.request.contextPath }/complain/preAddComplain" target="main">我要投诉</a>
								</li>
							</ul>
						</div> --%>
						<h1 class="type">
							<a href="javascript:void(0)">安全设置</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
										width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li><a href="${pageContext.request.contextPath }/user/updatePwd.jsp" target="main">修改密码</a>
								</li>

							</ul>
						</div> </#if> <script type="text/javascript">
							var contents = document
									.getElementsByClassName('content');
							var toggles = document
									.getElementsByClassName('type');

							var myAccordion = new fx.Accordion(toggles,
									contents, {
										opacity : true,
										duration : 400
									});
							myAccordion.showThisHideOpen(contents[0]);
						</script></td>
				</tr>
			</table>

			<!-- 左侧div结束 -->
		</div>
		<!-- 右侧div开始 -->
		<div id="right"
			style="float:left; width:81%;height:568px;margin-left:0px;margin-top:10px;background-image: url(${pageContext.request.contextPath }/images/mainBg.png); background-repeat: repeat-x;">
			<iframe width="100%" height="100%" frameborder="0" border="0"
				src="notice.html" marginwidth="0" marginheight="0" name="main"
				scrolling="no"></iframe>
			<!--  右侧div结束  -->
		</div>
		<!-- 内容结束 -->
	</div>
	<!-- 底部版权声明的div -->
	<div id="bottom"
		style="clear:left;width:100%;height:24px;padding:0px; background-image: url(${pageContext.request.contextPath }/images/i-1.png); background-repeat: repeat-x;text-align: center;">
		<span>版权所有：湖北理工学院计算机学院网络工程（1）班蔡得利  </span>
	</div>
					    
					    </c:when>
					    
					    <c:otherwise>
					   <div id="top" style="width:100%;height:64px;padding:0px;">

<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" 
class="admin_topbg">
  <tr>
    <td width="45%" height="64"><img src="${pageContext.request.contextPath }/images/logoS2.gif" width="262" 

height="64"></td>
    <td width="55%" valign="top"   align="left"><table width="100%" border="0" cellspacing="0" 

cellpadding="0">
      <tr>
        <td width="72%" height="38" class="admin_txt" valign="middle">
        <span 

style="color:red;font-weight:bold">${sessionScope.userinfo.realName } </span> 您好,感谢登录使用本系统！
    <span   id="bottomMsg" style="margin-right:18px;"></span>
                <script type="text/javascript">
         	var now=new Date();
		 //获取年
		 var year=now.getFullYear();
		 //获取月
		var month=now.getMonth()+1;
		//获取日
		var date=now.getDate();
		var week=["星期天","星期一","星期二","星期三","星期四","星期五","星期六"];
	     var day=now.getDay();
		 var todayInfo="今天是："+year+"年"+month+"月"+date+"日   "+
		    week[day];
		 document.getElementById("bottomMsg").innerHTML=todayInfo;
         </script>
<a href="#" target="_self" onClick="logout();"><img 

src="${pageContext.request.contextPath }/images/out.gif" alt="安全退出" width="46" height="20" border="0" align="middle"></a>
</td>
        <td   width="18%">&nbsp;</td>
     <td width="10%">&nbsp;</td>  
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
        </div>
	<!-- 内容开始 -->
	<div id="cont"
		style="width:100%;height:auto; background-color:red;padding:0px;margin-top:2px;">
		<!-- 左侧div开始 -->
		<div id="left"
			style="float:left; width:18%; height:568px;margin-top:3px;padding:0px;border-right:1px solid  #777E82;">
			<table width="100%" height="280" border="0" cellpadding="0"
				cellspacing="0" bgcolor="#EEF2FB">
				<tr>
					<td width="182" valign="top">
						<!-- 内容层 -->
						<div id="container">
					
					
					
					
					
					
					
						

					
						<h1 class="type">
							<a href="javascript:void(0)">服务中心</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
										width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li><a
									href="${pageContext.request.contextPath}/userservlet?method=personEdit&realId=${sessionScope.userinfo.userId}"
									target="main">个人资料</a>
								</li>
								<li><a href="${pageContext.request.contextPath}/userservlet?method=restCar&pageNo=1" target="main">我要租车位</a>
								</li>
								<li><a href="${pageContext.request.contextPath}/report/addNewReport.jsp"  target="main">我要报修</a>
								</li>
								<li><a href="${pageContext.request.contextPath}/complain/addComplain.jsp" target="main">我要投诉</a>
								</li>
							</ul>
						</div> 
						<h1 class="type">
							<a href="javascript:void(0)">安全设置</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="${pageContext.request.contextPath }/images/menu_topline.gif"
										width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li><a href="${pageContext.request.contextPath }/user/updatePwd.jsp" target="main">修改密码</a>
								</li>

							</ul>
						</div> </#if> <script type="text/javascript">
							var contents = document
									.getElementsByClassName('content');
							var toggles = document
									.getElementsByClassName('type');

							var myAccordion = new fx.Accordion(toggles,
									contents, {
										opacity : true,
										duration : 400
									});
							myAccordion.showThisHideOpen(contents[0]);
						</script></td>
				</tr>
			</table>

			<!-- 左侧div结束 -->
		</div>
		<!-- 右侧div开始 -->
		<div id="right"
			style="float:left; width:81%;height:568px;margin-left:0px;margin-top:10px;background-image: url(${pageContext.request.contextPath }/images/mainBg.png); background-repeat: repeat-x;">
			<iframe width="100%" height="100%" frameborder="0" border="0"
				src="notice.html" marginwidth="0" marginheight="0" name="main"
				scrolling="no"></iframe>
			<!--  右侧div结束  -->
		</div>
		<!-- 内容结束 -->
	</div>
	<!-- 底部版权声明的div -->
	<div id="bottom"
		style="clear:left;width:100%;height:24px;padding:0px; background-image: url(${pageContext.request.contextPath }/images/i-1.png); background-repeat: repeat-x;text-align: center;">
		<span>版权所有：湖北理工学院计算机学院网络工程（1）班蔡得利 </span>
	</div>
	
</body>
					    
					    </c:otherwise>
					  </c:choose>





	
</html>
