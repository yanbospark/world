<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../resources/common/jsp/validation_front.jsp" %>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="<%=basepath%>/resources/common/js/jquery.js"></script>
<script src="<%=basepath%>/resources/common/js/jquery.min.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <meta name="description" content="">
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>图片上传</title>
</head>
<body>
	<form action="<%=basepath %>/front/user/upload" method="post"  enctype="multipart/form-data">
    <input type="file" value="上传个人图片" name="image"/>
    <a class="btn_02 fr"><button type="submit" style="background-image: url('<%=basepath %>/resources/images/btn_02.png');">提交</button></a>
    </form>
</body>
</html>