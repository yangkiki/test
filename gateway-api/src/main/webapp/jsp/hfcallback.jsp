<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- 1. 汇付post回调前台，website为全静态页面，无法处理post请求 --%>
<%@ page import="java.io.*" %>
<%
	PrintWriter pw = response.getWriter();
	String cmdId = request.getParameter("cmdId");	// en
	String respCode = request.getParameter("respCode");	//en
	
	String respDesc = request.getParameter("respDesc");  //cn
	
	respDesc = respDesc == null ? "" : new String(respDesc.getBytes("ISO-8859-1"), "UTF-8");
	
	boolean isSuccess = "000".equals(respCode);
	String tipTitle = "";

	if (cmdId == null) {
		pw.print(respDesc);
		return;
	}
	switch(cmdId) {
		case "UserRegister" :
			tipTitle = isSuccess ? "恭喜您注册成功" : "注册失败";
			break;
		case "NetSave" :
			tipTitle = isSuccess ? "恭喜您充值成功" : "充值失败";
			break;
		case "Cash" :
			tipTitle = isSuccess ? "恭喜您提现成功" : ("999".equals(respCode) ? "提现申请提交成功" : "提现失败");
			break;
		default:
			tipTitle = isSuccess ? "操作成功" : "操作失败";
			break;
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>操作结果</title>
		<style>
			body {
				padding: 0;
				margin: 0;
			}
			.return-popup-content {
				display: block;
				position: fixed;
				background-color: white;
				z-index: 1002;
				overflow: hidden;
			}
			.border-grey {
				border: #ccc 1px solid;
			}
			.bg-white {
				background: white;
			}
			.company-area-success-text {
				margin-left: 20px;
				line-height: 24px;
			}
			.font-yahei {
				font-family: microsoft yahei;
			}
			.font12 {
				font-size: 12px;
			}
			.font24 {
				font-size: 24px;
			}
			.color-grass-green {
				color: #34CC99;
			}
			.color-orange {
				color: #FB7A02;
			}
			.margin-top20 {
				margin-top: 20px !important;
			}
			.btn {
				display: inline-block;
				padding: 6px 12px;
				margin-bottom: 0;
				font-size: 14px;
				font-weight: normal;
				line-height: 1.42857143;
				text-align: center;
				white-space: nowrap;
				vertical-align: middle;
				-ms-touch-action: manipulation;
				touch-action: manipulation;
				cursor: pointer;
				-webkit-user-select: none;
				-moz-user-select: none;
				-ms-user-select: none;
				user-select: none;
				background-image: none;
				border: 1px solid transparent;
				border-radius: 4px;
			}
			.btn.btn-orange {
				background: #f53f1a;
				color: #fff;
			}
			.btn.btn-padding70 {
				padding-left: 70px;
				padding-right: 70px;
			}
			.font18 {
				font-size: 18px;
			}
			.margin-right10 {
				margin-right: 10px !important;
			}
			a {
				color: #666;
				text-decoration: none;
			}
			a:hover {
				color: #e43f06
			}
		</style>
	</head>
	<body>
		<!-- 弹出层信息 -->
		<div class="return-popup-content" id="xypopup" style="display: none;">
			<div class="border-grey bg-white" style="overflow: hidden;padding: 20px;">
				<div class="company-area-success-text font-yahei font12">
					<span class="font24 color-grass-green"><%=tipTitle %></span>
					<br/>
					<%if (!isSuccess) {%>
						<!-- <span class="color-orange">温馨提示：</span> -->
						<%=respDesc %>
					<%}%>
					<div class="margin-top20 padding-bottom20">
						<a class="btn btn-orange btn-padding70 font18 font-yahei margin-right10" href="#" onclick="window.close();">关闭页面</a>
						 <a href="/website/html/">回到首页</a>
					</div>
				</div>
			</div>
		</div>
		<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
		<script>
			$(function() {
				$("#xypopup").css({
					position: "fixed",
					left: ($(window).width() - $("#xypopup").outerWidth()) / 2,
					top: ($(window).height() - $("#xypopup").outerHeight()) / 2,
					display: 'block'
				});
			});
		</script>
		<%
			Enumeration paramNames = request.getParameterNames();
			
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
			
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					
					paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
					
					if (paramValue.length() != 0) {
		%>				
			<%="<input type=\"hidden\" name=\"" + paramName + "\" value=\"" + paramValue  + "\" /> \n" %>
		<%				
					}
				}
			}
		%>
	</body>
</html>