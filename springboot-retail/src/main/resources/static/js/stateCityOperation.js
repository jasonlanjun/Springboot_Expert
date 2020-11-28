function getCity(stateSelected) {
		if (stateSelected) {
			$.ajax({
				type : "POST",
				url : "/admin/cityByState",
				data : 'state=' + stateSelected,
				success : function(response, responseStatus) {
					$('#city').empty(); // clear the current elements in select box
					for (row in response) {
						$('#city').append(
								$('<option></option>').attr('value',
										response[row]).text(response[row]));
					}

				},
				error : function(responseData) {
					alert("Error in Get City" + JSON.stringify(responseData));
				}
			});
		} else {
			$('#city').html('<option value="">Select State First</option>');
		}
	}