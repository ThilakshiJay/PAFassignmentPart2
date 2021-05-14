$(document).ready(function()
{ 
	//page refresh
 		$("#alertSuccess").hide(); 
 		$("#alertError").hide(); 
});

// save button
$(document).on("click", "#btnSave", function(event)
{ 
 //Your code 
	// Clear alerts---------------------
 	$("#alertSuccess").text(""); 
 	$("#alertSuccess").hide(); 
 	$("#alertError").text(""); 
 	$("#alertError").hide();

	// Form validation-------------------
	var status = validateResearcherForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
 		return; 
 	}

	// If valid------------------------
	var type = ($("#hidResearcherIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{ 
 		url : "ResearcherAPI", 
 		type : type, 
 		data : $("#formResearcher").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemSaveComplete(response.responseText, status); 
 		} 
	});

});

function onItemSaveComplete(response, status)
{ 
 //Your code

	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success") 
	{ 
 		$("#alertSuccess").text("Successfully saved."); 
 		$("#alertSuccess").show(); 
		$("#divResearcherGrid").html(resultSet.data); 
	} else if (resultSet.status.trim() == "error") 
	{ 
 		$("#alertError").text(resultSet.data); 
 		$("#alertError").show(); 
	}else if (status == "error")
	{ 
 		$("#alertError").text("Error while saving."); 
 		$("#alertError").show(); 
	} else
	{ 
 		$("#alertError").text("Unknown error while saving.."); 
 		$("#alertError").show(); 
	} 
	
	$("#hidResearcherIDSave").val(""); 
 	$("#formResearcher")[0].reset(); 
}

//update
$(document).on("click", ".btnUpdate", function(event)
{ 
 //Your code 
	$("#hidResearcherIDSave").val($(this).data("researcherid"));  
 	$("#researcherID").val($(this).closest("tr").find('td:eq(0)').text()); 
 	$("#researcherName").val($(this).closest("tr").find('td:eq(1)').text()); 
 	$("#researcherNIC").val($(this).closest("tr").find('td:eq(2)').text()); 
 	$("#researcherAddress").val($(this).closest("tr").find('td:eq(3)').text());
	$("#researcherEmail").val($(this).closest("tr").find('td:eq(4)').text());
	$("#researcherPhoneNo").val($(this).closest("tr").find('td:eq(5)').text());
});

//delete---------------------------------
$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "ResearcherAPI", 
 type : "DELETE", 
 data : "researcherID=" + $(this).data("researcherid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

//delete------------------------------
function onItemDeleteComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully deleted."); 
 			$("#alertSuccess").show(); 
 			$("#divResearcherGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") 
 		{ 
 			$("#alertError").text(resultSet.data); 
 			$("#alertError").show(); 
 		 
 		} else if (status == "error") 
 		{ 
 			$("#alertError").text("Error while deleting."); 
 			$("#alertError").show(); 
 		} else
 		{ 
 			$("#alertError").text("Unknown error while deleting.."); 
 			$("#alertError").show(); 
 		} 
	}
}

// CLIENT-MODEL=================================================================
function validateResearcherForm()
{ 
 //Validations 
	// NAME
	if ($("#txtName").val().trim() == "") 
	{ 
		return "Insert Researcher name."; 
	}
	
	// NIC
	if ($("#txtNIC").val().trim() == "") 
	{ 
		return "Insert Researcher NIC."; 
	}
	
	// ADDRESS
	if ($("#txtAddress").val().trim() == "") 
	{ 
		return "Insert Researcher Address."; 
	}
	
	// EMAIL
	if ($("#txtEmail").val().trim() == "") 
	{ 
		return "Insert Researcher Email."; 
	}
	
	// PHONENO
	if ($("#txtphone").val().trim() == "") 
	{ 
		return "Insert Researcher PhoneNo."; 
	}
	
	
 	

	return true; 
}

//generate researcher card
function getResearcherCard(name, nic, address, email, phoneno )
{ 
	var researcher = ""; 
 //Generate card 
// generate the markup for the card
	researcher += "<div class=\"researcher card bg-light m-2\" style=\"max-width: 10rem; float: left;\">"; 
	researcher += "<div class=\"card-body\">"; 
	researcher += name + "";
	researcher += "<br>";
	researcher += nic + "";
	researcher += "<br>";
	researcher += address + "";
	researcher += "<br>";
	researcher += email + "";
	researcher += "<br>";
	researcher += phoneno + "";   
	researcher += "</div>"; 
	researcher += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">"; 
	researcher += "</div>";

	return researcher; 
}



