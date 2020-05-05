$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

//SAVE
$(document).on("click", "#btnSave", function(event) 
{  
	//CLEAR ALERT
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide(); 
	$("#alertError").text("");  
	$("#alertError").hide(); 

	//FORM VALIDATION
	 var status = validatepatientForm();  
	 if (status != true)  
	 {   
		 $("#alertError").text(status);  
		 $("#alertError").show();   
		 return;  
	 } 

	 //IF VALID
	 var type = ($("#hidPatientIDSave").val() == "") ? "POST" : "PUT"; 

	 $.ajax( 
	 {  
	 	url : "PatientsAPI",  
	 	type : type,  
	 	data : $("#formPatient").serialize(),  
	 	dataType : "text",  
	 	complete : function(response, status)  
	 	{   
	 		onHospSaveComplete(response.responseText, status);  
	 	} 
	 }); 

}); 


function onHospSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divPatientsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   } 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidPatientIDSave").val("");  
	$("#formPatient")[0].reset(); 
}



//UPDATE
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidPatientIDSave").val($(this).closest("tr").find('#hidPatientIDUpdate').val());
	$("#first_name").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#last_name").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
	
});

//REMOVE
$(document).on("click", ".btnRemove", function(event) 
		{  
			$.ajax(  
					{   
						url : "PatientsAPI",   
						type : "DELETE",   
						data : "patientID=" + $(this).data("patientid"),   
						dataType : "text",   
						complete : function(response, status)   
						{    
							onPatientDeleteComplete(response.responseText, status);   
						}  
		}); 
});

function onPatientDeleteComplete(response, status) 
{  
		if (status == "success")  
		{   
				var resultSet = JSON.parse(response); 


				if (resultSet.status.trim() == "success")   
				{    
					$("#alertSuccess").text("Successfully deleted.");    
					$("#alertSuccess").show(); 

		 
					$("#divPatientsGrid").html(resultSet.data);   
				} else if (resultSet.status.trim() == "error")   
				{    
					$("#alertError").text(resultSet.data);    
					$("#alertError").show();   
				} 

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


//CLIENTMODEL
function validatepatientForm()
{   
	//First Name
	if ($("#first_name").val().trim() == "")  
	{   
		return "Insert First Name."; 
	} 
	
	//Last Name
	if ($("#last_name").val().trim() == "") 
	{  
		return "Insert Last Name .";  
	} 
	 
	//Address
	if ($("#address").val().trim() == "") 
	{   
		 return "Insert Address."; 
	} 
	 
	//Email
	if ($("#email").val().trim() == "") 
	{   
		 return "Insert Email."; 
	} 
	
	//Checking if a valid email
	var tmpEmail = $("#email").val().trim();
	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+[A-Z]{2,4}/igm;
	if (! re.test(tmpEmail)){
		return "Insert a Valid Email";
	}
	 
	 return true; 
} 
