function addMedicine() {
	
	var medicineName = $("#medicineName").val();
	var medicineType = $("#medicineType").val();
	var is3EFormRequired = $("#is3EFormRequired").is(":checked");  ;
	//alert(is3EFormRequired);
    var userCredId=localStorage.getItem("userId");
	var data = {
		"medicineName": medicineName,
		"medicineType": medicineType,
		"is3ERequired":is3EFormRequired,
		"userCredId":userCredId
	}
	$.ajax({
		url: '/medicine/add',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		
		success: function(data) {
			
			alert("medicine added successfully")
			window.location.href="/medicine/add"
		},
		error: function(data) {
			console.log(data);
			alert(data.responseText)
			$("#medicineName").val('');
			$("#medicineType").val('');
		}
	});
	console.log(data);
}



function addMedicineToDropDown() {
	 var userCredId=localStorage.getItem("userId");

	$.ajax({
		url: `../../medicine/getAllMedicine?userCredId=`+userCredId,
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


function mapMedicineWithSymptom() {
	//alert("hi ")
	var symptomId = $('#dropdownSymptom').find(":selected").val();
	//var medicineId = $('#dropdownMedicine').val();
	console.log(medicineId)
	var medicineId = $.map($("#dropdownMedicine").val(), function(str) {
		return parseInt(str, 10); // Specify base 10 for decimal conversion
	});
	console.log(medicineId)
	var data = {
		"symptomId": symptomId,
		"medicineIds": medicineId
	}
	$.ajax({
		url:'/medicine/mapSymptomWithMedicines',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		
		success: function(data) {

			alert("medicine mapped successfully")
             window.location.href="/medicine/mapSymtomWithMedicine"
		},
		error: function() {
			console.log("AJAX call failed");
			alert("some error occured")
		}
	});
	console.log(data);
}


$( document ).ready(function() {
	$('.dropdown-with-search').select2();
	 var userCredId=localStorage.getItem("userId");
    $.ajax({
		url: '/medicine/getAllMedicine?userCredId='+userCredId,
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
				option=option+"<td>"+data[i].medicineName+"</td>"
				option=option+"<td>"+data[i].medicineType+"</td>"
				option=option+"<td>"+data[i].is3ERequired+"</td>"
				option=option+"<td>"+data[i].recordTracking+"</td>"
				option=option+"</tr>"
			}
			
			$('#medicineTableBody').empty();
			$('#medicineTableBody').append(option);
		    $('#medicine_data_table').DataTable();
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
	alert
	// get all symptom medicine mapping and show in table
	 $.ajax({
		url:'/medicine/getAllSymptomsMedicineMapping',
		method: "GET",
		contentType: "application/json",
		success: function(data) {
			console.log("********************symptom*********************ashish")
			console.log(data)
			var option = "";
			for ( var i = 0, l = data.length; i < l; i++ ) {
				option=option+"<tr>"
				option=option+"<td>"+data[i].symptomsMaster.symptom+"</td>"
				option=option+"<td>"+data[i].medicineMaster.medicineName+"</td>"
				option=option+"<td>"+data[i].recordTracking+"</td>"
				option=option+"</tr>"
			}
			$('#symtomMedicineTableBody').empty();
			$('#symtomMedicineTableBody').append(option);
		    $('#symptom_medicine_data_table').DataTable();
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
});



