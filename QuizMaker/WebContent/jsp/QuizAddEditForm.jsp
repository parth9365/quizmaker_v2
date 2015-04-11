<%@include file="Header.jsp" %>

<script type="text/javascript">
	$(document).ready(function() { 
		$("#courseId").select2();
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
				<h2>Add New Quiz</h2>
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
		
			<form action="../servlet/QuizController?action=addEditSubmit" class="search_form" method="post">
			
			<!--[if !IE]>start fieldset<![endif]-->
			<fieldset>
				<!--[if !IE]>start forms<![endif]-->
				<div class="forms">

					<div class="row">
						<label>Course Name:</label>
						<div class="inputs"> 
							<span class="input_wrapper select_wrapper">
								<select name="courseId" id="courseId" style="width: 200px;" >
									<option value="">--select--</option>
									<c:forEach items="${courses }" var="course">
										<option value="${course.CourseId }" >${course.CourseName }</option>
									</c:forEach>
								</select>
								<span class="system positive" style="display: none;">This is a positive message</span>							
							</span>
						</div>
					</div>
					
					<div class="row">
						<label>Quiz Title:</label>
						<div class="inputs"> 
							<input class="form-control" name="title" id="title" type="text" />
							<span class="system positive" style="display: none;">This is a positive message</span>							
						</div>
					</div>
					
					<div class="row">
						<label>Description:</label>
						<div class="inputs">
							<textarea class="form-control" name="description" id="description" style="height: 70px;"></textarea>
						</div>
					</div>
					
					<div class="row">
						<div class="inputs">
							<input name="" type="submit" id="submitBtn" class="btn btn-primary">
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
<%@include file="Footer.jsp" %>