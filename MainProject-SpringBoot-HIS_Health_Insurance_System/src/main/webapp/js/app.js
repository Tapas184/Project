/**
 * 
 */
$(document).ready(function() {
	$("#emailid").blur(function() {
		$("#dupmail").html("");
		var emailId = $("#emailid").val();

		$.ajax({
			type: "GET",
			url: "validmail?email=" + emailId,
			success: function(res) {
				if (res == 'duplicate') {
					$("#dupmail").html("Email is already registered");
					$("#emailid").focus();
				} //if
			},//function
			error: function() {
				$("#dupmail").html("Problem in data Fetch");
			}
		}); //ajax
	});
});

//Verify conform password
function unlock() {
	$("#error").html("");
	var newpass = $("#newpassword").val();
	var confpass = $("#confpassword").val();
	if (newpass != confpass) {
		$("#error").html("Error: New and Cofirm passwored both should match")
		$("#confpassword").focus();
		return false;
	}
	return true;
}

//check new password and re enter password is equal or not in add new oassword page
$(document).ready(function() {
	$("#confpassword").blur(function() {
		$("#error").html("");
		var newPass = $("#newpassword").val();
		var confirm = $("#confpassword").val();
		if (newPass != confirm) {
			$("#error").html("Error:Both password are not same");
			$("#confpassword").focus();
		}
	});
});