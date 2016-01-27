<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv=X-UA-Compatible content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" align="left" width="100%">
	<tr>
		<td colspan="2" valign="top"><tiles:insertAttribute name="body" />
		</td>
	</tr>	
</table>
</body>
</html>
