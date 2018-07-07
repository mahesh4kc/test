<html>
<head>
</head>
<body>
 
	File uploaded to : <%= request.getAttribute("uploadedFilePath") %>
	<br/><br/>
	<a href="upload/<%= request.getAttribute("uploadedFileName") %>">
        Click here to download it</a>
 
</body>
</html>