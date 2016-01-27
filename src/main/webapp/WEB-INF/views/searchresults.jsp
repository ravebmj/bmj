
<%@page import="org.bmj.userinsights.search.dto.SearchResultDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ResourceBundle"%>
<script src="resources/js/searchresult.js"></script>
<%
ResourceBundle configProperties = ResourceBundle.getBundle("userinsights_messages");
String errmsgnocheckboxselected = configProperties.getString("warning.message.no.checkbox.selected");
String errmsgmorethantencheckboxselected = configProperties.getString("warning.message.morethanten.checkbox.selected");
SearchResultDto model = (SearchResultDto)request.getAttribute("searchDto"); 
String keyword = "";
if(model!=null){
	keyword = model.getKeyword();
}
%>
<script type="text/javascript">
var errmsgnocheckboxselected = '<%=errmsgnocheckboxselected%>';
var errmsgmorethantencheckboxselected = '<%=errmsgmorethantencheckboxselected%>';

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
<script src="resources/js/Tooltip.js"></script>



<body id="headbg">
  <div id="wrapper">    
	<div id="main-container fl searchresults">	
	<div class="search-results-panel fl">
		<div id="search-title" class="fl"><spring:message code="search_result_header"/></div><div class="download-top" id="downloadBtn1"><a class="downloadBtn" id="downloadBtn" ><img src="resources/images/download-icon.png" style="vertical-align:middle"><spring:message code="search_result_link_download"/></a></div>
		</div>
		<form:hidden path="searchDto.sortFlag"/>
		<form:hidden path="searchDto.weightageSortFlag"/>
		<table id="searchTable" class="display select" style="margin:-20px 0 0 0">
		 <thead>
		  <tr>
		    <td></td> <td class="title"><spring:message code="search_result_title"/></td><td class="type"><spring:message code="search_result_type"/></td><td class="product"><spring:message code="search_result_product"/></td><td class="project"><spring:message code="search_result_project"/></td><td class="users"><spring:message code="search_result_users"/></td><td class="tags"><spring:message code="search_result_tag"/></td><td class="ledited"><spring:message code="search_result_lastedited"/></td><td style="display:none">Order</td>
		  </tr>
		 </thead> 
		 <tbody>
		 <c:if test="${not empty searchDto.searchResult}">
		 	<c:forEach items="${searchDto.searchResult}" var="insight">
			  <tr id="${insight.id}">
			    <td><input type="checkbox" class="tailsCheckBox"></td> 			    
			    <td onMouseOver="sshow('closediv${insight.id}title')" onMouseOut="hhide('closediv${insight.id}title')" >				    
				    <div id="${insight.id}" class="clsShowTwoLines insightTitle"><span data-tooltip title="${insight.escpedTitle}" class="cursor"><a>${insight.escpedTitle}</a></span></div>
				   
			    </td>
			    <td>${insight.insightTypeName}</td>   
			    <td onMouseOver="sshow('closediv${insight.id}product')" onMouseOut="hhide('closediv${insight.id}product')">
			    <c:choose>
			     <c:when test="${not empty insight.insightProductsDto}">
			     	<div class="clsShowTwoLines clssearchproduct"><span>
				     	<c:forEach items="${insight.insightProductsDto}" var="currPro" varStatus="proIndex"> 
				   		 	<a id="${currPro.product.id}" class="insightProductClass">${currPro.product.name}</a><c:if test="${!proIndex.last }">,</c:if>
				      	</c:forEach>
			      	</span></div>
			     	<div id="closediv${insight.id}product" style="display:none;" class="closediv"><span class="fl"><span class="hiddentext fl">        
		    		<c:forEach items="${insight.insightProductsDto}" var="currPro" varStatus="proIndex"> 
			   		 	<a id="${currPro.product.id}" class="insightProductClass">${currPro.product.name}</a><br>
			      </c:forEach>
			       </span></span></div>
			      </c:when>
			      <c:otherwise>
		              &nbsp;
		          </c:otherwise>  	    
			    </c:choose>
			    </td>
			    <td onMouseOver="sshow('closediv${insight.id}project')" onMouseOut="hhide('closediv${insight.id}project')">
			    <c:choose>
			     <c:when test="${not empty insight.insightProjectsDto}">
			     <div class="clsShowTwoLines clssearchproject"><span>
				     <c:forEach items="${insight.insightProjectsDto}" var="currPro" varStatus="proIndex"> 
				   		 	<a id="${currPro.project.id}" class="insightProjectClass">${currPro.project.name}</a><c:if test="${!proIndex.last }">,</c:if>
				      </c:forEach>
			      </span></div>
			     <div id="closediv${insight.id}project" style="display:none;" class="closediv"><span class="fl"><span class="hiddentext fl">       
		    		<c:forEach items="${insight.insightProjectsDto}" var="currPro" varStatus="proIndex"> 
			   		 	<a id="${currPro.project.id}" class="insightProjectClass">${currPro.project.name}</a><br>
			      </c:forEach>
			       </span></span></div>
			      </c:when>
			      <c:otherwise>
		              &nbsp;
		          </c:otherwise>  
			    </c:choose>  
			    </td> 
			    <td class="alignright">${insight.foundCount}</td>
			    <td onMouseOver="sshow('closediv${insight.id}tag')" onMouseOut="hhide('closediv${insight.id}tag')">
			     <c:choose>
			     <c:when test="${not empty insight.insightTagsDto}">
			      <div class="clsShowTwoLines clssearchtag"><span>
				     <c:forEach items="${insight.insightTagsDto}" var="currPro" varStatus="proIndex"> 
				   		 	<a id="${currPro.tag.id}" class="insightTagClass">${currPro.tag.name}</a><c:if test="${!proIndex.last }">,</c:if>
				      </c:forEach>
			      </span></div>
			      <div id="closediv${insight.id}tag" style="display:none;" class="closediv"><span class="fl"><span class="hiddentext fl">       
		    		<c:forEach items="${insight.insightTagsDto}" var="currPro" varStatus="proIndex"> 
			   		 	<a id="${currPro.tag.id}" class="insightTagClass">${currPro.tag.name}</a><br>
			      </c:forEach>
			      </span></span></div>
			      </c:when>
			     </c:choose>  
			    </td>   
			    <td>${insight.lastEditedDate}</td>
			    <td style="display:none">${insight.searchWeightageOrder}</td>
			  </tr>
			</c:forEach>		
			</c:if>		
		 </tbody>
	</table>

	<div class="download-bottom" id="downloadBtn2"><a class="downloadBtn" id="downloadBtnDwn" ><img src="resources/images/download-icon.png" style="vertical-align:middle"><spring:message code="search_result_link_download"/></a></div>
	
	<div id="ajaxloader">
    <img src="resources/images/ajax-loader.gif"/>
</div>

	
</div>
</div>
<form action="downloadReport.html" id="idDownloadInsights"  method="post" style="display: none" >
	    <input type="hidden" id="selectedInsights" name="selectedInsights">
		<input type="submit"  value="Submit" >
</form>	
<form action="downloadReportDisplay.html" id="idDownloadInsightsDisplay"  method="post" style="display: none" >
	    <input type="hidden" id="selectedInsights" name="selectedInsights">
		<input type="submit"  value="Submit" >
</form>
</body>

