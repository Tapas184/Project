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
					$("#role").focus();
				
				elseif (res == 'INACTIVE') 
					$("#resultMsg").html("Role Inactive Kindly active");
					$("#role").focus();
					
				elseif(res == 'UNIQUE') 
				   $("#resultmsg").html("You can create role on this name");
				   $("#role").focus();
	
			},//function
			error: function() {
				$("#error").html("Problem in data Fetch");
			}
		}); //ajax
	});
});