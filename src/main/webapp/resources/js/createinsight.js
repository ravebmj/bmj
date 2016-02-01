

$(document).ready(function() {
	
	if(sessionExists =='false')
	{
		$("#actionPage").val('create');
	}
	var insightType = $(".select-input").val();
	if(insightType == 0)
	{
	$(".select-input").val("1");
	}
	var foundWith = $(".input-text-found").val();
	if(foundWith.length == 0  || foundWith === undefined || foundWith === null)
	{
	$(".input-text-found").val("1");
	}
	var foundDate = $("#idInsightDate").val(); 
	if(foundDate.length == 0  || foundDate === undefined || foundDate === null)
	{
		
		
	var date = new Date();
	var today = parseDate(date);
	$("#idInsightDate").val(today);
	}

	var foundWith  = document.getElementById("insightDetailsDto.foundCount").value;
	if(foundWith==0)
	{
		document.getElementById("insightDetailsDto.foundCount").value="1";
	}
	
	document.getElementById("idInsightDate").readOnly = true;
	// ***********************************Tag Editor code for Product start***********************************
	$('#idProduct').select2({
		tags : true,
		tokenSeparators : [ ',' ],

		createSearchChoice : function(term) {
			return {
				id : term,
				text : term,
				n : "new",
				s : ""

			};
		},

		ajax : {
			url : 'listProduct.ajx',
			dataType : 'json',
			type : 'GET',
			data : function(term, page) {
				return {
					prodName : term
				};
			},
			results : function(data, page) {
				return {
					results : data

				};
			}
		},

		// Take default tags from the input value
		initSelection : function(element, callback) {
			var data = [];

			function splitVal(string, separator) {
				var val, i, l;
				if (string === null || string.length < 1)
					return [];
				val = string.split(separator);
				for (i = 0, l = val.length; i < l; i = i + 1)
					val[i] = $.trim(val[i]);
				return val;
			}

			$(splitVal(element.val(), ",")).each(function() {
				data.push({
					id : this,
					text : this
				});
			});

			callback(data);
		},

		// Some nice improvements:

		// max tags is 3
		maximumSelectionSize : 13,

		// override message for max tags
		formatSelectionTooBig : function(limit) {
			return "Max tags is only " + limit;
		},

		formatResult : formatProductResult
	});
	
	// ***********************************Tag Editor code for Product end***********************************

	
	
	
	// ***********************************Tag Editor code for Project start***********************************
	$('#idProject').select2({
		tags : true,
		tokenSeparators : [ ',' ],

		createSearchChoice : function(term) {
			return {
				id : term,
				text : term,
				n : "new",
				s : ""

			};
		},

		ajax : {
			url : 'listProjects.ajx',
			dataType : 'json',
			type : 'GET',
			data : function(term, page) {
				return {
					projName : term
				};
			},
			results : function(data, page) {
				return {
					results : data

				};
			}
		},

		// Take default tags from the input value
		initSelection : function(element, callback) {
			var data = [];

			function splitVal(string, separator) {
				var val, i, l;
				if (string === null || string.length < 1)
					return [];
				val = string.split(separator);
				for (i = 0, l = val.length; i < l; i = i + 1)
					val[i] = $.trim(val[i]);
				return val;
			}

			$(splitVal(element.val(), ",")).each(function() {
				data.push({
					id : this,
					text : this
				});
			});

			callback(data);
		},

		// Some nice improvements:

		// max tags is 3
		maximumSelectionSize : 13,

		// override message for max tags
		formatSelectionTooBig : function(limit) {
			return "Max tags is only " + limit;
		},

		formatResult : formatProjectResult
	});
	
	// ***********************************Tag Editor code for Project end***********************************	
	

	
	// ***********************************Tag Editor code for Tags start***********************************
	$('#idTags').select2({
		tags : true,
		tokenSeparators : [ ',' ],

		createSearchChoice : function(term) {
			return {
				id : term,
				text : term,
				n : "new",
				s : ""

			};
		},

		ajax : {
			url : 'listTags.ajx',
			dataType : 'json',
			type : 'GET',
			data : function(term, page) {
				return {
					tagName : term
				};
			},
			results : function(data, page) {
				return {
					results : data

				};
			}
		},

		// Take default tags from the input value
		initSelection : function(element, callback) {
			var data = [];

			function splitVal(string, separator) {
				var val, i, l;
				if (string === null || string.length < 1)
					return [];
				val = string.split(separator);
				for (i = 0, l = val.length; i < l; i = i + 1)
					val[i] = $.trim(val[i]);
				return val;
			}

			$(splitVal(element.val(), ",")).each(function() {
				data.push({
					id : this,
					text : this
				});
			});

			callback(data);
		},

		// Some nice improvements:

		// max tags is 3
		maximumSelectionSize : 13,

		// override message for max tags
		formatSelectionTooBig : function(limit) {
			return "Max tags is only " + limit;
		},

		formatResult : formatTagResult
	});
	
	// ***********************************Tag Editor code for Tags end***********************************	
	
	setFoundViaDropDown();
	setMainUserTypeDropDown();
	setGeographiesDropDown();
	
	var fv_others_value  = document.getElementById("fv_others").value;
	var mut_others_value  = document.getElementById("mut_others").value;
	var g_others_value  = document.getElementById("g_others").value;
	if(fv_others_value.length == 0  || fv_others_value === undefined || fv_others_value === null)
	{
		document.getElementById("fv_others").style.display = "none";
	}
	if(mut_others_value.length == 0  || mut_others_value === undefined || mut_others_value === null)
	{
		document.getElementById("mut_others").style.display = "none";
	}
	
	if(g_others_value.length == 0  || g_others_value === undefined || g_others_value === null)
	{
		document.getElementById("g_others").style.display = "none";
	}

	addRemoveClassForAttachment();
	
	var insightType = $(".select-input").val();
	showFieldForInsightPage(insightType);
	
	
});

function formatTagResult(data) {
	var markup;
	if (data.n === "new")
		markup = "<span class='label label-info margin-default'> (New Tag) </span>"	+ data.text;
	else
		markup = data.text;
	return markup;
};

function formatProjectResult(data) {
	var markup;
	if (data.n === "new")
		markup = "<span class='label label-info margin-default'> (New Project) </span>"	+ data.text;
	else
		markup = data.text;
	return markup;
};
function formatProductResult(data) {
	var markup;
	if (data.n === "new")
		markup = "<span class='label label-info margin-default'> (New Product) </span>"	+ data.text;
	else
		markup = data.text;
	return markup;
};

function submitInsight(type){

	console.debug('Inside Submit Insight');
	populateFoundVia(); // Populate found via data in hidden field.
	populateMainUserType();
	populateGeographies();
	var orginalDescription = nicEditors.findEditor('idDesc').getContent().replace(/&nbsp;/g, ' ').trim();
	var descToSave =replaceAll('<br>','<br/>',orginalDescription);
	document.getElementById("idDesc").value = descToSave;
	document.getElementById("frmInsight").action='saveinsight.html';
	document.getElementById("saveType").value=type;
	
	if(validate()){//nilesh	
	document.getElementById("frmInsight").submit();
	}
	
}

$(function() {
	// When user clicks on checkbox for "other'
	$('#chkFvOther').on('click',
			function() {
		
				if ($(this).is(':checked')) {// IF checked enable text filed.
					document.getElementById("fv_others").style.display = "block";
	
				} else {// else enable text field and clear text field value.
					console.debug('click********');
					document.getElementById("fv_others").style.display = "none";
					document.getElementById("fv_others").value='';
				}
	
	});
});
	
$(function() {
	// When user clicks on checkbox for "other'
	$('#chkGOther').on('click',
			function() {
				if ($(this).is(':checked')) {// IF checked enable text filed.
					document.getElementById("g_others").style.display = "block";
				} else {// else enable text field and clear text field value.
					console.debug('click********');
					document.getElementById("g_others").style.display = "none";
					document.getElementById("g_others").value='';
				}
	
	});
});

$(function() {
	// When user clicks on checkbox for "other'
	$('#chkMutOther').on('click',
			function() {
				if ($(this).is(':checked')) {// IF checked enable text filed.
					document.getElementById("mut_others").style.display = "block";
	
				} else {// else enable text field and clear text field value.
					console.debug('click********');
					document.getElementById("mut_others").style.display = "none";;
					document.getElementById("mut_others").value='';
				}
	
	});
});

/**
 * This method gets call before form submit.
 * It retrieve checked status of "Found Via" and set that value in  hidden id "idFoundVia"
 * e.g: "id1,id2,id3:othervalue,id7"
 */
function populateFoundVia(){
	var strFoundVia='';
	var arryFoundVia = document.forms['frmInsight'].elements['chkfoundVia[]'];
	var idOther=document.getElementById("idOtherFoundVia").value;
	for (var i=0, len=arryFoundVia.length; i<len; i++) {
	    if(arryFoundVia[i].checked){// If checked
			if(strFoundVia==''){
				if(arryFoundVia[i].value==idOther){
					strFoundVia=idOther+':'+document.getElementById("fv_others").value.trim();
				}else{
					strFoundVia=arryFoundVia[i].value;
				}
			}else{
				if(arryFoundVia[i].value==idOther){
					strFoundVia=strFoundVia+','+idOther+':'+document.getElementById("fv_others").value.trim();
				}else{
					strFoundVia=strFoundVia+','+arryFoundVia[i].value;
				}
			}	    	
		}
	}
	document.getElementById("idFoundVia").value=strFoundVia;
}

/**
 * This method gets call before form submit.
 * It retrieve checked status of "Main User Type" and set that value in  hidden id "idMainUserType"
 * e.g: "id1,id2,id3:othervalue,id7"
 */
function populateMainUserType(){
	var strMainUserType='';
	var arryMainUserType = document.forms['frmInsight'].elements['chkMainUserType[]'];
	var idOther=document.getElementById("idOtherMainUserType").value;
	for (var i=0, len=arryMainUserType.length; i<len; i++) {
	    if(arryMainUserType[i].checked){// If checked
			if(strMainUserType==''){
				if(arryMainUserType[i].value==idOther){
					strMainUserType=idOther+':'+document.getElementById("mut_others").value.trim();
				}else{
					strMainUserType=arryMainUserType[i].value;
				}
			}else{
				if(arryMainUserType[i].value==idOther){
					strMainUserType=strMainUserType+','+idOther+':'+document.getElementById("mut_others").value.trim();
				}else{
					strMainUserType=strMainUserType+','+arryMainUserType[i].value;
				}
			}	    	
		}
	}
	document.getElementById("idMainUserType").value=strMainUserType;
}

/**
 * This method gets call before form submit.
 * It retrieve checked status of "Geographies" and set that value in  hidden id "idGeographies"
 * e.g: "id1,id2,id3:othervalue,id7"
 */
function populateGeographies(){
	var strGeographies='';
	var arryGeographies = document.forms['frmInsight'].elements['chkGeographies[]'];
	var idOther=document.getElementById("idOtherGeographies").value;
	for (var i=0, len=arryGeographies.length; i<len; i++) {
	    if(arryGeographies[i].checked){// If checked
			if(strGeographies==''){
				if(arryGeographies[i].value==idOther){
					strGeographies=idOther+':'+document.getElementById("g_others").value.trim();
				}else{
					strGeographies=arryGeographies[i].value;
				}
			}else{
				if(arryGeographies[i].value==idOther){
					strGeographies=strGeographies+','+idOther+':'+document.getElementById("g_others").value.trim();
				}else{
					strGeographies=strGeographies+','+arryGeographies[i].value;
				}
			}	    	
		}
	}
	document.getElementById("idGeographies").value=strGeographies;
}

/**
 * This methods get call on load of page.
 * It set checkbox status of "Found Via" based on hidden id "idFoundVia".
 */
function setFoundViaDropDown(){
	var strFoundVia=document.getElementById("idFoundVia").value;// This is saved data for 'Found Via'. Format is "id1,id2,id3:othervalue,id7"
	var arrFoundVia = strFoundVia.split(',');
	var idOther=document.getElementById("idOtherFoundVia").value;// Id of 'Other' option of 'found via' master.
	var arryChkFoundVia = document.forms['frmInsight'].elements['chkfoundVia[]'];
	for (var i=0, len=arryChkFoundVia.length; i<len; i++) {// loop through checkbox elements.
		if(isExistInArray(arrFoundVia,arryChkFoundVia[i].value)){ // check if current checkbox value present in already saved result array.
			arryChkFoundVia[i].checked=true; // if present then checked the checkbox component.
			if(arryChkFoundVia[i].value==idOther){ // if checked element is of 'Other' type.
				document.getElementById("fv_others").value=getOtherValueFrmArray(arrFoundVia);// set value in text field of 'Other' option.
			}
		}
	}
}


/**
 * This methods get call on load of page.
 * It set checkbox status of "Main User Type" based on hidden id "idMainUserType".
 */
function setMainUserTypeDropDown(){
	var strMainUserType=document.getElementById("idMainUserType").value;// This is saved data for 'Main User Type'. Format is "id1,id2,id3:othervalue,id7"
	var arrMainUserType = strMainUserType.split(',');
	var idOther=document.getElementById("idOtherMainUserType").value;// Id of 'Other' option of 'Main User Type' master.
	var arryChkMainUserType = document.forms['frmInsight'].elements['chkMainUserType[]'];
	for (var i=0, len=arryChkMainUserType.length; i<len; i++) {// loop through checkbox elements.
		if(isExistInArray(arrMainUserType,arryChkMainUserType[i].value)){ // check if current checkbox value present in already saved result array.
			arryChkMainUserType[i].checked=true; // if present then checked the checkbox component.
			if(arryChkMainUserType[i].value==idOther){ // if checked element is of 'Other' type.
				document.getElementById("mut_others").value=getOtherValueFrmArray(arrMainUserType);// set value in text field of 'Other' option.
			}
		}
	}
}

/**
 * This methods get call on load of page.
 * It set checkbox status of "Geographies" based on hidden id "idGeographies".
 */
function setGeographiesDropDown(){
	var strGeographies=document.getElementById("idGeographies").value;// This is saved data for 'Geographies'. Format is "id1,id2,id3:othervalue,id7"
	var arrGeographies = strGeographies.split(',');
	var idOther=document.getElementById("idOtherGeographies").value;// Id of 'Other' option of 'Geographies' master.
	var arryChkGeographies = document.forms['frmInsight'].elements['chkGeographies[]'];
	for (var i=0, len=arryChkGeographies.length; i<len; i++) {// loop through checkbox elements.
		if(isExistInArray(arrGeographies,arryChkGeographies[i].value)){ // check if current checkbox value present in already saved result array.
			arryChkGeographies[i].checked=true; // if present then checked the checkbox component.
			if(arryChkGeographies[i].value==idOther){ // if checked element is of 'Other' type.
				document.getElementById("g_others").value=getOtherValueFrmArray(arrGeographies);// set value in text field of 'Other' option.
			}
		}
	}
}

/**
 * Get text box value of 'Other' option of  Checkbox from result.
 * @param arrFoundVia
 * @param idOther
 */
function getOtherValueFrmArray(arrFoundVia){
	for (var k=0, len=arrFoundVia.length; k<len; k++) {
		   var checkedValue=arrFoundVia[k];
		   if(checkedValue.indexOf(":")!=-1){// Other value
			   return checkedValue.substring(checkedValue.indexOf(":")+1,checkedValue.length);
		   }
	}
	return '';
}

/**
 * Check if pass value exist in array.
 * @param arrElements : Array which contains values for comparison
 * @param compValue value need to be compare with arrays
 * @returns {Boolean}
 */
function isExistInArray(arrElements,compValue){
	for (var j=0, len=arrElements.length; j<len; j++) {
		   if(arrElements[j]==compValue){
			   return true;
		   }else if((arrElements[j].indexOf(':'))!=-1){// Other option is set.
			   if(arrElements[j].substring(0,arrElements[j].indexOf(":"))==compValue){// Check Id field of 'Other' option of result with component element. 
				   return true;
			   }

		   }
	}
	return false;
}

function isHTML(str) {
    var a = document.createElement('div');
    a.innerHTML = str;
    for (var c = a.childNodes, i = c.length; i--; ) {
        if (c[i].nodeType == 1) return true; 
    }
    return false;
}

/*
 * Client Side Validation.
 */
function validate(){
	$("#error-message-title").hide();
	$("#error-message-description").hide();
	$("#error-message-type").hide();
	$("#error-message-foundDate").hide();
	$("#error-message-foundVia").hide();
	$("#error-message-project").hide();
	$("#error-message-product").hide();
	$("#error-message-foundwith").hide();
	$("#error_message_indiaction").hide();
	$("#error-message-tag").hide();
	$("#error-message-mainUserType").hide();
	$("#error-message-geographies").hide();
	$("#error-message-attachment").hide();
	$("#error-message-company").hide();
	
	
	var checkForUserInsight = true;
	var checkForMarkerInsight = true;
	var checkForCompetitorInsight = true;
	var checkForBugInsight = true;
	
	
	
	var submitFlag = true;	
   
	// Title
	var title = $(".input-textarea-title").val().trim();
	if((title.length ==0) || title==undefined ){
		showErrorMessage('#error-message-title',errmsgTitleEmpty);
		submitFlag = false;
	}else if(title.length >2000){
		showErrorMessage('#error-message-title',errmsgTitleMaxLimit);
		submitFlag = false;
	}
	
	$(".input-textarea-title").val(title);
	//Description
	var orginalDescription = nicEditors.findEditor('idDesc').getContent().replace(/&nbsp;/g, ' ').trim();
	
	if(orginalDescription.length == 4 && orginalDescription == '<br>')
	{
		showErrorMessage('#error-message-description',errmsgDescriptionEmpty);
		submitFlag = false;
	}
	
	
	var descToValidate =replaceAll('<br>','<br/>',orginalDescription);
	
	if((descToValidate.length ==0) || descToValidate==undefined )
	{
	    showErrorMessage('#error-message-description',errmsgDescriptionEmpty);
	    submitFlag = false;
	}else if(descToValidate.length >4000){
		showErrorMessage('#error-message-description',errmsgDescriptionMaxLimit);
		submitFlag = false;
	}
	//InsightType
	var insightType = $(".select-input").val();
	if(insightType == 0)
	{
	showErrorMessage('#error-message-type',errmsgInsightSelect);
	submitFlag = false;
	}

	
	if(insightType ==1){
		 checkForUserInsight = true;
		 checkForMarkerInsight = false;
		 checkForCompetitorInsight = false;
		 checkForBugInsight = false;
	}else if(insightType==2){
		 checkForUserInsight = false;
		 checkForMarkerInsight = true;
		 checkForCompetitorInsight = false;
		 checkForBugInsight = false;
	}else if(insightType==3){
		 checkForUserInsight = false;
		 checkForMarkerInsight = false;
		 checkForCompetitorInsight = true;
		 checkForBugInsight = false;
	}else if(insightType==4){
		 checkForUserInsight = false;
		 checkForMarkerInsight = false;
		 checkForCompetitorInsight = false;
		 checkForBugInsight = true;
	}else{
		 checkForUserInsight = false;
		 checkForMarkerInsight = false;
		 checkForCompetitorInsight = false;
		 checkForBugInsight = false;
	}
	
	
	
	//Found Date
	var foundDate = $("#idInsightDate").val(); 
    var today = new Date();     
    var d = new Date(foundDate);     
    var todayDateOnly = new Date(today.getFullYear(),today.getMonth(),today.getDate()); 
    var dDateOnly = new Date(d.getFullYear(),d.getMonth(),d.getDate());
    if(dDateOnly>todayDateOnly){ 
    	showErrorMessage('#error-message-foundDate',errmsgFoundDate);	
    	submitFlag = false;
    }

  //Found Via
    if(checkForUserInsight || checkForBugInsight )
    {
    var foundVia = document.getElementById("idFoundVia").value;
    var toCheck = foundVia.indexOf("9");
    var foundViaFlag = true;
    if(toCheck == -1)
    {
    foundViaFlag = false;
    }
    if(foundViaFlag)
    {
    	var foundViaText = foundVia.substring(foundVia.lastIndexOf(',')+1, foundVia.length);
    	var foundViaTextfinal = foundViaText.substring(foundViaText.indexOf(':')+1, foundViaText.length).trim();
		if(foundViaTextfinal.length ==0){
		showErrorMessage('#error-message-foundVia',errmsgFoundViaOtherempty);
		submitFlag = false;
        }else if(foundViaTextfinal.length>100){
        showErrorMessage('#error-message-foundVia',errmsgFoundViaOtherMaxLimit);
    	submitFlag = false;
        }
		
    }}

    // For Project
    if(checkForUserInsight || checkForMarkerInsight){
    var projectDetail = $("#idProject").val();
    if(projectDetail.length>0){
    if(projectDetail.length >100){
    	showErrorMessage('#error-message-project',errmsgProjectMaxLimit);
    	submitFlag = false;
	}
    }}
 
  
 // For Product
    var productDetail = $("#idProduct").val();
    if(productDetail.length>0){
    if(productDetail.length >100){
    	showErrorMessage('#error-message-product',errmsgProductMaxLimit);
    	submitFlag = false;
	}
    }

   // For Tag
    var tagDetail = $("#idTags").val();
    if(tagDetail.length>0){
    if(tagDetail.length >100){
    	showErrorMessage('#error-message-tag',errmsgTagMaxLimit);
    	submitFlag = false;
	}
    }
    // Found with Users
    if(checkForUserInsight){
    var foundWithUser = $(".input-text-found").val();
    $("#error-message-foundwith").hide();
	if(foundWithUser.length>0){
    var isNumber =  /^\d+$/.test(foundWithUser);
	var userSize = parseInt(foundWithUser);
    if(isNumber )
	{
	if (userSize>99999 || userSize==0) 
	{
	showErrorMessage('#error-message-foundwith',errmsgFoundWithRange);
	submitFlag =false;
	}
	}else{
	submitFlag = false;
	showErrorMessage('#error-message-foundwith',errmsgFoundWithOnlyDigit);
	}
    }}
    
 

	//For Main User
	var mainUserType = document.getElementById("idMainUserType").value;
    var toCheck = mainUserType.indexOf("14");
    var mainUserTypeFlag = true;
    if(toCheck == -1)
    {
    	mainUserTypeFlag = false;
    }
    if(mainUserTypeFlag)
    {
    	var mainUserTypeText = mainUserType.substring(mainUserType.lastIndexOf(',')+1, mainUserType.length);
    	var mainUserTypeTextfinal = (mainUserTypeText.substring(mainUserTypeText.indexOf(':')+1, mainUserTypeText.length)).trim();
		if(mainUserTypeTextfinal.length ==0){
		showErrorMessage('#error-message-mainUserType',errmsgUserTypeSelect);
		submitFlag = false;
		}else if(mainUserTypeTextfinal.length>100){
			showErrorMessage('#error-message-mainUserType',errmsgUserTypeMaxLimit);
			submitFlag = false;
		}
		}
	
    //For Geogaraphic
	var geogaraphic = document.getElementById("idGeographies").value;
	var toCheck = geogaraphic.indexOf("6");
	var geogaraphicFlag = true;
    if(toCheck == -1)
    {
    	geogaraphicFlag = false;
	}
	if(geogaraphicFlag)
	 {
	    	var geogaraphicText = geogaraphic.substring(geogaraphic.lastIndexOf(',')+1, geogaraphic.length);
	    	var geogaraphicTextfinal =geogaraphicText.substring(geogaraphicText.indexOf(':')+1, geogaraphicText.length).trim();
			if(geogaraphicTextfinal.length ==0){
			showErrorMessage('#error-message-geographies',errmsgGeographiesSelect);
			submitFlag = false;
			} else if(geogaraphicTextfinal.length>100){
				showErrorMessage('#error-message-geographies',errmsgGeographiesMaxLimit);
				submitFlag = false;
			}
			
	 }
	
	
	  //Company
	 if(checkForCompetitorInsight || checkForBugInsight){
	var company = $("#idCompany").val().trim();
	 if(company.length >100){
		showErrorMessage('#error-message-company',errmsgCompanyMaxLimit);
		submitFlag = false;
	}
	 }
	 
	
	 // Removing data from insight type
	 if(insightType ==1){
		 document.getElementById("insightDetailsDto.insightServerity").value='0';
	}else if(insightType==2){
		 document.getElementById("insightDetailsDto.insightServerity").value='0';
		 document.getElementById("insightDetailsDto.foundCount").value='0';
		document.getElementById("idFoundVia").value="";
	}else if(insightType==3){
		document.getElementById("idFoundVia").value = "";
		 document.getElementById("insightDetailsDto.foundCount").value='0';
		document.getElementById("insightDetailsDto.insightServerity").value='0';
		$("#idProject").val("");
	}else if(insightType==4){
		 document.getElementById("insightDetailsDto.foundCount").value='0';
		$("#idProject").val("");
	}
	 
	 //Moving as per error messsages
	 if( ($('#error-message-title').is(':visible')) ||  ($('#error-message-description').is(':visible'))){
		 $("#errorPlace").val('upper');
	 }else if(($('#error-message-company').is(':visible'))||  ($('#error-message-type').is(':visible')) || ($('#error-message-foundVia').is(':visible'))
			 || ($('#error-message-foundDate').is(':visible')) || ($('#error-message-project').is(':visible')) || ($('#error-message-product').is(':visible')) || ($('#error-message-foundwith').is(':visible')))
	 {
		 $("#errorPlace").val('upper');
	 }else if(($('#error-message-tag').is(':visible')) || ($('#error-message-mainUserType').is(':visible')) || ($('#error-message-geographies').is(':visible'))
			  || ($('#error-message-attachment').is(':visible'))){
		 $("#errorPlace").val('upper');
	 }
	
 	
 	if($("#errorPlace").val().trim() == 'upper')
 	{ 
 		$('#error-message-indication').show();
 	   $('html, body').animate({
           scrollTop: $("#main-container").offset().top
       }, 0);
 	}
	
	return submitFlag;
}
/**
 * It gets call when user clicks on add button of URL.  
 * @param insightId
 */
function addLink(insightId){
	$("#error-message-addURL").hide();
	document.getElementById("error-message-addURL").innerHTML = "";
	var submitFlag = true;
	var addUrlDeatil = $("#idUrl").val().trim();
	  
	
	if( $('#tableWebLink').length ) 
	{
		var table = document.getElementById('tableWebLink');
	    for (var r = 0, n = table.rows.length; r < n; r++) {
	        for (var c = 0, m = table.rows[r].cells.length; c < m; c++) {
	            var tableData = table.rows[r].cells[c].innerHTML;
	            if(tableData.trim() == addUrlDeatil){
	            	showErrorMessage('#error-message-addURL',errmsgUrlDuplicate);
	            	document.getElementById("idUrl").value = "";
	            	submitFlag = false;
	            }
	        }
	    }
	}
	
	
    if(submitFlag)
	{
	  var regexp1 = /^(([\w]+:)?\/\/)?(([\d\w]|%[a-fA-f\d]{2,2})+(:([\d\w]|%[a-fA-f\d]{2,2})+)?@)?([\d\w][-\d\w]{0,253}[\d\w]\.)+[\w]{2,4}(:[\d]+)?(\/([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)*(\?(&?([-+_~.\d\w]|%[a-fA-f\d]{2,2})=?)*)?(#([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)?$/;
	  var regexp2 = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	    if(addUrlDeatil.length==0)
	    {
	    	showErrorMessage('#error-message-addURL',errmsgAddURLEmpty);
	    	submitFlag = false;
	    } else if(addUrlDeatil.length >3550){
		   showErrorMessage('#error-message-addURL',errmsgaddURLMaxLimit);
		    submitFlag = false;
		}else if((!(regexp2.test(addUrlDeatil))) && (addUrlDeatil.length !=0) )
				{
			
			if(!(regexp1.test(addUrlDeatil)))
		{
		showErrorMessage('#error-message-addURL',errmsgAddURLInvalid);
		submitFlag = false;
		}
			
				} }
	if(submitFlag)
		{
		$.ajax({
			url : 'addLink.ajx',
			type : 'get',
			data : "value="	+ document.getElementById("idUrl").value	
			+ "&insightId="+insightId,
			success : function(serverResponse) {
				refreshWebLinks(serverResponse);
				document.getElementById("idUrl").value = "";
			},
			error : function(data, status, er) {
				
			}
		});
		}
	
}
/**
 * Refresh weblink table with latest weblinks. 
 * after add or delete weblinks.
 * @param serverResponse
 */
function refreshWebLinks(serverResponse){
	var finalWLdata = "";
	if(serverResponse.lstInsightWeblinkDTO.length>0){// If attached list is more than zero.
		finalWLdata = "<table width='98%' cellpadding='5' cellspacing='0' border='0' style='margin:0 auto;' id='tableWebLink'>";
		for (var i=0, len=serverResponse.lstInsightWeblinkDTO.length; i<len; i++) {
			if(serverResponse.lstInsightWeblinkDTO[i].flgDelete==false){// If weblink is not deleted.
				finalWLdata =finalWLdata+"<tr>";
				finalWLdata =finalWLdata+"<td  width='72%'>"+serverResponse.lstInsightWeblinkDTO[i].weblinkValue+"</td>";
				finalWLdata =finalWLdata+"<td><a href='javascript:void(0)'  onclick='deleteLink("+serverResponse.lstInsightWeblinkDTO[i].id+","+serverResponse.lstInsightWeblinkDTO[i].insightId+",&apos;"+serverResponse.lstInsightWeblinkDTO[i].webLinkUIId+"&apos;);' > <img src='resources/images/close-btn.png' /></a></td>";
				finalWLdata =finalWLdata+"</tr>";
			}
		}
		finalWLdata = finalWLdata+"</table>";
	}
	document.getElementById("divWebLinks").innerHTML= finalWLdata;
	
}


/**
 * It gets call when user clicks on delete icon of URL list.  
 * @param insightId
 */
function deleteLink(id,insightId,weblinkUIId){  	
	$.ajax({
		url : 'deleteLink.ajx',
		type : 'get',
		data : "weblinkId="	+ id	
		+ "&insightId="+insightId
		+ "&weblinkUIId="+weblinkUIId,
		success : function(serverResponse) {
			refreshWebLinks(serverResponse);
		},
		error : function(data, status, er) {
			
		}
	});
}


function showErrorMessage(id,errorMessage)
{
	$(id).text(errorMessage);
	$(id).show();
	$(id).addClass("red");
}

function openInsightLightBox(){
	
 window.scrollTo(0,0);
 document.getElementById('light_createinsight').style.display='block';
 document.getElementById('fade_editinsight').style.display='block'; 
 
 

}
function closeInsightLightBox(){
 document.getElementById('light_createinsight').style.display='none';
 document.getElementById('fade_editinsight').style.display='none';
}

$(function() {
	$("#idInsightDate").datepicker({
		buttonImage : 'resources/images/calender.png',
		buttonImageOnly : true,
		 maxDate: new Date,
		showOn : 'button',
		 buttonText : '', 
		dateFormat: 'M dd yy' 
	});
});


function validateFoundWith(val)
{
	$("#error-message-foundwith").hide();
	if(val.length>0){
    var isNumber =  /^\d+$/.test(val);
	var userSize = parseInt(val);
    if(isNumber )
	{
	if (userSize>99999 || userSize==0) 
	{
	showErrorMessage('#error-message-foundwith',errmsgFoundWithRange);
	submitFlag =false;
	}
	}else{
	submitFlag = false;
	showErrorMessage('#error-message-foundwith',errmsgFoundWithOnlyDigit);
	}
    }
	}

function onlyNumberKey(evt,value)
{
	$("#error-message-foundwith").hide();
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
	{
	showErrorMessage('#error-message-foundwith',errmsgFoundWithOnlyDigit);
    return false;
	}
	if(value.length>4)
	{
	showErrorMessage('#error-message-foundwith',errmsgFoundWithRange);
	}
	
	return true;
}

function parseDate(d) {
    var monthNames = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ],
        d2 = monthNames[d.getMonth()] +' '+ d.getDate() +' '+d.getFullYear();
    return d2;
} 


function replaceAll(find, replace, str) 
{
  while( str.indexOf(find) > -1)
  {
    str = str.replace(find, replace);
  }
  return str;
}
/**
 * It adds class to attachment section dynamically based on size of attachment records.
 */
function addRemoveClassForAttachment(){
	var sizeAttchment=eval(document.getElementById("attchmentSize").value);
	if(sizeAttchment==0){// If there are no attachments.
		$("#divAttachDetails").removeClass("attachment");
	}else{// If there are attachments.
		$("#divAttachDetails").addClass("attachment");
	}
	
}
/**
 * It gets call when user clicks on cancel button.
 */
function cancelInsight(){
	if(document.getElementById("insightDetailsDto.id").value==0){// In case of new Insight
		document.getElementById("frmInsight").action='dashboard.html';
		document.getElementById("frmInsight").submit();
	}else{
		document.getElementById("frmInsight").method="get";
		document.getElementById("insightId").value=document.getElementById("insightDetailsDto.id").value;
		document.getElementById("frmInsight").action='viewinsight.html';
		document.getElementById("frmInsight").submit();
	}
}

$(window).scroll(function(event) {
    var scroll = $(this).scrollTop();
    var docHeight = $(document).height();
    var windowHeight = $(window).height();
    var footerHeight = $('.footer').height();

    if(docHeight - (windowHeight + scroll) < footerHeight) {
        $('.btn-panel').css({
            bottom: 70
        });
    } else {
        $('.btn-panel').css({
            bottom: -12
        });
    }

});

var windowWidth = $(window).width();

$(window).resize(function() {
    windowWidth = $(window).width();

    if(windowWidth > 680) {
        $('.btn-panel').css({
            position: "fixed"
        });
    } else {
        $('.btn-panel').css({
            position: "inherit"
        });
    }

});



function showFieldForInsightPage(insightType)
{	
	$("#error-message-title").hide();
	$("#error-message-description").hide();
	$("#error-message-type").hide();
	$("#error-message-foundDate").hide();
	$("#error-message-foundVia").hide();
	$("#error-message-project").hide();
	$("#error-message-product").hide();
	$("#error-message-foundwith").hide();
	
	$("#error-message-tag").hide();
	$("#error-message-mainUserType").hide();
	$("#error-message-geographies").hide();
	$("#error-message-attachment").hide();
	$("#error-message-company").hide();
	if(insightType == 1) // User 
	{
		document.getElementById("idDivProject").style.display = "inline";
		document.getElementById("idDivfoundWith").style.display = "inline";
		document.getElementById("idDivfoundVia").style.display = "inline";
		document.getElementById("idDivSeverity").style.display = "none";
		document.getElementById("idDivCompany").style.display = "none";

	}else if(insightType == 2) // Marker
	{
		document.getElementById("idDivProject").style.display = "inline";
		document.getElementById("idDivfoundWith").style.display = "none";
		document.getElementById("idDivfoundVia").style.display = "none";
		document.getElementById("idDivSeverity").style.display = "none";
		document.getElementById("idDivCompany").style.display = "none";
	}else if(insightType ==3){ // Comp
		document.getElementById("idDivProject").style.display = "none";
		document.getElementById("idDivfoundWith").style.display = "none";
		document.getElementById("idDivfoundVia").style.display = "none";
		document.getElementById("idDivSeverity").style.display = "none";
		document.getElementById("idDivCompany").style.display = "inline";	
	}else if(insightType ==4){  // BUg
		document.getElementById("idDivProject").style.display = "none";
		document.getElementById("idDivfoundWith").style.display = "none";
		document.getElementById("idDivfoundVia").style.display = "inline";
		document.getElementById("idDivSeverity").style.display = "inline";
		document.getElementById("idDivCompany").style.display = "inline";
	}else{
		document.getElementById("idDivProject").style.display = "none";
		document.getElementById("idDivfoundWith").style.display = "none";
		document.getElementById("idDivfoundVia").style.display = "none";
		document.getElementById("idDivSeverity").style.display = "none";
		document.getElementById("idDivCompany").style.display = "none";
	}

}


