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
function search(tag, projectName, product){
	var form; // dynamic form that will call controller
    form = $('<form />', {
        action: "dashBoardSearch.html",
        method: 'post',
        style: 'display: none;'
    });
    //Form parameter insightId
    $("<input>").attr("type", "hidden").attr("name", "tag").val(tag).appendTo(form);//tag value appended to form
    $("<input>").attr("type", "hidden").attr("name", "product").val(product).appendTo(form);//product value appended to form
    $("<input>").attr("type", "hidden").attr("name", "projectName").val(projectName).appendTo(form);//projectName append to form
    
    //Form submit
    form.appendTo('body').submit();
}