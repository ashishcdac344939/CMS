function searchPatientForESA() {
	var searchValue = $("#searchPatientByUserId").val();
	$.ajax({
		url: '/patient/getESAByPatientcredId/' + searchValue,
		method: "GET",
		contentType: "application/json",
		success: function(data) {
			console.log("********************esasymptom")
			console.log(data)
			//var option = "";
			
			/*if(data.length==0){
				alert("User does not Exist")
				return;
			}*/
			InitiateDataTable(data)
			/*for (var i = 1, l = data.length; i <= l; i++) {
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
			$('.esaVisitDetailsTable').show();
			if ($.fn.DataTable.isDataTable('#esaTable')) {
				$('#esaTable').DataTable().destroy();
			}*/

			// set the serched emailid to localstorage for further use
			localStorage.setItem("patientUserId", searchValue);

			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
}
function InitiateDataTable(data) {
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
	$('.esaVisitDetailsTable').show();
	if ($.fn.DataTable.isDataTable('#esaTable')) {
		$('#esaTable').DataTable().destroy();
	}
}
var circles = [];
function esaDetails() {

	$("#esaId").show();
	const formHeader = document.getElementById('esaDiv');

	if (formHeader) {
		formHeader.scrollIntoView({ behavior: 'smooth' });
	}
}

function viewESAStatistics() {

	let pcredId = localStorage.getItem("patientCredId");
	let patientId = localStorage.getItem("patientId");
	let patientUserId = localStorage.getItem("patientUserId");
	// Redirect to the specified URL
	//pCredId="+pcredId+"&patientId="+patientId+"&
	window.location.href = "/patient/getESAMapDataByPatientCredId?patientEmailId=" + patientUserId;
}


function showMapOfESAStatistics(painDates, painValueList, symptom, completedBy) {
	/* Extract the painData from the Model */
	console.log(painValueList)

	if (painDates != null && painDates.length > 0) {

		// Create the Plotly trace
		// Initialize arrays to store line and marker colors
		var lineColors = [];
		var markerColors = [];

		// Create the Plotly trace
		for (var i = 0; i < painValueList.length; i++) {
			var color = 'black'; // Default color
			if (i > 0) {
				// Compare the current value to the previous value
				if (painValueList[i] < painValueList[i - 1]) {
					color = 'green'; // Set color to green for decreasing values
				} else if (painValueList[i] > painValueList[i - 1]) {
					color = 'red'; // Set color to red for increasing values
				}
			}

			lineColors.push(color);
			markerColors.push(color);
		}


		var trace = {
			x: painDates,
			y: painValueList,
			mode: 'lines+markers',
			name: 'Pain',
			text: completedBy, // Annotate data points with "Completed By"
			hoverinfo: 'x+y+text', // Display x, y, and text when hovering over data points
			line: {
				color: lineColors, // Set line colors based on the array
			},
			marker: {
				color: markerColors, // Set marker colors based on the array
			},

		};

		// Create the layout
		var layout = {
			title: `${symptom} Over Time`,
			xaxis: {
				title: 'Date',
				tickformat: '%Y-%m-%d %H',
				type: 'category', // Treat x-axis values as categories
				tickangle: -30,// Format the date as 'YYYY-MM-DD'
			},
			yaxis: {
				title: `${symptom} Value`,
			},
			// Enable the responsive property
			responsive: true,
		};

		// Create the chart
		Plotly.newPlot(`${symptom}` + '-chart', [trace], layout);
	}
}


function SubmitESAFORM() {
	const patientCredId = localStorage.getItem('patientUserId');
	const patientEdmontonSymptomAssessments = {}; // Object to store selected values

	// Loop through the radio buttons by their name attribute
	const symptomArray = ['pain', 'tiredness', 'nausea', 'depression', 'anxiety', 'drowsiness', 'appetite', 'wellbeing', 'sob', 'others'];

	/*for (var i = 0; i < symptomArray.length; i++) {
		const symptomName = symptomArray[i];
		const selectedValue = $("input[name='" + symptomName + "']:checked").val();
		if(selectedValue==undefined){
			alert("Please Select value for all the Symptoms ")
			return;
		}
		patientEdmontonSymptomAssessments[symptomName] = selectedValue;
	}*/
	
	patientEdmontonSymptomAssessments[symptomArray[0]] = $("#Pain1").val();
	patientEdmontonSymptomAssessments[symptomArray[1]] = $("#Tiredness").val();
	patientEdmontonSymptomAssessments[symptomArray[2]] = $("#Nausea").val();
	patientEdmontonSymptomAssessments[symptomArray[3]] = $("#Depression").val();
	patientEdmontonSymptomAssessments[symptomArray[4]] = $("#Anxiety").val();
	patientEdmontonSymptomAssessments[symptomArray[5]] = $("#Drowsiness").val();
	patientEdmontonSymptomAssessments[symptomArray[6]] = $("#Appetite").val();
	patientEdmontonSymptomAssessments[symptomArray[7]] = $("#Wellbeing").val();
	patientEdmontonSymptomAssessments[symptomArray[8]] = $("#SOB").val();
	patientEdmontonSymptomAssessments[symptomArray[9]] = $("#others1").val();
	
	console.log("patientEdmontonSymptomAssessments ",patientEdmontonSymptomAssessments)
	
    var givenby=$("#completedBy").val();
    if(givenby=="Choose..." ){
		alert("Given by can not be empty")
		return ;
	}
    if(circles.length==0){
		alert("Please mark location of Pain")
		return ;
	}
	patientEdmontonSymptomAssessments["completedBy"] = givenby;

	var data = {
		"patientEdmontonSymptomAssessments": patientEdmontonSymptomAssessments,
		"circleData": circles,
		"patientUserId": patientCredId,
	};

	console.log(data)
	// Log or use the selected values as needed
	//console.log(data);
	// save the value to backend
	$.ajax({
		url: '/patient/addEdmSymptomAssessment',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),

		success: function(data) {

			alert("Edm Symptom added successfully")

			$.ajax({
				url: '/patient/getESAByPatientcredId/' + patientCredId,
				method: "GET",
				contentType: "application/json",
				success: function(data) {
					console.log("********************esasymptom")
					console.log(data)
					InitiateDataTable(data)
					$('#esaId').hide();

					// Loop through the radio buttons by their name attribute
					const symptomArray = ['pain', 'tiredness', 'nausea', 'depression', 'anxiety', 'drowsiness', 'appetite', 'wellbeing', 'sob', 'others'];

					patientEdmontonSymptomAssessments[symptomName] = null;
					$("input[type='range']").val(0)
					resetCircleOfImageESAS();
					$("#completedBy option").prop("selected", function () {
              // return defaultSelected property of the option
              return this.defaultSelected;
          });
					patientEdmontonSymptomAssessments["completedBy"] =''
				},
				error: function() {
					console.log("AJAX call failed");

				}
			});

		},
		error: function() {
			console.log("AJAX call failed");
			$("#symtomName").val('');
		}
	});


}



function getEsaByEsaId(esaid) {
	// Counter for the serial number
	let serialNumber = 1;
	let symptomsLabel = [];
	let symptomValue = [];
	$("#ESAModal").modal('show')
	$.ajax({
		url: "/patient/getESADataByESAId?esaId=" + esaid,
		method: "GET",
		success: function(data) {
			console.log(data)
			// Clear existing table rows, if any
			$("#eSAModalViewTable tbody").empty();
			createCirclesOnESAImage(data.bodyCirclePatientESAMappings)
			// Iterate over the properties of the data object to add rows for each key-value pair
			for (const key in data) {
				if (data.hasOwnProperty(key) && key !== "id" && key !== "bodyCirclePatientESAMappings" && key !== "recordTracking" && key !== "completedBy" && key !== "others_remark") {
					const value = data[key];
					symptomsLabel.push(key)
					symptomValue.push(value);
					/* if(value===null) 
					 addTableRow(serialNumber,key, "NA");
					 else{
						 addTableRow(serialNumber,key,value);
					 }
					 serialNumber++; */
				}
			}
			console.log(symptomsLabel);
			console.log(symptomValue);
			generateESAMap(symptomsLabel, symptomValue)
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
}

document.addEventListener('DOMContentLoaded', function() {
	$("input[type='range']").val(0)
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

function resetCircleOfImage() {
	
	// Remove all 'painCircle' elements from the humanBodyContainer
	const painCircles = document.querySelectorAll('.painCircle');
	for (const circle of painCircles) {
		humanBodyContainer.removeChild(circle);
	}

	// Clear the circles array
	circles = [];

}

