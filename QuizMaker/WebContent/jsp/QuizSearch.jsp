
<%@include file="Header.jsp" %>


<div id="content">
<div class="inner">
<div class="section">
	<div class="section_content">
		<span class="section_content_top"></span>
		<div class="section_content_inner">
			<display:table requestURI = "QuizController?action=searchResult" name="quizes" id="quiz" pagesize="10" export="false" sort="list" class="displayTable">
			    <display:column property="Title" title="Name" sortable="true" headerClass="sortable" />
			    <display:column property="Description" title="Description" sortable="true" headerClass="sortable" />
			    <display:column title="Action"><a href="#">Generate Quiz</a> | <a href="#" onclick="deleteQuiz(${quiz.QuizId }, ${quiz.CourseId });">Delete</a></display:column>
			</display:table>
		</div>	
	</div>
</div>
</div>
</div>

<script>
	function deleteQuiz(quizId, courseId){
		
		var url = "QuizController?action=delete&quizId="+quizId+"&courseId="+courseId;
		
		if(confirm("Do you really want to delete Quiz?\nIt will delete all related question.\nDo you want to continue?")){
			
			$.ajax({
				  url: url,
				  type: 'GET',
				  data: '',
				  success: function(data) {
					if(data == "Success"){	
						location.reload();
					}
				  },
				  error: function(e) {
					//called when there is an error
					console.log(e.message);
				  }
			});
		}
		
	}
</script>

<%@include file="Footer.jsp" %>