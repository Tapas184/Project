/**
 * 
 */
//Mail check valid or not
$(document).ready(function() {
	$("#emailid").blur(function() {
		$("#maildup").html("");
		var emailId = $("#emailid").val();

		$.ajax({
			type: "GET",
			url: "/registration/validmail?email=" + emailId,
			success: function(res) {
				if (res == 'unique') {
					$("#maildup").html("Invalid mail mailid");
					$("#emailid").focus();
				} //if
			},//function
			error: function(res) {
				if (res == 'duplicate')
					$("#maildup").html("Valid mail id");
			}
		}); //ajax
	});
});