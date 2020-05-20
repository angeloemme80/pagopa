<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JMS Test Application</title>
</head>
<body>
	<h1>Send message here</h1>
	<form action="send" method="get">
		<textarea rows="2" cols="40" id="msg" name="msg"></textarea><br/>
		<input type="radio" name="type" value="queue" checked="checked"> Queue<br/>
  		<input type="radio" name="type" value="topic1"> Topic 1<br/>
  		<input type="radio" name="type" value="topic2"> Topic 2<br/>
		<button type="submit">SEND</button>
	</form>
	
	<h1>Read message here</h1>
	<form action="read" method="get">
		<button type="submit">READ</button>
	</form>
</body>
</html>