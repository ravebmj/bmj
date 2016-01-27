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
			            value: item.id,
			        }
			        }));
			    }
			    });
		    },
		    select: function(event, ui) {
		        console.debug(' ui.item.label ::'+ui.item.label);
		        console.debug(' ui.item.value ::'+ui.item.value);
		        openInsightLightBox();
		        getInsightData(ui.item.value);
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
	if((descToValidate.length ==0) || descToValidate==undefined )
	{
	    showErrorMessage('#error-message-editdesc',errmsgDescriptionEmpty);
	    submitFlag = false;
	}else if(descToValidate.length >4000){
		showErrorMessage('#error-message-editdesc',errmsgDescriptionMaxLimit);
		submitFlag = false;
	}
	
	  // For Project
    var projectDetail = $("#idInsigtEditProject").val();
    if(projectDetail.length>0){
    if(projectDetail.length >100){
    	showErrorMessage('#error-message-editproject',errmsgProjectMaxLimit);
    	submitFlag = false;
	}else if((projectDetail.match('/^[a-zA-Z0-9!@#+{}$&()\[\]\'_-]+$/'))){
		showErrorMessage('#error-message-editproject',errmsgProjectPaternMismatch+"'");
		submitFlag = false;
	}
    }
    
  
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
    }
	
	 var companyName = $("#idInsigtEditCompany").val().trim();
		 if(companyName.length >100){
			showErrorMessage('#error-message-editcompany',errmsgCompanyMaxLimit);
			submitFlag = false;
		}
	if(submitFlag)
{
		

	$.ajax({
		url : 'saveEditInsightData.ajx',
		type : 'get',
		data:  "idInsightEditTitle="
			+ title
			+ "&idInsightEditDesc=" + descToValidate
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
			redirectEditSubmit();
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


}
function renderProduct(){
	
	$('#idInsigtEditProduct').select2({
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


}

function redirectEditSubmit(){
	document.getElementById("frmInsight").method="get";
	document.getElementById("frmInsight").action='viewinsight.html';
	document.getElementById("fromSave").value='true';
	document.getElementById("insightId").value=document.getElementById("hidEditInsightId").value;
	document.getElementById("frmInsight").submit();
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