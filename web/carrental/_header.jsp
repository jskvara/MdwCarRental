<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Car Rental</title>
</head>
<body>

	<div>
		<c:if test="${not empty loginUrl}"><a href="${loginUrl}">Login</a></c:if>
		<c:if test="${not empty logoutUrl}"><a href="${logoutUrl}">Logout</a></c:if>
	</div>

	<c:forEach var="msg" items="${f:getMessages()}">
		<div class="${f:h(msg.type)}">${f:h(msg.message)}</div>
	</c:forEach>
