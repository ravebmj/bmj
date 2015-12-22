<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">

$(document).ready(function() {

    $('#example').DataTable( {

        "paging":   false,

        "ordering": false,

        "info":     false

    } );

} );


</script>
<script>
function submitForm(insightid){
	$("#dashboardForm").submit();
	/* $.post('viewInsight.html', { insightId: insightid }, function(data) { 
	    
	}); */
   
}
</script>



<c:url value="/questionPost" var="postUrl"/>

<form:form id="dashboardForm" action="viewinsight.html" modelAttribute="dashboardDto" method="post"> 
<div id="wrapper">
	<div id="main-container fl">
	
			<div class="header-title">Recently added insights</div>
		
    <table>
   
    <tr>
    	
        <th><div class="row-start fl">
			<div class="table-title fl">Insight</div>
		</div></th>
        <th><div class="row  fl">
			<div class="table-title fl">Project</div>
		</div></th>
        <th><div class="row  fl">
			<div class="table-title fl">Type</div>
		</div></th> 
         <th><div class="row  fl">
			<div class="table-title fl">Last Edited</div>
		</div></th>         
    </tr>
      <c:if test="${not empty dashboardDto.recentInsightsDtoLst}">
       
    <c:forEach items="${dashboardDto.recentInsightsDtoLst}" var="currQue" varStatus="queIndex">
        
	        <tr>	       		
	       		 <%-- <form:hidden path="recentInsightsDtoLst[${queIndex.index}].insightId"/> --%> 
	       		 <input id="${currQue.insightId}" type="hidden" name="${currQue.insightId}"  value="${currQue.insightId}"/> 
	            <td>
	              <div class="row-start-1 fl">
					<div class="table-title-row fl">
	               <a  href="#" onclick="submitForm(${currQue.insightId})"> <c:out value="${currQue.insightName}"/></a><br/>
	              	</div>
	              </div>  
	            </td>
	            <td>  
	                <div class="row  fl"> 
	                <div class="table-row fl">     	
	                <c:out value="${currQue.projectName}"/><br/>
	                </div>
	                </div>
	             </td>
	             <td> 
	               <div class="row  fl">
					<div class="table-row-grey fl">         	
	                <c:out value="${currQue.type}"/><br/>
	                </div>
	                </div>
	             </td>
	             <td> 
	               <div class="row  fl">
					<div class="table-row-end fl">         	
	                <c:out value="${currQue.lastEdited}"/><br/>
	                </div>
	                </div>
	             </td>
	            
	        </tr>
       		
    </c:forEach>
   	 </c:if>
    </table>
    <div class="viewall-top fr"><a href="#">view all</a></div>
   </div>
   </div> 
</form:form> 




<div id="wrapper">
	<div id="main-container fl">			
	
	<div class="row-header fl">
			<div class="header-title">Strongest evidence against insights</div>
		</div>
		<div class="row-start fl">
			<div class="table-title fl">Insight</div>
		</div>
		<div class="row  fl">
			<div class="table-title fl">Project</div>
		</div>
		<div class="row  fl">
			<div class="table-title fl">&nbsp;</div>
		</div>
		<div class="row  fl">
			<div class="table-title fl">Last Edited</div>
		</div>
		<div class="row-start-1 fl">
			<div class="table-title-row fl">Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">BMJ print design</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">&nbsp;</div>
		</div>
		<div class="row  fl">
			<div class="table-row-end fl">05-Jan-2015</div>
		</div>
	<div class="row-start-1 fl">
			<div class="table-title-row fl">Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">BMJ print design</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">&nbsp;</div>
		</div>
		<div class="row  fl">
			<div class="table-row-end fl">05-Jan-2015</div>
		</div>
		<div class="row-start-1 fl">
			<div class="table-title-row fl">Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">BMJ print design</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">&nbsp;</div>
		</div>
		<div class="row  fl">
			<div class="table-row-end fl">05-Jan-2015</div>
		</div>

		<div class="row-start-1 fl">
			<div class="table-title-row fl">Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">BMJ print design</div>
		</div>
		<div class="row  fl">
			<div class="table-row fl">&nbsp;</div>
		</div>
		<div class="row  fl">
			<div class="table-row-end fl">05-Jan-2015</div>
		</div>
	
	<div class="viewall fr"><a href="#">view all</a></div>
	</div>
</div>
