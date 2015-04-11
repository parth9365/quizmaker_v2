
<%@include file="Header.jsp" %>


<div id="content">
<div class="inner">
<div class="section">
	<div class="section_content">
		<span class="section_content_top"></span>
		<div class="section_content_inner">
			<display:table requestURI = "CourseController?action=search" name="courses" id="course" pagesize="10" export="false" sort="list" class="displayTable">
			    <display:column property="CourseName" title="Name" sortable="true" headerClass="sortable" />
			    <display:column property="Description" title="Description" sortable="true" headerClass="sortable" />
			</display:table>
		</div>	
	</div>
</div>
</div>
</div>

<%@include file="Footer.jsp" %>