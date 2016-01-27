<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clear Cache</title>
</head>
<body>
	<form:form modelAttribute="cache">
		<c:if test="${!cache}">

			<p style="color: #00ff00;">
				<spring:message code="cache_success"></spring:message>
			</p>
		</c:if>
		<c:if test="${cache}">
			<p style="color: #ff0000;">
				<spring:message code="cache_err"></spring:message>
			</p>
		</c:if>
	</form:form>

</body>
</html>