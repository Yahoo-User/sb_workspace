<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>fileUpload</title>
</head>

<body>
	<h1>fileUpload.jsp</h1>
	
	<form action="/fileUpload" method="POST" enctype="multipart/form-data">
        <div><input type="file" name="files" multiple></div>
        <div><input type="file" name="files"></div>
        <div><input type="file" name="files"></div>
        <div><input type="file" name="files"></div>
        <div><input type="file" name="files"></div>
        <br>

        <div><input type="submit" value="Upload"></div>
    </form>
</body>
</html>