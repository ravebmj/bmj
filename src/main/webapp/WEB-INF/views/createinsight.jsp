<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.ResourceBundle"%>

<script src="resources/js/select2.js"></script>
<script src="resources/js/tinyeditor.js"></script>
<script src="resources/js/dropdown.js"></script>
<script src="resources/js/dropzone.js"></script>
<script src="resources/js/insightUpload.js"></script>
<script src="resources/js/insightLightBox.js"></script>
<script src="resources/js/niceEdit.js"></script>


<link rel="stylesheet" href="resources/css/select2.css" />
<link rel="stylesheet" href="resources/css/dropdown.css" /> 
<link rel="stylesheet" href="resources/css/tinyeditor.css" />
<link rel="stylesheet" href="resources/css/fileupload.css" />
 <%
 String sessionExists="false";
 if(session.getAttribute("BMJSessionToken")!=null)
 {sessionExists = "true";}
 ResourceBundle configProperties = ResourceBundle.getBundle("userinsights_messages");
String errmsgTitleEmpty = configProperties.getString("error.message.title.empty");
String errmsgTitleMaxLimit = configProperties.getString("error.message.title.max.limit");
String errmsgTitlePaternMismatch = configProperties.getString("error.message.title.patern.mismatch");
String errmsgDescriptionEmpty = configProperties.getString("error.message.description.empty");
String errmsgDescriptionMaxLimit = configProperties.getString("error.message.description.max.limit");
String errmsgDescriptionPaternMismatch = configProperties.getString("error.message.description.patern.mismatch");
String errmsgInsightSelect = configProperties.getString("error.message.insightype.select");
String errmsgFoundDate = configProperties.getString("error.message.founddate.future");
String errmsgFoundViaOtherempty = configProperties.getString("error.message.foundvia.other.empty");
String errmsgProjectMaxLimit = configProperties.getString("error.message.project.max.limit");
String errmsgProjectPaternMismatch = configProperties.getString("error.message.project.patern.mismatch");
String errmsgProductMaxLimit = configProperties.getString("error.message.product.max.limit");
String errmsgProductPaternMismatch = configProperties.getString("error.message.product.patern.mismatch");
String errmsgTagMaxLimit = configProperties.getString("error.message.tag.max.limit");
String errmsgTagPaternMismatch = configProperties.getString("error.message.tag.patern.mismatch");
String errmsgFoundWithRange = configProperties.getString("error.message.foundwith.range");
String errmsgFoundWithOnlyDigit = configProperties.getString("error.message.foundwith.only.digit");
String errmsgAddURLMaxLimit= configProperties.getString("error.message.addURL.max.limit");
String errmsgUserTypeSelect = configProperties.getString("error.message.usertype.other.empty");
String errmsgGeographiesSelect= configProperties.getString("error.message.geographies.other.empty");
String errmsgAddURLInvalid= configProperties.getString("error.message.addURL.invalid.url");
String errmsgUserTypeMaxLimit= configProperties.getString("error.message.usertype.other.max.limit");
String errmsgGeographiesMaxLimit= configProperties.getString("error.message.geographies.other.max.limit");
String errmsgFoundViaOtherMaxLimit= configProperties.getString("error.message.foundvia.other.max.limit");
String errmsgAddURLEmpty= configProperties.getString("error.message.addURL.empty");
String emsgAttchSize= configProperties.getString("message.attachment.size");
String errmsgCompanyMaxLimit= configProperties.getString("error.message.company.maxlimit");


%>

<script type="text/javascript">
var errmsgCompanyMaxLimit = '<%=errmsgCompanyMaxLimit%>';
var emsgAttchSize = '<%=emsgAttchSize%>';
var sessionExists = '<%=sessionExists%>';
var errmsgTitleEmpty = '<%=errmsgTitleEmpty%>';
var errmsgTitleMaxLimit = '<%=errmsgTitleMaxLimit%>';
var errmsgTitlePaternMismatch = '<%=errmsgTitlePaternMismatch%>';
var errmsgDescriptionEmpty = '<%=errmsgDescriptionEmpty%>';
var errmsgDescriptionMaxLimit = '<%=errmsgDescriptionMaxLimit%>';
var errmsgDescriptionPaternMismatch = '<%=errmsgDescriptionPaternMismatch%>';
var errmsgInsightSelect = '<%=errmsgInsightSelect%>';
var errmsgFoundDate = '<%=errmsgFoundDate%>';
var errmsgFoundViaOtherempty = '<%=errmsgFoundViaOtherempty%>';
var errmsgProjectMaxLimit = '<%=errmsgProjectMaxLimit%>';
var errmsgProjectPaternMismatch = '<%=errmsgProjectPaternMismatch%>';
var errmsgProductMaxLimit = '<%=errmsgProductMaxLimit%>';
var errmsgProductPaternMismatch = '<%=errmsgProductPaternMismatch%>';
var errmsgTagMaxLimit = '<%=errmsgTagMaxLimit%>';
var errmsgTagPaternMismatch = '<%=errmsgTagPaternMismatch%>';
var errmsgFoundWithRange = '<%=errmsgFoundWithRange%>';
var errmsgFoundWithOnlyDigit = '<%=errmsgFoundWithOnlyDigit%>';
var errmsgAddURLMaxLimit = '<%=errmsgAddURLMaxLimit%>';
var errmsgUserTypeSelect = '<%=errmsgUserTypeSelect%>';
var errmsgGeographiesSelect = '<%=errmsgGeographiesSelect%>';
var errmsgAddURLInvalid = '<%=errmsgAddURLInvalid%>';
var errmsgUserTypeMaxLimit = '<%=errmsgUserTypeMaxLimit%>';
var errmsgGeographiesMaxLimit = '<%=errmsgGeographiesMaxLimit%>';
var errmsgFoundViaOtherMaxLimit = '<%=errmsgFoundViaOtherMaxLimit%>';
var errmsgAddURLEmpty = '<%=errmsgAddURLEmpty%>';
</script>
 
<script src="resources/js/createinsight.js"></script>


<body id="create_insight">
<form:form method="GET" id="frmInsight" modelAttribute="mInsightDTO" action="" >

<div id="main-container" class="createview bluebdr">

<c:if test="${mInsightDTO.msgSuccess!=''}">
	<div class="green center margintop10">${mInsightDTO.msgSuccess}</div>
</c:if>
	<h2 class="h2_25">
										<c:choose>
										    <c:when test="${mInsightDTO.insightDetailsDto.id==0}">
												<spring:message code="create_insight_header_new"/>
											</c:when>    
										    <c:otherwise>
												<spring:message code="create_insight_header_edit"/>
										    </c:otherwise>
										</c:choose>	
	
	
	</h2>
	<div id="idDragDropArea" style="width:997px;margin:0 auto;">
	<fieldset class="fieldset">
	<div class="row-inside">
		<div class="form-title"><legend class="legend"><spring:message code="create_insight_title"/><span class="asterisk_red">*</span></legend>
		<div id="error-message-title" style="display: none" class="errormessage"></div>	
			<div class="input-control">
				<form:textarea path="insightDetailsDto.title" class="input-textarea-title" id="idInsightTitle"/>
								
			</div>
			
		</div>
		<div class="form-title"><legend class="legend"><spring:message code="create_insight_desc"/><span class="asterisk_red">*</span></legend>
		<div id="error-message-description" style="display: none" class="errormessage"></div>
			<div class="input-control" style="margin:0 0 0 10px;background:#fff;">
				<form:textarea path="insightDetailsDto.description" id="idDesc" style="width: 100%;height:100px"  />
			</div>
			
		</div>
		<div class="row-inside fl width240">
			<div class="form-title"><legend class="legend"><spring:message code="create_insight_type"/><span class="asterisk_red">*</span></legend>
			<div id="error-message-type" style="display: none" class="errormessage"></div>
			<div class="input-control fl">
 				<form:select path="insightDetailsDto.type" class="select-input" onchange="showFieldForInsightPage(this.value)">
				   <form:option value="0" label="--- Select ---"/>
				   <form:options items="${mInsightDTO.lstInsightTypesDto}" itemLabel="codeDecodedName" itemValue="codeDecodedCode"/>
				</form:select> 
				
			</div>
			
		</div>
		</div>
		
		<div id="idDivfoundWith" style="display: none">
				<div class="row-inside fl width255">
				<div class="form-title"><legend class="legend"><spring:message code="create_insight_foundwith"/></legend></div>
				<div id="error-message-foundwith" style="display: none"></div>
				<div class="input-control fl">
					<form:input path="insightDetailsDto.foundCount" class="input-text-found" onkeypress="return onlyNumberKey(event,this.value)"/>
					 <span><spring:message code="create_insight_foundwith_helptext"/></span>
					
				</div>
				</div>
				
			</div>

<fieldset class="fieldset-form">
<div id="error-message-foundDate" style="display: none" class="errormessage-founddate"></div><div id="error-message-foundVia" style="display: none" class="errormessage-found-via"></div><br/>
		<div class="row-inside">
			<div class="form-title">
					
	
			
				<div class="form-title"><legend class="legend-f"><spring:message code="create_insight_founddate"/>

				
				<span>
					<form:input path="foundDate" id="idInsightDate"/>
				</span>
				
					
					
				<!-- ************** Found Via Start*************** -->
				<div id="idDivfoundVia" style="display: none">
					<dl class="fdropdown">
						<span class="input-title-padd"><spring:message code="create_insight_foundvia"/></span> 
						<dt>
							 <div>
      							<span class="hida"></span> 
      							<div id="divSelFoundVia">${mInsightDTO.lblFoundVia}</div>  
    						</div>
						</dt>
						
						<dd>
						
							<div class="mutliSelect" id="divFoundVia">
							
								<ul>
										<c:forEach var="foundVia" items="${mInsightDTO.lstFoundViaDto}">
											<c:if test="${foundVia.name!='Other'}">
												<li><input type="checkbox" name="chkfoundVia[]"
													value="${foundVia.id}" /><label>${foundVia.name}</label></li>
											</c:if>

										</c:forEach>
										<c:forEach var="foundVia" items="${mInsightDTO.lstFoundViaDto}">
											<c:if test="${foundVia.name=='Other'}">
												<li><input type="checkbox" name="chkfoundVia[]"
													id="chkFvOther" value="${foundVia.id}" /><label>${foundVia.name}</label></li>
											</c:if>
										</c:forEach>
										<li><input type="textbox" id="fv_others" value=""></li>
											
								</ul>
							</div>
							
						</dd>
						
					</dl>
					
					</legend>
					
					
					<form:hidden path="strFoundVia" id="idFoundVia" style="width: 400px;"/>
					<form:hidden path="strOldFoundVia" id="idOldFoundVia" style="width: 400px;"/> </div>
				<!-- ************** Found Via End*************** -->

			</div>
			<div class="input-control"></div>



<div id="idDivCompany" style="display: none"><div class="row-inside">
<div class="form-title"><legend class="legend-f"><spring:message code="create_insight_company"/></legend></div>
		<div id="error-message-company" style="display: none" class="errormessage"></div>
			<div class="input-control">
				<form:input path="insightDetailsDto.companyName" id="idCompany" class="idcompany-input"  />
			</div></div></div>



		<div id="idDivProject" style="display: none">	<div class="row-inside">
					<div class="form-title"><legend class="legend-f"> <spring:message code="create_insight_project"/></legend></div>
					<div id="error-message-project" style="display: none" class="errormessage"></div>
				<div class="input-control">

					<form:hidden path="strProjects" id="idProject" style="width: 400px;"/>
                    <form:hidden path="strOldProjects" id="idOldProject" style="width: 400px;"/>
				</div></div>
			</div>



			<div class="row-inside">
				<div class="form-title"><legend class="legend-f"><spring:message code="create_insight_product"/></legend></div>
				<div id="error-message-product" style="display: none" class="errormessage"></div>
				<div class="input-control">

					<form:hidden path="strProducts" id="idProduct" style="width: 400px;"/>
					<form:hidden path="strOldProducts" id="idOldProduct" style="width: 400px;"/>

				</div>
				<span class="begin_labeltyp font14"><spring:message code="create_insight_product_helptext"/></span>
			</div>

</fieldset>

<fieldset class="fieldset-form">
<div id="idDivSeverity" style="display: none">

			<div class="row-inside">
				<div class="form-title"><legend class="legend"> <spring:message code="create_insight_severity"/></legend></div>
				<div class="input-control">
 				<form:select path="insightDetailsDto.insightServerity" class="select-input-severity">
				   <form:option value="0" label="--- Select ---"/>
				   <form:options items="${mInsightDTO.lstSeveritiesDto}" itemLabel="codeDecodedName" itemValue="codeDecodedCode"/>
				</form:select> 


				</div>
				</div>
			</div>



			<div class="row-inside">
				<div class="form-title"><legend class="legend"><spring:message code="create_insight_applyto"/></legend>
				<div id="error-message-mainUserType" style="display: none" class="errormessage"></div>
				</div>
				
				<!-- ************** Main User Type Start*************** -->
					<dl class="mdropdown">
						<dt>
							<div>
      							<span class="hida"></span>  
      							<div id="divSelMainUsrType">${mInsightDTO.lblMainUserType}</div>    
    						</div>
						</dt>
						<dd>
							<div class="mutliSelect" id="divMainUserType">
								<ul>
									<c:forEach var="mainUserType" items="${mInsightDTO.lstMainUserTypeDto}">
										<c:if test="${mainUserType.name!='Other'}">
											<li><input type="checkbox" name="chkMainUserType[]" value="${mainUserType.id}" /><label>${mainUserType.name}</label></li>
										</c:if>
								
									</c:forEach>
									<c:forEach var="mainUserType" items="${mInsightDTO.lstMainUserTypeDto}">
										<c:if test="${mainUserType.name=='Other'}">
											<li><input type="checkbox" name="chkMainUserType[]" id="chkMutOther" value="${mainUserType.id}" /><label>${mainUserType.name}</label></li>
										</c:if>
									</c:forEach>
   
        <li><input type="textbox" id="mut_others" value=""></li>
   							
											
								</ul>
							</div>
						</dd>
					</dl>
					<form:hidden path="strMainUserType" id="idMainUserType" style="width: 400px;"/>
					<form:hidden path="strOldMainUserType" id="idOldMainUserType" style="width: 400px;"/>
				<!-- ************** Main User Type End*************** -->
				<!-- ************** Geographies Start*************** -->
				<div class="row-inside">
				<div class="form-title"><div id="error-message-geographies" style="display: none" class="errormessage"></div>
				</div>
				
					<dl class="gdropdown">
						<dt>
							<div>
      							<span class="hida">&nbsp;</span>     
      							<div id="divSelGeographies">${mInsightDTO.lblGeographies}</div> 
    						</div>
						</dt>
						
						<dd>
						
							<div class="mutliSelect" id="divGeographies">
							
								<ul>
									<c:forEach var="geographies" items="${mInsightDTO.lstGeographiesDto}">
										<c:if test="${geographies.name!='Other'}">
											<li><input type="checkbox" name="chkGeographies[]" value="${geographies.id}" /><label>${geographies.name}</label></li>
										</c:if>
									</c:forEach>	
									
									<c:forEach var="geographies" items="${mInsightDTO.lstGeographiesDto}">
										<c:if test="${geographies.name=='Other'}">
											<li><input type="checkbox" name="chkGeographies[]" id="chkGOther" value="${geographies.id}" /><label>${geographies.name}</label></li>
										</c:if>						
									</c:forEach>										
        							<li><input type="textbox" id="g_others" value=""></li>
    				
			
											
								</ul>
							</div>
						</dd>
					</dl>
					<form:hidden path="strGeographies" id="idGeographies" style="width: 400px;"/>
					<form:hidden path="strOldGeographies" id="idOldGeographies" style="width: 400px;"/>
				<!-- ************** Geographies End*************** -->				
			</div>
			</div>
</fieldset>
<fieldset class="fieldset-form">			
			<div class="row-inside">
				<div class="form-title"><legend class="legend"> <spring:message code="create_insight_tag"/></legend></div><div id="error-message-tag" style="display: none"></div>
				<div class="input-control tag-control">

					<form:hidden path="strTags" id="idTags" style="width: 400px;"/>
					<form:hidden path="strOldTags" id="idOldTag" style="width: 400px;"/>
				</div>
			</div>
</fieldset>
<fieldset class="fieldset-form">	
			<div class="row-inside" >
			
			<div class="form-title">
				<legend class="legend"><spring:message code="create_insight_attachments"/></legend> 
			</div><div id="error-message-attachment" style="display: none" class="errormessage"></div>
				<div class="input-control ddrop" id="idDragDropArea">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Drop file to attach or <a href="#" onclick="document.getElementById('idDragDropArea').click(); return false;" />Browse</a><input name="file" type="file" multiple style="visibility: hidden;"  />
    </div>
				
			
				<div id="divAttachDetails" class="attachment">
						<c:if test="${fn:length(mInsightDTO.attachmentDTO.lstInsightAttachmentDTO) > 0}">
							<table width="98%" cellpadding="5" cellspacing="0" border="0" style="margin:0 auto;">
								<!--tr>
									<th width="58%">File Name</th>
									<th>Size</th>
									<th>Download</th>
									<th>Delete</th>
								</tr-->
								<c:forEach var="attachObj"
									items="${mInsightDTO.attachmentDTO.lstInsightAttachmentDTO}">
									<tr>
										<td width="75%" style="border-bottom:1px solid #82e676">${attachObj.fileName}</td>
										<td style="border-bottom:1px solid #82e676;color:#929397;font-size:14px;">${attachObj.sizeInKB}</td>
										<td style="border-bottom:1px solid #82e676"><a href="download.ajx?id=${attachObj.id}&fileId=${attachObj.fileId}&insightId=${attachObj.insightId}"> <img src="resources/images/download.png" /></a></td>
										<td style="border-bottom:1px solid #82e676"><a href="javascript:void(0)" onclick="deleteFile('${attachObj.id}','${attachObj.insightId}','${attachObj.fileId}');"> <img src="resources/images/close-btn.png" /></a></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
				</div>
			</div>
</fieldset>
<fieldset class="fieldset-form">
			<div class="row-inside">
				<div class="form-title"><legend class="legend"><spring:message code="create_insight_link"/></legend><div id="error-message-addURL" style="display: none" class="errormessage"></div></div>
				<div class="input-control">

					<input type="text" value="" class="input-text fl" id="idUrl"><input
						type="button" value="<spring:message code="create_insight_link_button"/>" class="fl addbtn" onclick="addLink(${insightDetailsDto.id})">

				</div><br/><br/>
				
				<div id="divWebLinks">
					<c:if test="${fn:length(mInsightDTO.weblinkDTO.lstInsightWeblinkDTO) > 0}">
						<table width="98%" cellpadding="5" cellspacing="0" border="0" style="margin:0 auto;">
							<!--tr>
								<th>Name</th>
								<th>Delete</th>
							</tr-->
							<c:forEach var="weblinkObj" items="${mInsightDTO.weblinkDTO.lstInsightWeblinkDTO}">
								<c:if test="${!weblinkObj.flgDelete}">
									<tr>
										<td  width="73%">${weblinkObj.weblinkValue}</td>
										<td><a href="javascript:void(0)" onclick="deleteLink('${weblinkObj.id}','${weblinkObj.insightId}','${weblinkObj.webLinkUIId}');"> <img src="resources/images/close-btn.png" /></a></td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</c:if>
				</div>				
			</div>

</fieldset>	
</fieldset>		
			<div class="btn-panel">
			<span>
					<input type="button" value="<spring:message code="create_insight_button_saveandclose"/>" onclick="submitInsight('1');" class="save-btn"/>&nbsp; 
					<input type="button" value="<spring:message code="create_insight_button_saveandadd"/>" onclick="submitInsight('2');" class="save-btn"/>
					<input type="button" value="<spring:message code="create_insight_button_cancel"/>" onclick="cancelInsight();" class="save-btn"/>
		    </span>
			</div>		
		</div>
</div>
	</div>
</div>
		<form:hidden path="insightDetailsDto.id" />
		<form:hidden path="idOtherFoundVia" id="idOtherFoundVia"/>
		<form:hidden path="idOtherMainUserType" id="idOtherMainUserType"/>
		<form:hidden path="idOtherGeographies" id="idOtherGeographies"/>
		<input type="hidden" name="insightId" id="insightId"/>
		<input type="hidden" name="saveType" id="saveType"/>
		<input type="hidden" name="attchmentSize" id="attchmentSize" value="${fn:length(mInsightDTO.attachmentDTO.lstInsightAttachmentDTO)}"/>
		<input type="hidden" name="fromSave" id="fromSave" value=""/> 
	  
</form:form>

<div id="light_createinsight" >
	<div class="popup_head">
		<spring:message code="create_insight_header_edit"/>
	</div>
	<div class="createinsight_popup">

		
		<form method="GET" id="frmInsightEdit" action="saveInsightEdit.ajx">
			<div class="row-inside createview">
				<div class="form-title">
					<legend>
						<spring:message code="create_insight_title"/> <span class="asterisk_red">*</span>
					</legend><div id="error-message-edittitle" style="display: none" class="errormessage-popup"></div>
					<div class="input-control">
						<textarea type="text" id="idInsightEditTitle" name="idInsightEditTitle" class="input-textarea-title-popup"></textarea>
					</div>
				</div>
				<div class="row-inside">
					<div class="form-title"><legend><spring:message code="create_insight_desc"/> <span class="asterisk_red">*</span></legend><div id="error-message-editdesc" style="display: none" class="errormessage-popup"></div>
						<div class="input-control inp-div">
							<textarea id="idInsightEditDesc" name="idInsightEditDesc" style="width: 100%;height:100px;background:#fff;"></textarea>
						</div>
					</div>
				</div>
				
				<div id="idDivFoundWithProject" style="display: none">
				<div class="row-inside">
					<div class="form-title">
						<legend><spring:message code="create_insight_project"/></legend>
					</div><div id="error-message-editproject" style="display: none" class="errormessage-popup"></div>
					<div class="input-control">
					<input type="hidden" id="idInsigtEditProject" name="idInsigtEditProject" style="width: 400px;"/>
					<input type="hidden" id="idInsigtEditOldProject" name="idInsigtEditOldProject" style="width: 400px;"/>
					</div>
				</div>
				</div>
				
				<div id="idDivFoundWithCompany" style="display: none">
				<div class="row-inside">
					<div class="form-title">
						<legend><spring:message code="create_insight_company"/></legend>
					</div><div id="error-message-editcompany" style="display: none" class="errormessage-popup"></div>
					<div class="input-control" style="margin:0 0 0 10px;background:#fff;">
					<input type="text" id="idInsigtEditCompany" name="idInsigtEditCompany" class="idcompany-inputedit" style="width:98%">
					</div>
				</div>
				</div>
	

				<div class="row-inside">
					<div class="form-title">
						<legend><spring:message code="create_insight_product"/></legend>
					</div><div id="error-message-editproduct" style="display: none" class="errormessage-popup"></div>
					<div class="input-control">

					<input type="hidden" id="idInsigtEditProduct" name="idInsigtEditProduct" style="width: 400px;"/>
					<input type="hidden" id="idInsigtEditOldProduct" name="idInsigtEditOldProduct" style="width: 400px;"/>

					</div>
					<div class="begin_label_popup font14 marginbtm30"><spring:message code="create_insight_product_helptext"/></div>
				</div>

<div id="idDivFoundWithEdit" style="display: none">
				<div class="row-inside">
					<div class="form-title">
						<legend><spring:message code="create_insight_foundwith"/></legend>
					</div><div id="error-message-editFoundWith" style="display: none" class="errormessage-popup"></div>
					<div class="input-control marginbtm30">

						<input type="text" value="1" class="usersno" id="idInsightEditfoundCnt" name="idInsightEditfoundCnt" onkeypress="return onlyNumberKeyEdit(event,this.value)"> <span><spring:message code="create_insight_foundwith_helptext"/></span>

					</div>
				</div></div>
			</div></div><div class="btnpanel">
		<a href="#" class="closebtn" onClick="closeInsightLightBox();"><spring:message code="create_insight_button_close"/></a>
		<a href="javascript:void(0)" onclick="saveEditInsightData();" class="savebtn" ><spring:message code="create_insight_button_save"/></a>
	</div>

	<input type="hidden" id="hidEditInsightId" name="hidEditInsightId"/>
	<input type="hidden" id="hidoldFoundCount" name="hidoldFoundCount"/>
	
	</form>
</div>
</body>
<div id="fade_editinsight" onClick="closeInsightLightBox();"></div>

<script src="resources/js/insightRichEditor.js"></script>

<!--    Confirmation Poup Start -->


<div id="idconfirmation_popup">
	<div class="popup_head">
		Confirmation Message <a href="#divAttachDetails" class="close fr"
			onClick="confirmation_popup_close();">CLOSE</a>
	</div>
	<div class="createinsight_popup">

		<form>
			<div class="row-inside createview">
				<div class="form-title">
					<legend>File Name already exist.Do you want to overwrite
						it?</legend>


				</div>


			</div>
	</div>
	<div class="btnpanel fl">
		<a href="#divAttachDetails" class="closebtn" onClick="confirmation_popup_close();">No</a>
		<a href="#divAttachDetails" class="savebtn" onclick="replaceAttachment();">Yes</a>
<!-- 		<input type="button" class="savebtn" onclick="replaceAttachment();"/> -->
	</div>
	</form>

</div>
<script type="text/javascript">
window.document.onkeydown = function (e)
{
	if (!e){
		e = event;
	}
	if (e.keyCode == 27){
		confirmation_popup_close();
	}
}
function confirmation_popup(){
	window.scrollTo(0,0);
	document.getElementById('idconfirmation_popup').style.display='block';
	document.getElementById('fade').style.display='block';	
}
function confirmation_popup_close(){
	document.getElementById('idconfirmation_popup').style.display='none';
	document.getElementById('fade').style.display='none';
}
</script>
<a href="#" onclick="confirmation_popup()">confirmation link</a>





<div id="fade" onClick="confirmation_popup_close();"></div>