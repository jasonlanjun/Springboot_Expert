$(document).ready( function () {
	 var table = $('#allusersTable').DataTable({
			"sAjaxSource": "/allusers",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "firstName" },
				  { "mData": "lastName" }
			]
	 })
});
