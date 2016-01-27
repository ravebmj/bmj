$(document).ready(function() {
	$("#gbqfq").autocomplete({
	    source: function(request, response) {
		    $.ajax({
		    url: "autoComSearchText.ajx",
		    type: "GET",
		    data: { nametitle: request.term},
		    success: function( data ) {
		             var arr=JSON.parse(data);
		        response( $.map( arr, function( item ) {
		        return {
		            label: item.text,
		            value: item.id,
		        }
		        }));
		    }
		    });
	    },	  
	    error: function (error) {
	       alert('error: ' + error);
	    },
	    minLength: 1
	    });
});
