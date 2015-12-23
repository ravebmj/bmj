<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	


  <link href="css/style.css" rel="stylesheet">


  <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>



<script>
$(document).ready( function () {
  var table = $('#example').DataTable();
} );
</script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script type="text/javascript">

    function show(obj) 
    {
        var e1 = document.getElementById('search-bottom');
		
        if ( e1.style.display == 'none' )
		{
            e1.style.display = 'block';
				document.getElementById('btn-insight').style.margin="-60px 98px 8px 0";
				document.getElementById('close').style.display="block";
					document.getElementById('search-bottom').style.height="206px";
					document.getElementById('gbqfab').style.display="none";
						document.getElementById('gbqfab').style.cursor="pointer";
						document.getElementById('headbg').style.height="461px";
						document.getElementById('headbg').style.background="url(images/headbgclick.png) left top repeat-x";


		}
		

			
      
	}

	function hide(obj) {

    var el = document.getElementById('search-bottom');

        el.style.display = 'none';
			document.getElementById('btn-insight').style.margin="10px 17px 8px 0";
			document.getElementById('gbqfab').style.display="block";
				document.getElementById('gbqfab').style.cursor="pointer";
				document.getElementById('headbg').style.height="305px";
				document.getElementById('headbg').style.background="url(images/headbgmin.png) left top repeat-x";

}


</script>

</script>
 <script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
			
			function DropDown(el) {
				this.dd = el;
				this.placeholder = this.dd.children('span');
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = '';
				this.index = -1;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						return false;
					});

					obj.opts.on('click',function(){
						var opt = $(this);
						obj.val = opt.text();
						obj.index = opt.index();
						obj.placeholder.text('Gender: ' + obj.val);
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			}

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-1').removeClass('active');
				});

			});
			$(function() {

				var dd = new DropDown( $('#dd2') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-2').removeClass('active');
				});

			});
			$(function() {

				var dd = new DropDown( $('#dd3') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});
			
		</script>


<link rel="stylesheet" type="text/css" media="screen" 
      href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/themes/ui-lightness/jquery-ui.css" />
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>

    <script>
      $(function() {$( "#fromdt" ).datepicker({ buttonImage: 'images/calender.png', buttonImageOnly: true, showOn: 'button'  });});
      $(function() {$( "#todate" ).datepicker({ buttonImage: 'images/calender.png', buttonImageOnly: true, showOn: 'button'  });});
    </script>



 <body id="headbg">

  <div id="wrapper">
	
	

	<div id="main-container fl">
<div id="search-title">Search Results</div> <div class="download-top"><img src="images/download-icon.png">Download</div>

		<table id="searchTable" class="display">
 <thead>
  <tr>
    <td><input type="checkbox" class="headCheckBox"></td> <td class="title">Title</td><td class="type">Type</td><td class="product">Product</td><td class="project">Project</td><td class="users">Users</td><td class="tags">Tags</td><td class="ledited">Last Edited</td>
  </tr>
 </thead>
 
 <tbody>
 	<c:forEach items="${searchDto.searchResult}" var="insight">
	  <tr>
	    <td><input type="checkbox" id="${insight.projectName}" class="tailsCheckBox"></td> <td><a id="${insight.title}" class="insightTitle">${insight.title}</a></td>
	    <td>${insight.type}</td>   
	    <td>${insight.product}</td>
	    <td>${insight.projectName}</td>   
	    <td>${insight.user}</td>
	    <td>${insight.tags}</td>   
	    <td>${insight.lastEditedDate}</td>
	  </tr>
	</c:forEach>

 </tbody>
</table>
 <div class="download-bottom"><img src="images/download-icon.png">Download</div>
</div>

  </div>
  </div>
  </div>
  <form  id="form1" action="viewinsight.html"></form>
  </body>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js" language="javascript" type="text/javascript">
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">

$( document ).ready(function() {


    $("#searchTable").DataTable();
    
    
    
    /*
    On click event on title
    This method will call view insight
    And pass insight id as parameter
    */
    $(".insightTitle").click(function(){
    	
    	viewInsight(this.id);
    	   	
    });

    
    /*
    */
    
    $(".headCheckBox").click(function(){
    	alert("comin");
    	alert(this.checked);
    	if(this.checked){
    	var  ob=	$(".tailsCheckBox");
    	alert(ob[0].id);
    	jQuery.each(ob, function(index, item) {
    		alert(index+"  "+"  "+item);
    	    // do something with `item` (or `this` is also `item` if you like)
    	});
    	
    	}
    	
    });
    		
    
   

});

</script>
