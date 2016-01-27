 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <script src="resources/js/footer.js"></script>
 </div>
<div id="footer" class="footer"><span><spring:message code="footer_message_part1"/> &copy; <spring:message code="footer_message_part2"/> <div class="ver fr"><spring:message code="footer_message_part3_version_number"/></span></div>
</div>
<div id="light">
 <div class="popup_head"><spring:message code="footer_message_login_header"/></div>
    <div class="popup">
    <div id="errormessage" style="display: none"></div>
        <form id="loginform" name="loginform" method="get" action="createinsight.html">
             <p><span class="title"> <spring:message code="footer_message_login_username"/></span> <input type="text" name="username" class="login" id="username"></p>
                <p><span class="title"> <spring:message code="footer_message_login_password"/></span> <input type="password" name="password" class="login" id="password"></p>
                <input type="hidden" name="actionPage" id= "actionPage" value="" />
                <p><input type="button" value="<spring:message code="footer_message_button_cancel"/>" name="" class="cancelbtn fl font18" onclick="lightbox_close();"><input type="button" value="<spring:message code="footer_message_button_login"/>" name="" class="loginbtn fr font18" onclick="submitForm()"></p>
            </form>
  
    </div>
 
 </div>
 

 
  
 
 
 
<div id="fade" onClick="lightbox_close();"></div>
