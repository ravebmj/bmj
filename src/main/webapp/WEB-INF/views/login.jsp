<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
Login Page
<form:form method="GET" modelAttribute="loginDTO" action="loginSubmit.html">
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
<form:input type="hidden" id="pageRequested" path="pageRequested"/>
</form:form>