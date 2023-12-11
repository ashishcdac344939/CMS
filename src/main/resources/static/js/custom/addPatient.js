$(document).ready(function() {
	$('.dropdown-with-search').select2();

	$("input[name='maritalStatus']").change(function() {
		if ($('#maritalStatusO').is(":checked")) {
			console.log($(this).attr('id'))
			$("#maritalStatusOther").show()
		} else {
			$("#maritalStatusOther").hide()
		}
	})
	$("input[name='housingStatus']").change(function() {
		if ($('#housingStatusO').is(":checked")) {
			console.log($(this).attr('id'))
			$("#otherInputHousingStatus").show()
		} else {
			$("#otherInputHousingStatus").hide()
		}
	})
	$("input[name='langKnown']").change(function() {
		if ($('#langOther').is(":checked")) {
			console.log($(this).attr('id'))
			$("#otherInputLanguage").show()
		} else {
			$("#otherInputLanguage").hide()
		}
	})
	$("input[name='langKnown']").change(function() {
		if ($('#langOther').is(":checked")) {
			console.log($(this).attr('id'))
			$("#otherInputLanguage").show()
		} else {
			$("#otherInputLanguage").hide()
		}
	})
	$("#occupationOfPatient").change(function() {
		if ($(this).val() === "others") {
			console.log($(this).attr('id'))
			$("#otherInputOccupation").show()
		} else {
			$("#otherInputOccupation").hide()
		}
	})
	$("#eduQualification").change(function() {
		if ($(this).val() === "others") {
			console.log($(this).attr('id'))
			$("#otherInputEducation").show()
		} else {
			$("#otherInputEducation").hide()
		}
	})
	$("#familyHeadEdu").change(function() {
		if ($(this).val() === "others") {
			console.log($(this).attr('id'))
			$("#familyHeadEduOthers").show()
		} else {
			$("#familyHeadEduOthers").hide()
		}
	})
	$("#familyHeadOc").change(function() {
		if ($(this).val() === "others") {
			console.log($(this).attr('id'))
			$("#otherInputFamilyHeadOcc").show()
		} else {
			$("#otherInputFamilyHeadOcc").hide()
		}
	})
	$("input[name='treatmentRecieved']").change(function() {
		if ($('#treatmentReceivedOther').is(":checked")) {
			console.log($(this).attr('id'))
			$("#otherInputTreatmentReceived").show()
		} else {
			$("#otherInputTreatmentReceived").hide()
		}
	})
})

function addPatientDetails() {
	var docId = localStorage.getItem("userId");

	var centerDetailsId = $('#centerDetailsId').val();
	var opdipd = $('input[name="IPDOROPD"]:checked').val();
	var patientRegNo = $('#patientRegNo').val();
	pname = $("#patientName").val()
	userId = $("#userId").val()
	password = $("#passwrord").val()
	mobile = $("#userMobile").val()
	//alMobile = $("#alternateMobile").val()
	uniqueId = $("#userUniqueId").val()
	gender = $("input[name='gender']:checked").val()
	dob = $("#dob").val()
	// education qualification 
	eduQualification = $("#eduQualification").val()
	if (eduQualification == 'others') {
		eduQualification = $("#otherInputEducation_1").val()
	}
	//occupation of patient 
	occupationOfPatient = $("#occupationOfPatient").val()
	if (occupationOfPatient == 'others') {
		occupationOfPatient = $("#occupationOtherInput").val()
	}

	/*socialEcoStatus = $("#socialEcoStatus").val()*/
	var maritalStatus = $('input[name="maritalStatus"]:checked').val();
	if (maritalStatus == 'others') {
		maritalStatus = $("#maritalStatusOtherInput").val()
	}

	var languageknown = [];
	$("#languageKnown input:checkbox:checked").map(function() {
		if ($(this).val() != 'others')
			languageknown.push($(this).val());
		else
			languageknown.push($("#languageOtherInput").val());
	});
	var housingStatus = $('input[name="housingStatus"]:checked').val();
	if (housingStatus == 'others') {
		housingStatus = $("#housingStatusOtherInput").val()
	}
	//var 
	PrimaryCareGiver = $("#careGiver").val()
	PrimaryCareGiverRelation = $("#careGiverRelation").val()
	noOfSons = $("#sons").val()
	noOfDaughter = $("#daughter").val()
	//alert(noOfDaughter)
	noOfDependentChildren = $("#dependentChildren").val()
	noOfMarriedDaughter = $("#marriedDaughter").val()
	earningMembName = $("#earningMemb").val()

	familyHeadEducation = $("#familyHeadEdu").val()
	if (familyHeadEducation == 'others') {
		familyHeadEducation = $("#familyHeadEduOtherInput").val()
	}
	familyHeadOccupation = $("#familyHeadOc").val()
	if (familyHeadOccupation == 'others') {
		familyHeadOccupation = $("#familyHeadOccOtherInput").val()
	}
	familyHeadIncome = $("#familyHeadInc").val()

	address = $("#address").val()
	distToNCI = $("#distToNCI").val()
	stateId = $("#stateDetails").val();
	districtId = $("#district").val();
	modeOfTrans = $("input[name='modeOfTrans']:checked").val()
	//alert(stateId +" "+ districtId )
	cancerDiagnose = $("#cancerDiagnose").val()
	stateOfCancer = $("#stageOfCancer").val();
	currentDiagnosis = $("#pdCurrentDiagnosis").val();
	var treatmentReceived = [];
	$("#treatmentReceivedId input:checkbox:checked").map(function() {
		if ($(this).val() != 'others')
			treatmentReceived.push($(this).val());
		else
			treatmentReceived.push($("#treatmentReceivedOtherInput").val());
	});
	var treatmentDetails = $("#pdTreatmentDetails").val();
	patientDetails = JSON.stringify({
		"userModel": {
			"userName": pname,
			"userId": userId,
			"passwrord": password,
			"userMobile": mobile,
			"userUniqueId": uniqueId,
			"address": address,
			"userRoleId": 2,
			"userDescription": null,
			"centerDetailsId": centerDetailsId
		},
		"docId": docId,
		"opdipd": opdipd,
		"patientRegNo": patientRegNo,
		/*"pCurrentSocioEconomicStatus": socialEcoStatus,*/
		"pDaughter": noOfDaughter,
		"pGender": gender,
		"pDob": dob,
		"pEduQualification": eduQualification,
		"occupationOfPatient": occupationOfPatient,
		"maritalStatus": maritalStatus,
		"languageknown": languageknown,
		"housingStatus": housingStatus,
		"familyHeadEducation": familyHeadEducation,
		"familyHeadOccupation": familyHeadOccupation,
		"familyHeadIncome": familyHeadIncome,
		"stateOfCancer": stateOfCancer,
		"pPrimaryCareGiver": PrimaryCareGiver,
		"currentDiagnosis": currentDiagnosis,
		"treatmentReceived": treatmentReceived,
		"treatmentDetails": treatmentDetails,
		"primaryCareGiverRelationWithPatient": PrimaryCareGiverRelation,
		"pFamilyEarningMember": earningMembName,
		"pSons": noOfSons,
		"pDependentChildren": noOfDependentChildren,
		"pMarrigebleDaughters": noOfMarriedDaughter,
		"pDistanceToNciInkm": distToNCI,
		"pModeOfTransport": modeOfTrans,
		"pWhenCancerDiagnosed": cancerDiagnose,
		"stateId": stateId,
		"districtId": districtId
	});
	console.log("****************************************************************")
	console.log(patientDetails)

	var validateResult = validate(patientDetails);
	if (validateResult == false) {
		return;
	}
	var settings = {
		"url": "/patient/add",
		"method": "POST",
		contentType: "application/json",
		"timeout": 0,
		"data": patientDetails,
	};
	
		$.ajax(settings).done(function(response) {
			//alert(response);
			$("#success-modal-text").html(response)
			$("#success-modal-btn").attr('href','/patient/add')
			 $("#success-modal-btn").removeAttr('data-bs-dismiss','modal');
				$("#success-modal").modal('show')
			//window.location.href = "/patient/add"
	
		}).fail(function() {
			$("#error-modal-text").html("Something went wrong")
			$("#error-modal-btn").attr('href','/patient/add')
			 $("#error-modal-btn").removeAttr('data-bs-dismiss','modal');
				$("#error-modal").modal('show')
			console.log("error in registration")
		});
}



function validate(data) {
	var data = JSON.parse(patientDetails);
	console.log(data)
	
   // changed 
	if ($('input[name="IPDOROPD"]:checked').length <= 0) {
		alert("Select OPD/IPD option of Paient the Patient");
		scrollToInputField($('input[name="IPDOROPD"]'));
		return false;
	}
	if (data.patientRegNo=="") {
		alert("Patient Registration number can not be empty");
		scrollToInputField($('#patientRegNo'));
		return false;
	}
	
	
	if (data.userModel.userName == "") {
		alert("Name can not be empty");
		scrollToInputField($("#patientName"));
		return false;
	}
	if (data.userModel.userId == "") {
		alert("userId/Email can not be empty");
		scrollToInputField($("#userId"));
		return false;
	}
	if (data.userModel.passwrord == "") {
		alert("Passwrord can not be empty");
		scrollToInputField($("#passwrord"));
		return false;
	}
	if (data.userModel.userMobile == "") {
		alert("UserMobile can not be empty");
		scrollToInputField($("#userMobile"));
		return false;
	}
	if (data.userModel.userUniqueId == "") {
		alert("Adhar Number can not be empty");
		scrollToInputField($("#userUniqueId"));
		return false;
	}
	
	
	// changed
	if (data.pGender == "") {
		alert("Select Gender of the Patient");
		scrollToInputField($("input[name='gender']"));
		return false;
	}
	if (data.pDob == "") {
		alert("Select Patient Date Of Birth");
		scrollToInputField($("#address"));
		return false;
	}
	if (data.pEduQualification == "Choose...") {
		alert("Select Patient Education qualification");
		scrollToInputField($("#eduQualification"));
		return false;
	}
	if (data.occupationOfPatient == "Choose...") {
		alert(" Please Select Patient Occupation ");
		scrollToInputField($("#occupationOfPatient"));
		return false;
	}
	if (data.maritalStatus == "") {
		alert("Please Select Marital status of the Patient");
		scrollToInputField($('input[name="maritalStatus"]'));
		return false;
	}
	if (data.languageknown.length <=0) {
		alert("Please Select language known by  the Patient");
		scrollToInputField($("#languageKnown"));
		return false;
	}
	if (data.housingStatus == "") {
		alert("Please Select housing status of the Patient");
		scrollToInputField($('input[name="housingStatus"]'));
		return false;
	}
	if (data.pPrimaryCareGiver == "") {
		alert("Please Enter primary CareGiver Name  of the Patient");
		scrollToInputField($("#careGiver"));
		return false;
	}
	if (data.PrimaryCareGiverRelation == "") {
		alert("Please Enter primary CareGiver Relation With Patient");
		scrollToInputField($("#careGiverRelation"));
		return false;
	}
	if (data.pSons == "") {
		alert("Please Enter No Of Son of Patient");
		scrollToInputField($("#sons"));
		return false;
	}
	if (data.pDaughter == "") {
		alert("Please Enter No Of Daughter of Patient");
		scrollToInputField($("#daughter"));
		return false;
	}
	if (data.pDependentChildren == "") {
		alert("Please Enter No Of Dependent Children of Patient");
		scrollToInputField($("#dependentChildren"));
		return false;
	}
	if (data.earningMembName == "") {
		alert("Please Enter Earning Member Name");
		scrollToInputField($("#earningMemb"));
		return false;
	}
	if (data.familyHeadEducation == "Choose...") {
		alert("Select Education qualification of Head of Family");
		scrollToInputField($("#familyHeadEdu"));
		return false;
	}
	if (data.familyHeadOccupation == "Choose...") {
		alert("Select Occupation of Head of Family");
		scrollToInputField($("#familyHeadOc"));
		return false;
	}
	if (data.familyHeadIncome == "Choose...") {
		alert("Select Earning  of Head of Family");
		scrollToInputField($("#familyHeadInc"));
		return false;
	}
	if (data.userModel.address == "") {
		alert("Address  can not be empty");
		scrollToInputField($("#address"));
		return false;
	}
	if (data.pDistanceToNciInkm == "Choose...") {
		alert("Enter Distance of Home From NCI/IRCH");
		scrollToInputField($("#distToNCI"));
		return false;
	}
	if (data.stateId == "Choose...") {
		alert("Select State of Patient");
		scrollToInputField($("#stateDetails"));
		return false;
	}
	if (data.districtId == "Choose...") {
		alert("Select City of Patient");
		scrollToInputField($("#district"));
		return false;
	}
	if (data.pModeOfTransport == "") {
		alert("Select Mode  of Transport the Patient");
		scrollToInputField($("input[name='modeOfTrans']"));
		return false;
	}
	if (data.pWhenCancerDiagnosed == "") {
		alert("Select Patient Date Of Cancer Diagnosis");
		scrollToInputField($("#cancerDiagnose"));
		return false;
	}
	if (data.stateOfCancer == "Choose...") {
		alert("Select stage  of Cancer");
		scrollToInputField($("#stageOfCancer"));
		return false;
	}
	if (data.currentDiagnosis == "") {
		alert("Please Enter Current Diagnosis ");
		scrollToInputField($("#pdCurrentDiagnosis"));
		return false;
	}
	if (data.treatmentReceived.length <=0) {
		alert("Please Select Treatment Received  by  the Patient");
		scrollToInputField($("#treatmentReceivedId"));
		return false;
	}


	return true;
}


function scrollToInputField(inputId) {
	var targetElement = $(inputId);
	/*$('html, body').scrollTo(targetElement,800);*/
	targetElement.focus()
	$([document.documentElement, document.body]).animate({
        scrollTop: targetElement.offset().top - 2*($('header').height())
    }, 0);

}