<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.net.*"%>

<script src="js/insight.js"></script> 
<script src="resources/js/searchresults.js"></script>
<script src="resources/js/viewinsight.js"></script>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" media="screen" 
      href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/themes/ui-lightness/jquery-ui.css" />
<script type="text/javascript" src="resources/js/jquery.reticence.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js" language="javascript" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css"/>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script src="resources/js/modernizr.js"></script>

<% 
String sessionExists="false";
if(session.getAttribute("BMJSessionToken")!=null)
{sessionExists = "true";}
%>

<script  type="text/javascript">
var sessionExists = '<%=sessionExists%>';
</script>


<div id="main-container" class="viewinsight bluebdr">
<c:if test="${mInsightDTO.msgSuccess!=''}">
	<div class="green center margintop10">${mInsightDTO.msgSuccess}</div>
</c:if>
<div class="edit"><input type="button" value="<spring:message code="viewinsight_edit_button"/>" class="editbtn" onclick="showEditInsight(this.id)" id="idEditButton"></div>
	<h2 class="h2_25"><spring:message code="viewinsight_title"/></h2>
	
	
	<div class="insighttitle font18">${mInsightDTO.insightDetailsDto.escpedTitle}    </div>
	<div><span class="addedon font13"><spring:message code="viewinsight_label_addedon"/> ${mInsightDTO.addedDate} </span>&nbsp;<span class="maillink font13"> <spring:message code="viewinsight_label_by"/> ${mInsightDTO.username}</span></div>
	<fieldset>
	<legend><spring:message code="viewinsight_label_description"/></legend>
	<span>${mInsightDTO.insightDetailsDto.description}</span>
<div class="view"><br/><span class="grey_highlight"><spring:message code="viewinsight_label_type"/></span><span id="viewInsightType">&nbsp;${mInsightDTO.insightDetailsDto.insightTypeName}</span><br/><br/>
<span class="grey_highlight"> <spring:message code="viewinsight_label_foundon"/></span><span>&nbsp;${mInsightDTO.foundDate} </span>&nbsp;&nbsp;

<div id="idDivfoundViaView" style="display: none">
<c:if test="${not empty mInsightDTO.insightDetailsDto.foundVias}">
<span class="grey_highlight"><spring:message code="viewinsight_label_via"/></span>&nbsp;<span>
	${mInsightDTO.strFoundVias}
	</span>
</c:if>
</div>
</div></fieldset>

<div id="idDivProjectView" style="display: none">
 <c:if test="${not empty mInsightDTO.insightDetailsDto.insightProjectsDto}">
	<fieldset><legend><spring:message code="viewinsight_label_project"/></legend>
	<div class="view">

    
    <c:forEach items="${mInsightDTO.insightDetailsDto.insightProjectsDto}" var="currPro" varStatus="queIndex">    	
    	<a id="${currPro.project.id}" class="insightProjectClass">${currPro.project.name}</a><c:if test="${!queIndex.last }">,</c:if>
    </c:forEach>
 
	</div></fieldset>
 </c:if> 
 </div>
 <c:if test="${ not empty mInsightDTO.insightDetailsDto.escpedCompanyName}"><br/>
 <div id="idDivCompanyView"><span class="grey_highlight"><spring:message code="viewinsight_label_company_name"/></span><span>&nbsp;${mInsightDTO.insightDetailsDto.escpedCompanyName}</span>&nbsp;&nbsp;</div>
 </c:if>

  <c:if test="${not empty mInsightDTO.insightDetailsDto.insightProductsDto}">
	<fieldset><legend><spring:message code="viewinsight_label_product"/></legend>
	<div class="view">
    <c:forEach items="${mInsightDTO.insightDetailsDto.insightProductsDto}" var="currPro" varStatus="queIndex">    	
    	<a id="${currPro.product.id}" class="insightProductClass">${currPro.product.name}</a><c:if test="${!queIndex.last }">,</c:if>
    </c:forEach>
	</div>
	</fieldset>
	 </c:if>
	 
	 
	<fieldset>
		<div id="idDivfoundWithView" style="display: none">
	<div class="margintop20"><legend><spring:message code="viewinsight_label_strength"/> 
	<span class="no">${mInsightDTO.insightDetailsDto.foundCount}</span><span>&nbsp;<spring:message code="viewinsight_label_user"/></span></legend></div> </div><br/>
	<div class="margintop10">
	<div id="idDivSeverityView" style="display: none">
	<c:if test="${not empty mInsightDTO.insightDetailsDto.insightServerity and mInsightDTO.insightDetailsDto.insightServerity!=0}">
	<legend><spring:message code="viewinsight_label_severity"/><img src="displaySeverityImage.html?id=${mInsightDTO.insightDetailsDto.insightServerity}" class="vertmiddle"></legend>
	</c:if>
	</div>
	</div>
	<div>
	
		 <c:if test="${not empty mInsightDTO.insightDetailsDto.mainUserTypes}">  
		 <fieldset class="fieldset_geoprahic">
			<legend class="fl"><spring:message code="viewinsight_label_appliesto"/></legend> <div class="fl applies_w">${mInsightDTO.strAppliesto}</div>
			</fieldset>
		</c:if>
		
			<fieldset class="fieldset_geoprahic">
		 <c:if test="${not empty mInsightDTO.insightDetailsDto.geographies}">  
			<legend class="fl"><spring:message code="viewinsight_label_geolocation"/></legend> <span class="fl">${mInsightDTO.strGeographicalLoc}</span>
		</c:if>
</fieldset>
	</div></fieldset>
	 
	 <c:if test="${not empty mInsightDTO.insightDetailsDto.insightTagsDto}">  
	<fieldset><legend><spring:message code="viewinsight_label_tag"/></legend>
		<div style="">	     
	    <c:forEach items="${mInsightDTO.insightDetailsDto.insightTagsDto}" var="currPro" varStatus="queIndex">	    	
	    	<a id="${currPro.tag.id}" class="insightTagClass">${currPro.tag.name}</a><c:if test="${!queIndex.last }">,</c:if>
	    </c:forEach>
  		</div></fieldset>
	</c:if>
	<c:if test="${not empty mInsightDTO.lstInsightAttachmentDTO}">
	<span class="fr viewdownload"><a id="${mInsightDTO.insightDetailsDto.id}" class="clsDownloadAll"><spring:message code="viewinsight_label_attchment_all"/></a></span>
	<fieldset><legend><spring:message code="viewinsight_label_attchment"/></legend>
		
	 <c:forEach items="${mInsightDTO.lstInsightAttachmentDTO}" var="fileAtta">
	 	<c:set var="extname" value="${fileAtta.fileExt}" />
		<div style=""><span class="dtable">			
			<c:if test="${fn:containsIgnoreCase(extname, 'docx') || fn:containsIgnoreCase(extname, 'doc')}">		
				 <div id="effect-1" class="effects clearfix">
            <div class="img imgtext">
				<img src="resources/images/doc-thumb.png"><div class="ellipsis">${fileAtta.fileName}</div>
				  <div class="overlay">
				 
                    <a href="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}" class="expand"><img src="resources/images/downloading-icon.png" alt="Download" title="Download" style="width:25px;height:25px" /></a>
                     <p>${fileAtta.fileName}</p>
                                       
                </div>
                </div>
                </div>
			</c:if> 
			<c:if test="${fn:containsIgnoreCase(extname, 'xls') || fn:containsIgnoreCase(extname, 'xlsx')}">
		   <div id="effect-1" class="effects clearfix">
            <div class="img imgtext">
				<img src="resources/images/xls-thumb.jpg"><div class="ellipsis">${fileAtta.fileName}</div>
				  <div class="overlay">
                   <a href="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}" class="expand"><img src="resources/images/downloading-icon.png" alt="Download" title="Download" style="width:25px;height:25px" /></a>
                    <p>${fileAtta.fileName}</p>                   
                </div>
                </div>
            </div>			
			</c:if> 
			<c:if test="${fn:containsIgnoreCase(extname, 'ppt') || fn:containsIgnoreCase(extname, 'pptx')}">
				 <div id="effect-1" class="effects clearfix">
            <div class="img imgtext">
				<img src="resources/images/ppt-thumb.jpg"><div class="ellipsis">${fileAtta.fileName}</div>
				  <div class="overlay">
                   <a href="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}" class="expand"><img src="resources/images/downloading-icon.png" alt="Download" title="Download" style="width:25px;height:25px" /></a>
                   <p>${fileAtta.fileName}</p>                   
                </div>
                </div>
            </div>	
			</c:if> 
			<c:if test="${fn:containsIgnoreCase(extname, 'tif') || fn:containsIgnoreCase(extname, 'tiff')}">	
				<div id="effect-1" class="effects clearfix">
            <div class="img imgtext">
				<img src="resources/images/tiff-thumb.png"><div class="ellipsis">${fileAtta.fileName}</div>
				  <div class="overlay">
                   <a href="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}" class="expand"><img src="resources/images/downloading-icon.png" alt="Download" title="Download" style="width:25px;height:25px" /></a>
                   <p>${fileAtta.fileName}</p>                   
                </div>
                </div>
            </div>			
			</c:if> 
			<c:if test="${fn:containsIgnoreCase(extname, 'pdf')}">
			<div id="effect-1" class="effects clearfix">
            <div class="img imgtext">
				<img src="resources/images/pdf-thumb.jpg"><div class="ellipsis">${fileAtta.fileName}</div>
				  <div class="overlay">
                   <a href="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}" class="expand"><img src="resources/images/downloading-icon.png" alt="Download" title="Download" style="width:25px;height:25px" /></a>
                   <p>${fileAtta.fileName}</p>                   
                </div>
                </div>
            </div>		
			</c:if>
			<c:if test="${fn:containsIgnoreCase(extname, 'csv')}">
			<div id="effect-1" class="effects clearfix">
            <div class="img imgtext">
				<img src="resources/images/csv1.jpg"><div class="ellipsis">${fileAtta.fileName}</div>
				  <div class="overlay">
                  <a href="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}" class="expand"><img src="resources/images/downloading-icon.png" alt="Download" title="Download" style="width:25px;height:25px" /></a>
                  <p>${fileAtta.fileName}</p>                   
                </div>
                </div>
            </div>		
			</c:if>
			 <c:if test="${fn:containsIgnoreCase(extname, 'jpg') || fn:containsIgnoreCase(extname, 'jpeg') 
			 			|| fn:containsIgnoreCase(extname, 'png') || fn:containsIgnoreCase(extname, 'bmp')
			 			|| fn:containsIgnoreCase(extname, 'gif')}">
				<div id="effect-1" class="effects clearfix">
            <div class="img imgtext">
				<img src="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}"  width="90" height ="70"  border="1"><div class="ellipsis">${fileAtta.fileName}</div>
				  <div class="overlay">
                   <a href="download.ajx?id=${fileAtta.id}&fileId=${fileAtta.fileId}&insightId=${fileAtta.insightId}" class="expand"><img src="resources/images/downloading-icon.png" alt="Download" title="Download" style="width:25px;height:25px" /></a>
                   <p>${fileAtta.fileName}</p>                   
                </div>
                </div>
            </div>	
			</c:if> 			
		</span></div>
	
	 </c:forEach>
	</fieldset>
   </c:if>
		 <c:if test="${not empty mInsightDTO.insightDetailsDto.weblinks}">  
		<fieldset><legend><spring:message code="viewinsight_label_url"/></legend>
		    <c:forEach items="${mInsightDTO.insightDetailsDto.weblinks}" var="currPro" varStatus="queIndex">	
		     <a href="#" onclick="window.open('${currPro.weblinkValue}')">${currPro.weblinkValue}</a><br/>
		     </c:forEach>
		</fieldset>
	 </c:if>
	 
	</div>
	<form method="get" id="frmEdit" action="editinsight.html" >
		<input type="hidden" id="insightId" name="insightId" value="${mInsightDTO.insightDetailsDto.id}"/> 
	</form>