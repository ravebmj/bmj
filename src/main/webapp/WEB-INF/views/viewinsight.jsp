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
<span class="grey_highlight">Found On:</span><span>Fri Dec 11 2015</span>&nbsp;&nbsp;<span class="grey_highlight">via</span>&nbsp;<span>Focused Groups</span></div></fieldset>
	<fieldset><legend>Projects</legend>
<div class="view"><a href="#">Project 1</a>,  <a href="#">Project 2</a>,  <a href="#">Project 3</a>,  <a href="#">Project 4</a>. <a href="#"> Project 5</a>,  <a href="#">Project 6</a>, <a href="#"> Project 7</a>, <a href="#">Project 8</a></div></fieldset>
	<fieldset><legend>Products</legend>
	<div class="view"><a href="#">Product 1</a>,<a href="#"> Product 2</a>, <a href="#">Product 3</a>, <a href="#">Product 4</a>, <a href="#">Product 5</a></div>
	<div class="margintop20">Strength: <img src="images/6.png">Users</div>
	<div class="margintop10">Severity: <img src="images/flag-green.png">(Green)</div>
	<div class="margintop20">Applies to: Doctors Geographical Location: UK</div></fieldset>
	<fieldset><legend>Tags</legend>
		<div style="">Tag1, Tag2, Tag3, Tag4</div></fieldset>
			<fieldset><legend>Attachments</legend>
		<div style=""><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span><span class="dtable"><img src="images/Form_img.png"><br/>IMG_2522.JPG </span></div></fieldset>
		<fieldset><legend>URL'S</legend>
			<a href="#">https://www.google.co.in</a><br/>
			<a href="#">https://www.google.co.in</a>		
		</fieldset>
	</form:form>
	</div>