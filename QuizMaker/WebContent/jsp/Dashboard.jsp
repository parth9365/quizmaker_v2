
<%@include file="Header.jsp" %>		
		
		<!--[if !IE]>start content<![endif]-->
		<div id="content">
			<div class="inner">
				
				
				<!--[if !IE]>start section<![endif]-->
				<div class="section">
					
					<!--[if !IE]>start title wrapper<![endif]-->
					<div class="title_wrapper">
						<span class="title_wrapper_top"></span>
						<div class="title_wrapper_inner">
							<span class="title_wrapper_middle"></span>
							<div class="title_wrapper_content">
								<h2>Dashboard</h2>
							</div>
						</div>
						<span class="title_wrapper_bottom"></span>
					</div>
					<!--[if !IE]>end title wrapper<![endif]-->
					
					<!--[if !IE]>start section content<![endif]-->
					<div class="section_content">
						<span class="section_content_top"></span>
						
						<div class="section_content_inner">
						<!--[if !IE]>start lists<![endif]-->
						<div class="lists">
							<div class="lists_inner">
								<dl>
									<dt>Course</dt>
									<dd>
										<div class="dd_top">
											<ul class="dd_bottom">
												<li><a href="CourseController?action=addForm">Add New Course</a></li>
												<li><a href="CourseController?action=search">Search Course</a></li>
											</ul>
										</div>
									</dd>
								</dl>
								
								<dl>
									<dt>Quiz</dt>
									<dd>
										<div class="dd_top">
											<ul class="dd_bottom">
												<li><a href="QuizController?action=addForm">Add New Quiz</a></li>
												<li><a href="QuizController?action=searchResult">Search Quiz</a></li>
												<li><a href="QuizController?action=generateQuiz">Generate Quiz</a></li>
											</ul>
										</div>
									</dd>
								</dl>
								
								<dl>
									<dt>Question</dt>
									<dd>
										<div class="dd_top">
											<ul class="dd_bottom">
												<li><a href="QuestionController?action=importForm">Import Questions</a></li>
												<li><a href="QuestionController?action=searchForm">Search Question</a></li>	
												<li><a href="QuestionController?action=addEditForm">Add New Question</a></li>
												<li><a href="QuestionController?action=addQuestionToQuiz">Add Questions to Quiz</a></li>
											</ul>
										</div>
									</dd>
								</dl>
															
							</div>
						</div>
						<!--[if !IE]>end lists<![endif]-->
						</div>
						
						<span class="section_content_bottom"></span>
					</div>
					<!--[if !IE]>end section content<![endif]-->
				</div>
				<!--[if !IE]>end section<![endif]-->
				
			</div>
		</div>
		<!--[if !IE]>end content<![endif]-->
		
<%@include file="Footer.jsp" %>