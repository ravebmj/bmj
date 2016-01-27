/*
	Dropdown with Multiple checkbox select with jQuery - May 27, 2013
	(c) 2013 @ElmahdiMahmoud
	license: http://www.opensource.org/licenses/mit-license.php
 */

$(function() {

	$(".fdropdown dt div").on('click', function() {
		$(".fdropdown dd ul").slideToggle('fast');
	});

	$(".fdropdown dd ul li a").on('click', function() {
		$(".fdropdown dd ul").hide();
	});
	$(".mdropdown dt div").on('click', function() {
		$(".mdropdown dd ul").slideToggle('fast');
	});

	$(".mdropdown dd ul li a").on('click', function() {
		$(".mdropdown dd ul").hide();
	});
	$(".gdropdown dt div").on('click', function() {
		$(".gdropdown dd ul").slideToggle('fast');
	});

	$(".gdropdown dd ul li a").on('click', function() {
		$(".gdropdown dd ul").hide();
	});

	function getSelectedValue(id) {
		return $("#" + id).find("dt a span.value").html();
	}

	$(document).bind('click', function(e) {
		var $clicked = $(e.target);
		if (!$clicked.parents().hasClass("fdropdown"))
			$(".fdropdown dd ul").hide();
		if (!$clicked.parents().hasClass("mdropdown"))
			$(".mdropdown dd ul").hide();
		if (!$clicked.parents().hasClass("gdropdown"))
			$(".gdropdown dd ul").hide();
	});
	
	// Changes on click of checkbox of "Found Via" dropdown.
	//var txtFoundVia;
	$('#divFoundVia input[type="checkbox"]').on(
			'click',
			function() {
				  var title= $(this).next('label').text();
				  //txtFoundVia = txtFoundVia+title+ ",";
				  if ($(this).is(':checked')) {
					    $(".hida").hide();
					    var strDisplay=document.getElementById('divSelFoundVia').innerHTML;
						if(strDisplay.indexOf(title)==-1){//if title is not present then add
						    if(strDisplay.trim()==''){
						    	strDisplay=title;
						    }else{
						    	strDisplay=strDisplay+', '+title;
						    }
						    document.getElementById('divSelFoundVia').innerHTML=strDisplay;
					    }
					    $(".hida").hide();
				 } else {
					 var strDisplay=document.getElementById('divSelFoundVia').innerHTML;
					 if(strDisplay.indexOf(', '+title+',')!=-1){
						 strDisplay=strDisplay.replace(', '+title,'');
					 }else if(strDisplay.indexOf(', '+title)!=-1){
						 strDisplay=strDisplay.replace(', '+title, '');
					 }else if(strDisplay.indexOf(title+',')!=-1){
						 strDisplay=strDisplay.replace(title+',', '');
					 }else if(strDisplay.indexOf(title)!=-1){
						 strDisplay=strDisplay.replace(title, '');
					 }
					 document.getElementById('divSelFoundVia').innerHTML=strDisplay;

				 }
			
			});

	// Changes on click of checkbox of "Found Via" dropdown on label click
	$('#divFoundVia label').on(
			'click',
			function(e) {
				
				  var title= $(this).text();
				  var checbox = $(this).prev().is(':checked');
				  if (!checbox) {
					  $(this).prev().click();
					    $(".hida").hide();
					    var strDisplay=document.getElementById('divSelFoundVia').innerHTML;
					   
						if(strDisplay.indexOf(title)==-1){//if title is not present then add
						    if(strDisplay.trim()==''){
						    	strDisplay=title;
						    }else{
						    	strDisplay=strDisplay+', '+title;
						    }
						    document.getElementById('divSelFoundVia').innerHTML=strDisplay;
					    }
					    $(".hida").hide();
				 } else {
					  $(this).prev().click();
					 var strDisplay=document.getElementById('divSelFoundVia').innerHTML;
					 if(strDisplay.indexOf(', '+title+',')!=-1){
						 strDisplay=strDisplay.replace(', '+title,'');
					 }else if(strDisplay.indexOf(', '+title)!=-1){
						 strDisplay=strDisplay.replace(', '+title, '');
					 }else if(strDisplay.indexOf(title+',')!=-1){
						 strDisplay=strDisplay.replace(title+',', '');
					 }else if(strDisplay.indexOf(title)!=-1){
						 strDisplay=strDisplay.replace(title, '');
					 }
					
					 document.getElementById('divSelFoundVia').innerHTML=strDisplay;

				 }
			
		
			});

	
	// Changes on click of checkbox of "Main User Type" dropdown 
	$('#divMainUserType input[type="checkbox"]').on(
			'click',
			function() {
				  var title= $(this).next('label').text();
				  if ($(this).is(':checked')) {
					    $(".hida").hide();
					    var strDisplay=document.getElementById('divSelMainUsrType').innerHTML;//$("#divSelFoundVia").innerHTML(txtFoundVia);
						if(strDisplay.indexOf(title)==-1){//if title is not present then add
						    if(strDisplay.trim()==''){
						    	strDisplay=title;
						    }else{
						    	strDisplay=strDisplay+', '+title;
						    }
						    document.getElementById('divSelMainUsrType').innerHTML=strDisplay;
					    }
					    $(".hida").hide();
				 } else {
					 var strDisplay=document.getElementById('divSelMainUsrType').innerHTML;
					 if(strDisplay.indexOf(', '+title+',')!=-1){
						 strDisplay=strDisplay.replace(', '+title,'');
					 }else if(strDisplay.indexOf(', '+title)!=-1){
						 strDisplay=strDisplay.replace(', '+title, '');
					 }else if(strDisplay.indexOf(title+',')!=-1){
						 strDisplay=strDisplay.replace(title+',', '');
					 }else if(strDisplay.indexOf(title)!=-1){
						 strDisplay=strDisplay.replace(title, '');
					 }
					 document.getElementById('divSelMainUsrType').innerHTML=strDisplay;

				 }
			
			});	
	
	// Changes on click of checkbox of "MainUser Type" dropdown on label click
	$('#divMainUserType label').on(
			'click',
			function(e) {
				
				  var title= $(this).text();
				  var checbox = $(this).prev().is(':checked');
				  if (!checbox) {
					  $(this).prev().click();
					    $(".hida").hide();
					    var strDisplay=document.getElementById('divSelMainUsrType').innerHTML;
					   
						if(strDisplay.indexOf(title)==-1){//if title is not present then add
						    if(strDisplay.trim()==''){
						    	strDisplay=title;
						    }else{
						    	strDisplay=strDisplay+', '+title;
						    }
						    document.getElementById('divSelMainUsrType').innerHTML=strDisplay;
					    }
					    $(".hida").hide();
				 } else {
					  $(this).prev().click();
					 var strDisplay=document.getElementById('divSelMainUsrType').innerHTML;
					 if(strDisplay.indexOf(', '+title+',')!=-1){
						 strDisplay=strDisplay.replace(', '+title,'');
					 }else if(strDisplay.indexOf(', '+title)!=-1){
						 strDisplay=strDisplay.replace(', '+title, '');
					 }else if(strDisplay.indexOf(title+',')!=-1){
						 strDisplay=strDisplay.replace(title+',', '');
					 }else if(strDisplay.indexOf(title)!=-1){
						 strDisplay=strDisplay.replace(title, '');
					 }
					
					 document.getElementById('divSelMainUsrType').innerHTML=strDisplay;

				 }
			
		
			});
	
	// Changes on click of checkbox of "Geographies" dropdown.
	$('#divGeographies input[type="checkbox"]').on(
			'click',
			function() {
				  var title= $(this).next('label').text();
				  //txtFoundVia = txtFoundVia+title+ ",";
				  if ($(this).is(':checked')) {
					    $(".hida").hide();
				    var strDisplay=document.getElementById('divSelGeographies').innerHTML;//$("#divSelFoundVia").innerHTML(txtFoundVia);
						if(strDisplay.indexOf(title)==-1){//if title is not present then add
						    if(strDisplay.trim()==''){
						    	strDisplay=title;
						    }else{
						    	strDisplay=strDisplay+', '+title;
						    }
						    document.getElementById('divSelGeographies').innerHTML=strDisplay;
					    }
					    $(".hida").hide();
				 } else {
					 var strDisplay=document.getElementById('divSelGeographies').innerHTML;
					 if(strDisplay.indexOf(', '+title+',')!=-1){
						 strDisplay=strDisplay.replace(', '+title,'');
					 }else if(strDisplay.indexOf(', '+title)!=-1){
						 strDisplay=strDisplay.replace(', '+title, '');
					 }else if(strDisplay.indexOf(title+',')!=-1){
						 strDisplay=strDisplay.replace(title+',', '');
					 }else if(strDisplay.indexOf(title)!=-1){
						 strDisplay=strDisplay.replace(title, '');
					 }
					 document.getElementById('divSelGeographies').innerHTML=strDisplay;

				 }
			
			});	
	
	// Changes on click of checkbox of "Geographies" dropdown on label click
	// Changes on click of checkbox of "MainUser Type" dropdown on label click
	$('#divGeographies label').on(
			'click',
			function(e) {
				
				  var title= $(this).text();
				  var checbox = $(this).prev().is(':checked');
				  if (!checbox) {
					  $(this).prev().click();
					    $(".hida").hide();
					    var strDisplay=document.getElementById('divSelGeographies').innerHTML;
					   
						if(strDisplay.indexOf(title)==-1){//if title is not present then add
						    if(strDisplay.trim()==''){
						    	strDisplay=title;
						    }else{
						    	strDisplay=strDisplay+', '+title;
						    }
						    document.getElementById('divSelGeographies').innerHTML=strDisplay;
					    }
					    $(".hida").hide();
				 } else {
					  $(this).prev().click();
					 var strDisplay=document.getElementById('divSelGeographies').innerHTML;
					 if(strDisplay.indexOf(', '+title+',')!=-1){
						 strDisplay=strDisplay.replace(', '+title,'');
					 }else if(strDisplay.indexOf(', '+title)!=-1){
						 strDisplay=strDisplay.replace(', '+title, '');
					 }else if(strDisplay.indexOf(title+',')!=-1){
						 strDisplay=strDisplay.replace(title+',', '');
					 }else if(strDisplay.indexOf(title)!=-1){
						 strDisplay=strDisplay.replace(title, '');
					 }
					
					 document.getElementById('divSelGeographies').innerHTML=strDisplay;

				 }
			
		
			});
	
	

});
