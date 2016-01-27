<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.bmj.userinsights.server.BMJSessionToken" %>
<script src="js/login.js"></script>
<%@page import="java.util.ResourceBundle"%>
<link rel="shorcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
<link href="resources/css/style.css" rel="stylesheet">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" media="screen" 
      href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/themes/ui-lightness/jquery-ui.css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

<% 
String username="";
String sessionExists="false";
if(session.getAttribute("BMJSessionToken")!=null)
{BMJSessionToken bmjSessionToken = (BMJSessionToken) session.getAttribute("BMJSessionToken");
username = bmjSessionToken.getUserFullName();
sessionExists = "true";}
ResourceBundle configProperties = ResourceBundle.getBundle("userinsights_messages");
String errmsgEmptyUserNameField = configProperties.getString("error.message.login.emptyUsername");
String errmsgEmptyPasswordField = configProperties.getString("error.message.login.emptyPassword");
String errmsgEmptyField = configProperties.getString("error.message.login.emptyFields");
String errmsgLoginDetailIncorrect = configProperties.getString("error.message.usernameandpassword.incorrect");
String errmsgWhileValidating = configProperties.getString("error.message.exception.occured");
String errmsgAddSerachFoundDate = configProperties.getString("error.message.addserach.future");
String errmsgAdvSeracheDates = configProperties.getString("error.message.advserach.dates");
%>
<script type="text/javascript">
var errmsgEmptyField = '<%=errmsgEmptyField%>';
var errmsgEmptyUserNameField = '<%=errmsgEmptyUserNameField%>';
var errmsgEmptyPasswordField = '<%=errmsgEmptyPasswordField%>';
var errmsgLoginDetailIncorrect = '<%=errmsgLoginDetailIncorrect%>';
var errmsgWhileValidating = '<%=errmsgWhileValidating%>';
var errmsgAddSerachFoundDate = '<%=errmsgAddSerachFoundDate%>';
var errmsgAdvSeracheDates = '<%=errmsgAdvSeracheDates%>';
</script>
<script src="resources/js/header.js"></script>
<div id="overlay-div"></div>
 <div id="wrapper">
	<div class="header-inside">
		<div class="logo fl"><a href="home.html"><img src="resources/images/logo.jpg"></a></div>
		
		<div class="main-nav">

			<ul class="nav fr">
			<% if (sessionExists.equalsIgnoreCase("true")) { %>
			<li><div class="loginname font13 fr" id="idShowUserName"><spring:message code="header_message_userhint"/> <%=username%></div></li>
			    <%
					} else {
				%>
				<li><div class="loginname font13 fr" id="idShowUserName" style="display: none"></div></li>
				<% } %>
				<li><a href="home.html">Home</a></li> 

				<%
					if (sessionExists.equalsIgnoreCase("true")) {
				%>
				<li><a href="logout.html" id="idSignDetail">  <spring:message code="header_message_label_out"/></a></li>
				<%
					} else {
				%>
				<li><a href="#" onclick="lightbox_open()" id="idSignDetail">  <spring:message code="header_message_label_in"/></a></li>
				<% } %>

			</ul>
			
		</div>
		
	</div>

</div>



