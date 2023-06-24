//Check for role
$(document).ready(function() {
	$("#role").blur(function() {
		$("#error").html("");
		$("#success").html("");
		var roleName = $("#role").val();
		$.ajax({
			type: "GET",
			url: "roleCheck?roles=" + roleName,
			success: function(res) {
				if (res == 'ACTIVE')
					$("#error").html("Role already Exist in DB");
				if (res == 'INACTIVE')
					$("#error").html("Role Inactive Kindly active");
				if (res == 'UNIQUE')
					$("#success").html("You can create role on this name");
					$("#role").focus();
			},//function
			error: function() {
				$("#error").html("Problem in data Fetch");
			}
		}); //ajax
	});
});