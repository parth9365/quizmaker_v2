<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="../js/noty/js/noty/packaged/jquery.noty.packaged.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("#courseId").select2();
		$("#quizId").select2();
	
		$("#submitBtn").click(function(){
			//$("#popupForm").submit();
			var isValid = validateForm();
			
			if(isValid){
				$.ajax({
			           type: "POST",
			           url: "QuestionController?action=addQuestionToQuizSubmit",
			           data: $("#popupForm").serialize(), // serializes the form's elements.
			           success: function(data)
			           {
			        	   data = $.trim(data);
			        	   //if(data == "Success"){
			        		   generate("success", "Question(s) added to Quiz Successfully");
			        		   //$("<div>Question added to Quiz Successfully</div>").dialog();
			        		   $("#dialog").dialog('close');
			        	   //}
			               
			           }
			    });
			} else {
				//generate("alert", "Please select the course and quiz.");
				$("<div>Please select the course and quiz.</div>").dialog();
			}
			
		});
	});
	
	function validateForm(){
		var quizId = $("#quizId").select2("val");
		console.log("QuizId: "+quizId);
		if(quizId == ""){
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

<div id="content">
<div class="section" style="padding-top: 0px;">
	
	<!--[if !IE]>start section content<![endif]-->
	<div class="section_content">
		<div class="section_content_inner">
			<form action="../servlet/QuestionController?action=addQuestionToQuizSubmit" id="popupForm" class="search_form" method="post">
			
			<input type="hidden" name="questionId" name="questionId" value="${param.questionId }" />	
				
			<!--[if !IE]>start fieldset<![endif]-->
			<fieldset>
				<!--[if !IE]>start forms<![endif]-->
				<div class="forms" style="width: 500px;">
					
					<div class="row">
						<label style="width: 100px;">Course:</label>
						<div class="inputs" style="width: 300px; float: left;"> 
							<select id="courseId" name="courseId" style="width: 100%;" onchange="fillQuizDropdown(this);">
						       <option value="">--select--</option>
								<c:forEach items="${courses }" var="course">
									<option value="${course.CourseId }" >${course.CourseName }</option>
								</c:forEach>
							</select>							
						</div>
					</div>
					
					<div class="row">
						<label style="width: 100px;">Quiz:</label>
						<div class="inputs" style="width: 300px; float: left;">
							<select id="quizId" name="quizId" style="width: 100%;" required>
						       <option value="">--select--</option>
<%-- 								<c:forEach items="${quizes }" var="quiz">
									<option value="${quiz.QuizId }" >${quiz.Title }</option>
								</c:forEach> --%>
							</select>							
						</div>
					</div>
					
					<input type="hidden" name="from" value="${param.from }" />
					
					<div class="row">
						<div class="inputs" style="text-align: center;">
							<input name="" class="btn btn-primary" type="button" id="submitBtn" value="Submit">
						</div>
					</div>
				</div>
			</fieldset>
			
			</form>
		</div>
		
	</div>
</div>
</div>

<script>
function fillQuizDropdown(obj) {
	var courseId = obj.value;
	//alert(courseId);
	if(courseId != ""){
		$.ajax({
			url : "QuizController?action=getQuizForCourse",
			data: {
				"courseId": courseId
			},
			method: 'post',	
			success : function(data) {
				//alert("data:"+data+":");
				if(data != "[]"){
					var quiz = $("#quizId");
					quiz.html("");
					quiz.append("<option value=''>--select--</option>");
					$.each($.parseJSON(data), function(idx, item){
		            	//alert(item.CATEGORYNAME);
		            	quiz.append('<option value="'+item.QuizId+'">'+item.Title+'</option>');
		            });
				}else{
					$("<div>You have not entered any quiz for this course!!</div>").dialog();
				}
			}
		});
	}
}
</script>

