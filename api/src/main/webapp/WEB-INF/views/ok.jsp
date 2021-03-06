<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>处理成功</title>
<meta name="viewport" content="user-scalable=no, width=device-width, minimum-scale=1, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-title" content="YVES ROCHER">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="format-detection" content="telephone=no">

</head>

<body class="invest-successful">
	<div class="main" style="width:100%;height:100%;position:fixed">
		<div class="successful" style="width:100%;position:absolute;top:44%;font-size:22px;text-align:center">
            <c:choose>
                <c:when test="${resp.respCode=='000'}">
                    OK
                </c:when>
                <c:otherwise>
                    Failed<br>${resp.respCode}${resp.respDesc}
                </c:otherwise>
            </c:choose>
        </div>
	</div>
</body>
</html>
