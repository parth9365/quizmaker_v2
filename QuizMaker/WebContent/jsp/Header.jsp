<%@ page language="java" %>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<style>
.custom-file-input {
  color: transparent;
}
.custom-file-input::-webkit-file-upload-button {
  visibility: hidden;
}
.custom-file-input::before {
  content: 'Select Import file';
  color: black;
  display: inline-block;
  background: -webkit-linear-gradient(top, #f9f9f9, #e3e3e3);
  border: 1px solid #999;
  border-radius: 3px;
  padding: 5px 8px;
  outline: none;
  white-space: nowrap;
  -webkit-user-select: none;
  cursor: pointer;
  text-shadow: 0px 0px #fff;
  /* font-weight: 700; */
  font-size: 10pt;
  font-family: 'Trebuchet Ms';
}
.custom-file-input:hover::before {
  border-color: black;
}
.custom-file-input:active {
  outline: 0;
}
.custom-file-input:active::before {
  background: -webkit-linear-gradient(top, #e3e3e3, #f9f9f9); 
}

.btn {
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
	font-family: 'Trebuchet Ms';
}

.btn-primary {
	color: #fff;
	background-color: #428bca;
	border-color: #357ebd;
}

.form-control {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	font-family: 'Trebuchet Ms';
}

input {
	margin: 0em;
	font: -webkit-small-control;
	color: initial;
	letter-spacing: normal;
	word-spacing: normal;
	text-transform: none;
	text-indent: 0px;
	text-shadow: none;
	display: inline-block;
	text-align: start;
}

div {
	display: block;
} 

.col-lg-4 {
	width: 30%;
}
</style>

<title>Quiz Maker</title>
<link media="screen" rel="stylesheet" type="text/css" href="../css/admin.css"  ></link>
<link media="screen" rel="stylesheet" type="text/css" href="../css/display-tag.css"  ></link>
<!--[if lte IE 6]><link media="screen" rel="stylesheet" type="text/css" href="css/admin-ie.css" /><![endif]-->
<!-- aurora-theme is default -->

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css" ></link>
<link rel="stylesheet" type="text/css" href="../css/admin-white.css" ></link>
<link rel="stylesheet" type="text/css" href="../js/select2/select2.css" ></link>
<link rel="stylesheet" type="text/css" href="../js/jquery.dataTables.min.css"></link>
    
<script type="text/javascript" src="../js/behaviour.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script type="text/javascript" src="../js/select2/select2.js"></script>
<!-- <script type="text/javascript" src="../js/bootbox.min.js"></script> -->
<script type="text/javascript" src="../js/noty/js/noty/packaged/jquery.noty.packaged.min.js"></script>


</head>

<body>

	<!--[if !IE]>start wrapper<![endif]-->
	<div id="wrapper">
		
		<!--[if !IE]>start header main menu<![endif]-->
		
		<div id="header_main_menu">
		
		<span id="header_main_menu_bg"></span>
		<!--[if !IE]>start header<![endif]-->
		<div id="header">
			<div class="inner">
				<h1 id="logo"><a href="#"></a></h1>
				
				<!--[if !IE]>start user details<![endif]-->
				<div id="user_details">
					<ul id="user_details_menu">
						<li class="welcome">Welcome <strong> ${sessionScope.userBean.userName } </strong></li>
						
						<li>
							<ul id="user_access">
								<li class="first"><a href="#">My account</a></li>
								<li class="last"><a href="#">Log out</a></li>
							</ul>
						</li>
						
					</ul>
					
					<!-- <div id="server_details">
						<dl>
							<dt>Server time :</dt>
							<dd>9:22 AM | 03/04/2009</dd>
						</dl>
					</div> -->
					
				</div>
				
				<!--[if !IE]>end user details<![endif]-->
			</div>
		</div>
		<!--[if !IE]>end header<![endif]-->
		
		<!--[if !IE]>start main menu<![endif]-->
		<div id="main_menu">
			<div class="inner">
			<ul>
				<li>
					<a href="UserController?action=dashboard" class="selected_lk"><span class="l"><span></span></span><span class="m"><em>Dashboard</em><span></span></span><span class="r"><span></span></span></a>
					<ul>
						<!-- <li><a href="#" class="selected_lk"><span class="l"><span></span></span><span class="m"><em>Create Course</em><span></span></span><span class="r"><span></span></span></a></li> -->
						<li><a href="CourseController?action=addForm"><span class="l"><span></span></span><span class="m"><em>Add New Course</em><span></span></span><span class="r"><span></span></span></a></li>
						<li><a href="QuizController?action=addForm"><span class="l"><span></span></span><span class="m"><em>Add New Quiz</em><span></span></span><span class="r"><span></span></span></a></li>
						<li><a href="QuizController?action=generateQuiz"><span class="l"><span></span></span><span class="m"><em>Generate Quiz</em><span></span></span><span class="r"><span></span></span></a></li>
						<li><a href="QuestionController?action=addEditForm"><span class="l"><span></span></span><span class="m"><em>Add New Question</em><span></span></span><span class="r"><span></span></span></a></li>
						<li><a href="QuestionController?action=importForm"><span class="l"><span></span></span><span class="m"><em>Import Question</em><span></span></span><span class="r"><span></span></span></a></li>
					</ul>
				</li>
				<!-- <li>
					<a href="#"><span class="l"><span></span></span><span class="m"><em>Products</em><span></span></span><span class="r"><span></span></span></a>
				</li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Orders</em><span></span></span><span class="r"><span></span></span></a></li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>User Accounts</em><span></span></span><span class="r"><span></span></span></a></li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Static Pages</em><span></span></span><span class="r"><span></span></span></a></li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>SEO Settings</em><span></span></span><span class="r"><span></span></span></a></li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Moderators</em><span></span></span><span class="r"><span></span></span></a></li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Blog Settings</em><span></span></span><span class="r"><span></span></span></a></li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>FaQs</em><span></span></span><span class="r"><span></span></span></a></li>
				<li><a href="#"><span class="l"><span></span></span><span class="m"><em>Hosting Options</em><span></span></span><span class="r"><span></span></span></a></li> -->
			</ul>
			</div>
			<span class="sub_bg"></span>
		</div>
		<!--[if !IE]>end main menu<![endif]-->
		
		</div>
		
		<!--[if !IE]>end header main menu<![endif]-->
		
		