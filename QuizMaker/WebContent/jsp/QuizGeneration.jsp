<%@include file="Header.jsp" %>

<script type="text/javascript">
	$(document).ready(function() { 
		$("#courseId").select2(); 
		$("#quizId").select2();
		
		var quizId = '${param.quizId}';
		var courseId = '${param.courseId}';
		
		if(quizId != ""){
			
			$("#courseId").select2("val", courseId);
			$("#courseId").trigger("change");
			setTimeout( function(){ $("#quizId").select2("val", quizId) }, 50);
		}
		
	});
</script>

<div id="content">
<div class="inner">
<div class="section">
					
	<!--[if !IE]>start title wrapper<![endif]-->
	<div class="title_wrapper">
		<span class="title_wrapper_top"></span>
		<div class="title_wrapper_inner">
			<span class="title_wrapper_middle"></span>
			<div class="title_wrapper_content">
				<h2>Generate Quiz</h2>
				<!-- <ul class="section_menu left">
					<li><a href="#" class="selected_lk"><span class="l"><span></span></span><span class="m"><em>Orders</em><span></span></span><span class="r"><span></span></span></a></li>
					<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Products</em><span></span></span><span class="r"><span></span></span></a></li>
					<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Members</em><span></span></span><span class="r"><span></span></span></a></li>
					<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Site Pages</em><span></span></span><span class="r"><span></span></span></a></li>
				</ul> -->
			</div>
		</div>
		<span class="title_wrapper_bottom"></span>
	</div>
	<!--[if !IE]>end title wrapper<![endif]-->
	
	<!--[if !IE]>start section content<![endif]-->
	<div class="section_content">
		<span class="section_content_top"></span>
		
		<div class="section_content_inner">
		
			<form action="../servlet/QuizController?action=generateQuizSubmit" id="quizGeneration" class="search_form" method="post">
			
			<!--[if !IE]>start fieldset<![endif]-->
			<fieldset>
				<!--[if !IE]>start forms<![endif]-->
				<div class="forms">

					<div class="row">
						<label>Course:</label>
						<div class="inputs"> 
							<select id="courseId" name="courseId" style="width: 200px;" onchange="fillQuizDropdown(this);">
						       <option value="">--select--</option>
						       <c:forEach items="${courses }" var="course">
									<option value="${course.CourseId }" >${course.CourseName }</option>
								</c:forEach>
						   	</select>
							<span class="system positive" style="display: none;">This is a positive message</span>							
						</div>
					</div>
					
					<div class="row">
						<label>Quiz:</label>
						<div class="inputs"> 
							<select id="quizId" name="quizId" style="width: 200px;" >
						       <option value="">--select--</option>
						   	</select>
							<span class="system positive" style="display: none;">This is a positive message</span>							
						</div>
					</div>
					
					<div class="row">
						<label>No of copy:</label>
						<div class="inputs"> 
							<input type="text" name="noOfCopy" id="noOfCopy" class="form-control" maxlength="2" required="required" autocomplete="off" required>
						</div>
					</div>
					
					<div class="row">
						<label></label>
						<div class="inputs">
						Sectionalized
						<input type="radio" id="type" value="section" name="type">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Mixed
						<input type="radio" id="type" name="type" value="mixed" checked>
						</div>
					</div>
					
					<div class="row">
						<label></label>
						<div class="inputs"> 
							<input type="submit" class="btn btn-primary" value="Generate" />
							<input type="button" onclick="submitForm();" class="btn btn-primary" value="Print Answers" />
							<input type="hidden" name="printAnswers" id="printAnswers" value="" />						
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
	function submitForm(){
		$("#printAnswers").val("1");
		$("#quizGeneration").submit();
	}

	function displayFile(obj){
		$("#fileValue").html(obj.value);
	};
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
						alert("You have not entered any quiz for this course!!");
					}
				}
			});
		}
	}
</script>

<%@include file="Footer.jsp" %>