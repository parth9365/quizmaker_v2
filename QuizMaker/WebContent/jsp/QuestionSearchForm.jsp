<%@include file="Header.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#courseId").select2();
		$("#quizId").select2();

		/* $("#quizId").on("change", function(){
			if($(this).val() != ""){
				$("#quizSearch").submit();
			}
		});	 */
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
				<c:choose>
					<c:when test="${param.from ne null and param.from ne '' }">
						<h2>Choose quiz to add questions</h2>
					</c:when>
					<c:otherwise>
						<h2>Search Question</h2>
					</c:otherwise>
				</c:choose>
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
			<c:choose>
				<c:when test="${param.from ne null and param.from ne '' }">
					<form action="../servlet/QuestionController?action=searchResultForAddQue" id="quizSearch" class="search_form" method="post">
				</c:when>
				<c:otherwise>
					<form action="../servlet/QuestionController?action=searchResult" id="quizSearch" class="search_form" method="post">
				</c:otherwise>
			</c:choose>
			
			<!--[if !IE]>start fieldset<![endif]-->
			<fieldset>
				<!--[if !IE]>start forms<![endif]-->
				<div class="forms">

					<div class="row">
						<label>Course:</label>
						<div class="inputs"> 
							<select id="courseId" name="courseId" style="width: 100%;" onchange="fillQuizDropdown(this);">
						       <option value="">--select--</option>
								<c:forEach items="${courses }" var="course">
									<option value="${course.CourseId }" >${course.CourseName }</option>
								</c:forEach>
							</select>							
						</div>
					</div>
					
					<div class="row">
						<label>Quiz:</label>
						<div class="inputs"> 
							<select id="quizId" name="quizId" style="width: 100%;">
						       <option value="">--select--</option>
<%-- 								<c:forEach items="${quizes }" var="quiz">
									<option value="${quiz.QuizId }" >${quiz.Title }</option>
								</c:forEach> --%>
							</select>							
						</div>
					</div>
					
					<input type="hidden" name="from" value="${param.from }" />
					
					<div class="row">
						<div class="inputs">
							<input name="" class="btn btn-primary" type="submit" value="Search" id="submitBtn">
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
					alert("You have not entered any quiz for this course!!");
				}
			}
		});
	}
}
</script>

<%@include file="Footer.jsp" %>