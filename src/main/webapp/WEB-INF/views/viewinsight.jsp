<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/insight.js"></script> 
	
<div id="main-container fl view">
	<h2 class="h2_25">View Insight</h2>
	<form:form modelAttribute="mInsightDTO">
	<div class="edit"><input type="button" value="Edit" class="editbtn"></div>
	<div style="color:#242627;font-size:18px;">${mInsightDTO.title}    </div>
	<div><span style="color:#666769;font-size:11px;">Added on : ${mInsightDTO.addedDate}     By:</span><span  style="color:#666769;font-size:11px;"><a href="mailto:john@bmj.com" target="blank">john@bmj.com</a></span></div>
	<fieldset>
	<legend>Description</legend>
	<span>${mInsightDTO.description}</span>
<div class="fl  view"><br/><span class="grey_highlight">Type:</span><span> User Insight</span><br/>
<span class="grey_highlight">Found On:</span><span>${mInsightDTO.foundDate} </span>&nbsp;&nbsp;<span class="grey_highlight">via</span>&nbsp;<span>Focused Groups</span></div></fieldset>
	<fieldset><legend>Projects</legend>
<div class="view">

 <c:if test="${not empty mInsightDTO.projectsList}">
       
    <c:forEach items="${mInsightDTO.projectsList}" var="currQue" varStatus="queIndex">
    	<a href="#" onclick="${currQue.id}">${currQue.name}</a>,  
    </c:forEach>
  </c:if> 





</div></fieldset>
	<fieldset><legend>Products</legend>
	<div class="view">
	
	 <c:if test="${not empty mInsightDTO.productsList}">
       
    <c:forEach items="${mInsightDTO.productsList}" var="currQue" varStatus="queIndex">
    	<a href="#" onclick="${currQue.id}">${currQue.name}</a>,  
    </c:forEach>
  </c:if>
	
	
	
	
	</div>
	<div class="margintop20">Strength: ${mInsightDTO.foundCount} Users</div>
	<div class="margintop10">Severity: ${mInsightDTO.insightServerity} <img src="images/flag-green.png">(Green)</div>
	<div class="margintop20">Applies to: Doctors Geographical Location: UK</div></fieldset>
	<fieldset><legend>Tags</legend>
		<div style="">
		
	 <c:if test="${not empty mInsightDTO.tagList}">       
	    <c:forEach items="${mInsightDTO.tagList}" var="currQue" varStatus="queIndex">
	    	<a href="#" onclick="${currQue.id}">${currQue.name}</a>,  
	    </c:forEach>
  	</c:if>
		
		
		</div></fieldset>
			<fieldset><legend>Attachments</legend>
		<div style=""><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span></div></fieldset>
		<fieldset><legend>URL'S</legend>
			<a href="#">https://www.google.co.in</a><br/>
			<a href="#">https://www.google.co.in</a>		
		</fieldset>
	</form:form>
	</div>