function deleteAccount(){
	connect("/hiwi/Applicant/js/deleteAccount","",null);
}

function check(){
	var pw = document.getElementById("newpasswort").value;
	var pww = document.getElementById("newpasswortwdh").value;
	var mail = document.getElementById("newemail").value;
	var name = document.getElementById("newnutzername".value);
	if(document.getElementById("dataconfirm").checked){
		deleteAccount();
		togglePopup("data_acc_del", true);
	}else{
		
		
	}
}

function changeAccount(){
	
}