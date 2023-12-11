$(document).ready(function() {
	$("input[type='range']").val(0)
	$('.dropdown-with-search').select2();
	$('input:checkbox[name="treatmentRecieved"]').change(
		function() {
			if ($(this).is(':checked') && $(this).val() == 'other') {
				$("#otherInputDiv").show()
			} else {
				$("#otherInputDiv").hide()
			}
		});
	$("input[name='ppIsRadiatefromPointOfOrigin']").change(function() {
		if ($('#ppIsRadiatefromPointOfOriginY').is(":checked")) {
			$("#ppRadiatefromPointOfOriginDetailsDiv").show()
		} else {
			$("#ppRadiatefromPointOfOriginDetailsDiv").hide()
		}
	})
	$("input[name='ppIsWorsenedByActivity']").change(function() {
		if ($('#ppIsWorsenedByActivityY').is(":checked")) {
			$("#ppWorsenedByActivityDetailsDiv").show()
		} else {
			$("#ppWorsenedByActivityDetailsDiv").hide()
		}
	})
})

function printPrescription() {
	/*window.print()*/
	var mywindow = window.open('', 'my_div', 'height=800,width=1200');
	mywindow.document.write('<html><head><title></title>');
	mywindow.document.write('<style type="text/css">'
		+ 'body { margin: 0; font-family: "Rubik",sans-serif; font-size: 1rem; font-weight: 400; line-height: 1.5; color: #7c8798;'
		+ 'text-align: var(--bs-body-text-align);-webkit-text-size-adjust: 100%;-webkit-tap-highlight-color: transparent;}'
		+ '.table{--bs-table-border-color: #e8eef3;width: 1000px; margin-bottom: 1rem; vertical-align: top;border-collapse: collapse;font-size:0.75rem} '
		+ '.table>tbody { vertical-align: inherit;}'
		+ '.table>:not(caption)>*>* { padding: 12px 12px; border-bottom-width: 1px;}'
		+ 'td,th{text-align: left;border-color: inherit; border-style: solid; border-width: 0;border-right: 1px solid var(--bs-table-border-color);}'
		+ 'td:last-child,th:last-child{border-right: none}.text-center { text-align: center!important;}'
		+ 'tr{border-color: var(--bs-table-border-color);}.mb-0 {margin-bottom: 0!important;}'
		+ 'tbody tr:last-child td{border-bottom-width: 0px}'
		+ '.text-end {text-align: right !important;}'
		+ '.text-end {text-align: right !important;} .px-3 {padding-right: 1rem !important;padding-left: 1rem !important;}'
		+'.d-flex {display: flex !important;}.justify-content-between { justify-content: space-between !important;}'
		+ '</style></head><body>');
	mywindow.document.write($("#prescriptionPrintDiv").html());
	mywindow.document.write('</body></html>');
	mywindow.print()
	mywindow.document.close();
}
function printPainAssessment() {
	var mywindow = window.open('', 'my_div', 'height=800,width=1200');
	mywindow.document.write('<html><head><title></title>');
	mywindow.document.write('<style type="text/css">'
		+ 'body { margin: 0; font-family: "Rubik",sans-serif; font-size: 1rem; font-weight: 400; line-height: 1.5; color: #7c8798;'
		+ 'text-align: var(--bs-body-text-align);-webkit-text-size-adjust: 100%;-webkit-tap-highlight-color: transparent;}'
		+ '.table{--bs-table-border-color: #e8eef3;width: 100%; margin-bottom: 1rem; vertical-align: top;border-collapse: collapse;font-size:0.75rem} '
		+ '.table>tbody { vertical-align: inherit;}'
		+ '.table>:not(caption)>*>* { padding: 12px 12px; border-bottom-width: 1px;}'
		+ 'td,th{border-color: inherit; border-style: solid; border-width: 0;border-right: 1px solid var(--bs-table-border-color);}'
		+ 'td:last-child,th:last-child{border-right: none}.text-center { text-align: center!important;}'
		+ 'tr{border-color: var(--bs-table-border-color);}.mb-0 {margin-bottom: 0!important;}'
		+ 'tbody tr:last-child td{border-bottom-width: 0px}'
		+ '#imageContainer { position: relative; width: 300px; height: 300px; background-image: url("../../dist/img/body.png");'
		+ 'background-size: cover; background-repeat: no-repeat;print-color-adjust: exact; -webkit-print-color-adjust: exact !important;color-adjust: exact !important}'
		+ '.circle { position: absolute; border: 4px solid blue; border-radius: 50%; width: 15px; height: 15px;}'
		+ '</style></head><body>');
	mywindow.document.write($("#painAssessmentPrintDiv").html());
	mywindow.document.write('</body></html>');
	setTimeout(function() { mywindow.print(); mywindow.document.close(); }, 500)

}

function viewPainAssessment() {
	var id = $('input[name=patientVisitRadio]:checked').val()
	console.log(id, " pain id")
	var settings = {
		"url": "/patient/getPatientPainAssessmentsDetailByVisitId",
		"method": "POST",
		contentType: "application/json",
		"timeout": 0,
		"data": JSON.stringify({
			"pvId": id
		}),
	};

	$.ajax(settings).done(function(response) {
		var pAssesData = response
		$("#maxPainExpPreview").text(pAssesData.ppMaxPainExperienced)
		$("#leastPainExpPreview").text(pAssesData.ppNrsRepresentingLeastPain)
		$("#painMaxPreview").text(pAssesData.ppPainMaximumDuringDay)
		$("#radiateFromPointPreview").text(pAssesData.ppRadiatefromPointOfOriginDetails)
		$("#worsenedActivityPreview").text(pAssesData.ppWorsenedByActivityDetails)
		$("#reliefPosturePreview").text(pAssesData.ppIsAnyPostureGivesRelief)
		$("#treamentReceivedPreview").text(pAssesData.ppIsReceivedTreatmentForYear)
		$("#painReliefMedicationPreview").text(pAssesData.ppPainReliefAfterMedication)
		$("#reliefLastPreview").text(pAssesData.ppHowLongReliefRetained)
		$("#interfereDailyActivityPreview").text(pAssesData.ppHowMuchPainInterfereDailyActivity)
		$("#painDaignosisPreview").text(pAssesData.ppDiagnosis)
		$("#ladderStepPreview").text(pAssesData.ppLadderStep)
		// Call the function to create circles on the image
		createCirclesOnImage(pAssesData.bodyCirclePainAssesmentMapping);
		var otherSymptom = ""
		var typeOfPain = ""
		$.each(pAssesData.patientPainAssesmentSymtomMasterMapping, function(index, value) {
			otherSymptom += value.symptomsMaster.symptom + " "
		})
		$.each(pAssesData.patientPainAssesmentPainMasterMapping, function(index, value) {
			typeOfPain += value.painMaster.painName + " "
		})
		$("#typeOfPainPreview").text(typeOfPain)
		$("#otherSymptomPreview").text(otherSymptom)
		console.log(response);
	});
}

/* ================================================ showing image ====================================================== */
// Sample circle data (replace with data retrieved from your server)
const savedCircles1 = [
	{ x: 81, y: 145.215 },
	// Add more circles as needed
];

// Function to create and position circle elements on the image
function createCirclesOnImage(savedCircles) {
	console.log(savedCircles)
	const imageContainer = document.getElementById('imageContainer');
	// Clear the previous circles by removing all child elements
	while (imageContainer.firstChild) {
		imageContainer.removeChild(imageContainer.firstChild);
	}
	savedCircles.forEach(circle => {
		// Create a new div element for each circle
		const circleElement = document.createElement('div');
		circleElement.className = 'circle';

		// Set the position of the circle based on the saved data
		circleElement.style.left = `${circle.x}px`;
		circleElement.style.top = `${circle.y}px`;

		// Append the circle to the image container
		imageContainer.appendChild(circleElement);
	});
}


/* ====================================================================================================== */



function generateESAMap(label, value) {
	var trace = {
		x: label,
		y: value,
		mode: 'lines+markers',
		name: 'Pain',

	};

	// Create the layout
	var layout = {
		title: "",
		xaxis: {
			title: 'Symptoms',
			type: 'category', // Treat x-axis values as categories
			tickangle: -30,// Format the date as 'YYYY-MM-DD'
		},
		yaxis: {
			title: 'Value',
		},
		// Enable the responsive property
		responsive: true,
	};
	var config = {
		displayModeBar: false
	};
	// Create the chart
	Plotly.newPlot('eSAMap', [trace], layout, config);

}
//Function to create and position circle elements on the image
function createCirclesOnESAImage(savedCircles) {
	console.log(savedCircles)
	const imageContainer = document.getElementById('imageContainerESA');
	// Clear the previous circles by removing all child elements
	while (imageContainer.firstChild) {
		imageContainer.removeChild(imageContainer.firstChild);
	}

	savedCircles.forEach(circle => {
		// Create a new div element for each circle
		const circleElement = document.createElement('div');
		circleElement.className = 'circle';

		// Set the position of the circle based on the saved data
		circleElement.style.left = `${circle.x}px`;
		circleElement.style.top = `${circle.y}px`;

		// Append the circle to the image container
		imageContainer.appendChild(circleElement);
	});
}
//Function to add a row to the table
function addTableRow(serial, key, value) {
	const tableBody = $("#eSAModalViewTable tbody");
	const newRow = $("<tr>");
	newRow.append(`<td>${serial}</td>`);
	newRow.append(`<td>${key}</td>`);
	newRow.append(`<td>${value}</td>`);
	tableBody.append(newRow);
}


function viewPrescription() {
	var id = $('input[name=patientVisitRadio]:checked').val()
	$("#prescriptionModal").modal('show')
	var settings = {
		"url": "../../medicine/getPatientPrescription?patientVisitId=" + id,
		"method": "GET",
		"headers": {
			"Authorization": "Bearer " + localStorage.getItem("token")
		},
	};

	$.ajax(settings).done(function(response) {
		console.log(response, " prescription");
		$("#form3eData").empty();
		$("#symptomMedicineTable").empty();
		var patientPrescriptionDate = response
		console.log(patientPrescriptionDate[0])
		$("#patientNamePresc").text(patientPrescriptionDate[0].name)
		$("#patientNamePresc3e").text(patientPrescriptionDate[0].name)

		var d = new Date();
		var cDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		$("#currDate").text(cDate)
		$("#pDob").text(patientPrescriptionDate[0].dob)
		$("#pvId").text(patientPrescriptionDate[0].pvId)
		$("#pDob").text(patientPrescriptionDate[0].dob)
		/*var d = new Date();
		var cDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();*/
		$("#patientAddress").text(patientPrescriptionDate[0].address)
		$("#doctorRegNo").text(patientPrescriptionDate[0].docRegNo)
		$("#patientAadhaar").text(patientPrescriptionDate[0].adharno)
		$("#docName").text(patientPrescriptionDate[0].docName)
		$("#docNamePresc3e").text(patientPrescriptionDate[0].docName)
		var d = new Date();
		var cDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		var cTime = d.getHours() + ":" + d.getMinutes()
		$(".currDate").text(cDate)
		$(".currTime").text(cTime)
var age = getAge(patientPrescriptionDate[0].dob)
		$("#pAge").text(age)

		$.each(patientPrescriptionDate, function(index, value) {
			if (value.is3ERequired) {
				$("#form3eData").append('<tr><td>13/09/2023</td><td>' + value.medicineName + '</td><td>' + value.medicineQuantity + '</td><td></td><td></td>')
				$("#symptomMedicineTable").append('<tr><td>' + value.medicineName + '</td><td>' + value.noOfDays + '</td><td>' + value.daysInterval + '</td><td>' + value.medicineInADays + '</td><td>' + value.medicineQuantity + '</td><td>' + value.remark + '</td>')
			} else {
				$("#symptomMedicineTable").append('<tr><td>' + value.medicineName + '</td><td>' + value.noOfDays + '</td><td>' + value.daysInterval + '</td><td>' + value.medicineInADays + '</td><td>' + value.medicineQuantity + '</td><td>' + value.remark + '</td>')
			}
		})
		console.log(response);
	});
}

function getAge(dateString) {
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

function viewTodaysScheduledVisites() {
	$("#patientScheduledModal").modal('show')
}
function viewPrescription1(pid) {
	var id = pid;
	//alert(id)
	$("#prescriptionModal").modal('show')
	var settings = {
		"url": "../../medicine/getPatientPrescription?patientVisitId=" + id,
		"method": "GET",
	};

	$.ajax(settings).done(function(response) {
		//alert("success")
		console.log(response, " prescription");
		$("#form3eData").empty();
		$("#symptomMedicineTable").empty();
		var patientPrescriptionDate = response
		console.log(patientPrescriptionDate[0])
		$("#patientNamePresc").text(patientPrescriptionDate[0].name)
		$("#patientNamePresc3e").text(patientPrescriptionDate[0].name)
		var d = new Date();
		var cDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		$("#currDate").text(cDate)
		$("#pDob").text(patientPrescriptionDate[0].dob)
		$("#pvId").text(patientPrescriptionDate[0].pvId)
		$("#docName").text(patientPrescriptionDate[0].docName)
		var age = getAge(patientPrescriptionDate[0].dob)
		$("#pAge").text(age)
		$.each(patientPrescriptionDate, function(index, value) {
			if (value.is3ERequired) {
				$("#form3eData").append('<tr><td>13/09/2023</td><td>' + value.medicineName + '</td><td>' + value.medicineQuantity + '</td><td></td><td></td>')
				$("#symptomMedicineTable").append('<tr><td>' + (index+1) + '</td><td>' + value.medicineName + '</td><td>' + value.noOfDays + '</td><td>' + value.daysInterval + '</td><td>' + value.medicineInADays + '</td><td>' + value.medicineQuantity + '</td>')
			} else {
				$("#symptomMedicineTable").append('<tr><td>' + value.symptom + '</td><td>' + value.medicineName + '</td><td>' + value.noOfDays + '</td><td>' + value.daysInterval + '</td><td>' + value.medicineInADays + '</td><td>' + value.medicineQuantity + '</td>')
			}
		})
		console.log(response);
	});
}


function addVisitDetail() {
	var infoData = $("#addNewVisit").attr("data-info").split(" ")

	// get the treatment received
	var treatmentReceived = []
	$("input[name='treatmentRecieved']:checked").each(function() {
		var treatment = $(this).val();
		if (treatment == 'other') {
			treatmentReceived.push($("#otherInput").val());
		}
		else {
			treatmentReceived.push(treatment);
		}

	});
	console.log(treatmentReceived)

	var data = {
		"invistigationDate": $('#investigationDate').val(),
		"presentStatus": $('input[name="presentStatus"]:checked').val(),
		"importantFinding": $("#importantFinding").val(),
		"pdCurrentDiagnosis": $("#pdCurrentDiagnosis").val(),
		"pdSecondaries": $("#pdSecondaries").val(),
		"indicationOfReferral": $("#indicationOfReferral").val(),
		"presentStatus": $('input[name="presentStatus"]:checked').val(),
		"pdStageOfCancer": $("#pdStageOfCancer").val(),
		"pdTreatmentDetails": $("#pdTreatmentDetails").val(),
		"treatmentReceviedAsString": treatmentReceived,
		"timeOfPresentationToPcwAfterDiagnosis": $("#timeOfPresentationToPcwAfterDiagnosis").val(),
		"userCredential": { "id": parseInt(infoData[1]) },
		"pUserCredential": { "id": parseInt(infoData[0]) },
		"patientDetail": { "pid": parseInt(infoData[2]) },
		"nextVisitDate": $('#nextVisitDate').val(),
	}


	var result = validateAddVisit(data);
	if (result == false) {
		return;
	}

	$.ajax({
		url: '/patient/savePatientVisitDiagnosisDetail',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(data) {
			//alert("Patient visit details saved successfully")
			$("#success-modal-text").html('Patient visit details saved successfully')
			$("#success-modal").modal('show')
			$("#visitDetailsDiv").hide();
			$("#searchPatient").show();
			//reload the table data
			var searchValue = $("#searchPatientValue").val();
			fetchData(searchValue).done(function(data) {
				//alert("success")
				// Log the data to the console for inspection
				initializeDataTable(data);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				//alert(jqXHR.responseText);
			});
		},
		error: function() {
			console.log("AJAX call failed");
		}
	});
	console.log(data)
}



function validateAddVisit(data) {
	//console.log(data)
	if (data.invistigationDate == "") {
		alert("Select Investigation  Date ");
		scrollToInputField($('#investigationDate'));
		return false;
	}
	if (data.nextVisitDate == "") {
		alert("Select Next Visit  Date ");
		scrollToInputField($('#nextVisitDate'));
		return false;
	}
	if (data.importantFinding == "") {
		alert("Enter Important Findind ");
		scrollToInputField($("#importantFinding"));
		return false;
	}
	if (data.pdSecondaries == "") {
		alert("Enter Secondery Observation");
		scrollToInputField($("#pdSecondaries"));
		return false;
	}
	if ($('input[name="presentStatus"]:checked') == "") {
		alert("Select Present Status");
		scrollToInputField($('input[name="presentStatus"]'));
		return false;
	}
	return true;
}

function scrollToInputField(inputId) {
	var targetElement = $(inputId);
	/*$('html, body').scrollTo(targetElement,800);*/
	targetElement.focus()
	$([document.documentElement, document.body]).animate({
		scrollTop: targetElement.offset().top - 2 * ($('header').height())
	}, 0);

}

/** function addPatientDetails() {
	var data = {
		"userModel": {
			"userId": $("#userId").val(),
			"userName": $("#userName").val(),
			"passwrord": $("#passwrord").val(),
			"userMobile": $("#userMobile").val(),
			"address": $("#address").val(),
			"userUniqueId": $("#userUniqueId").val(),
			"userRoleId": 2,
			"userDescription": $("#userDescription").val()
		},
		"pCurrentSocioEconomicStatus": $("#pCurrentSocioEconomicStatus").val(),
		"pDaughter": $("#pDaughter").val()
	}

	$.ajax({
		url: '/patient/add',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(data) {
			// Assuming the data returned from the API is the value you want to set
			var newValue = data;
			// Set the value of the input using .val()
			alert("Patient saved successfully")
		},
		error: function() {
			console.log("AJAX call failed");
		}
	});
	console.log(data)
}*/
function addPrescription() {

	const selectedData = {
		"patientDetail": {
			"pid": $("#showPrescriptionPatientId").val()
		},
		patientVisitDiagnosisDetail: {
			pvId: $("#showPrescriptionPatientvisitId").val() // Replace with the actual value
		},
		medicineAndQuantity: medicineAndPrescription,
	};

	console.log(selectedData)
	//add data in db

	$.ajax({
		url: "/medicine/savePatientPrescription",
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(selectedData),
		success: function(data) {
			// Assuming the data returned from the API is the value you want to set
			var newValue = data;

			// Set the value of the input using .val()
			alert("Prescription saved successfully")
			$("#patientPrescriptionDiv").hide()
			//reload the table data
			var searchValue = $("#searchPatientValue").val();
			fetchData(searchValue).done(function(data) {
				//alert("success")
				// Log the data to the console for inspection
				initializeDataTable(data);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				//alert(jqXHR.responseText);
			});
		},
		error: function() {
			console.log("AJAX call failed");
		}
	});
}

// Reset Image  Circles
function resetCircleOfImage() {
	// Remove all 'painCircle' elements from the humanBodyContainer
	const painCircles = document.querySelectorAll('.painCircle');
	for (const circle of painCircles) {
		humanBodyContainer.removeChild(circle);
	}

	// Clear the circles array
	circles = [];

}

/* ============================================ add pain ========================================================== */
var circles = [];

document.addEventListener('DOMContentLoaded', function() {
	$("input[type='range']").val(0)
	const humanBodyContainer = document.getElementById('humanBodyContainer');
	let isDrawing = false;
	let currentCircle = null;

	humanBodyContainer.addEventListener('mousedown', (e) => {
		isDrawing = true;
		const circle = document.createElement('div');
		circle.className = 'painCircle';
		const rect = humanBodyContainer.getBoundingClientRect();
		const x = e.clientX - rect.left - circle.offsetWidth / 2;
		const y = e.clientY - rect.top - circle.offsetHeight / 2;
		circle.style.left = x + 'px';
		circle.style.top = y + 'px';
		humanBodyContainer.appendChild(circle);
		currentCircle = circle;
		circles.push({ x, y });
	});

	humanBodyContainer.addEventListener('mousemove', (e) => {
		if (!isDrawing) return;
		const rect = humanBodyContainer.getBoundingClientRect();
		const x = e.clientX - rect.left - currentCircle.offsetWidth / 2;
		const y = e.clientY - rect.top - currentCircle.offsetHeight / 2;
		currentCircle.style.left = x + 'px';
		currentCircle.style.top = y + 'px';
	});

	document.addEventListener('mouseup', () => {
		isDrawing = false;
		currentCircle = null;
	});
	const saveButton = document.getElementById('saveButton');
});
// Reset Image  Circles
function resetCircleOfImageESAS() {
	// Remove all 'painCircle' elements from the humanBodyContainer
	const painCircles = document.querySelectorAll('.painCircle');
	console.log(painCircles)
	for (const circle of painCircles) {
		humanBodyContainerForEsa.removeChild(circle);
	}

	// Clear the circles array
	circles = [];

}
document.addEventListener('DOMContentLoaded', function() {
	const humanBodyContainer = document.getElementById('humanBodyContainerForEsa');
	let isDrawing = false;
	let currentCircle = null;

	humanBodyContainer.addEventListener('mousedown', (e) => {
		isDrawing = true;
		const circle = document.createElement('div');
		circle.className = 'painCircle';
		const rect = humanBodyContainer.getBoundingClientRect();
		const x = e.clientX - rect.left - circle.offsetWidth / 2;
		const y = e.clientY - rect.top - circle.offsetHeight / 2;
		circle.style.left = x + 'px';
		circle.style.top = y + 'px';
		humanBodyContainer.appendChild(circle);
		currentCircle = circle;
		circles.push({ x, y });
	});

	humanBodyContainer.addEventListener('mousemove', (e) => {
		if (!isDrawing) return;
		const rect = humanBodyContainer.getBoundingClientRect();
		const x = e.clientX - rect.left - currentCircle.offsetWidth / 2;
		const y = e.clientY - rect.top - currentCircle.offsetHeight / 2;
		currentCircle.style.left = x + 'px';
		currentCircle.style.top = y + 'px';
	});

	document.addEventListener('mouseup', () => {
		isDrawing = false;
		currentCircle = null;
	});
	const saveButton = document.getElementById('saveButton');
});
/* ====================================================================================================== */


function addPatientPainAssessment() {


	var integerPainMasterList = $.map($("#painMasterList").val(), function(str) {
		return parseInt(str, 10); // Specify base 10 for decimal conversion
	});
	var integerSymptomsMasterList = $.map($("#symptomsMasterList").val(), function(str) {
		return parseInt(str, 10); // Specify base 10 for decimal conversion
	});

	var data = {
		"patientPainAssessments": {
			"ppDiagnosis": $("#ppDiagnosis").val(),
			"ppHowLongReliefRetained": $("#ppHowLongReliefRetained").val(),
			"ppHowMuchPainInterfereDailyActivity": $("#ppHowMuchPainInterfereDailyActivity").val(),
			"ppInvistigationDate": $("#ppInvistigationDate").val(),
			"ppIsAnyPostureGivesRelief": $("#ppIsAnyPostureGivesRelief").val(),
			"ppIsRadiatefromPointOfOrigin": $("#ppIsRadiatefromPointOfOrigin").val(),
			"ppIsReceivedTreatmentForYear": $("#ppIsReceivedTreatmentForYear").val(),
			"ppIsWorsenedByActivity": $("#ppIsWorsenedByActivity").val(),
			"ppLadderStep": $("#ppLadderStep").val(),
			"ppLocationOfPain": $("#ppLocationOfPain").val(),
			"ppMaxPainExperienced": $("#ppMaxPainExperienced").val(),
			"ppPainMaximumDuringDay": $("#ppPainMaximumDuringDay").val(),
			"ppPainReliefAfterMedication": $("#ppPainReliefAfterMedication").val(),
			"ppRadiatefromPointOfOriginDetails": $("#ppRadiatefromPointOfOriginDetails").val(),
			"ppReceivedTreatmentForYearDetails": $("#ppReceivedTreatmentForYearDetails").val(),
			"ppWorsenedByActivityDetails": $("#ppWorsenedByActivityDetails").val(),
			"ppNrsRepresentingLeastPain": $("#ppNrsRepresentingLeastPain").val(),
			"patientDetail": {
				"pid": $("#ppPatientId").val()
			},
			"patientVisitDiagnosisDetail": { "pvId": $("#ppPatientvisitId").val() }
		},

		"painMasterList": circles,
		"symptomsMasterList": integerSymptomsMasterList

	}

	$("input[name='ppIsRadiatefromPointOfOrigin']").change(function() {
		if ($('#housingStatusO').is(":checked")) {
			console.log($(this).attr('id'))
			$("#otherInputHousingStatus").show()
		} else {
			$("#otherInputHousingStatus").hide()
		}
	})
	var result = validateAddPatientPainAssesment(data);
	if (result == false) {
		return;
	}
	console.log(data)
	$.ajax({
		url: '/patient/savePatientPainAssessmentsDetail',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(data) {
			// Assuming the data returned from the API is the value you want to set
			var newValue = data;

			// Set the value of the input using .val()
			alert("Patient Pain Assessmnt saved successfully")
			$("#patientPainAssessmentDetailsDiv").hide()
			//reload the table data
			var searchValue = $("#searchPatientValue").val();
			fetchData(searchValue).done(function(data) {
				//alert("success")
				// Log the data to the console for inspection
				initializeDataTable(data);
				$("input[type='range']").val(0)
			}).fail(function(jqXHR, textStatus, errorThrown) {
				//alert(jqXHR.responseText);
			});
		},
		error: function() {
			console.log("AJAX call failed");
		}
	});
	console.log(data)
}

function validateAddPatientPainAssesment(data) {
	if (data.patientPainAssessments.ppMaxPainExperienced == 'Choose...') {
		alert("Please Choose Value of Max NRS Pain Experienced");
		scrollToInputField($('#ppMaxPainExperienced'));
		return false;
	}
	if (data.patientPainAssessments.ppNrsRepresentingLeastPain == 'Choose...') {
		alert("Please Choose Value of Min NRS Pain Experienced");
		scrollToInputField($('#ppNrsRepresentingLeastPain'));
		return false;
	}
	if (data.patientPainAssessments.ppPainMaximumDuringDay == 'Choose...') {
		alert("Please Choose Value of Maximim Pain During Day");
		scrollToInputField($('#ppPainMaximumDuringDay'));
		return false;
	}
	// changed
	if ($('input[name="ppIsRadiatefromPointOfOrigin"]:checked').val() == undefined) {
		alert("Please Select Radiate from Point Of Origin");
		scrollToInputField($("input[name='ppIsWorsenedByActivity']"));
		return false;
	}
	if ($('input[name="ppIsRadiatefromPointOfOrigin"]:checked').val() == 'yes') {
		if (data.patientPainAssessments.ppRadiatefromPointOfOriginDetails == '') {
			alert("Radiate from Point Of Origin Details Field Can't be Empty");
			scrollToInputField($("input[name='ppRadiatefromPointOfOriginDetailsDiv']"));
			return false;
		}
	}
	if ($('input[name="ppIsWorsenedByActivity"]:checked').val() == undefined) {
		alert("Select Is Worsened By Activity");
		scrollToInputField($("input[name='ppIsWorsenedByActivity']"));
		return false;
	}
	if ($('input[name="ppIsWorsenedByActivity"]:checked').val() == 'yes') {
		if (data.patientPainAssessments.ppWorsenedByActivityDetails == '') {
			alert("Worsened By Activity Details Field Can't be Empty");
			scrollToInputField($("input[name='ppIsWorsenedByActivity']"));
			return false;
		}
	}
	if (data.patientPainAssessments.treppIsReceivedTreatmentForYear == '') {
		alert("Received Treatment For Your Pain Field Can't be Empty");
		scrollToInputField($('#ppReceivedTreatmentForYearDetails'));
		return false;
	}
	if (data.patientPainAssessments.ppIsAnyPostureGivesRelief == '') {
		alert("Any Posture Gives Relief Field Can't be Empty");
		scrollToInputField($('#ppIsAnyPostureGivesRelief'));
		return false;
	}

	if (data.patientPainAssessments.ppPainReliefAfterMedication == 'Choose...') {
		alert("Please Choose Value of Pain Relief After Medication");
		scrollToInputField($('#ppPainReliefAfterMedication'));
		return false;
	}
	if (data.patientPainAssessments.ppHowLongReliefRetained == 'Choose...') {
		alert("Please Choose Value of How Long Relief Retained");
		scrollToInputField($('#ppHowLongReliefRetained'));
		return false;
	}
	if (data.painMasterList.length <= 0) {
		alert("Please Mark Type of Pain");
		scrollToInputField($('#humanBodyContainer'));
		return false;
	}
	if (data.patientPainAssessments.ppHowMuchPainInterfereDailyActivity == 'Choose...') {
		alert("Please Choose Value of How Much Pain Interfere Daily Activity");
		scrollToInputField($('#ppHowMuchPainInterfereDailyActivity'));
		return false;
	}
	if (data.patientPainAssessments.ppHowMuchPainInterfereDailyActivity == 'Choose...') {
		alert("Please Choose Value of How Much Pain Interfere Daily Activity");
		scrollToInputField($('#ppHowMuchPainInterfereDailyActivity'));
		return false;
	}
	if (data.patientPainAssessments.ppDiagnosis == 'Choose...') {
		alert("Please Choose Diagnosis Details");
		scrollToInputField($('#ppDiagnosis'));
		return false;
	}
	if (data.patientPainAssessments.ppLadderStep == 'Choose...') {
		alert("Please Choose Value of Ladder Step");
		scrollToInputField($('#ppLadderStep'));
		return false;
	}
	if (data.symptomsMasterList.length <= 0) {
		alert("Please Choose Symptoms Details");
		return false;
	}

	return true;
}
function scrollToInputField(inputId) {
	var targetElement = $(inputId);
	/*$('html, body').scrollTo(targetElement,800);*/
	targetElement.focus()
	$([document.documentElement, document.body]).animate({
		scrollTop: targetElement.offset().top - 2 * ($('header').height())
	}, 0);

}

function showPainAssessment() {
	window.location.href = "#patientPainAssessmentDetailsDiv"
	console.log($('input[name=patientVisitRadio]:checked').val(), "   id")
	var visitId = $('input[name=patientVisitRadio]:checked').val()
	// Create a Date object for the current date
	var currentDate = new Date();

	// Format the date to YYYY-MM-DD
	var formattedDate = currentDate.toISOString().split('T')[0];
	// Set the value of the date input
	$('#ppInvistigationDate').val(formattedDate);

	// Disable the date input
	$('#ppInvistigationDate').prop('disabled', true);
	var settings = {
		"url": "/pain/getAllPainDetails",
		"method": "GET",
		"timeout": 0,
	};

	$.ajax(settings).done(function(response) {
		console.log("pain");
		console.log(response);
		var multiSelect = '<select multiple="" class="form-control"	id="painMasterList">'


		$.each(response, function(index, value) {
			multiSelect += '<option value="' + value.painMasterId + '">' + value.painName + '</option>'
		})
		multiSelect += "</select>"
		$("#painTypeDiv").empty();
		$("#painTypeDiv").append(multiSelect)
	});

	var settings = {
		"url": "/symptom/getAllSymptoms",
		"method": "GET",
		"timeout": 0,
	};

	$.ajax(settings).done(function(response) {
		console.log("symptom");
		console.log(response);
		/*var multiSelect = '<select multiple="" class="form-control"	id="symptomsMasterList">'


		$.each(response, function(index, value) {
			multiSelect += '<option value="' + value.symptomId + '">' + value.symptom + '</option>'
		})
		multiSelect += "</select>"
		$("#symptomsTypeDiv").empty()
		$("#symptomsTypeDiv").append(multiSelect)*/
		var dt = []
		$.each(response, function(index, value) {
			/*multiSelect += '<option value="' + value.symptomId + '">' + value.symptom + '</option>'*/
			dt.push({ id: value.symptomId, text: value.symptom })
		})
		console.log("dt   ", dt);
		$("#symptomsMasterList").select2({ "data": dt, "multiple": true, 'placeholder': 'Choose Symptoms' })
	});
	/*var infoData = $("#painAssessmentButtonId").attr("data-info")
	console.log(infoData)
	$("#ppPatientId").attr("value", infoData)*/
	$("#ppPatientvisitId").attr("value", visitId);
	$("#patientPainAssessmentDetailsDiv").show()
	$("#patientPrescriptionDiv").hide()
	$("#visitDetailsDiv").hide()

}


function showPrescription() {
	window.location.href = "#patientPrescriptionDiv"
	var visitId = $('input[name=patientVisitRadio]:checked').val()
	$("#patientPainAssessmentDetailsDiv").hide()
	$("#patientPrescriptionDiv").show()
	var infoData = $("#addPrescriptioButtonId").attr("data-info")
	$("#showPrescriptionPatientId").attr("value", infoData)
	$("#showPrescriptionPatientvisitId").attr("value", visitId);
	$("#visitDetailsDiv").hide()
	var data = {
		"ppId": visitId
	}


	$.ajax({
		url: `../../medicine/getAllMedicine`,
		method: "GET",
		contentType: "application/json",
		headers: {
			"Authorization": "Bearer " + localStorage.getItem("token")
		},
		success: function(data) {
			console.log("********************medicine")
			console.log(data)
			/*var option = "";
			for (var i = 0, l = data.length; i < l; i++) {
				option = option + "<option value=" + data[i].medicineId + ">" + data[i].medicineName + "</option>"
			}
			$('#dropdownMedicine').empty();
			$('#dropdownMedicine').append(option);*/
			var medicineData = []
			for (var i = 0, l = data.length; i < l; i++) {
				medicineData.push({ id: data[i].medicineId, text: data[i].medicineName })
			}
			$("#dropdownMedicine").select2({ 'data': medicineData, 'multiple': true, 'placeholder': 'Choose Medicines' })
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
}



function caluclateMedicineCount() {
	var daysclaim = $("#daysclaim").val();
	var DaysInterval = $("#DaysInterval").val();
	var MedicineinaDay = $("#MedicineinaDay").val();
	$("#TotalMedicineCount").val(daysclaim * MedicineinaDay / DaysInterval);

}
// add data to the  prescribed datatable datatable
var medicineAndPrescription = [];
$("#addMedicineToPrescriptionTable").click(function() {
	// get medicine data
	// Iterate through the selected options
	var medicineId = []
	var medicineName = []
	$("#dropdownMedicine option:selected").each(function() {
		var option = $(this);
		medicineId.push(option.val());
		medicineName.push(option.text());
	});


	// medicine prescription related details
	var daysclaim = $("#daysclaim").val();
	var DaysInterval = $("#DaysInterval").val();
	var MedicineinaDay = $("#MedicineinaDay").val();
	totalcount = (daysclaim * MedicineinaDay) / DaysInterval;

	// medicine Remark
	var remark = $("#medicineRemark").val();

	var data = ''
	for (var i = 0; i < medicineId.length; i++) {

		// add data to the array which are going to send for saving in db
		medicineAndPrescription.push({
			medicineId: medicineId[i],
			medicineQuantity: totalcount,
			noOfDays: daysclaim,
			daysInterval: DaysInterval,
			medicineInaDays: MedicineinaDay,
			remark: remark
		});

		// create the table 
		data = data + '<tr  title=' + medicineId[i] + '>'
		data = data + "<td><input  type='radio' name='medicineId' value=" + medicineId[i] + "></td>"
		data = data + "<td value=" + medicineId[i] + ">" + medicineName[i] + "</td>"
		data = data + "<td value=" + daysclaim + ">" + daysclaim + "</td>"
		data = data + "<td value=" + DaysInterval + ">" + DaysInterval + "</td>"
		data = data + "<td value=" + MedicineinaDay + ">" + MedicineinaDay + "</td>"
		data = data + "<td value=" + totalcount + ">" + totalcount + "</td>"
		data = data + "<td >" + remark + "</td>"
		data = data + '</tr>'
	}
	// append the data to the datatable
	//   $('#prescribedMedicineDataTable').empty();
	$('#prescribedMedicineDataTable').append(data);
	// show datatable
	console.log(data)
	$('#symptomMedicineMappingId2').show();
	$('#submitPrescription').show();
	
	$('html, body').animate({
        scrollTop: $("#symptomMedicineMappingId2").offset().top
    }, 2000);

	$("#dropdownMedicine").val("");
	$("#dropdownMedicine").trigger("change")
	$("#daysclaim").val("")
	$("#DaysInterval").val("")
	$("#MedicineinaDay").val("")
	$("#TotalMedicineCount").val("")
	$("#medicineRemark").val("")

})
function removeMedicine() {
	var mid = $('input[name="medicineId"]:checked').val();
	// remove the row from data table
	$("#prescribedMedicineDataTable tr[title='" + mid + "']").remove();

	// remove medicine from the list of medicine which will be send  for saving in db
	medicineAndPrescription = y = jQuery.grep(medicineAndPrescription, function(value) {
		console.log(value)
		if (value.medicineId != mid)
			return value;
	});

	console.log("-------------------------------")
	console.log(medicineAndPrescription)
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
					return '<input type="radio" class="form-check-input" name="patientVisitRadio" value="' + row.pvid + '">'
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
		if (painAssessmentStatus !== 'Filled') {
			$("#painAssessmentButtonId").removeAttr("disabled");
			$("#viewPainAssessmentId").hide();
		} else {
			$("#painAssessmentButtonId").attr("disabled", true);
			$("#viewPainAssessmentId").show();
			$('#painAssessmentButtonId').attr('title', 'Aleready Filled');
		}
		if (prescreptionStatus !== 'Filled') {
			$("#addPrescriptioButtonId").removeAttr("disabled");
			$("#viewPrescriptionId").hide();
		} else {
			$("#viewPrescriptionId").show();
			$("#addPrescriptioButtonId").attr("disabled", true);
			$('#addPrescriptioButtonId').attr('title', 'Aleready Filled');
			$("#addPrescriptioButtonId").addClass("hover-text");
		}
		//console.log(secondaryFindings)

	});
}
