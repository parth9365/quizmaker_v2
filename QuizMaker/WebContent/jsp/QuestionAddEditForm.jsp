<%@include file="Header.jsp" %>

<div id="content">
<div class="inner">
<div class="section">
					
	<!--[if !IE]>start title wrapper<![endif]-->
	<div class="title_wrapper">
		<span class="title_wrapper_top"></span>
		<div class="title_wrapper_inner">
			<span class="title_wrapper_middle"></span>
			<div class="title_wrapper_content">
				<h2>Add New Question</h2>
			</div>
		</div>
		<span class="title_wrapper_bottom"></span>
	</div>
	<!--[if !IE]>end title wrapper<![endif]-->
	
	<!--[if !IE]>start section content<![endif]-->
	<div class="section_content">
		<span class="section_content_top"></span>
		
		<div class="section_content_inner">
		
			<form name="questionAddEdit" id="questionAddEdit" class="search_form" action="../servlet/QuestionController?action=addEditSubmit" method="post">
			
			<!--[if !IE]>start fieldset<![endif]-->
			<fieldset>
				<!--[if !IE]>start forms<![endif]-->
				<div class="forms">

					<%-- <div class="row">
						<label>Quiz:</label>
						<div class="inputs"> 
							<select id="quizId" name="quizId" style="width: 100%;">
						       <option value="">--select--</option>
								<c:forEach items="${quizes }" var="quiz">
									<option value="${quiz.QuizId }" >${quiz.Title }</option>
								</c:forEach>
							</select>							
						</div>
					</div> --%>
					
					<%-- <div class="row">
						<label>Question No:</label>
						<div class="inputs"> 
							<input class="form-control" name="questionNo" id="questionNo" type="text" value="${maxQuizNo }" readonly />
							<span class="system positive" style="display: none;">This is a positive message</span>							
						</div>
					</div> --%>
					
					<div class="row">
						<label>Question:</label>
						<div class="inputs"> 
							<input class="form-control" name="question" id="question" type="text" required />
							<span class="system positive" style="display: none;">This is a positive message</span>							
						</div>
					</div>
					
					<div class="row">
						<label>Question Type:</label>
						<div class="inputs">
							<select name="questionType" id="questionType" style="width: 100%;" onchange="showOptions(this);" required>
								<option value="">--select--</option>
								<option value="MCQ">Multiple Choice Question</option>
								<option value="YES_NO">YES/NO</option>
								<option value="FILL_BLANKS">Fill in the blanks</option>
								<option value="ESSAY">Essay</option>
								<option value="SHORT_NOTE">Short Note</option>
							</select>
						</div>
					</div>
					<div class="row">
						<label>Type to Display:</label>
						<div class="inputs">
							<input class="form-control" name="questionTypeForDisplay" id="questionTypeForDisplay" type="text" />
						</div>
					</div>
					<div id="options" style="display: none;">
						<div class="row">
							<label>Option1:</label>
							<div class="inputs">
								<input class="form-control" name="option1" id="option1" type="text" />
							</div>
						</div>
						
						<div class="row">
							<label>Option2:</label>
							<div class="inputs">
								<input class="form-control" name="option2" id="option2" type="text" />
							</div>
						</div>
						
						<div class="row">
							<label>Option3:</label>
							<div class="inputs">
								<input class="form-control" name="option3" id="option3" type="text" />
							</div>
						</div>
						
						<div class="row">
							<label>Option4:</label>
							<div class="inputs">
								<input class="form-control" name="option4" id="option4" type="text" />
							</div>
						</div>
						
						<div class="row">
							<label>Option5:</label>
							<div class="inputs">
								<input class="form-control" name="option5" id="option5" type="text" />
							</div>
						</div>
						
						<div class="row">
							<label>Option6:</label>
							<div class="inputs">
								<input class="form-control" name="option6" id="option6" type="text" />
							</div>
						</div>
						
						<div class="row">
							<label>Option7:</label>
							<div class="inputs">
								<input class="form-control" name="option7" id="option7" type="text" />
							</div>
						</div>
						
						<div class="row">
							<label>Option8:</label>
							<div class="inputs">
								<input class="form-control" name="option8" id="option8" type="text" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<label>Answer:</label>
						<div class="inputs">
							<input class="form-control" name="answer" id="answer" type="text" />
						</div>
					</div>
					
					
					<c:if test="${param.quizId ne null }">
					<div class="row">
						<label>Grouped With:</label>
						<div class="inputs">
							<select name="groupedWith" id="groupedWith">
								<option value="">--select--</option>
								<c:forEach items="${questions }" var="q">
									<option value="${q.QuestionNo }">${q.QuestionNo }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					</c:if>
					
					<div class="row">
						<div class="inputs">
							<input name="" class="btn btn-primary" type="submit" id="submitBtn">
							<!-- <span class="button green_button"><span><span>GREEN BUTTON</span></span><input name="" type="submit"></span>
							<span class="button gray_button"><span><span>NEUTRAl GREY</span></span><input name="" type="submit"></span>  -->
						</div>
					</div>
				</div>
			</fieldset>
			</form>
		
		</div>
	</div>

</div>
</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#questionType").select2();
		$("#quizId").select2();
		$("#groupedWith").select2();
		
		var isEdit = '${isEdit}';
		
		var quizId = "";
		if(isEdit == 1){
			quizId = "${question.quizId }";	
		}else{
			quizId = "${param.quizId }";
		}
		
		var maxQuizNo = "${maxQuizNo }";
		
		if(quizId != ""){
			$("#quizId").select2("val", quizId);
			$("#quizId").select2("enable", false);
		}
		
		
		if(isEdit == "1"){
			var questionType = "${question.questionType}";
			
			$("#questionNo").val('${question.questionNo}');
			$("#question").val('${question.question}');
			$("#questionType").select2("val", '${question.questionType}');
			
			if(questionType == "MCQ"){
				$("#options").css("display", "block");
				$("#option1").val('${question.option1 }');
				$("#option2").val('${question.option2 }');
				$("#option3").val('${question.option3 }');
				$("#option4").val('${question.option4 }');
				$("#option5").val('${question.option5 }');
				$("#option6").val('${question.option6 }');
				$("#option7").val('${question.option7 }');
				$("#option8").val('${question.option8 }');
				
			}
			
			$("#answer").val('${question.answer }');
			$("#groupedWith").select2("val", '${question.groupedId }');
		}
		
		$("#questionAddEdit").submit(function(){
			$("#quizId").select2("enable", true);
		});
		
		$("#quizId").on("change", function(){
			var quizId = $(this).val();
			if(quizId != ""){
				getMaxQuestionNo(quizId);
			}
		});
	});	
	
	function getMaxQuestionNo(quizId){
		var url = "QuestionController?action=getMaxQuestionNo&quizId="+quizId;
		
		$.ajax({
			  url: url,
			  type: 'GET',
			  data: '',
			  success: function(data) {
				$("#questionNo").val(data);
				$("#questionNo").attr("readonly", "readonly");
			  },
			  error: function(e) {
				//called when there is an error
				console.log(e.message);
			  }
		});
	}
	
	function showOptions(obj){
		if(obj.value == "MCQ"){
			$("#options").css("display", "block");
		}else{
			$("#options").css("display", "none");
		}
	}
</script>
<%@include file="Footer.jsp" %>