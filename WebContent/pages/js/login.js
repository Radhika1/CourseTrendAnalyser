//http://stackoverflow.com/questions/18892484/show-login-after-user-logsout-and-press-back-button
//$(document).ready(function() {
//	// $('.tabs').hide();
//
//
//	$('#btnLogout').hide();
//	$('.loginButton').hover(function() {
//		$(this).addClass('hover');
//	}, function() {
//		$(this).removeClass('hover');
//	});
//
//	$("#txtPassword").keyup(function(e) {
//		if (e.keyCode == 13)
//			doLogin();
//	});
//	eraseCookie('username');
//});

$(document).ready(function() {
	eraseCookie('username');
});

function validateForm() {
	$.ajax({
		type : "POST",
		data : $("#loginForm").serialize(),
		url : "/CTA/Login?OPERATION_TYPE=CHECK_LOGIN",
		dataType : 'json',
		success : fnLoginCompleted,
		error : fnLoginFailed
	});
}

function doReset() {
	$("#loginFailedMessage").remove();
}

function fnLoginCompleted(servletResponse) {
	var json = servletResponse;
	var status = json.LOGIN_RESPONSE.response.IS_AUTHENTIC_USER;
	if (status == JSON.parse(true)) {
		createCookie('username', $('#uname').val(), 1);
		console.log('Here!!')
		window.location.href = 'professorHome.jsp';
		
	} else {
		fnLoginFailed();
	}
}

function fnLoginFailed(response) {
	$.unblockUI();
	$('#loginForm').before(
			"<span id='loginFailedMessage' style='color: #ff0000;' >Invalid username/password</span>");
	$('#loginFailedMessage').blink({
		delay : 500
	});
}


function createCookie(name, value, days) {
	var expires = "";
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toGMTString();
	} else
		expires = "";
	document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for ( var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name, "", -1);
}

function fnDataFailure(response) {
	alert(response);
}