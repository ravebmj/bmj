function submitForm()
{
	var actionPage  = $("#actionPage").val();
	var username = $("#username").val();
	var password = $("#password").val();
	if((username.length==0)&&(password.length==0))
	{
		showErrorMessage(errmsgEmptyField);
	}else if((password.length==0)){
		showErrorMessage(errmsgEmptyPasswordField);
	}else if((username.length==0)){
		showErrorMessage(errmsgEmptyUserNameField);
	}else{
		$.ajax({

			url : 'validatingUser.ajx',
			type : 'get',
			data : "username="
				+ document.getElementById('username').value
				+ "&password=" + encodeURI(document.getElementById('password').value),
				success : function(result) {
					
					$("#username").val('');
					$("#password").val('');
					if(result== 'InValidUser'){
						$("#errormessage").text(errmsgLoginDetailIncorrect);
						$("#errormessage").show();
						$("#errormessage").addClass("green");
					}else if(result.substring(0,9)== 'ValidUser'){
						lightbox_close();
						if(actionPage == 'view'){
							 document.getElementById("idSignDetail").href="logout.html";
							 document.getElementById("idSignDetail").innerHTML="Sign Out";
							 document.getElementById("idSignDetail").removeAttribute("onclick");
							 document.getElementById("idShowUserName").style.display = "block";	
							 document.getElementById("idShowUserName").innerHTML = 'Hi,'+result.substring(10,(result.length));
							 document.getElementById("idEditButton").setAttribute("onclick", "perfromSubmitOpeartionForEdit()");
						}else if(actionPage == 'search'){
							 document.getElementById("idSignDetail").href="logout.html";
							 document.getElementById("idSignDetail").innerHTML="Sign Out";
							 document.getElementById("idSignDetail").removeAttribute("onclick");
							 document.getElementById("idShowUserName").style.display = "block";	
							 document.getElementById("idShowUserName").innerHTML = 'Hi,'+result.substring(10,(result.length));
							 document.getElementById("btnAddNewInsight").setAttribute("onclick", "submitPageForNewInsight()");
						}else if(actionPage == 'create'){
							 document.getElementById("idSignDetail").href="logout.html";
							 document.getElementById("idSignDetail").innerHTML="Sign Out";
							 document.getElementById("idSignDetail").removeAttribute("onclick");
							 document.getElementById("idShowUserName").style.display = "block";	
							 document.getElementById("idShowUserName").innerHTML = 'Hi,'+result.substring(10,(result.length));
						} else if(actionPage == 'Edit'){
							perfromSubmitOpeartionForEdit();
						}else{
							document.loginform.submit();
						}
						
					}else{
						showErrorMessage(errmsgWhileValidating);
					}
		},
				error : function(data, status, er) {


				}

		});
	}

}


function showErrorMessage(errorMessage)
{
	$("#errormessage").text(errorMessage);
	$("#errormessage").show();
	$("#errormessage").addClass("red");
}


document.onkeypress = function keypressed(e){
	var keyCode = (window.event) ? e.which : e.keyCode;
	if(keyCode==13)
	{
		var source = e.target || e.srcElement;
		
		if(source.form.id == 'loginform')
		{
			submitForm();
		}
		
	}
};
