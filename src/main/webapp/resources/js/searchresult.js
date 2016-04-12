$(document).ready(function() {
		$("#actionPage").val('search');
		
		if (serachType == 'insight') {
			$("#close").click();
		} else if (serachType == 'deatil') {			
			document.getElementById("idInsightType").value = insightType;
			document.getElementById("idServerity").value = serverity;
			document.getElementById("idCreatedDate").value = dateRangeOpt;
			document.getElementById("fromdt").value = fromDate;
			document.getElementById("todate").value = toDate; 
			document.getElementById("gbqfq").value = keyword;
			
			$("#idDivAdvanceSearch").click();
		}

		$("#close").click(function() {
			$("#error-message-fromDate").hide();
			document.getElementById("gbqfq").value = '';
			document.getElementById("idInsightType").value = 0;
			document.getElementById("idServerity").value = 0;
			document.getElementById("idCreatedDate").value = 1;
			document.getElementById("fromdt").value = 'From';
			document.getElementById("todate").value = 'To'; 
		});
		
		
		
		
	});