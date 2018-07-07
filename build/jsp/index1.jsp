<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>Jquery Inline Form Validation Engine</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/css/template.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/css/validationEngine.jquery.css" />
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.validationEngine-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.validationEngine.js"></script>

		<!-- AJAX SUCCESS TEST FONCTION	
			<script>function callSuccessFunction(){alert("success executed")}
					function callFailFunction(){alert("fail executed")}
			</script>
		-->
		
		<script>	
		$(document).ready(function() {
			$("#form1").validationEngine()
		});
	</script>	
	</head>
	<body>
	
		<form id="form1"  method="post" action="">
		<hr></hr>
				<input value="" class="validate[required,custom[noSpecialCaractersForUserId],length[0,8]]" id="loginID" type="text"   />
		</form>
		
	</body>
</html>