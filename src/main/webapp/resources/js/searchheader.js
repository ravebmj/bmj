$(document).ready(function() {
		$(".clsShowTwoLines span").reticence();
		$(window).resize(function() {
			$(".clsShowTwoLines span").reticence();
		});
	});


function undoInsight()
{
	$.ajax({
		url : 'undoInsight.ajx',
		type : 'get',
		data: "&insightid=" + document.getElementById('insightid').value,
		success : function(result) {
			document.getElementById("idUndoBanner").style.display = "none";
			$("#frmBannerSubmit").submit();
					
		   },
		error : function(data, status, er) {
			document.getElementById("idUndoBanner").style.display = "none";
		}
	});
}

setTimeout(function() {
    $('#idUndoBanner').fadeOut('fast');
    document.getElementById("idUndoBanner").style.display = "none";
}, 60000);