//For checking the mail valid or not
	$(document).ready(function() {
		$("#email").blur(function() {
			$("#dupmail").html("");
			var emailId = $("#email").val();
			$.ajax({
				type : "GET",
				url : "validmail?email=" + emailId,
				success : function(res) {
					if (res == 'duplicate') {
						$("#dupmail").html("Email is already registered");
						$("#email").focus();
					} //if
				},//function
			error : function(){
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
								type : "GET",
								url : "getstate?cid=" + couId,
								success : function(data) {
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
								type : "GET",
								url : "getcity?sid=" + stId,
								success : function(data) {
									$.each(data, function(cityId, cityName) {
										$('<option>').val(cityId)
												.text(cityName).appendTo(
														"#cityid");
									});
								}
							});
						});
			});
