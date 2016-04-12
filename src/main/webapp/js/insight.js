/*
 * @Param insightId
 * This method will navigate to view insight page
 * for the select insight
 */

function viewInsight(insightId){
	var form; // dynamic form that will call controller	
    form = $('<form />', {
        action: "viewinsight.html",
        method: 'get',
        style: 'display: none;'
    });
    //Form parameter insightId
    $("<input>").attr("type", "hidden").attr("name", "insightId").val(insightId).appendTo(form);
    $("<input>").attr("type", "hidden").attr("name", "googleSession").val(googleStatus).appendTo(form);// Parameter to hold current status of google sign in session.
    //Form submit
    form.appendTo('body').submit();
}

/*
 * @Param tag, projectName, product
 * This method will call search with basis of any of three
 * 1 field will contain value rest will be null
 */
function search(searchType, id){	
	var form; // dynamic form that will call controller
    form = $('<form />', {
        action: "dashBoardSearch.html",
        method: 'get',
        style: 'display: none;'
    });
    //Form parameter insightId
    $("<input>").attr("type", "hidden").attr("name", "searchType").val(searchType).appendTo(form);//searchType value appended to form
    $("<input>").attr("type", "hidden").attr("name", "id").val(id).appendTo(form);//id append to form
    
    //Form submit
    form.appendTo('body').submit();
}

/*
 * @Param tag, projectName, product
 * This method will call search with basis of any of three
 * 1 field will contain value rest will be null
 */
function quickAdvanceSearch(serachType) {
	$("#error-message-fromDate").hide();
	if (serachType == 'insight') {
		doingAdvanceQuickSearch(serachType);
	} else {
		var submitFlag = true;
		var fromDate = $("#fromdt").val();
		var toDate = $("#todate").val();
		var today = new Date();
		var d = new Date(fromDate);
		var todate = new Date(toDate);
		var todayDateOnly = new Date(today.getFullYear(), today.getMonth(),
				today.getDate());
		var dDateOnly = new Date(d.getFullYear(), d.getMonth(), d.getDate());
		var toDateOnly = new Date(todate.getFullYear(), todate.getMonth(),
				todate.getDate());

		if (dDateOnly > todayDateOnly) {
			$("#error-message-fromDate").text(errmsgAddSerachFoundDate);
			$("#error-message-fromDate").show();
			$("#error-message-fromDate").addClass("red");
			submitFlag = false;
		}

		if (toDateOnly > todayDateOnly) {
			$("#error-message-fromDate").text(errmsgAddSerachFoundDate);
			$("#error-message-fromDate").show();
			$("#error-message-fromDate").addClass("red");
			submitFlag = false;
		}

		if (toDateOnly < dDateOnly) {
			$("#error-message-fromDate").text(errmsgAdvSeracheDates);
			$("#error-message-fromDate").show();
			$("#error-message-fromDate").addClass("red");
			submitFlag = false;
		}

		if (submitFlag) {
			doingAdvanceQuickSearch(serachType);
		}
	}

}


function addnewinsight(idValue){
	if(sessionExists == 'false')
		{
		if(idValue == 'btnAddNewInsight')
		{document.getElementById('actionPage').value = "newinsight";
		}else{
		document.getElementById('actionPage').value = "search";
		}
		lightbox_open();
		}else{
			submitPageForNewInsight();
		}
}


function viewall(sortType){	
	var form; // dynamic form that will call controller
    form = $('<form />', {
        action: "searchresults.html",
        method: 'get',
        style: 'display: none;'
    });
    //Form parameter sort type
    $("<input>").attr("type", "hidden").attr("name", "sortType").val(sortType).appendTo(form);//sortType value appended to form 
    //Form submit
    form.appendTo('body').submit();
}


function submitPageForNewInsight()
{
	var form; // dynamic form that will call controller
    form = $('<form />', {
        action: "createinsight.html",
        method: 'get',
        style: 'display: none;'
    });
    //Form parameter insightId
   // $("<input>").attr("type", "hidden").attr("name", "insightId").val(insightId).appendTo(form);
    $("<input>").attr("type", "hidden").attr("name", "googleSession").val(googleStatus).appendTo(form);// Parameter to hold current status of google sign in session
    //Form submit
    form.appendTo('body').submit();
}

function doingAdvanceQuickSearch(serachType)
{
	var form; // dynamic form that will call controller
    form = $('<form />', {
        action: "advanceSearch.html",
        method: 'get',
        style: 'display: none;'
    });
    var keyword = $("#gbqfq").val(); 
    var insightType = $("#idInsightType").val(); 
    var serverity = $("#idServerity").val();
    var createdDate = $("#idCreatedDate").val();
    var fromDate = $("#fromdt").val();
    var toDate = $("#todate").val();
   
    //Form parameter insightId
    $("<input>").attr("type", "hidden").attr("name", "keyword").val(keyword).appendTo(form);//search keyword, text which is being searched
    $("<input>").attr("type", "hidden").attr("name", "insightType").val(insightType).appendTo(form);//insight 
    $("<input>").attr("type", "hidden").attr("name", "serverity").val(serverity).appendTo(form);//serverity
    $("<input>").attr("type", "hidden").attr("name", "createdDate").val(createdDate).appendTo(form);//created date
    $("<input>").attr("type", "hidden").attr("name", "fromDate").val(fromDate).appendTo(form);//from date
    $("<input>").attr("type", "hidden").attr("name", "toDate").val(toDate).appendTo(form);//to date 
    $("<input>").attr("type", "hidden").attr("name", "serachType").val(serachType).appendTo(form);//search keyword, text which is being searched
    //Form submit
    form.appendTo('body').submit();
}


function downloadAllAttachmentsAsZip(insightId){
	var form; // dynamic form that will call controller	
    form = $('<form />', {
        action: "downloadAttachmentsZip.html",
        method: 'get',
        style: 'display: none;'
    });
    //Form parameter insightId
    $("<input>").attr("type", "hidden").attr("name", "insightId").val(insightId).appendTo(form);
    //Form submit
    form.appendTo('body').submit();
}
