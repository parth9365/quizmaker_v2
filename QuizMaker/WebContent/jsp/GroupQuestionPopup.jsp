<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="../js/noty/js/noty/packaged/jquery.noty.packaged.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	$("#questionId").select2();
	
	$("#submitBtn").click(function(){
		var isValid = validateForm();
		
		if(isValid){
			$.ajax({
		           type: "POST",
		           url: "QuestionController?action=linkQuestionSubmit",
		           data: $("#popupForm").serialize(), // serializes the form's elements.
		           success: function(data)
		           {
		        	   data = $.trim(data);
		        	   //if(data == "Success"){
		        		   generate("success", "Questions grouped successfully.");
		        		   //$("<div>Question added to Quiz Successfully</div>").dialog();
		        		   $("#dialog2").dialog('close');
		        	   //}
		               
		           }
		    });
		}
	});
});

</script>

<div id="content">
<div class="section" style="padding-top: 0px;">
	
	<!--[if !IE]>start section content<![endif]-->
	<div class="section_content">
		<div class="section_content_inner">
			<form action="../servlet/QuestionController?action=linkQuestionSubmit" id="popupForm" class="search_form" method="post">
			
			<!--[if !IE]>start fieldset<![endif]-->
			<fieldset>
				<!--[if !IE]>start forms<![endif]-->
				<div class="forms" style="width: 500px;">
					
					<div class="row">
						<label style="width: 100px;">Question:</label>
						<div class="inputs" style="width: 300px; float: left;"> 
							${parentQuestion.questionId } - ${parentQuestion.question }
						</div>
					</div>
					
					<input type="hidden" name="parentQuestionId" id="parentQuestionId" value="${parentQuestion.questionId }" />
					
					<div class="row">
						<label style="width: 100px;">Group to  <br>Question:</label>
						<div class="inputs" style="width: 300px; float: left;"> 
							<select id="questionId" name="questionId" style="width: 100%;" onchange="getQuestionDetail(this);">
						       <option value="">--select--</option>
								<c:forEach items="${questions }" var="question">
									<c:if test="${question.QuestionId ne param.questionId }">
										<option value="${question.QuestionId }" >${question.QuestionId } - ${question.Question }</option>
									</c:if>
								</c:forEach>
							</select>							
						</div>
					</div>
					
					<div class="row">
						<label style="width: 100px;" id="queDetailLbl"></label>
						<div class="inputs" style="width: 300px; float: left;" id="queDetail"> 
							&nbsp;							
						</div>
					</div>
					
					<div class="row">
						<div class="inputs" style="text-align: center;">
							<input name="" class="btn btn-primary" type="button" id="submitBtn" value="Group!">
						</div>
					</div>
				</div>
			</fieldset>
			
			</form>
		</div>
		
	</div>
</div>
</div>

<script type="text/javascript">
function getQuestionDetail(obj){
	var questionID = $("#questionId").select2("val");
	//alert(questionID);
	var url = "QuestionController?action=getQuestionDetail&questionId="+questionID;
	$.ajax({
		  url: url,
		  type: 'GET',
		  data: '',
		  success: function(data) {
				$("#queDetailLbl").html("&nbsp;")
				$("#queDetail").html(data);
		  },
		  error: function(e) {
			//called when there is an error
			console.log(e.message);
		  }
	}); 
}

function validateForm(){
	var questionId = $("#questionId").select2("val");
	
	if(questionId == ""){
		return false;
	}
	
	return true;
}

function generate(type, text) {
    var n = noty({
        text        : text,
        type        : type,
        dismissQueue: true,
        timeout     : 3000,
        closeWith   : ['click'],
        layout      : 'topCenter',
        theme       : 'defaultTheme',
        maxVisible  : 10
    });
    console.log('html: ' + n.options.id);
}
</script>
