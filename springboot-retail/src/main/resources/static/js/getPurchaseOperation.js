function getPoBrand(poCategory) {
	if (poCategory) {
		$.ajax({
			type : "POST",
			url : "/admin/productBrandByCategory",
			data : 'productCategory=' + poCategory,
			success : function(response, responseStatus) {
				$('#poBrand').empty(); // clear the current elements in select
										// box
				$('#poProduct').empty();
				var brandList = response[0];
				var productList = response[1];
				for (row in brandList) {
					$('#poBrand').append(
							$('<option></option>')
									.attr('value', brandList[row]).text(
											brandList[row]));
				}
				for (row in productList) {
					$('#poProduct').append(
							$('<option></option>').attr('value',
									productList[row]).text(productList[row]));
				}
			},
			error : function(responseData) {
				alert("Error in getPoBrand" + responseData);
			}
		});
	} else {
		$('#poBrand').html('<option value="">Select Category First</option>');
		$('#poProduct').html('<option value="">Select Category First</option>');
	}
}

function getProduct(poBrand) {
	if (poBrand) {
		$.ajax({
			type : "POST",
			url : "/admin/productByBrand",
			data : 'productBrand=' + poBrand,
			success : function(response, responseStatus) {
				$('#poProduct').empty(); // clear the current elements in
											// select box
				for (row in response) {
					$('#poProduct').append(
							$('<option></option>').attr('value', response[row])
									.text(response[row]));
				}

			},
			error : function(responseData) {
				alert("Error in getProduct" + responseData);
			}
		});
	} else {
		$('#poProduct').html('<option value="">Select Brand First</option>');
	}
}