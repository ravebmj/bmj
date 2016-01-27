$(document).ready(function() {

	var myDropzone = new Dropzone("div#idDragDropArea",
		{ 
		url: "upload.ajx",
		 params: {
		      uid: document.getElementById("insightDetailsDto.id").value
		 },
		addRemoveLinks : true,
		
		// The setting up of the dropzone
		init : function() {
			this.on("success", function(file, serverResponse) {//get call after sucessfull response. 
				refreshAttachmentData(serverResponse);
				if(serverResponse.errMessage!=''){//Error Message
					showErrorMessage("#error-message-attachment",serverResponse.errMessage);
				}else if(serverResponse.fileAlreadyExist=='true'){
					confirmation_popup();
				}else{
					showCountMessage("#error-message-attachment",emsgAttchSize+$("#divAttachDetails").find("tr").length);
				}
			});
			
		}
		}
	);
	
	
	$(".file-dropzone").on('dragover', handleDragEnter);
	$(".file-dropzone").on('dragleave', handleDragLeave);
	$(".file-dropzone").on('drop', handleDragLeave);

	function handleDragEnter(e) {
		this.classList.add('drag-over');
	}

	function handleDragLeave(e) {
		this.classList.remove('drag-over');
	}

});
/**
 * Replace duplicate file name attachment with latest uploaded version.
 */
function replaceAttachment(){
	confirmation_popup_close();
  	$.ajax({
		url : 'replaceAttachment.ajx',
		type : 'GET',
		data : "uid="	+ document.getElementById("insightDetailsDto.id").value,
		success : function(serverResponse) {
			refreshAttachmentData(serverResponse);
			if(serverResponse.errMessage!=''){
				showErrorMessage("#error-message-attachment",serverResponse.errMessage);
			}else{
				showCountMessage("#error-message-attachment",emsgAttchSize+serverResponse.lstInsightAttachmentDTO.length );
			}
			
		},
		error : function(data, status, er) {
			
		}
	});
}
/**
 * Refresh attachment table with latest attachment from database 
 * after add or delete attachment.
 * @param serverResponse
 */
function refreshAttachmentData(serverResponse){
	var finaldata = "";
	if(serverResponse.lstInsightAttachmentDTO.length>0){// If attached list is more than zero.
		finaldata = "<table width='98%' border='0' cellspacing='0' cellpadding='5' style='margin:0 auto;'>";
		for (var i=0, len=serverResponse.lstInsightAttachmentDTO.length; i<len; i++) {
			finaldata =finaldata+"<tr>";
			finaldata =finaldata+"<td width='75%' style='border-bottom:1px solid #82e676'>"+serverResponse.lstInsightAttachmentDTO[i].fileName+"</td>";
			finaldata =finaldata+"<td style='border-bottom:1px solid #82e676;color:#929397;font-size:14px;'>"+serverResponse.lstInsightAttachmentDTO[i].sizeInKB+"</td>";
			finaldata =finaldata+"<td style='border-bottom:1px solid #82e676'><a href='download.ajx?id="+serverResponse.lstInsightAttachmentDTO[i].id+"&fileId="+serverResponse.lstInsightAttachmentDTO[i].fileId+"&insightId="+serverResponse.lstInsightAttachmentDTO[i].insightId+"'> <img src='resources/images/download.png' /></a></td>";
			finaldata =finaldata+"<td style='border-bottom:1px solid #82e676'><a href='javascript:void(0)'  onclick='deleteFile("+serverResponse.lstInsightAttachmentDTO[i].id+","+serverResponse.lstInsightAttachmentDTO[i].insightId+",&apos;"+serverResponse.lstInsightAttachmentDTO[i].fileId+"&apos;);' > <img src='resources/images/close-btn.png' /></a></td>";
			finaldata =finaldata+"</tr>";
		}
		finaldata = finaldata+"</table>";
	}
	document.getElementById("divAttachDetails").innerHTML= finaldata;
	document.getElementById("attchmentSize").value=serverResponse.lstInsightAttachmentDTO.length;// Populates hidden variable which holds size of attachment records.
	addRemoveClassForAttachment();
	
}
/**
 * It gets call when user clicks on delete icon.  
 * @param insightId
 */
function deleteFile(attachmentId,insightId,fileId){
  	$.ajax({
		url : 'deleteAttachment.ajx',
		type : 'GET',
		data : "attachmentId="	+ attachmentId	
		+ "&insightId="+insightId
		+ "&fileId="+fileId+"",
		success : function(serverResponse) {
			console.debug('After delete');
			console.debug(' length is'+serverResponse.lstInsightAttachmentDTO.length);
			refreshAttachmentData(serverResponse);
			if(serverResponse.errMessage!=''){
				showErrorMessage("#error-message-attachment",serverResponse.errMessage);
			}else{
				showCountMessage("#error-message-attachment",emsgAttchSize+serverResponse.lstInsightAttachmentDTO.length );
			}
		},
		error : function(data, status, er) {
			
		}
	});
}


function showCountMessage(id,message)
{
	$(id).text(message);
	$(id).show();
	$(id).removeClass("errormessage");
	$(id).addClass("green");

}