<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="resources/css/select2.css" />
<script
	src="http://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script src="js/insight.js"></script>
<script src="resources/js/searchresults.js"></script>
<script type="text/javascript" src="resources/js/jquery.reticence.js"></script>
<script src="resources/js/searchTextAutoComplete.js"></script>

<% 
String sessionExists="false";
if(session.getAttribute("BMJSessionToken")!=null)
{sessionExists = "true";}
%>

<script type="text/javascript">
var sessionExists = '<%=sessionExists%>';
</script>

<div id="wrapper">
	<div id="search-panel" class="fl">
		<div id="advancepanel">
			<div id="gbqfw" role="search">
				<h2 class="search-head-title">
					<spring:message code="search_header_title" />
				</h2>

				<div id="advsearch">

					<fieldset class="adv-panel-field" id="gbqff">

						<div id="gbfwa" class="gbqfwa ">
							<div id="gbqfqw" class="gbqfqw " gh="sb">
								<div id="gbqfaa"></div>

								<div id="gbqfqwb" class="gbqfqwb"
									style="left: 0px; right: 19px;">
									<input id="gbqfq" class="gbqfif" name="keyword" type="text"
										autocomplete="on" value="${searchKeyword}" aria-label="Search"
										dir="ltr" spellcheck="false" aria-haspopup="true"
										aria-live="off" aria-owns="gs_sbt50"
										style="border: none; padding: 0px; margin: 0px; height: auto; width: 100%; position: absolute; z-index: 6; left: 0px; background: url(&quot;data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw%3D%3D&quot;) transparent;" />
									<form:hidden path="searchCriteria.strSearch" id="idSearch"
										style="width: 400px;" />

								</div>
								<div id="gbqfab" style="cursor: pointer;">
									<div class="aoo J-JN-I" tabindex="0" gh="sda" role="button"
										data-tooltip="Show search options"
										aria-label="Advanced search options">
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="gb_R gb_Pd" id="gbqfbw">
						<button onclick="quickAdvanceSearch('insight');" class="gbqfb"
							aria-label="Search Northgate Information Solutions Ltd Mail"
							name="" id="gbqfb">
							<span class="gbqfi gb_Lb"></span>
						</button>
					</div>

				</div>
			</div>

			<div id="search-bottom" style="display: none" class="fl">
				<div class="fr" id="close" onclick="hide()">
					<img src="resources/images/close.png">
				</div>
				<h2 class="advance-search-title">
					<spring:message code="search_header_advsearch" />
				</h2>
				<div id="error-message-fromDate" style="display: none"
					class="dateinfo"></div>
				<div class="dateinfo fr">
					<c:if test="${not empty fromDate}">
   					 ${fromDate}
				</c:if>
				</div>
				<div class="fl">
					<div tabindex="1" class="wrapper-dropdown-1 styled-select"
						style="float: left;">
						<form:select id="idInsightType" path="searchCriteria.insightType">
							<c:choose>
								<c:when test="${not empty searchCriteria.lstInsightTypesDto}">
									<form:option label="Search all insights" value="0" />
									<form:options items="${searchCriteria.lstInsightTypesDto}"
										itemLabel="codeDecodedName" itemValue="codeDecodedCode" />
								</c:when>
								<c:otherwise>
									<form:option label="Search all insights" value="0" />
								</c:otherwise>
							</c:choose>
						</form:select>
					</div>
					<div tabindex="1" class="wrapper-dropdown-2 styled-select"
						style="float: left;">
						<form:select id="idServerity" path="searchCriteria.serverity">
							<c:choose>
								<c:when test="${not empty searchCriteria.lstSeveritiesDto}">
									<form:option label="Search all severities" value="0" />
									<form:options items="${searchCriteria.lstSeveritiesDto}"
										itemLabel="codeDecodedName" itemValue="codeDecodedCode" />
								</c:when>
								<c:otherwise>
									<form:option label="Search all severities" value="0" />
								</c:otherwise>
							</c:choose>
						</form:select>
					</div>
					<div tabindex="1" class="wrapper-dropdown-3 styled-select"
						style="float: left;">
						<form:select id="idCreatedDate" path="searchCriteria.createdDate">
							<c:choose>
								<c:when test="${not empty searchCriteria.lstDateCriteriaDto}">
									<form:options items="${searchCriteria.lstDateCriteriaDto}"
										itemLabel="codeDecodedName" itemValue="codeDecodedCode" />
								</c:when>
								<c:otherwise>
									<form:option label="Created Date" value="1" />
								</c:otherwise>
							</c:choose>
						</form:select>
					</div>

					<div style="float: left;">
						<input id=fromdt type=text name="fromDate" value="From"
							readonly="true">
					</div>
					<div style="float: left;">
						<input id=todate type=text name="toDate" value="To"
							readonly="true">
					</div>
				</div>
				<div id="search" class="gb_R gb_Sd btnsearch" style="display: none">
					<button id="gbqfb_search" onclick="quickAdvanceSearch('deatil');"
						name="" aria-label="Search Gmail" class="gbqfb">
						<spring:message code="search_header_search" />
					</button>
				</div>
			</div>
			
			<div onclick="show()" id="idDivAdvanceSearch" ><h2 class="advance-search-title">
					Advanced Search
				</h2></div>
			<div id="btn-insight">
				<button id="btnAddNewInsight" name=""
					onclick="addnewinsight(this.id)" class="btnaddinsight fr font18">
					<spring:message code="search_header_addnew" />
				</button>
			</div>
		</div>

	</div>

</div>
