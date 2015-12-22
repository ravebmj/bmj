<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js" language="javascript" type="text/javascript">
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">

$( document ).ready(function() {


    $("#searchTable").DataTable();

    /*
    Button click
    */
    
   

});

</script>



<div id="main-container fl">
<form:form modelAttribute="dashboardDto">

  ${dashboardDto.searchTxt}
<div id="search-title">Search Results</div> <div class="download-top"><img src="images/download-icon.png">Download</div>

		<table id="searchTable" class="display">
 <thead>
  <tr>
    <td><input type="checkbox"></td> <td class="title">Title</td><td class="type">Type</td><td class="product">Product</td><td class="project">Project</td><td class="users">Users</td><td class="tags">Tags</td><td class="ledited">Last Edited</td>
  </tr>
 </thead>
 
 <tbody>
  <tr>
    <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>
   <tr>
    <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>
   <tr>
   <td><input type="checkbox"></td>  <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
     <td><input type="checkbox"></td><td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
       <td><input type="checkbox"></td><td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
     <td><input type="checkbox"></td><td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
     <td><input type="checkbox"></td><td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
      <td><input type="checkbox"></td><td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
    <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
       <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>
 
   <tr>
      <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>
 
   <tr>
    <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
  <td><input type="checkbox"></td>   <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>
  
   <tr>
   <td><input type="checkbox"></td>  <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
       <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

   <tr>
       <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

  <tr>
       <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>
 
   <tr>
       <td><input type="checkbox"></td> <td>Nobody reads BMJ cover reads BMJ cover medicin...</td><td>BMJ print design</td>   <td>Product 1, Product 2
Product 3, Product 4...</td><td>Project Name</td>   <td>53</td><td>Tag1, Tag2</td>   <td>05-jan-2015</td>
  </tr>

 </tbody>
</table>
 <div class="download-bottom"><img src="images/download-icon.png">Download</div>
 </form:form>
</div>