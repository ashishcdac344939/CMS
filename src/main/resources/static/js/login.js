$(document).ready(function() {
	$("#unameDiv").hide()
	$("#emailDiv").hide()
	$("#getOtpBtn").hide()
	$("#passwordDiv").hide()
	$("#otpDiv").hide()
	$("#loginOtpBtn").hide()
	$("#loginPassBtn").hide()
})

$("#btnOtp").click(function() {
	$("#emailDiv").show()
	$("#getOtpBtn").show()
	$(".otpPassBtn").hide()
})

$("#getOtpBtn").click(function() {

	var uname = $("#email").val()

	if (validateEmail(uname)) {
		$("#otpDiv").show()
		$("#loginOtpBtn").show()
		getOtp(uname)
		/*loginCall2(uname, null, otp)*/
	} else
		alert("Enter valid Email")

})

$("#btnPassword").click(function() {
	$(".otpPassBtn").hide()
	$("#unameDiv").show()
	$("#passwordDiv").show()
	$("#loginPassBtn").show()
})
// Add a click event handler to your form's submit button using jQuery
$("#loginPassBtn").on("click", function(event) {
    // Prevent the default form submission behavior, which would reload the page
	// Get the current value of the password field
	var passwordField = $("#password");
	var currentPassword;
	// Add your prefix to the password
	var userName = $("#username").val();
	if (userName == "") {
		alert("Please Enter the UserName")
		return;
	}else if(!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(userName))) {
		alert("Please Enter the Valid UserName")
		return;
	}
	if (passwordField.val() != "") {
		var prefix = "p";
		currentPassword = passwordField.val();
	} else {
		// password is not field so alert  to insert the password and return from submission
		alert("Please Enter the Password")
		return;
	}
	var newPassword = prefix + currentPassword;

	// Update the password field with the modified value
	passwordField.val(newPassword);

	// Perform any additional logic you need here
	document.getElementById('loginForm').submit();// Manually submit the form
});
$("#loginOtpBtn").on("click", function(event) {
    // Prevent the default form submission behavior, which would reload the page
    // Get the current value of the password field
    var otp = $("#otp1").val() + $("#otp2").val() + $("#otp3").val() + $("#otp4").val();
    var passwordField = $("#otpPassword");
     var prefix = "o";
    var currentPassword=otp;
    var newPassword = prefix + currentPassword;

    // Update the password field with the modified value
    passwordField.val(newPassword);

    // Perform any additional logic you need here

    // Submit the form programmatically
    console.log(newPassword)
});

/*$("#loginPassBtn").click(function() {
	var uname = $("#username").val()
	var password = $("#password").val()
	loginCall(uname, password, null)
})*/


/*$("#loginOtpBtn").click(function() {
	var uname = $("#email").val()
	var otp = $("#otp1").val() + $("#otp2").val() + $("#otp3").val() + $("#otp4").val()
	loginCall(uname, null, otp)
})*/

function getOtp(userMail) {
	var settings = {
		"url": "/login/getOtp",
		"method": "POST",
		"headers": {
			"Content-Type": "application/json"
		},
		"data": JSON.stringify({
			"userId": userMail
		}),
	};
	$.ajax(settings).done(function(response) {
		alert(response.message)
	}).fail(function(response) {
		alert("Otp not sent ",response)
	})
}

function loginCall(username, password, otp) {
	alert(password+" "+ username)
	console.log("username: "+ username)
	console.log("password: "+ password)
	var loginData;
	if (otp === null) {
		loginData = JSON.stringify({
			"userId": username,
			"passwrord": "p"+password,
		})
	} else {
		loginData = JSON.stringify({
			"username": username,
			"password": "o"+otp
		})
	}
	var settings = {
		"url": "/submitLoginForm",
		"method": "POST",
		"headers": {
			"Content-Type": "application/json"
		},
		"data": loginData,
	};

	$.ajax(settings).done(function(res) {

		$.ajax({

			type: 'POST',
			contentType: 'application/json;charset=utf-8',
			url: '../doctor/index',
			data: JSON.stringify(res),
			beforeSend: function(xhr) {
				xhr.setRequestHeader('Authorization', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdWx2YW50c2luZ2g3NTdAZ21haWwuY29tIiwiZXhwIjoxNjkxODIxNjI4fQ.AOxSsQfS5zkYeNhyl3b76n1EidMDJ-dw8kdrfYo05PsrWjE6vCn34qXrfvn5e1dR_mGtgL5zsIq6XxeC3ltlUw');
			},

		}).done(function(data, jqXHR) {
			console.log(data)
			var w = window.open();
			$(w.document.body).html(data);
			//window.location.href=data;

		}).fail(function(jqXHR, textStatus, errorThrown) {


		});

		alert("login success");

	}).fail(function() {
		alert("login fail");
	});
}


/* for otp */
/*var container = document.getElementsByClassName("otp-input-group")[0];
container.onkeyup = function(e) {
	var target = e.srcElement || e.target;
	var maxLength = parseInt(target.attributes["maxlength"].value, 10);
	var myLength = target.value.length;
	if (myLength >= maxLength) {
		var next = target;
		while (next = next.nextElementSibling) {
			if (next == null)
				break;
			if (next.tagName.toLowerCase() === "input") {
				next.focus();
				break;
			}
		}
	}
	// Move to previous field if empty (user pressed backspace)
	else if (myLength === 0) {
		var previous = target;
		while (previous = previous.previousElementSibling) {
			if (previous == null)
				break;
			if (previous.tagName.toLowerCase() === "input") {
				previous.focus();
				break;
			}
		}
	}
}
*/


$('.otp-input-group input:first').keydown(function(event) {
	console.log("adfgjash")
	// check for hyphen
	if ($(this).val() === 1) {
		console.log("adfgjash11231231")
		event.preventDefault();
		$(this).next('.otp-input-group input').focus();
	}
});
/**/


function validateEmail(mail) {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
		return true
	}
	return false
}