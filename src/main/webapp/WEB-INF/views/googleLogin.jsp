<html>
<head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<meta name="google-signin-client_id"
	content="1075831596585-jjengvqaon5rp4elo0qou5t8314c583h.apps.googleusercontent.com">
<title>BMJ Catalogue</title>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="resources/js/signin.js"></script>
<style>
.container{margin:0 auto;width:26%;}
h3{font-size:24px;font-weight:normal;padding-left:20px;}
.signinbtn{padding-left:20px;}
#error{padding-left:20px;color: #FF0000;}
</style>
</head>

<body>
	<div class="container">
		<div id="header" class="row">
			<div class="col-xs-12 col-md-6 col-md-offset-3">
				<span class="bmj-logo"><img src="resources/images/logo.jpg" class="img-responsive" alt="BMJ" /></span>
			</div>
		</div>
		<div class="row">
			<div class="google-container col-md-5 col-md-push-3">
				<h3>Please sign in using your BMJ Google Account</h3>
				<div class="g-signin2 signinbtn" data-onsuccess="onSignIn"></div>
				<p id="error"></p>
			</div>
		</div>
		<div id="error"></div>
	</div>
	<input type="hidden" id="fromPageType" name="fromPageType" value="${pageType}"/>
	<input type="hidden" id="insightId" name="insightId" value="${insightId}"/>
</body>
</html>