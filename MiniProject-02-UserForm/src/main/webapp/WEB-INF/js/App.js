$(document).ready(function() {
	$("#email").blur(function() {
		$("#dupmail").html("");
		var emailId = $("#email").val();
		$.ajax({
			type: "GET",
			url: "validmail?email=" + emailId,
			sucess: function(res) {
				if (res == 'duplicate') {
					$("#dupmail").html("Email is already registered");
					$("#email").focus();
				} //if
			} //function
		}); //ajax
	});
});

$(document).ready(function() {
	$("#countryid").change(function() {
		var couId = $("#countryid").val();
		$.ajax({
			type: "GET",
			url: "getstate?cid=" + couId,
			success: function(data) {
				$.each(data,function(stateId,stateName){
					$('<option>').val(stateId).text(stateName).apppendTo("#stateid");
				});
			}
		});
	});
});