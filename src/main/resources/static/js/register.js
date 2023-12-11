$("#submitBtn").click(function() {

	var email = $("#email").val()
	var mobile = $("#mobilePrefix").val() + $("#mobile").val()
	var username = $("#username").val()
	var password = $("#password").val()
	var add = ''; /*$("#address").val()*/
	var uniqueId = $("#userUniqueId").val()
	var userDescription = $("#userDescription").val()
	var centerDetailsId = $('#centerDetailsId').val();
	// alert(centerDetailsId)
	
	var json = JSON.stringify({
		"userId": email,
		"userName": username,
		"passwrord": password,
		"userMobile": mobile,
		"address": add,
		"userUniqueId": uniqueId,
		"userRoleId": 1,
		"userDescription": userDescription,
		"centerDetailsId": centerDetailsId
	})
	console.log(json)
	
   if(validateRegistrationData(json)==false){
	// stop ajax call by returning method
	  return;
    }

	var settings = {
		"url": "../../doctorForRest/register",
		"method": "POST",
		"headers": {
			"Content-Type": "application/json", 
		},
		"data": json,
	};
	
	$.ajax(settings).done(function(response) {
		alert("User Registered Successfully");
		window.location.href='/login/loginForm';
	}).fail(function (jqXHR, textStatus) {
   alert(textStatus+" "+jqXHR)
});

}) 	

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
		//alert("correct email id")
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