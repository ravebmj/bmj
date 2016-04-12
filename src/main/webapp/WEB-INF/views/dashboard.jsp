<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ResourceBundle"%>
<script src="resources/js/searchheader.js"></script>
<script src="resources/js/Tooltip.js"></script>
<body id="headbg">
	<div id="wrapper" class="dashboard">
		<div id="main-container fl">
			<div class="row-header fl">
				<div class="viewall-top fr">
				<c:if test="${not empty dashboardDto.recentInsightsLst}">
					<a id="recentviewallid" class="recentViewAllClass"><spring:message
							code="dashboard_viewall_all" /></a></c:if>
				</div>
				<div class="header-title">
					<spring:message code="dashboard_viewall_title" />
				</div>
			</div>
			<div class="row-start fl">
				<div class="table-title font18 fl">
					<spring:message code="dashboard_viewall_insight" />
				</div>
			</div>
			<div class="row-head-p  fl">
				<div class="table-title font18 fl">
					<spring:message code="dashboard_viewall_project" />
				</div>
			</div>
			<div class="row-head-t  fl">
				<div class="table-title font18 fl">
					<spring:message code="dashboard_viewall_type" />
				</div>
			</div>
			<div class="row-head  fl">
				<div class="table-title font18 fr">
					<spring:message code="dashboard_viewall_lastedited" />
				</div>
			</div>
			<c:choose>
				<c:when test="${not empty dashboardDto.recentInsightsLst}">
					<c:forEach items="${dashboardDto.recentInsightsLst}" var="currQue"
						varStatus="queIndex">
						<div class="row-full fl">
							<div class="row-start-1  fl">
								<div class="table-title-row fl"
									onMouseOver="sshow('closediv${currQue.id}title')"
									onMouseOut="hhide('closediv${currQue.id}title')">
									<div id="${currQue.id}" class="clsShowTwoLines insightTitle">
										<span data-tooltip title="${currQue.escpedTitle}"
											class="cursor"><a>${currQue.escpedTitle}</a></span>
									</div>
								</div>
							</div>
							<div class="row-p  fl">
								<div class="table-row fl"
									onMouseOver="sshow('closediv${currQue.id}project')"
									onMouseOut="hhide('closediv${currQue.id}project')">
									<c:choose>
										<c:when test="${not empty currQue.insightProjectsDto}">
											<div class="clsShowTwoLines recentouterdivclass">
												<span> <c:forEach
														items="${currQue.insightProjectsDto}" var="currPro"
														varStatus="proIndex">
														<a id="${currPro.project.id}" class="insightProjectClass">${currPro.project.name}
														</a>
														<c:if test="${!proIndex.last }">,</c:if>
													</c:forEach>
												</span>
											</div>
											<div id="closediv${currQue.id}project" style="display: none;"
												class="closediv">
												<span class="fr"><span class="hiddentext fl">
														<c:forEach items="${currQue.insightProjectsDto}"
															var="currPro" varStatus="proIndex">
															<a id="${currPro.project.id}" class="insightProjectClass">${currPro.project.name}
															</a>
															<br />
														</c:forEach>
												</span></span>
											</div>
										</c:when>
										<c:otherwise>
		              &nbsp;
		             </c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="row-t  fl">
								<div class="table-row-grey fl">
									<c:out value="${currQue.insightTypeName}" />
								</div>
							</div>
							<div class="row  fr">
								<div class="table-row-end font15 fr">
									<c:out value="${currQue.lastEditedDate}" />
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="norecordsfound">
						<spring:message code="dashboard_viewall_norecord" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="newpanel">
		<div class="viewall_bottom fr">
					<a id="productViewallid" class="productViewAllClass"><spring:message code="dashboard_viewall_product" /></a></div>
		<div class="header-title-middle">
					<spring:message code="dashboard_product_title" />
					<c:if test="${not empty dashboardDto.productDtoLst}">
					</c:if>
				</div>
		
		<c:choose>
		<c:when test="${not empty dashboardDto.productDtoLst}">
		
		<c:forEach items="${dashboardDto.productDtoLst}" var="currPro" > 
		<div class="insigt-cont"><span class="insigt-det">
    	<br/><a id="${currPro.id}" class="insightProductClass">${currPro.name} </a><br/></span></div>
       </c:forEach>
		</c:when>
		<c:otherwise>
		<div class="norecordsfound">
					<spring:message code="dashboard_viewall_norecord" />
					</div>
		</c:otherwise>
		</c:choose>
		</div>
		
		
		<div id="main-container fl">
			<div class="row-header fl">
				<div class="viewall_bottom fr">
				<c:if test="${not empty dashboardDto.strongestEvidenceInsightLst}">
					<a id="evidviewallid" class="evidViewAllClass"><spring:message
							code="dashboard_strgevi_all" /></a></c:if>
				</div>
				<div class="header-title">
					<spring:message code="dashboard_strgevi_title" />
				</div>
			</div>
			<div class="row-start fl">
				<div class="table-title font18 fl">
					<spring:message code="dashboard_strgevi_insight" />
				</div>
			</div>
			<div class="row-head-middle  fl">
				<div class="table-title font18 fl">
					<spring:message code="dashboard_strgevi_project" />
				</div>
			</div>
			<div class="row-head-end  fl">
				<div class="table-title font18 fr">
					<spring:message code="dashboard_strgevi_users" />
				</div>
			</div>
			<c:choose>
				<c:when test="${not empty dashboardDto.strongestEvidenceInsightLst}">
					<c:forEach items="${dashboardDto.strongestEvidenceInsightLst}"
						var="evidQue" varStatus="queIndex">
						<div class="row-full fl">
							<div class="row-start-1 fl">
								<div class="table-title-row fl"
									onMouseOver="sshow('evid${evidQue.id}title')"
									onMouseOut="hhide('evid${evidQue.id}title')">
									<div id="${evidQue.id}" class="clsShowTwoLines insightTitle">
										<span data-tooltip title="${evidQue.escpedTitle}"
											class="cursor"><a>${evidQue.escpedTitle}</a></span>
									</div>
								</div>
							</div>
							<div class="row-p-middle  fl">
								<div class="table-row fl"
									onMouseOver="sshow('evid${evidQue.id}project')"
									onMouseOut="hhide('evid${evidQue.id}project')">
									<c:choose>
										<c:when test="${not empty evidQue.insightProjectsDto}">
											<div class="clsShowTwoLines evidouterdivclass">
												<span> <c:forEach
														items="${evidQue.insightProjectsDto}" var="evidPro"
														varStatus="proIndex">
														<a id="${evidPro.project.id}" class="insightProjectClass">${evidPro.project.name}</a>
														<c:if test="${!proIndex.last }">,</c:if>
													</c:forEach>
												</span>
											</div>
											<div id="evid${evidQue.id}project" style="display: none;"
												class="closediv">
												<span class="fr"><span class="hiddentext fl">
														<c:forEach items="${evidQue.insightProjectsDto}"
															var="evidPro" varStatus="proIndex">
															<a id="${evidPro.project.id}" class="insightProjectClass">${evidPro.project.name}</a>
															</br>
														</c:forEach>
												</span></span>
											</div>

										</c:when>
										<c:otherwise>
		         &nbsp;
		         </c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="row  fr">
								<div class="table-row-end fr">
									<c:out value="${evidQue.foundCount}" />
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="norecordsfound">
						<spring:message code="dashboard_strgevi_norecord" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>


<c:if test="${not empty dashboardDto.insightId  || not empty dashboardDto.bannerText}">
<form:form method="GET" id="frmBannerSubmit" action	="dashboard.html" >
<input type="hidden" name="banner" id="banner" value="true"/>
<input type="hidden" id="googleSession" name="googleSession" value=""/>
</form:form>
<div id="idUndoBanner" style="display: inline;" >
${dashboardDto.bannerText}
<c:if test="${not empty dashboardDto.insightId}">
<input type="hidden" name="insightId" id="insightid" value="${dashboardDto.insightId}"/>
</c:if>
</div>
</c:if>









