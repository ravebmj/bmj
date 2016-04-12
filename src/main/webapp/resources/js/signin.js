//GOOGLE SIGN IN PAGE


/**
 * callback function after successful Google sign in on Google Login Page. 
 * @param googleUser
 */
function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	document.getElementById('error').innerHTML = "";
	if(strEndsWith(profile.getEmail(),'@bmj.com')){ // If valid northgate email id 
	//if(strEndsWith(profile.getEmail(),'@northgate-is.com')){ // If valid BMJ email id 
		submitPageAfterLogin();
	}else {
		document.getElementById('error').innerHTML = 'Access Denied: sign in with a BMJ email address';
		googleUser.disconnect();
	}
}
 function strEndsWith(str, suffix) {
	 return str.match(suffix+"$")==suffix;
 }
 /**
  * Navigate to page based on "fromPageType" after successful google sign in.
  */
 function submitPageAfterLogin(){
	 var fromPageType = document.getElementById("fromPageType").value;
	 if(fromPageType=='createnew' || fromPageType=='signin'){
			var form; // dynamic form that will call controller
		    form = $('<form />', {
		        action: "createinsight.html",
		        method: 'get',
		        style: 'display: none;'
		    });
		    //Form parameter insightId
		    $("<input>").attr("type", "hidden").attr("name", "insightId").val(insightId).appendTo(form);
		    $("<input>").attr("type", "hidden").attr("name", "googleSession").val("true").appendTo(form);
		    //Form submit
		    form.appendTo('body').submit();
	 }else if(fromPageType=='editinsight'){
			var form; // dynamic form that will call controller
		    form = $('<form />', {
		        action: "editinsight.html",
		        method: 'get',
		        style: 'display: none;'
		    });
		    //Form parameter insightId
		    $("<input>").attr("type", "hidden").attr("name", "insightId").val(document.getElementById("insightId").value).appendTo(form);
		    $("<input>").attr("type", "hidden").attr("name", "googleSession").val("true").appendTo(form);
		    //Form submit
		    form.appendTo('body').submit();
	 }else if(fromPageType=='dashboard'){
			var form; // dynamic form that will call controller
		    form = $('<form />', {
		        action: "dashboard.html",
		        method: 'get',
		        style: 'display: none;'
		    });
		    //Form parameter insightId
		    $("<input>").attr("type", "hidden").attr("name", "googleSession").val("true").appendTo(form);
		    
		    //Form submit
		    form.appendTo('body').submit();
	 }else if(fromPageType=='viewinsight'){
			var form; // dynamic form that will call controller
		    form = $('<form />', {
		        action: "viewinsight.html",
		        method: 'get',
		        style: 'display: none;'
		    });
		    //Form parameter insightId
		    $("<input>").attr("type", "hidden").attr("name", "googleSession").val("true").appendTo(form);
		    $("<input>").attr("type", "hidden").attr("name", "insightId").val(document.getElementById("insightId").value).appendTo(form);
		    //Form submit
		    form.appendTo('body').submit();
	 }
 }

 
 
 	/**
 	 * This code gets call on page (SessionCheck) load to check existence of google sign in session.
 	 */
	$(document).ready(function() {
		var status='';
		gapi.load('auth2', function() {
			auth2 = gapi.auth2.init({
			    client_id: '1075831596585-jjengvqaon5rp4elo0qou5t8314c583h.apps.googleusercontent.com'
			  }).then(function(){
				    auth2 = gapi.auth2.getAuthInstance();
				    status=auth2.isSignedIn.get();
				    if(status == true){
					    submitPageAfterLogin();
				    }else{
				    	redirectToGoogleLogIn(document.getElementById("fromPageType").value,document.getElementById("insightId").value);
				    }
			});			  

		});
		
	});
	/**
	 * This gets call when user does not have google login session in browser.
	 */
	function redirectToGoogleLogIn(pageType,insightId){
		var form; // dynamic form that will call controller
	    form = $('<form />', {
	        action: "googleLogin.html",
	        method: 'post',
	        style: 'display: none;'
	    });
	    //Form parameter pageType
	    $("<input>").attr("type", "hidden").attr("name", "pageType").val(pageType).appendTo(form);
	    $("<input>").attr("type", "hidden").attr("name", "insightId").val(insightId).appendTo(form);
	    //Form submit
	    form.appendTo('body').submit();

	}	