/**
 * 
 */
function changePassword(){
	var oldPwd =$("#oldpwd").val();
	var newPwd = $("#newPwd").val();
	var retypeNewPwd = $("#retypeNewPwd").val();
	var data ={
		"oldPwd":oldPwd,
		"newPwd":newPwd,
		"retypeNewPwd":retypeNewPwd
	};
	console.log(newPwd +" "+retypeNewPwd )
	if(newPwd === retypeNewPwd){
		$.ajax({
		url: '/password/changePassword', 
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(data) {
			console.log(data)
			alert(data.message)
			if(data.status===true){
			window.location.href = "/user/home"
			}
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
}
		
	else{
		alert("New Password and Retype Password must be same")
	}
}

function resetPassword(){
	
	var email = $("#emailReset").val();
	alert(email)
	$.ajax({
		url: '/password/forgot_password?'+'email='+email,
		method: "POST",
		contentType: "application/json",
		success: function(data) {
			console.log(data)
			alert(data)
			window.location.href = "/user/home"
			
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
}
function resetPasswordProcessing(){
	var pass1 = $("#passwordReset").val();
	var pass2 = $("#retypeResetpassword").val();
	var token = $("#tokenHidden").val();
	var data ={
		"newPwd":pass1,
		"retypeNewPwd":pass2,
		"token":token
	};
	if(pass1 === pass2){
		$.ajax({
		url: '/password/reset_password',
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(data) {
			alert(data)
			window.location.href = "/user/home"
			
		},
		error: function() {
			console.log("AJAX call failed");

		}
	});
}}