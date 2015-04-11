
<%@include file="Header.jsp" %>


<div id="content">
<div class="inner">
<div class="section">
	<div class="section_content">
		<span class="section_content_top"></span>
		<input type="hidden" id="quizIdParam" name="quizIdParam" value="${param.quizId }" />
		<div class="section_content_inner">
			<div style="text-align: right;"><a href="QuestionController?action=addEditForm&quizId=${questions[0].QuizId }"><button class="btn">Add New Question</button></a></div>
			<display:table requestURI = "QuestionController?action=searchResult" name="questions" id="question" pagesize="20" export="false" sort="list" class="displayTable">
				
				<display:column title="" >
					<input type='checkbox' name='questionIdChkbox' class="chkQue" value='${question.QuestionId }' />
				</display:column>
			    <display:column property="QuestionNo" title="Question No" sortable="true" headerClass="sortable" />
			    <display:column property="Question" title="Question" sortable="true" headerClass="sortable" />
			    <display:column property="QuestionTypeForDisplay" title="Type" sortable="true" headerClass="sortable" />
			    <display:column property="GroupedWith" title="Linked Question" sortable="true" headerClass="sortable" style="width: 10%;" />
			    <display:column property="Title" title="Quiz" sortable="true" headerClass="sortable"  />
			    <display:column title="Action" paramId="QuestionId" style="width: 100px;">
			    	<a href="QuestionController?action=edit&questionId=${question.QuestionId }" title="Edit Question"><img src="../img/edit-icon.png" height="20px;" width="20px;" /></a> &nbsp;&nbsp;
			    	<a onclick="deleteQuestion(${question.QuestionId }, '${param.quizId }');" style="cursor: pointer;" title="Delete Question"><img src="../img/delete-icon.png" height="20px;" width="20px;" /></a>&nbsp;&nbsp;
			    	<c:if test="${param.from ne null}">
			    		<a onclick="addToQuiz(${question.QuestionId });" style="cursor: pointer;" title="Add Question To Quiz"><img src="../img/add-icon.png" height="20px;" width="20px;" /></a>
					</c:if>
				</display:column>
			</display:table>
			
			<button class="btn" onclick="addMultipleToQuiz()" > Add to Quiz </button> 
			
		</div>	
	</div>
</div>
</div>
</div>

<div id="dialog" title="Add Question to Quiz" style="display: none;">
	
</div>

<script>

	function addToQuiz(questionId){
		//$("#dialog").dialog();
		//alert(quizId);
		var url = "QuestionController?action=addQuestionToQuizPopup&questionId="+questionId;
		$.ajax({
			  url: url,
			  type: 'GET',
			  data: '',
			  success: function(data) {
					$("#dialog").html(data);
					$("#dialog").dialog({
						width: 550
					});
			  },
			  error: function(e) {
				//called when there is an error
				console.log(e.message);
			  }
		}); 
		
	}

	function deleteQuestion(questionId, quizIdParam){
		console.log("Parameterized QuizId: "+quizIdParam);
	    
		var url = "QuestionController?action=delete&questionId="+questionId+"&quizId="+quizIdParam;
		
		/* if(confirm("Do you really want to delete Question?\nIf it's grouped question then it will delete all related question.\nDo you want to continue?")){
			
			
		} */
		
		
		$('<div></div>').appendTo('body')
		  .html('<div><h6>Do you really want to delete Question?</h6></div>')
		  .dialog({
		      modal: true, title: 'Delete Question', zIndex: 10000, autoOpen: true,
		      width: 'auto', resizable: false,
		      buttons: {
		          Yes: function () {
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
		            $(this).dialog("close");
		            
		          },
		          No: function () {
		              //doFunctionForNo();
		              $(this).dialog("close");
		          }
		      },
		      close: function (event, ui) {
		          $(this).remove();
		      }
		});
	}
	
	function addMultipleToQuiz(){
		var questionIds = [];
		$('.chkQue').each(function(i, obj) {
			if(obj.checked){
				questionIds.push(obj.value);
			}
		});
		
		console.log(questionIds.join(","));
		
		addToQuiz(questionIds);
	}
</script>

<%@include file="Footer.jsp" %>