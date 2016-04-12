
<%@page import="org.bmj.userinsights.search.dto.SearchResultDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ResourceBundle"%>
<script src="resources/js/searchresult.js"></script>
<%
SearchResultDto model = (SearchResultDto)request.getAttribute("searchDto"); 
String keyword = "";
if(model!=null){
	keyword = model.getKeyword();
}
%>
<script type="text/javascript">
var serachType = '';
var keyword = '';
var dateRangeOpt = '';
var insightType = '';
var serverity = '';
var fromDate = '';
var toDate = '';
<% if(model!=null) {%>
serachType = '<%=model.getSerachType()%>'; 
<% if(keyword != null && keyword.trim().length() != 0) { %>
	keyword = '<%=model.getKeyword().replaceAll("<'\'/", "&lt;/").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot")%>'; 
<%}%>
insightType = '<%=model.getInsightType()%>';
serverity = '<%=model.getServerity()%>';
<% if(model!=null && model.getDateRangeOpt()!=null) {%>
	dateRangeOpt = '<%=model.getDateRangeOpt()%>';
<%}%>
fromDate = '<%=model.getFromDate()%>';
toDate = '<%=model.getToDate()%>';
<%}%>
</script> 
<script src="resources/js/searchheader.js"></script>
<script src="resources/js/Tooltip.js"></script>



<body id="headbg">
  <div id="wrapper">    
	<div id="main-container fl searchresults">	
	<div class="search-results-panel fl">
		<div id="search-title" class="fl"><spring:message code="search_product_title"/></div>
		</div>
		<table id="searchTable" class="display select" style="margin:-20px 0 0 0">
		 <thead>
		 </thead> 
		 <tbody>
		 <c:if test="${not empty searchDto.productresult}">
		 	<c:forEach items="${searchDto.productresult}" var="product">
			   <tr><td>
			   <a id="${product.id}" onclick="showProduct(this.id)">${product.name}</a>
			   </td></tr>
			</c:forEach>		
			</c:if>		
		 </tbody>
	</table>

</div>

	
</div>


</body>

