function showEditInsight(clicked_id){
	
	if(sessionExists == 'false')
	{
	lightbox_open();
	if(clicked_id == 'idEditButton')
	{document.getElementById('actionPage').value = "Edit";}
	}else{
		perfromSubmitOpeartionForEdit();}
}

function perfromSubmitOpeartionForEdit()
{
	document.getElementById("frmEdit").submit();

}


$(document).ready(function(){
	

	
	$("#actionPage").val('view');
    if (Modernizr.touch) {
        // show the close overlay button
        $(".close-overlay").removeClass("hidden");
        // handle the adding of hover class when clicked
        $(".img").click(function(e){
            if (!$(this).hasClass("hover")) {
                $(this).addClass("hover");
            }
        });
        // handle the closing of the overlay
        $(".close-overlay").click(function(e){
            e.preventDefault();
            e.stopPropagation();
            if ($(this).closest(".img").hasClass("hover")) {
                $(this).closest(".img").removeClass("hover");
            }
        });
    } else {
        // handle the mouseenter functionality
        $(".img").mouseenter(function(){
            $(this).addClass("hover");
        })
        // handle the mouseleave functionality
        .mouseleave(function(){
            $(this).removeClass("hover");
        });
    }
    
    
	var viewinsightType  = $("#viewInsightType").text();
	showFieldForViewInsightPage(viewinsightType);
});


//<![CDATA[
$(function(){
  $(".ellipsis").each(function(i){
    len=$(this).text().length;
    if(len>46)
    {
      $(this).text($(this).text().substr(0,46)+'...');
    }
  });
});
// ]]



function showFieldForViewInsightPage(insightType)
{	
	
	var checkInsightType = insightType.trim();
	if(checkInsightType == 'User Insight') // User 
	{
		document.getElementById("idDivProjectView").style.display = "inline";
		document.getElementById("idDivfoundWithView").style.display = "inline";
		document.getElementById("idDivfoundViaView").style.display = "inline";
		document.getElementById("idDivSeverityView").style.display = "none";
		document.getElementById("idDivCompanyView").style.display = "none";
	}else if(checkInsightType == 'Market Insight') // Marker
	{
		document.getElementById("idDivProjectView").style.display = "inline";
		document.getElementById("idDivfoundWithView").style.display = "none";
		document.getElementById("idDivfoundViaView").style.display = "none";
		document.getElementById("idDivSeverityView").style.display = "none";
		document.getElementById("idDivCompanyView").style.display = "none";
	}else if(checkInsightType =='Competitor Insight'){ // Comp
		document.getElementById("idDivProjectView").style.display = "none";
		document.getElementById("idDivfoundWithView").style.display = "none";
		document.getElementById("idDivfoundViaView").style.display = "none";
		document.getElementById("idDivSeverityView").style.display = "none";
		document.getElementById("idDivCompanyView").style.display = "inline";	
	}else if(checkInsightType =='BUG'){  // BUg
		document.getElementById("idDivProjectView").style.display = "none";
		document.getElementById("idDivfoundWithView").style.display = "none";
		document.getElementById("idDivfoundViaView").style.display = "inline";
		document.getElementById("idDivSeverityView").style.display = "inline";
		document.getElementById("idDivCompanyView").style.display = "inline";
	}else{
		document.getElementById("idDivProjectView").style.display = "none";
		document.getElementById("idDivfoundWithView").style.display = "none";
		document.getElementById("idDivfoundViaView").style.display = "none";
		document.getElementById("idDivSeverityView").style.display = "none";
		document.getElementById("idDivCompanyView").style.display = "none";
	}

}