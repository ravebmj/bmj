<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/js/select2.js"></script>
<script src="resources/js/createinsight.js"></script>
<link rel="stylesheet" href="resources/css/select2.css" />
<form:form method="POST" id="frmInsight" modelAttribute="mInsightDTO" action="" >
<div id="main-container fl createview">
	<h2 class="h2_25">Create New Insight</h2>
	<div class="row-inside">
		<div class="form-title">
			<span class="input-title">Title</span> <span class="asterisk_red">*</span>
			<div class="input-control">
				<input type="text" value="Title text will come over here.."
					class="input-text">
			</div>
		</div>
		<div class="row-inside">
			<div class="form-title">
				<span class="select-title">Type</span> <span class="asterisk_red">*</span>
			</div>
			<div class="input-control">

				<select class="select-text">
					<option value=""></option>
				</select>

			</div>
		</div>
		<div class="row-inside">
			<div class="form-title">
				<span class="input-title">Found Date</span> <span><input
					id=fromdt type=text value="From"></span><span
					class="input-title-padd">Found Via</span> <select
					class="select-text170">
					<option value="Found Via">Found Via</option>
				</select>
			</div>
			<div class="input-control"></div>


			<div class="row-inside">
				<div class="form-title">
					<span class="input-title">Project</span>
				</div>
				<div class="input-control">

					<img src="images/tageditor.png">

				</div>
			</div>

			<div class="row-inside">
				<div class="form-title">Product</div>
				<div class="input-control">

					<form:hidden path="products" id="idProduct" style="width: 400px;"/>

				</div>
				<span>Begin typing to find and create labels or press down to
					select a suggested label</span>
			</div>


			<div class="row-inside">
				<div class="form-title">Found With</div>
				<div class="input-control">

					<input type="text" value="1"> users (if unsure enter 1)

				</div>
			</div>


			<div class="row-inside">
				<div class="form-title">Severity</div>
				<div class="input-control">

					<select class="select-text485">
						<option value="Severity">Severity</option>
					</select>

				</div>
			</div>



			<div class="row-inside">
				<div class="form-title">Applies To</div>
				<div class="input-control">

					<select class="select-text485">
						<option value="Main User Type">Main User Type</option>
					</select><br /> <select class="select-text485">
						<option value="Geographics">Geographics</option>
					</select>

				</div>
			</div>
			<div class="row-inside">
				<div class="form-title">Tag</div>
				<div class="input-control">

					<img src="images/tageditor.png">

				</div>
			</div>

			<div class="row-inside">

				<div class="input-control">
					<Img src="images/fileupload.png">

				</div>
			</div>

			<div class="row-inside">
				<div class="form-title">ADD URL</div>
				<div class="input-control">

					<input type="text" value="WWW." class="input-text"><br /> <input
						type="button" value="ADD" class="fr">

				</div>
			</div>

			<div class="row-inside">

				<div class="links input-control">

					<div class="">www.abc.com</div>
					<div class="fr">
						<img src="images/close-btn.png">
					</div>
					<div class="">www.xyz.com</div>
					<div class="fr">
						<img src="images/close-btn.png">
					</div>

				</div>
			</div>
					<input type="button" value="save" onclick="submitInsight();"/>
		</div>
	</div>
</div>
		<form:hidden path="id" />
	
</form:form>