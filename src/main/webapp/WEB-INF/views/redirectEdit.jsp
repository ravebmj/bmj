<%
String saveType=request.getParameter("saveType");
String target="";
String insightId=(String)request.getAttribute("insightId");
target="viewinsight.html?fromSave=false&insightId="+insightId;

%>
<form id="main" method="get" name="main" action="" onsubmit="redirect(this);">
	<input type="hidden" name="insightId" value="<%=insightId%>"/> 
	<input type="hidden" name="fromSave" value="false"/> 
    <input type="submit" name="submit" id="idSubmit" style="display:none"/> 
     
</form>
<script>
function redirect(elem){
    elem.setAttribute("action","<%=target%>");
    elem.submit();
    
}
document.getElementById("idSubmit").click();
</script>