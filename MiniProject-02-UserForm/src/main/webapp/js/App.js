//For checking the mail valid or not
$(document).ready(function() {
	$("#email").blur(function() {
		$("#dupmail").html("");
		var emailId = $("#email").val();
		$.ajax({
			type: "GET",
			url: "validmail?email=" + emailId,
			success: function(res) {
				if (res == 'duplicate') {
					$("#dupmail").html("Email is already registered");
					$("#email").focus();
				} //if
			},//function
			error: function() {
				$("#dupmail").html("Problem in data Fetch");
			}
		}); //ajax
	});
});
// Pupuleting dependent dropdown
$(document).ready(
	function() {
		$("#countryid").change(
			function() {
				$("#stateid").find('option').remove();
				$('<option>').val('').text('-Select-').appendTo(
					"#stateid");
				$("#cityid").find('option').remove();
				$('<option>').val('').text('-Select-').appendTo(
					"#cityid");
				var couId = $("#countryid").val();
				$.ajax({
					type: "GET",
					url: "getstate?cid=" + couId,
					success: function(data) {
						$.each(data,
							function(stateId, stateName) {
								$('<option>').val(stateId)
									.text(stateName)
									.appendTo("#stateid");
							});
					}
				});
			});
		$("#stateid").change(
			function() {
				$("#cityid").find('option').remove();
				$('<option>').val('').text('-Select-').appendTo(
					"#cityid");
				var stId = $("#stateid").val();
				$.ajax({
					type: "GET",
					url: "getcity?sid=" + stId,
					success: function(data) {
						$.each(data, function(cityId, cityName) {
							$('<option>').val(cityId)
								.text(cityName).appendTo(
									"#cityid");
						});
					}
				});
			});
	});

//For checking New Password and Confirm password is  same ofr not
function validatePwd() {
	$("#errorid").html("");
	var newPwd = $("#newPwd").val();
	var confPwd = $("#confrmPwd").val();
	if (newPwd != confPwd) {
		$("#errorid").html("New Password and Confirm password not equal");
		return false;
	}
	return true;
}//function


function conformPwd() {
		$("#error").html("");
		var newPwd = $("#newPass").val();
		var confPwd = $("#confPass").val();
		if (newPwd != confPwd) {
			$("#error").html("Both Password mismatch");
			return false;
		}
		return true;
	}

