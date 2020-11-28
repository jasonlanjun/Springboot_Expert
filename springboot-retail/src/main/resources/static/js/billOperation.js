var billingItems = {
	billingItems : []
}

var totalSellingPrice = 0.0;
var totalQuantity = 0;
var totalDiscount = 0.0;
var totalNetPrice = 0.0;

function getProduct(barcode) {
	if (barcode) {
		$
				.ajax({
					type : "POST",
					url : "/admin/productByBarcode",
					data : 'productBarcode=' + barcode,
					success : function(response, responseStatus) {
						if (response !== null) {
							var isNewEntry = true;
							if (billingItems.billingItems.length == 0) {
								var trHTML = '<tr><td>' + "" + '</td><td>'
										+ response.productName + '</td><td>' + 1 
										+ '</td><td>' + response.priceListing 
										+ '</td><td>' + response.priceDiscount 
										+ '</td><td>' + response.priceSelling 
										+ '</td></tr>';
								$('#billList').append(trHTML);

								var newItemObject = {
									billNumber : document.getElementById('billNumber').value,
									billDate : document.getElementById('billDate').value,
									product : response.productName,
									productBarcode : response.productBarcode,
									quantity : 1,
									sellingPrice : response.priceListing,
									discount : response.priceDiscount,
									netPrice : response.priceSelling,
									customerMobile : document.getElementById('customerMobile').value
								}
								billingItems.billingItems.push(newItemObject);
							} else {
								for (var i = 0; i < billingItems.billingItems.length; i++) {
									if (billingItems.billingItems[i].productBarcode == response.productBarcode) {
										billingItems.billingItems[i].quantity = billingItems.billingItems[i].quantity + 1;
										billingItems.billingItems[i].netPrice = billingItems.billingItems[i].netPrice + response.priceSelling;
									
										$('#billList').find('tr').each(function(i) {
											var $tds = $(this).find('td');
											product = $tds.eq(1).text();
												if (JSON.stringify(product) == JSON.stringify(response.productName)) {
													$(this).remove();
												}
										});

										var trHTML = '<tr><td>' + "" + '</td><td>' + response.productName
												+ '</td><td>' + billingItems.billingItems[i].quantity
												+ '</td><td>' + response.priceListing
												+ '</td><td>' + response.priceDiscount
												+ '</td><td>' + billingItems.billingItems[i].netPrice
												+ '</td></tr>';
										$('#billList').append(trHTML);
										isNewEntry = false;
										break;
									}
								}

								if (isNewEntry) {
									var trHTML = '<tr><td>' + "" + '</td><td>' + response.productName
											+ '</td><td>' + 1 + '</td><td>' + response.priceListing
											+ '</td><td>' + response.priceDiscount
											+ '</td><td>' + response.priceSelling
											+ '</td></tr>';
									$('#billList').append(trHTML);

									var newItemObject = {
										billNumber : document.getElementById('billNumber').value,
										billDate : document.getElementById('billDate').value,
										product : response.productName,
										productBarcode : response.productBarcode,
										quantity : 1,
										sellingPrice : response.priceListing,
										discount : response.priceDiscount,
										netPrice : response.priceSelling,
										customerMobile : document.getElementById('customerMobile').value
									}
									billingItems.billingItems.push(newItemObject);
								}
							}

							totalQuantity = totalQuantity + 1;
							totalNetPrice = totalNetPrice + response.priceSelling;

							$('#totalQuantity').html(totalQuantity);
							$('#totalNetPrice').html(totalNetPrice);
						}
					},
					error : function(responseData) {
						alert("Error in Get Product By Barcode--" + JSON.stringify(responseData));
					}
				});
	}
}

function sendProduct() {
	$.ajax({
		type : "POST",
		url : "/admin/saveBillingRecords",
		headers : {
			'Content-Type' : 'application/json'
		},
		dataType : 'json',
		data : JSON.stringify(billingItems),
		success : function(response, responseStatus) {
			if (responseStatus == 'success') {
				$("#billForm").submit();
				var url = window.location.protocol + "//"
						+ window.location.host + "/admin/printBill";
				window.open(url, "myWindow", 'width=800,height=600').print();
				window.close();
			}
		},
		error : function(responseData) {
			alert("Error in send product" + JSON.stringify(responseData));
		}
	});

}

function getCustomer(customerMobile) {
	if (customerMobile.length === 10) {
		$.ajax({
			type : "POST",
			url : "/admin/customerByMobile",
			data : 'customerMobile=' + customerMobile,
			success : function(response, responseStatus) {
				$('#customerName').val(response.customerName);
				$("button").removeAttr("disabled");
				$('#billingMobile').val(response.customerMobile);
			},
			error : function(responseData) {
				alert("Error in Get Customer By Mobile"
						+ JSON.stringify(responseData));
			}
		});
	}
}