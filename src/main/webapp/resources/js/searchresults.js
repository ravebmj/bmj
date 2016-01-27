
$( document ).ready(function() {


//  $("#searchTable").DataTable();
    
	 // Array holding selected row IDs
	    var rows_selected = [];
	    var view_all;
	    var weightage_sort;
	   
	    view_all = $('input:hidden[name=sortFlag]').val(); 
	    weightage_sort = $('input:hidden[name=weightageSortFlag]').val();
	    if(weightage_sort == "weightageSort"){
	    	 var column_index = 8;
	 	     var sort_order = "asc";
	    }else{
	    	 column_index = 7;
	    	 sort_order = "desc";
	    }
	    	
	    if(view_all == "recent"){
	    	 column_index = 7;
	    	 sort_order = "desc";
	    }else if(view_all == "evidence"){
	    	column_index = 5;
	    	sort_order = "desc";
	    }
	    var numCols = $("#searchTable tr").length;
	    if(numCols ==1)
         {
	    	document.getElementById("downloadBtn").removeAttribute("class");
  			document.getElementById("downloadBtnDwn").removeAttribute("class");
  			 var table = $('#searchTable').DataTable({
  				     "aaSorting": [],
  				     "aLengthMenu": [
       	    	                [50, 100, 150, 200, -1],
       	    	                [50, 100, 150, 200, "All"]
       	    	            ], 
       	    	    "iDisplayLength" : 50,
       	    	    "bLengthChange": false,
  			    	"order": [[ column_index, sort_order ]],
  			    	"aoColumns":[
  			    	             {"bSortable": false},
  			    	             {"bSortable": false},
  			    	             {"bSortable": false},
  			    	             {"bSortable": false},
  			    	             {"bSortable": false},
  			    	             {"bSortable": false},
  			    	             {"bSortable": false},
  			    	             {"bSortable": false},
  			    	             {"bSortable": false}
  			    	         ],
  			      "oLanguage": {
  				 "sEmptyTable": "No results found"
  				 },
  				 "bInfo" : false
  			 });  	
          }else {
        	  var table = $('#searchTable').DataTable({
        		 "aaSorting": [],
        		 "aLengthMenu": [
      	    	                [50, 100, 150, 200, -1],
       	    	                [50, 100, 150, 200, "All"]
      	    	            ], 
      	    	"iDisplayLength" : 50,
  	 	    	"order": [[ column_index, sort_order ]],
  	 	    	"aoColumns":[
  	 	    	             {"bSortable": false},
  	 	    	             {"bSortable": true},
  	 	    	             {"bSortable": true},
  	 	    	             {"bSortable": false},
  	 	    	             {"bSortable": false},
  	 	    	             {"bSortable": true},
  	 	    	             {"bSortable": false},
  	 	    	             {"bSortable": true},
  	 	    	             {"bSortable": true}
  	 	    	         ]
  	 	      
  	 	    });

          
        	  }
	   



	    // Handle click on checkbox
	    $('#searchTable tbody').on('click', 'input[type="checkbox"]', function(e){
	       var $row = $(this).closest('tr');

	    	var rowVal = $(this).closest('tr')[0].id;// ID of the Row
			//alert(rowVal);

	       // Get row data
	       var data = table.row($row).data();



	       // Get row ID
	       var rowId = data[0];

	       // Determine whether row ID is in the list of selected row IDs 
	    //changes      
	    //var index = $.inArray(rowId, rows_selected);
	    var index = $.inArray(rowVal, rows_selected);

	       // If checkbox is checked and row ID is not in list of selected row IDs
	       if(this.checked && index === -1){
	    //changes      
	    //rows_selected.push(rowId);
	    rows_selected.push(rowVal);

	       // Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
	       } else if (!this.checked && index !== -1){
	          rows_selected.splice(index, 1);
	       }

	      /*  if(this.checked){
	          $row.addClass('selected');
	       } else {
	          $row.removeClass('selected');
	       } */

	       // Update state of "Select all" control
	       updateDataTableSelectAllCtrl(table);

	       // Prevent click event from propagating to parent
	       e.stopPropagation();
	    });

	    // Handle click on table cells with checkboxes
	    $('#searchTable').on('click', 'tbody td .childCheckBox, thead th:first-child', function(e){
	       $(this).parent().find('input[type="checkbox"]').trigger('click');
	    });

	    // Handle click on "Select all" control
	    $('#searchTable thead input[name="select_all"]').on('click', function(e){
	       if(this.checked){
	          $('#searchTable tbody input[type="checkbox"]:not(:checked)').trigger('click');
	       } else {
	          $('#searchTable tbody input[type="checkbox"]:checked').trigger('click');
	       }

	       // Prevent click event from propagating to parent
	       e.stopPropagation();
	    });

	    // Handle table draw event
	    table.on('draw', function(){
	       // Update state of "Select all" control
	       updateDataTableSelectAllCtrl(table);
	    });

	    // Handle form submission event 
	    $('#frm-searchTable').on('submit', function(e){
	       var form = this;
	       
	       // Iterate over all selected checkboxes
	       $.each(rows_selected, function(index, rowVal){
	          // Create a hidden element 
	          $(form).append(
	              $('<input>')
	                 .attr('type', 'hidden')
	                 .attr('name', 'id[]')
	                 .val(rowId)
	          );
	       });
	    });
   
    
    /*
    On click event on title
    This method will call view insight
    And pass insight id as parameter
    */
    $(".insightTitle").click(function(){ 
    	viewInsight(this.id);
    	   	
    });
    
    /*
    On click event on title
    This method will call search insight
    And pass product id and type as product as parameter
    */
	$(".insightProductClass").click(function(){
		search("product", this.id);
    	   	
    });
    
	/*
    On click event on title
    This method will call search insight
    And pass project id and type as product as parameter
    */
	$(".insightProjectClass").click(function(){ 
		search("project", this.id);
    	   	
    });
	
	/*
    On click event on title
    This method will call search insight
    And pass tag id and type as product as parameter
    */
	$(".insightTagClass").click(function(){
		search("tag", this.id);
    	   	
    });
	
	
	/*
    On click event on title
    This method will call search insight
    And pass project id and type as product as parameter*/
	$('.recentouterdivclass').on("click", ".insightProjectClass", function() {
		search("project", this.id);
	 });
	
		
	/*
    On click event on title
    This method will call search insight
    And pass project id and type as product as parameter*/
	$('.evidouterdivclass').on("click", ".insightProjectClass", function() {
	    search("project", this.id);
	 });
	
	 /*
    On click event on title
    This method will call view insight
    And pass insight id as parameter
    */
    $('#searchTable').on("click", ".insightTitle", function() {	
    	viewInsight(this.id);
    	   	
    });
  
    /*
    On click event on Download All Attachments
    This method will call downloadAllAttachmentsAsZip
    And pass insight id as parameter
    */
    $(".clsDownloadAll").click(function(){
    	downloadAllAttachmentsAsZip(this.id);
    });
	
	/*
    On click event on title
    This method will call search insight
    And pass project id and type as product as parameter*/
	$('#searchTable').on("click", ".insightProductClass", function() {
	    search("product", this.id);
	 });
	
	/*
    On click event on title
    This method will call search insight
    And pass project id and type as product as parameter*/	
	$('#searchTable').on("click", ".insightProjectClass", function() {
	    search("project", this.id);
	 });
	
	/*
    On click event on title
    This method will call search insight
    And pass project id and type as product as parameter*/
	$('#searchTable').on("click", ".insightTagClass", function() {
	    search("tag", this.id);
	 });	
	
	/*
    On click event on recent view all
    This method will call search insight
    And pass recent as parameter
    */
	$(".recentViewAllClass").click(function(){
    	viewall("recent");
    	   	
    });
	
	/*
    On click event on recent view all
    This method will call search insight
    And pass recent as parameter
    */
	$(".evidViewAllClass").click(function(){ 
    	viewall("evidence");
    	   	
    });
	
	$('#gbqfq').bind("enterKey",function(e){
		doingAdvanceQuickSearch('insight');
		});
		$('#gbqfq').keyup(function(e){
		    if(e.keyCode == 13)
		    {
		        $(this).trigger("enterKey");
		    }
		});	

	
	/*
	On click on download div
	*Download Insight
	*/
	$(".downloadBtn").click(function(){
		if($("input:checkbox:checked").length == 0)
		{
		alert(errmsgnocheckboxselected);
		}else if($("input:checkbox:checked").length >10){
		alert(errmsgmorethantencheckboxselected);} else {
		var selectedInsights = rows_selected;
		$("#selectedInsights").val(selectedInsights);	
		$("#idDownloadInsights").submit();					}
	});
	
	//callback handler for form submit
	$("#idDownloadInsights").submit(function(e)
	{
		$("#overlay-div").addClass("overlay-download");
		
		 $("#ajaxloader").show();
	    var postData = $(this).serializeArray();
	    var formURL = $(this).attr("action");
	    $.ajax(
	    {
	        url : formURL,
	        type: "POST",
	        data : postData,
	        success:function(data, textStatus, jqXHR) 
	        {
	        	$("#idDownloadInsightsDisplay").submit();
	        	$('#searchTable tbody input[type="checkbox"]:checked').trigger('click');
	        	
	        	$("#ajaxloader").hide();
	        	$("#overlay-div").removeClass("overlay-download");
	            
	        },
	        error: function(jqXHR, textStatus, errorThrown) 
	        {
	            //if fails  
	        	$("#ajaxloader").hide();
	        	$("#overlay-div").removeClass("overlay-download");
	        }
	    });
	    e.preventDefault(); //STOP default action
	    e.unbind(); //unbind. to stop multiple form submit.
	});
	    
});
		

//
//Updates "Select all" control in a data table
//
function updateDataTableSelectAllCtrl(table){
var $table             = table.table().node();
var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
var chkbox_select_all  = $('thead input[name="select_all"]', $table).get(0);

//If none of the checkboxes are checked
if($chkbox_checked.length === 0){
 chkbox_select_all.checked = false;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = false;
 }

//If all of the checkboxes are checked
} else if ($chkbox_checked.length === $chkbox_all.length){
 chkbox_select_all.checked = true;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = false;
 }

//If some of the checkboxes are checked
} else {
 chkbox_select_all.checked = true;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = true;
 }
}
}


function sshow(id) {

	document.getElementById(id).style.visibility = "visible";
	document.getElementById(id).style.display = "block";
}

function hhide(id) {
	document.getElementById(id).style.visibility = "hidden";
	document.getElementById(id).style.display = "none";
}

function show(obj) {

	var e1 = document.getElementById('search-bottom');

	if (e1.style.display == 'none') {

		e1.style.display = 'block';
		document.getElementById('gbqfbw').style.display = "none";

		document.getElementById('search').style.display = "block";
		document.getElementById('btn-insight').style.margin = "0px 59px 0px 0";
		document.getElementById('search').style.margin = "15px 62px 8px 0";
		document.getElementById('close').style.display = "block";
		document.getElementById('search-bottom').style.height = "182px";
		document.getElementById('gbqfab').style.display = "none";
		document.getElementById('gbqfab').style.border = "none";
		document.getElementById('gbqfab').style.cursor = "pointer";
		// document.getElementById('headbg').style.height="534px";
		document.getElementById('headbg').style.background = "url(resources/images/headbgclick.png) left top repeat-x";
	}

}

function hide(obj) {
	var el = document.getElementById('search-bottom');

	el.style.display = 'none';
	document.getElementById('gbqfbw').style.display = "inline";
	document.getElementById('btn-insight').style.margin = "0px 0px 0px 0";
	document.getElementById('search').style.display = "none";
	document.getElementById('search').style.margin = "10px 0px 8px 0";
	document.getElementById('gbqfab').style.display = "block";
	document.getElementById('gbqfab').style.cursor = "pointer";
	document.getElementById('headbg').style.height = "299px";
	document.getElementById('headbg').style.background = "url(resources/images/headbg.png) left top repeat-x";

	$("#error-message-fromDate").hide();
	document.getElementById("gbqfq").value = keyword;
	document.getElementById("idInsightType").value = 0;
	document.getElementById("idServerity").value = 0;
	document.getElementById("idCreatedDate").value = 1;
	document.getElementById("fromdt").value = 'From';
	document.getElementById("todate").value = 'To';
}

  
