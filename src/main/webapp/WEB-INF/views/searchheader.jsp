<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/insight.js"></script> 	
	 <link href="css/style.css" rel="stylesheet">
 <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
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


<div id="wrapper">
	<div id="search-panel" class="fl">	
	<div id="gbq">
    <div class="gbt" id="gbq2">
        <div id="gbqfw" role="search">
            <h2 class="a3I">Search for insights containing</h2>
           
               <!--  <fieldset class="gbxx">
                    <legend class="gbxx">Hidden fields</legend>
                    <div id="gbqffd"></div>
                </fieldset> -->
                <fieldset class="gbqff gb_R" id="gbqff">
                    <legend class="gbxx"></legend>
                    <div id="gbfwa" class="gbqfwa ">
                        <div id="gbqfqw" class="gbqfqw " gh="sb">
                            <div id="gbqfaa"></div>
                            <div id="gbqfqwb" class="gbqfqwb" style="left: 0px; right: 19px;">
                                 <input id="gbqfq" class="gbqfif" name="keyword" type="text" autocomplete="off" value="" aria-label="Search" dir="ltr" spellcheck="false" aria-haspopup="true" aria-live="off" aria-owns="gs_sbt50" style="border: none; padding: 0px; margin: 0px; height: auto; width: 100%; position: absolute; z-index: 6; left: 0px; background: url(&quot;data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw%3D%3D&quot;) transparent;"/>
                               
                            </div>
                            <div id="gbqfab" onclick="show()" style="cursor:pointer;">
                                <div class="aoo J-JN-I" tabindex="0" gh="sda" role="button" data-tooltip="Show search options" aria-label="Advanced search options">
                                    <div class="aoq"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="gb_R gb_Pd" id="gbqfbw">
                    <button  onclick="quickAdvanceSearch();" class="gbqfb" aria-label="Search Northgate Information Solutions Ltd Mail" name="" id="gbqfb"><span class="gbqfi gb_Lb"></span>
                    </button>
                </div>
           
        </div>
    </div>
</div>

		<div id="search-bottom" style="display:none" class="fl">
			<div class="fr" id="close" onclick="hide()"><img src="images/close.png"></div>
		<h2 class="advance-search-title">Advanced Search</h2>
	<div class="fl">
			<div tabindex="1" class="wrapper-dropdown-1" id="dd" style="float:left;">
			      
			     
			      <form:select id="idInsightType" path="searchCriteria.insightType">
			      		< <form:option value="0" label="Search all insights"/>
						<form:options items="${searchCriteria.lstInsightTypesDto}" itemLabel="insightTypeName" itemValue="insightTypeName"/> 
			      </form:select>
					  
				
			
						<!-- <span>Search all insights</span>
						<ul class="dropdown">
							<li><a href="#"><i class="icon-envelope icon-large"></i>Classic mail</a></li>
							<li><a href="#"><i class="icon-truck icon-large"></i>UPS Delivery</a></li>
							<li><a href="#"><i class="icon-plane icon-large"></i>Private jet</a></li>
						</ul> -->
					</div>
					<div tabindex="1" class="wrapper-dropdown-2" id="dd2" style="float:left;">
						<!-- <span>Search all severities</span>
						<ul class="dropdown">
							<li><a href="#"><i class="icon-envelope icon-large"></i>Classic mail</a></li>
							<li><a href="#"><i class="icon-truck icon-large"></i>UPS Delivery</a></li>
							<li><a href="#"><i class="icon-plane icon-large"></i>Private jet</a></li>
						</ul> -->
						<form:select id="idServerity" path="searchCriteria.serverity">
							 <form:option value="0" label="Search all severities"/>
							 <form:options items="${searchCriteria.lstSeveritiesDto}" itemLabel="serverityName" itemValue="serverityName"/> 
						</form:select>
					</div>
					<div tabindex="1" class="wrapper-dropdown-3" id="dd3" style="float:left;">
						<!-- <span>Created Date</span>
						<ul class="dropdown">
							<li><a href="#"><i class="icon-envelope icon-large"></i>Classic mail</a></li>
							<li><a href="#"><i class="icon-truck icon-large"></i>UPS Delivery</a></li>
							<li><a href="#"><i class="icon-plane icon-large"></i>Private jet</a></li>
						</ul> -->
						<form:select id="idCreatedDate" path="searchCriteria.createdDate">
							 <form:options items="${searchCriteria.lstDateCriteriaDto}" itemLabel="criteriaName" itemValue="criteriaName"/> 
						</form:select>
						
					</div>

					<div  style="float:left;">
						 <input id=fromdt type=text name="fromDate" value="From"> 
						 <%-- <form:input path="searchCriteria.fromDate"/> --%>
					</div>
					<div  style="float:left;">
						<input id=todate type=text name="toDate" value="To"> 
						 <%-- <form:input path="searchCriteria.toDate"/> --%>
					</div>
			</div>
		</div>
		 
	</div>
		
			<div style="float:right;margin:10px 17px 0 10px;" id="btn-insight"><button type="submit" id="btnAddNewInsight" name="addnewinsight" value=" "><img src="images/addnewinsight.png"></button></div>
		
	  </div>
  



