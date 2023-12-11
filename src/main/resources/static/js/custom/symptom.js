function addSymptom() {
	var symptom = $("#symtomName").val();
	//alert(symptom)

	var data = {
		"symptom": symptom,
	}
	$.ajax({
		url: '/symptom/add',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		
		success: function(data) {
		
			alert("Symptom added successfully")
			window.location.href="/symptom/add"
			
		},
		error: function() {
			console.log("AJAX call failed");
			$("#symtomName").val('');
		}
	});
	console.log(data);
}

function addSymptomToDropDown() {
	$.ajax({
		url: `../../symptom/getAllSymptoms`,
		method: "GET",
		contentType: "application/json",
		headers: {
			"Authorization": "Bearer " + localStorage.getItem("token")
		},
		success: function(data) {
			console.log("********************symptom")
			console.log(data)
			var option = "";
			for ( var i = 0, l = data.length; i < l; i++ ) {
				option=option+"<option value="+data[i].symptomId+">"+data[i].symptom+"</option>"
			}
			
			$('#dropdownSymptom').empty();
			$('#dropdownSymptom').append(option);
			
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
}


$( document ).ready(function() {
    $.ajax({
		url: `../../symptom/getAllSymptoms`,
		method: "GET",
		contentType: "application/json",
		headers: {
			"Authorization": "Bearer " + localStorage.getItem("token")
		},
		success: function(data) {
			console.log("********************symptom")
			console.log(data)
			var option = "";
			for ( var i = 0, l = data.length; i < l; i++ ) {
				option=option+"<tr>"
				option=option+"<td>"+data[i].symptom+"</td>"
				option=option+"<td>"+data[i].recordTracking+"</td>"
				/*option=option+"<td>"+data[i].symptomStatus+"</td>"*/
				option=option+"</tr>"
			}
			
			$('#symtomTableBody').empty();
			$('#symtomTableBody').append(option);
		    $('#symptom_data_table').DataTable();
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
});