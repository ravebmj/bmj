$(document).ready(function() {
	// ***********************************Tag Editor code for Insight title start***********************************
	$('#idSearch').select2({
		tags : true,
		tokenSeparators : [ ',' ],

		createSearchChoice : function(term) {
			return {
				id : term,
				text : term,
				n : "new",
				s : ""

			};
		},

		ajax : {
			url : 'listInsight.ajx',
			dataType : 'json',
			type : 'GET',
			data : function(term, page) {
				return {
					insightName : term
				};
			},
			results : function(data, page) {
				return {
					results : data

				};
			}
		},

		// Take default tags from the input value
		initSelection : function(element, callback) {
			var data = [];

			function splitVal(string, separator) {
				var val, i, l;
				if (string === null || string.length < 1)
					return [];
				val = string.split(separator);
				for (i = 0, l = val.length; i < l; i = i + 1)
					val[i] = $.trim(val[i]);
				return val;
			}

			$(splitVal(element.val(), ",")).each(function() {
				data.push({
					id : this,
					text : this
				});
			});

			callback(data);
		},

		// Some nice improvements:

		// max tags is 3
		maximumSelectionSize : 13,

		// override message for max tags
		formatSelectionTooBig : function(limit) {
			return "Max tags is only " + limit;
		},

		formatResult : formatInsightResult
	});
	
	// ***********************************Tag Editor code for Insight title end***********************************

	
	
});


function formatInsightResult(data) {
	var markup;
	if (data.n === "new")
		markup = "<span class='label label-info margin-default'> (New Insight) </span>"	+ data.text;
	else
		markup = data.text;
	return markup;
};
