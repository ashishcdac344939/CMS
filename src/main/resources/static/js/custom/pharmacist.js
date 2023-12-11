$(document).ready(function() {
	$('.dropdown-with-search').select2();
	$.ajax({
		url: '/pharmasist/getAllMedicineInventry',
		method: "GET",
		contentType: "application/json",
		success: function(data) {
			console.log("********************symptombbbbbbbbbbbbbbbbbbbbbbbbbbb")
			console.log(data)
			var option = "";
			for (var i = 0, l = data.length; i < l; i++) {
				option = option + "<tr>"
				option = option + "<td>" + data[i].medicineMaster.medicineName + "</td>"
				option = option + "<td>" + data[i].medicineCount + "</td>"
				option = option + "<td>" + data[i].recordTracking + "</td>"
				option = option + "</tr>"
			}

			$('#medicineInventryTable').empty();
			$('#medicineInventryTable').append(option);
			$('#medicine_inventry_table').DataTable();
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
	
	
	
	 var userCredId=localStorage.getItem("userId");

	$.ajax({
		url: `../../medicine/getAllMedicine?userCredId=`+userCredId,
		method: "GET",
		contentType: "application/json",
		success: function(data) {
			console.log("********************medicine")
			console.log(data)
			var option = "";
			for (var i = 0, l = data.length; i < l; i++) {
				option = option + "<option value=" + data[i].medicineId + ">" + data[i].medicineName + "</option>"
			}
            $('#selectMedicineId').empty();
			$('#selectMedicineId').append(option);
			//alert("successfully")
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
	
	
});



function addPharmasist() {
	//alert("clicked")
	var email = $("#email").val()
	var mobile = $("#mobilePrefix").val() + $("#mobile").val()
	var username = $("#username").val()
	var password = $("#password").val()
	var add = ''; /*$("#address").val()*/
	var uniqueId = $("#userUniqueId").val()
	var userDescription = $("#userDescription").val()
	var centerDetailsId = $('#centerDetailsId').val();
	
	var json = JSON.stringify({
		"userId": email,
		"userName": username,
		"passwrord": password,
		"userMobile": mobile,
		"address": add,
		"userUniqueId": uniqueId,
		"userRoleId": 3,// hardcoded value for pharmacist in UserRole table
		"userDescription": userDescription,
		"centerDetailsId": centerDetailsId
	})

  if(validateRegistrationData(json)==false){
	// stop ajax call by returning method
	  return;
    }
	console.log(json)

	var settings = {
		"url": "/pharmasist/add",
		"method": "POST",
		"headers": {
			"Content-Type": "application/json",
		},
		"data": json,
	};

	$.ajax(settings).done(function(response) {
		alert("user registered");
		window.location.href = ""
	}).fail(function(jqXHR, textStatus) {
		//alert(textStatus + " " + jqXHR)
	});

}

function validateRegistrationData(data){
	var data=JSON.parse(data);
	console.log(data)
	if(data.userId==""){
	  alert("Please Enter Email")
      return false;
	}
	if(!validateEmail(data.userId)){
	  alert("Please Enter Valid Email")
      return false;
	}
	if(data.userName==""){
		 alert("Please Enter Name")
       return false;
	}
	if(data.passwrord==""){
		 alert("Please Enter Password")
       return false;
	}
	if(data.userMobile==""){
		 alert("Please Enter Mobile Number")
       return false;
	}
	if(!validateMobile(data.userMobile)){
		 alert("Please Enter Valid Mobile Number")
       return false;
	}
	if(data.userUniqueId==""){
		 alert("Please Enter License OR Registration Number")
       return false;
	}
	return true;
}

function validateMobile(number) {

	if (number.match(/^(\+91|\+91\-|0)?[6789]\d{9}$/)) {
		return true;
	}
	return false;

}

function validateEmail(mail) {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
		return true
	}
	return false
}

function validatePassword(password) {
	if (password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,20}$/)) {
		return true
	}
	return false
}



function updateInventry() {
	var medicineId = $('#selectMedicineId').find(":selected").val();;
	var medicineCount = $('#medicineCountId').val();
	//console.log(medicineId+" "+medicineCount)
	$.ajax({
		url: '/pharmasist/updateMedicineCount',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			"medicineId": medicineId,
			"currentAvailableCount": medicineCount
		}),
		success: function(data) {
			console.log(data)
			alert("medicine count updatated successfully ");
			window.location.href = ""

		},
		error: function() {
			console.log("AJAX call failed");
		}
	});
}

var patientVisitId = 0;
/*function getMedicineByPatientVisitidId() {
	//alert("hi")
}*/

function getMedicineByPatientVisitidId() {
	var prescriptionId = $("#pVisitId").val();
	patientVisitId = prescriptionId;
	//alert(prescriptionId)
	$.ajax({
		url: '/pharmasist/getMedicineByPrescriptonId?pvid=' + prescriptionId,
		method: "GET",
		contentType: "application/json",
		success: function(data1) {
			console.log("########################")
			console.log(data1)

			// creating table for medicine allocation
			var option2 = "";
			var prescriptionData = data1.prescription;
			for (var i = 0, l = prescriptionData.length; i < l; i++) {
				// iterating loop again in medicine and their allocation till date 
				var alloctedmedicine = 0;
				for (var j = 0; j < data1.pmpAndAllocatedMedicineCount.length; j++) {
					if (prescriptionData[i].pmpId == data1.pmpAndAllocatedMedicineCount[j].pmpId)
						alloctedmedicine = data1.pmpAndAllocatedMedicineCount[j].medicineAllocatedCount;
				}

				var allocatedmedicineid = 'medicinecount_' + prescriptionData[i].pmpId;

				option2 = option2 + "<tr>"
				option2 = option2 + "<td>" + prescriptionData[i].medicineName + "</td>"
				option2 = option2 + "<td>" + prescriptionData[i].medicineQuantity + "</td>"
				option2 = option2 + "<td>" + prescriptionData[i].noOfDays + "</td>"
				option2 = option2 + "<td>" + alloctedmedicine + "</td>"
				option2 = option2 + "<td>" + prescriptionData[i].currentAvailableCount + "</td>"
				option2 = option2 + "<td><input type='text' class='form-control medicinecount' placeholder='Enter Medicine Count'  id=" + allocatedmedicineid + " value="+prescriptionData[i].medicineQuantity+"></td > ";
				option2 = option2 + "</tr>"
			}
			console.log(option2)
			$('#medicineAllocateTable').empty();
			$('#medicineAllocateTable').append(option2);
			$('#medicine_allocate_table').DataTable();


			// creating table for visited medical record
			var option = "";
			var data = data1.medicalVisitRecords;
			for (var i = 0, l = data.length; i < l; i++ ) {
		option = option + "<tr>"
		option = option + "<td>" + data[i].userCredId + "</td>"
		option = option + "<td>" + data[i].visitDate + "</td>"
		option = option + "<td><button class='btn btn-info' type='button' data-bs-target='#previousMedicineAllocatedModal' data-bs-toggle='modal' onclick='ViewMedicineAllocatedByMedicalVisitId(" + data[i].pmvrId + ")'>View Medicine Allocated</button></td>"
		option = option + "</tr>"
	}
	//console.log(option)
	$('#medicialVisitHistoryTable').empty();
	$('#medicialVisitHistoryTable').append(option);
	$('#medical_visit_history_table').DataTable();

	$('#preVisitTableDiv').show();
	$('#giveMedicineDiv').show();


  },
   error: function(data) {
	console.log(data)
	alert(data.responseText);
	window.location.href="";
     }
   });
}

function ViewMedicineAllocatedByMedicalVisitId(pmvrId) {
	//alert(pmvrId)
	$.ajax({
		url: '/pharmasist/getMecineAllocatedDetailsByPmvrId?pmvrId=' + pmvrId,
		method: "GET",
		contentType: "application/json",
		success: function(data) {
			console.log(data)
			//alert("medicine count updatated successfully ");
			/*$('#MedicineDetailsModelBody').empty();
			$('#MedicineDetailsModelBody').append("");*/

			var option = "";
			for (var i = 0, l = data.length; i < l; i++) {
				option = option + "<tr>"
				option = option + "<td>" + data[i].medicineName + "</td>"
				option = option + "<td>" + data[i].medicineAllocatedCount + "</td>"
				option = option + "</tr>"
			}
			//console.log(option)
			$('#matable').empty();
			$('#matable').append(option);
		},
		error: function() {
			console.log("AJAX call failed");
		}
	});
}

function saveMedicineAlloctedToPatient() {
	//alert("saved")
	// get data from the medicine 
	var pvddId = patientVisitId;
	var pharmasistCredId = localStorage.getItem("userId");
	console.log(pvddId + " " + pharmasistCredId);
   
    var pmpANDCount=[];
	var inputfields = $(".medicinecount");
	console.log(inputfields)
	inputfields.each(function() {
		var pmpAndcount={};
		var id=$(this).attr("id").split('_')[1];
		//alert(id)
		pmpAndcount.patientMedicinePrescriptionId=id;
		pmpAndcount.allocatedMedicine=$(this).val();
		pmpANDCount.push(pmpAndcount);
	});
    console.log(pmpANDCount);
	
	
	$.ajax({
		url: '/pharmasist/saveAllocatedMedicine',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			"pvddId":pvddId,
			"pharmasistCredId":pharmasistCredId,
			"pmpANDCount":pmpANDCount	
		}),
		success: function(data) {
			console.log(data)
			alert("medicine count updatated successfully ");
			window.location.href = ""
		},
		error: function() {
			console.log("AJAX call failed");
		}
	});
	
}