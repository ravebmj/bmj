var objInsightEditDDesc ;

$(document).ready(function() {
	if(document.getElementById("insightDetailsDto.id").value==0){// In case of new Insight do auto complete.
		$("#idInsightTitle").autocomplete({
		    source: function(request, response) {
			    $.ajax({
			    url: "autoComInsightTitle.ajx",
			    type: "GET",
			    data: { nametitle: request.term},
			    success: function( data ) {
			             var arr=JSON.parse(data);
			        response( $.map( arr, function( item ) {
			        return {
			        	
			            label: item.text,
			            value: item.text,
			            id: item.id,
			           
			        }
			        }));
			    }
			    });
		    },
		    select: function(event, ui) {
		        console.debug(' ui.item.label ::'+ui.item.label);
		        console.debug(' ui.item.id ::'+ui.item.id);
		        openInsightLightBox();
		        getInsightData(ui.item.id);
		        this.value = "";
		        return false;
		        
		    },
		    error: function (error) {
		       alert('error: ' + error);
		    },
		    minLength: 1
		    });
	}
});
/**
 * This retrieves insight data for requested Insight Id.
 * @param insightId
 */
function getInsightData(insightId){
	
	$.ajax({
		url : 'getInsightData.ajx',
		type : 'get',
		dataType : 'json',
		data : "insightId="	+ insightId,
		success : function(editInsightDTO) {
			populateInsightPopUp(editInsightDTO);
		},
		error : function(data, status, er) {
			
		}
	});

}

/**
 * This retrieves insight data for requested Insight Id.
 * @param insightId
 */
function saveEditInsightData(){
	
	$("#error-message-edittitle").hide();
	$("#error-message-editdesc").hide();
	$("#error-message-editproject").hide();
	$("#error-message-editproduct").hide();
	$("#error-message-editFoundWith").hide();
	$("#error-message-editcompany").hide();
	
	var checkForUserInsight = true;
	var checkForMarkerInsight = true;
	var checkForCompetitorInsight = true;
	var checkForBugInsight = true;
	var insightType = $("#hidInsightType").val();
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

	var submitFlag = true;	
	var frm = $('#frmInsightEdit');
	var nicInsightEditDesc = nicEditors.findEditor('idInsightEditDesc');
	var desc = nicInsightEditDesc.getContent();
	
	// Title
	var title = $("#idInsightEditTitle").val().trim();
	if((title.length ==0) || title==undefined ){
		showErrorMessage('#error-message-edittitle',errmsgTitleEmpty);
		submitFlag = false;
	}else if(title.length >2000){
		showErrorMessage('#error-message-edittitle',errmsgTitleMaxLimit);
		submitFlag = false;
	}
	
	//Description
	var description = nicEditors.findEditor('idInsightEditDesc').getContent().replace(/&nbsp;/g, ' ').trim();
	if(description.length == 4 && description == '<br>')
	{
		showErrorMessage('#error-message-editdesc',errmsgDescriptionEmpty);
		submitFlag = false;
	}
	var descToValidate =replaceAll('<br>','<br/>',description);
	var escBrDesc = replaceAll('<br/>','',descToValidate);
	var finalEscDesc = $("<div>"+escBrDesc+"</div>").text();
	if((descToValidate.length ==0) || descToValidate==undefined )
	{
	    showErrorMessage('#error-message-editdesc',errmsgDescriptionEmpty);
	    submitFlag = false;
	}else if(finalEscDesc.length >4000){
		showErrorMessage('#error-message-editdesc',errmsgDescriptionMaxLimit);
		submitFlag = false;
	}
	
	  // For Project
	   if(checkForUserInsight || checkForMarkerInsight){
    var projectDetail = $("#idInsigtEditProject").val();
    if(projectDetail.length>0){
    if(projectDetail.length >100){
    	showErrorMessage('#error-message-editproject',errmsgProjectMaxLimit);
    	submitFlag = false;
	}else if((projectDetail.match('/^[a-zA-Z0-9!@#+{}$&()\[\]\'_-]+$/'))){
		showErrorMessage('#error-message-editproject',errmsgProjectPaternMismatch+"'");
		submitFlag = false;
	}
    }}
    
  
 // For Product
    var productDetail = $("#idInsigtEditProduct").val();
    if(productDetail.length>0){
    if(productDetail.length >100){
    	showErrorMessage('#error-message-editproduct',errmsgProductMaxLimit);
    	submitFlag = false;
	}else if((productDetail.match('/^[a-zA-Z0-9!@#+{}$&()\[\]\'_-]+$/'))){
		showErrorMessage('#error-message-editproduct',errmsgProductPaternMismatch+"'");
		submitFlag = false;
	}
    }
    
    if(checkForUserInsight){
    // // Found with Users
    var foundWithUser = $("#idInsightEditfoundCnt").val();
    $("#error-message-editFoundWith").hide();
	if(foundWithUser.length>0){
    var isNumber =  /^\d+$/.test(foundWithUser);
	var userSize = parseInt(foundWithUser);
    if(isNumber )
	{
	if (userSize>99999 || userSize==0) 
	{
	showErrorMessage('#error-message-editFoundWith',errmsgFoundWithRange);
	submitFlag =false;
	}
	}else{
	submitFlag = false;
	showErrorMessage('#error-message-editFoundWith',errmsgFoundWithOnlyDigit);
	}
    }}
	
    if(checkForCompetitorInsight || checkForBugInsight){
	//Company
	 var companyName = $("#idInsigtEditCompany").val().trim();
		 if(companyName.length >100){
			showErrorMessage('#error-message-editcompany',errmsgCompanyMaxLimit);
			submitFlag = false;
		}}
	if(submitFlag)
{
		

	$.ajax({
		url : 'saveEditInsightData.ajx',
		type : 'post',
		data:  "idInsightEditTitle="
			+ title
			+ "&idInsightEditDesc=" + encodeURI(descToValidate)
			+ "&idInsigtEditProject="
			+ document.getElementById('idInsigtEditProject').value
			+ "&idInsigtEditCompany="
			+ document.getElementById('idInsigtEditCompany').value
			+ "&idInsigtEditOldProject="
			+ document.getElementById('idInsigtEditOldProject').value
			+ "&idInsigtEditProduct="
			+ document.getElementById('idInsigtEditProduct').value
			+ "&idInsigtEditOldProduct=" + document.getElementById('idInsigtEditOldProduct').value
			+ "&idInsightEditfoundCnt=" + document.getElementById('idInsightEditfoundCnt').value
			+ "&idInsight=" + document.getElementById('hidEditInsightId').value
			+ "&oldFoundCount=" + document.getElementById('hidoldFoundCount').value,
		
		success : function(result) {
			redirectEditSubmit(result);
		},
		error : function(data, status, er) {
			
		}
	});
	
}

}

/**
 * Populate fields of Insight Popup
 * @param insightId
 */
function populateInsightPopUp(editInsightDTO){
	document.getElementById("idInsightEditTitle").value=editInsightDTO.title;
	document.getElementById("hidInsightType").value=editInsightDTO.type;
	document.getElementById("idInsightEditfoundCnt").value=editInsightDTO.foundCount;
	
	document.getElementById("idInsigtEditProject").value=editInsightDTO.strProjects;
	document.getElementById("idInsigtEditOldProject").value=editInsightDTO.strOldProjects;
	
	document.getElementById("idInsigtEditProduct").value=editInsightDTO.strProducts;
	document.getElementById("idInsigtEditOldProduct").value=editInsightDTO.strOldProducts;
	document.getElementById("idInsigtEditCompany").value=editInsightDTO.companyName;
	
	document.getElementById("hidEditInsightId").value=editInsightDTO.id;
	document.getElementById("hidoldFoundCount").value=editInsightDTO.oldFoundCount;
	renderProduct();
	renderProject();
	document.getElementById("idInsightEditDesc").innerHTML=editInsightDTO.description;
	var nicInstance = nicEditors.findEditor('idInsightEditDesc');
	nicInstance.setContent(editInsightDTO.description);
	
	if(editInsightDTO.type ==1) //User
     {
		document.getElementById("idDivFoundWithCompany").style.display = "none";
		document.getElementById("idDivFoundWithProject").style.display = "inline";
		document.getElementById("idDivFoundWithEdit").style.display = "inline";
	 }else if(editInsightDTO.type ==2){ // Market
		document.getElementById("idDivFoundWithCompany").style.display = "none";
		document.getElementById("idDivFoundWithProject").style.display = "inline";
		document.getElementById("idDivFoundWithEdit").style.display = "none";
    }else if(editInsightDTO.type ==3){ // Comp
    	document.getElementById("idDivFoundWithCompany").style.display = "inline";
		document.getElementById("idDivFoundWithProject").style.display = "none";
		document.getElementById("idDivFoundWithEdit").style.display = "none"; 
	}else if(editInsightDTO.type ==4){ //Bug
		document.getElementById("idDivFoundWithCompany").style.display = "inline";
		document.getElementById("idDivFoundWithProject").style.display = "none";
		document.getElementById("idDivFoundWithEdit").style.display = "none"; 
		 }else{
		document.getElementById("idDivFoundWithCompany").style.display = "none";
		document.getElementById("idDivFoundWithProject").style.display = "none";
		document.getElementById("idDivFoundWithEdit").style.display = "none"; 
		 }

}



function renderProject(){
	
	$('#idInsigtEditProject').select2({
		tags : true,
		tokenSeparators : [ ',' ],

		createSearchChoice : function(term,data) {
			if (term.trim().length > 0) {
                if ($(data).filter(function () {
                  return this.text.toLowerCase().localeCompare(term.toLowerCase()) === 0;
                }).length === 0) {
                    return {
                        id: term,
                        text: term,
                        n : "new",
                        s : "",
                        isNew: true // this is necessary to check if the item is newly added or not
                    };
                }
            }
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


}
function renderProduct(){
	
	$('#idInsigtEditProduct').select2({
		tags : true,
		tokenSeparators : [ ',' ],

		createSearchChoice : function(term,data) {
			if (term.trim().length > 0) {
                if ($(data).filter(function () {
                  return this.text.toLowerCase().localeCompare(term.toLowerCase()) === 0;
                }).length === 0) {
                    return {
                        id: term,
                        text: term,
                        n : "new",
                        s : "",
                        isNew: true // this is necessary to check if the item is newly added or not
                    };
                }
            }
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


}

function redirectEditSubmit(result){
	
	var insightId = document.getElementById("hidEditInsightId").value;
	var fromSave;
	if(result == 'true'){
		document.getElementById("fromSave").value='true';
		fromSave = document.getElementById("fromSave").value;
	}else if(result == 'false'){
		document.getElementById("fromSave").value='false';
		fromSave = document.getElementById("fromSave").value;
	}
	var form; // dynamic form that will call controller	
    form = $('<form />', {
        action: "viewinsight.html",
        method: 'get',
        style: 'display: none;'
    });
    //Form parameter insightId
    $("<input>").attr("type", "hidden").attr("name", "insightId").val(insightId).appendTo(form);
    $("<input>").attr("type", "hidden").attr("name", "fromSave").val(fromSave).appendTo(form);
    //Form submit
    form.appendTo('body').submit();
	
	
	
}


function onlyNumberKeyEdit(evt,value)
{
	$("#error-message-editFoundWith").hide();
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
	{
	showErrorMessage('#error-message-editFoundWith',errmsgFoundWithOnlyDigit);
    return false;
	}
	if(value.length>4)
	{
	showErrorMessage('#error-message-editFoundWith',errmsgFoundWithRange);
	}
	
	return true;
}