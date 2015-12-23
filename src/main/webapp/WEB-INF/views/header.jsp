<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="resources/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
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
					document.getElementById('search-bottom').style.height="180px";
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



 <div id="wrapper">
	<div class="header fl">
		<div class="logo fl"><img src="images/logo.jpg"></div>
		<div class="main-nav">
			<ul class="nav fr">
				<li><a href="home.html">Home</a></li>
				<li><a href="#">Sign In</a></li>
			</ul>
		</div>
		
	</div>
</div>
