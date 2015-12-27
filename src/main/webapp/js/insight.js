/*
 * @Param insightId
 * This method will navigate to view insight page
 * for the select insight
 */

function viewInsight(insightId){
	var form; // dynamic form that will call controller
    form = $('<form />', {
        action: "viewinsight.html",
        method: 'post',
        style: 'display: none;'
    });
    //Form parameter insightId
    $("<input>").attr("type", "hidden").attr("name", "insightId").val(insightId).appendTo(form);
    
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
        method: 'post',
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
function quickAdvanceSearch(){
	var form; // dynamic form that will call controller
    form = $('<form />', {
        action: "advanceSearch.html",
        method: 'post',
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
    //Form submit
    form.appendTo('body').submit();
}


