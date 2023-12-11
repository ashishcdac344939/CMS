function searchPatient() {
	var searchValue = $("#searchPatientValue").val();
	fetchData(searchValue).done(function(data) {
		//alert("success")
		// Log the data to the console for inspection
		console.log(data);
		/*$("#patient_name").text(data[0].userName)*/
		$(".patientDetails").show()
		$(".visitDetailsTable").show();
		console.log(data[0].patientUcId+" "+localStorage.getItem("userId")+" "+data[0].patientId)
		$("#addNewVisit").attr("data-info", data[0].patientUcId + " " + localStorage.getItem("userId") + " " + data[0].patientId);
		// $("#response-container").html(response);
		// Return the data to be used by DataTables
		localStorage.setItem('patientCredId', data[0].patientUcId);
		localStorage.setItem('patientId', data[0].patientId);
		$("#ppPatientId").attr("value", data[0].patientId);
		$("#showPrescriptionPatientId").attr("value", data[0].patientId);
		patientCredId = data[0].patientUcId;
		showESATable(patientCredId);
		
		if(data.length>0 && data[0].doctorUcId!=null)
		 initializeDataTable(data);
	
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert(jqXHR.responseText);
	});
}


// Example of a custom jQuery function
function yourFunction(value) {
	//alert('You selected: ' + value);
}
function fetchData(searchValue) {
	return $.ajax({
		type: 'GET',
		url: '/patient/getPatientDetails?emailId=' + searchValue,
	});
}
// Function to initialize the DataTable
function initializeDataTable(data) {
	
	if ($.fn.DataTable.isDataTable('#patientVisitTable')) {
		$('#patientVisitTable').DataTable().destroy();
	}

	$('#patientVisitTable').DataTable({
		order: [[1, 'asc']],
		"processing": true,
		fixedHeader: {
			footer: true
		},
		scrollX: true,
		scrollY: true,
		data: data,
		"columns": [
			{
				data: null,
				"render": function(data, type, row) {
					return '<input type="radio" name="patientVisitRadio" value="' + row.pvid + '">'
				}
			},
			{ data: "pvid" },
			{
				data: "invistigationdate",
				render: function(data, type, row) {
					// Assuming "invistigationdate" is a timestamp in milliseconds
					const timestamp = row.invistigationdate;

					// Create a Date object from the timestamp
					const date = new Date(timestamp);

					// Get the year, month, and day components
					const year = date.getFullYear();
					const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is 0-based, so add 1 and pad with leading zero
					const day = String(date.getDate()).padStart(2, '0');

					// Construct the date string in YYYY-MM-DD format
					const formattedDate = `${year}-${month}-${day}`;

					return formattedDate;

				},
			},
			{
				data: "nextvisitdate",
				render: function(data, type, row) {
					// Assuming "invistigationdate" is a timestamp in milliseconds
					const timestamp = row.nextvisitdate;

					// Create a Date object from the timestamp
					const date = new Date(timestamp);

					// Get the year, month, and day components
					const year = date.getFullYear();
					const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is 0-based, so add 1 and pad with leading zero
					const day = String(date.getDate()).padStart(2, '0');

					// Construct the date string in YYYY-MM-DD format
					const formattedDate = `${year}-${month}-${day}`;

					return formattedDate;
				},
			},
			{ data: "presentStatus" },
			{ data: "importantFindings" },
			{ data: "secondaryFindings" },
			{
				"data": null,
				"render": function(data, type, row) {
					return row.painAssessmentFilled ? 'Filled' : 'Not Filled';
				},
			},
			{
				"data": null,
				"render": function(data, type, row) {
					return row.prescriptionFilled ? 'Filled' : 'Not Filled';
				},
			}
		],
		"language": {
			"emptyTable": "No data records found" // Custom message for no data
		},
		dom: 'Blfrtip',
	});
	
	
	
	       $("#painAssessmentButtonId").attr("disabled", true);
			$("#addPrescriptioButtonId").attr("disabled", true);
			 $("#viewPrescriptionId").hide();
			  $("#viewPainAssessmentId").hide();
	// Attach a click event listener to the radio buttons
	$('input[type=radio][name=patientVisitRadio]').change(function() {
		var selectedRow = $(this).closest('tr');
		 // Find the "secondaryFindings" column cell by its column index (e.g., 6 for the 7th column, assuming 0-based index)
    	
   		var painAssessmentStatus = selectedRow.find('td:eq(' + 7 + ')').text();
   		var prescreptionStatus = selectedRow.find('td:eq(' + 8 + ')').text();
		if(painAssessmentStatus !== 'Filled'){
			$("#painAssessmentButtonId").removeAttr("disabled");
			  $("#viewPainAssessmentId").hide();
		}else{
			$("#painAssessmentButtonId").attr("disabled", true);
			  $("#viewPainAssessmentId").show();
			$('#painAssessmentButtonId').attr('title', 'Aleready Filled');
		}
		if(prescreptionStatus !== 'Filled'){
			$("#addPrescriptioButtonId").removeAttr("disabled");
			$("#viewPrescriptionId").hide();
		}else{
			$("#viewPrescriptionId").show();
			$("#addPrescriptioButtonId").attr("disabled", true);
			$('#addPrescriptioButtonId').attr('title', 'Aleready Filled');
			$("#addPrescriptioButtonId").addClass("hover-text");
		}
		//console.log(secondaryFindings)
		
	});
}




function searchPatient1() {
	//$("#searchPatient").hide()
	$(document).ready(function() {
		let patientCredId;

		// Destroy the existing DataTable if it exists
		if ($.fn.DataTable.isDataTable('#patientVisitTable')) {
			$('#patientVisitTable').DataTable().destroy();
		}
		var searchValue = $("#searchPatientValue").val()
		var table = $('#patientVisitTable').DataTable({
			order: [[1, 'asc']],
			serverSide: true,
			"processing": true,
			fixedHeader: {
				footer: true
			},
			scrollX: true,
			scrollY: true,
			ajax: {
				type: 'GET',
				url: '/patient/getPatientDetails?emailId=' + searchValue,
				dataSrc: function(data) {
					// Log the data to the console for inspection
					console.log(data);
					$("#patient_name").text(data[0].userName)
					if (Array.isArray(data) && data.length === 0) {
						// Hide the "Processing" message here
						//$('#multi_col_order_processing').css('display', 'none');
						alert("no record found");
						window.location.href = ""
					}
					// Set the total records count
					table.page.info().recordsTotal = data.length;

					// $("#addNewVisit").on("click", addNewVisit(data.patientUcId,data.doctorUcId,data.patientId));

					$(".patientDetails").show()
					$(".visitDetailsTable").show();
					$("#addNewVisit").attr("data-info", data[0].patientUcId + " " + localStorage.getItem("userId") + " " + data[0].patientId);
					// $("#response-container").html(response);
					// Return the data to be used by DataTables
					localStorage.setItem('patientCredId', data[0].patientUcId);
					localStorage.setItem('patientId', data[0].patientId);
					$("#ppPatientId").attr("value", data[0].patientId);
					$("#showPrescriptionPatientId").attr("value", data[0].patientId);
					patientCredId = data[0].patientUcId;
					showESATable(patientCredId);
					return data;
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown)
				},

			},
			"columns": [
				{
					data: null,
					"render": function(data, type, row) {
						return '<input type="radio" name="patientVisitRadio" value="' + row.pvid + '">'
					}
				},
				{ data: "pvid" },

				{
					data: "invistigationdate",
					render: function(data, type, row) {
						// Assuming "invistigationdate" is a timestamp in milliseconds
						const timestamp = row.invistigationdate;

						// Create a Date object from the timestamp
						const date = new Date(timestamp);

						// Get the year, month, and day components
						const year = date.getFullYear();
						const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is 0-based, so add 1 and pad with leading zero
						const day = String(date.getDate()).padStart(2, '0');

						// Construct the date string in YYYY-MM-DD format
						const formattedDate = `${year}-${month}-${day}`;

						return formattedDate;
					}
				},
				{
					data: "nextvisitdate",
					render: function(data, type, row) {
						// Assuming "invistigationdate" is a timestamp in milliseconds
						const timestamp = row.nextvisitdate;

						// Create a Date object from the timestamp
						const date = new Date(timestamp);

						// Get the year, month, and day components
						const year = date.getFullYear();
						const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is 0-based, so add 1 and pad with leading zero
						const day = String(date.getDate()).padStart(2, '0');

						// Construct the date string in YYYY-MM-DD format
						const formattedDate = `${year}-${month}-${day}`;

						return formattedDate;
					}
				},
				{ data: "presentStatus" },
				{ data: "importantFindings" },
				{ data: "secondaryFindings" },
				{
					"data": null,
					"render": function(data, type, row) {
						if (row.painAssessmentFilled) {

							return 'Completed';
						} else {
							return 'Not Filled';
						}
					}
				},
				{
					"data": null,
					"render": function(data, type, row) {
						if (row.prescriptionFilled) {

							return 'Completed';
						} else {
							return 'Not Filled';
						}
					}
				}
			],
			"language": {
				"emptyTable": "No data records found" // Custom message for no data
			},
			dom: 'Blfrtip',

		})

	})

}

function showESATable(emaidID) {

	$('.esaVisitDetailsTable').show();
	if ($.fn.DataTable.isDataTable('#esaTable')) {
		$('#esaTable').DataTable().destroy();
	}
	/*var table = $('#esaTable').DataTable({
		serverSide: true,
		"processing": true,
		fixedHeader: {
			footer: true
		},
		scrollX: true,
		scrollY: true,

		ajax: {
			type: 'GET',
			url: '/symptom/getESAByPatientcredId/' + patientCredId, // Remove the extra closing parenthesis here
			dataSrc: function (data) {
				// Log the data to the console for inspection
				alert("i am in showESATable"+data);
				console.log(data);
				// Return the data to be used by DataTables
				return data;
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			}
		},
		"columns": [
			{ data: "pdcurrentdiagnosis" },
			{ data: "recordTracking" },
			{ data: "invistigationdate" },
			{ data: "completedBy" },
			{
				"data": null,
				"render": function (data, type, row) {
					console.log("row:  ", row);
					var id = row.pvid;
					return '<button class="btn waves-effect waves-light btn-primary" data-info="' + row.id + '"  onclick="showESAById(' + row.id + ')" id="showESAById">Show Full Details</button>';
				}
			}
		],
		dom: 'Blfrtip',
		buttons: [
			'copy', 'excel', {
				extend: 'pdfHtml5',
				orientation: 'landscape',
				pageSize: 'LEGAL'
			}
		]
	});*/

	$.ajax({
		url: '/patient/getESAByPatientcredId/'+emaidID,
		method: "GET",
		contentType: "application/json",
		success: function(data) {
			console.log("********************esasymptom")
			console.log(data)
			var option = "";
			for (var i = 1, l = data.length; i <= l; i++) {
				option = option + "<tr>"
				option = option + "<td>" + i + "</td>"
				option = option + "<td>" + formatDate(data[i - 1].recordTracking) + "</td>"
				option = option + "<td>" + data[i - 1].completedBy + "</td>"
				option += "<td><button class='btn btn-primary' onclick='getEsaByEsaId(" + data[i - 1].id + ")'>Click</button></td>";
				option = option + "</tr>"
			}

			$('#esaTableBody').empty();
			$('#esaTableBody').append(option);
			$('#esa_data_table').DataTable();
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
	//table.ajax.reload();
}
function isPrescriptionAndPainAssessmentFilled() {
	var selectedValue = $('input[name="patientVisitRadio"]:checked').val();
	// First AJAX request
	$.ajax({
		url: '/patient/isPrescriptionAndPainAssessmentFilled',
		method: "GET",
		contentType: "application/json",
		data: { patientVisitId: selectedValue },
		success: function(data) {
			// Assuming the data returned from the API is the value you want to set
			var newValue = data;
			console.log(newValue);
		},
		error: function() {
			console.log("First AJAX call failed");
		}
	});
};
function formatDate(timestamp) {
	// Assuming timestamp is in milliseconds
	const date = new Date(timestamp);

	// Get the year, month, and day components
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is 0-based, so add 1 and pad with leading zero
	const day = String(date.getDate()).padStart(2, '0');

	// Construct the date string in YYYY-MM-DD format
	const formattedDate = `${year}-${month}-${day}`;

	return formattedDate;
}
function addNewVisitDiv() {
	// Function to make an AJAX request
	window.location.href = "#visitDetailsDiv"
	$("#visitDetailsDiv").show();
	$("#patientPrescriptionDiv").hide();
	$("#patientPainAssessmentDetailsDiv").hide();

} 