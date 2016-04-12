 $(function() {$( "#fromdt" ).datepicker({ buttonImage: 'resources/images/calender.png', maxDate: new Date, buttonImageOnly: true, buttonText : '',  showOn: 'button'  });});
 $(function() {$( "#todate" ).datepicker({ buttonImage: 'resources/images/calender.png', maxDate: new Date,buttonImageOnly: true,  buttonText : '',  showOn: 'button'  });});
    
var aaaa =  $("#actionPage").val();
var googleStatus='false';// variable to hold current status of google sign in session
window.document.onkeydown = function (e)
{
 if (!e){
  e = event;
 }
 if (e.keyCode == 27){
  lightbox_close();
 }
};
function lightbox_open(){
	$("#errormessage").hide();
	$("#username").val('');
	$("#password").val('');
 window.scrollTo(0,0);
 document.getElementById('light').style.display='block';
 document.getElementById('fade').style.display='block'; 
}
function lightbox_close(){
 document.getElementById('light').style.display='none';
 document.getElementById('fade').style.display='none';
}

			function DropDown(el) {
				this.dd = el;
				this.placeholder = this.dd.children('span');
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = '';
				this.index = -1;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						return false;
					});

					obj.opts.on('click',function(){
						var opt = $(this);
						obj.val = opt.text();
						obj.index = opt.index();
						obj.placeholder.text('Gender: ' + obj.val);
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			}

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-1').removeClass('active');
				});

			});
			$(function() {

				var dd = new DropDown( $('#dd2') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-2').removeClass('active');
				});

			});
			$(function() {

				var dd = new DropDown( $('#dd3') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});
			/**
			 * This gets call when user does not have google login session in browser
			 * @param pageType
			 * @param insightId
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
			/**
			 * This code gets call when any page in BMJ application gets load to check existence of google sign in session.
			 * When google mail sign in / sign out does in one tab then below code gets executed.
			 */
			$(document).ready(function() {
	
				gapi.load('auth2', function() {
					 
					 auth2 = gapi.auth2.init({
						 client_id: '1075831596585-jjengvqaon5rp4elo0qou5t8314c583h.apps.googleusercontent.com'
						});
	
						auth2.currentUser.listen(function (googleUser) {
						    if (googleUser.isSignedIn()) {// Already google signin
						    	
						    	googleStatus='true';
						    } else {// Google sign out.Invalidate BMJ session.Go to BMJ login page.
						    	
						    	googleStatus='false';
						    	$.ajax({
									url : 'invalidateSession.ajx',
									type : 'post',
									success : function(result) {
										if(result=='true'){
											if(pageType==''){
												redirectToGoogleLogIn('dashboard','');
											}else{
												
												if (pageType=='viewinsight'){
													
													redirectToGoogleLogIn(pageType,insightId);
												}
											}
										}else{

										}
									},
									error : function(data, status, er) {
										
									}
								});
						    	
						    }
						});
	
	
				});
				
			});
			 
			/**
			 * When user clicks on "home" link in header.
			 * Need to pass current status of googleSession to controller.
			 */
			function redirectToHome(){
				var form; // dynamic form that will call controller	
			    form = $('<form />', {
			        action: "dashboard.html",
			        method: 'get',
			        style: 'display: none;'
			    });
			    //Form parameter insightId
			    $("<input>").attr("type", "hidden").attr("name", "googleSession").val(googleStatus).appendTo(form);
			    //Form submit
			    form.appendTo('body').submit();
			}

