 $(function() {$( "#fromdt" ).datepicker({ buttonImage: 'resources/images/calender.png', maxDate: new Date, buttonImageOnly: true, buttonText : '',  showOn: 'button'  });});
 $(function() {$( "#todate" ).datepicker({ buttonImage: 'resources/images/calender.png', maxDate: new Date,buttonImageOnly: true,  buttonText : '',  showOn: 'button'  });});
    
var aaaa =  $("#actionPage").val();
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