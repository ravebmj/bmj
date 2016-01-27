
//<![CDATA[
bkLib.onDomLoaded(function() {
	new nicEditor({
		buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
				'ol',
				'ul', 'removeformat', 'hr', 'fontFamily',
				'fontFormat' ]
	}).panelInstance('idDesc');
	
	new nicEditor({
		buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
				'ol',
				'ul', 'removeformat', 'hr', 'fontFamily',
				'fontFormat' ]
	}).panelInstance('idInsightEditDesc');
	document.getElementById('light_createinsight').className="light_create";
});
//]]>
