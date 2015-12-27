<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/insight.js"></script> 	



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
	            <td>
	              <div class="row-start-1 fl">
					<div class="table-title-row fl">
	               <a  href="#" onclick="viewInsight(${currQue.insightId})"> <c:out value="${currQue.insightName}"/></a><br/>
	              	</div>
	              </div>  
	            </td>
	            <td>  
	                <div class="row  fl"> 
	                <div class="table-row fl">
	                 <c:if test="${not empty currQue.projects}">       
    					<c:forEach items="${currQue.projects}" var="currPro" varStatus="proIndex">     	
	               <a  href="#" onclick="${currPro.project.id}">  <c:out value="${currPro.project.name}"/>,</a><br/>
	               </c:forEach>
	               </c:if>
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
    <div class="viewall-top fr"><a href="#" onclick="search()">view all</a></div>
   </div>
   
   
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


