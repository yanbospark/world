<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	application.setAttribute("path", basepath);
%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="<%=basepath %>/resources/common/js/jquery.min.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <meta name="description" content="">
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>嘟嘟牛年会登录</title>
<link href="<%=basepath %>/resources/common/css/login.css" rel="stylesheet" type="text/css">
<link href="<%=basepath %>/resources/common/css/Default.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="bg_big">
  <div class="title_box">
    <img src="<%=basepath %>/resources/images/title_img.png" alt="" class="img_01">
    <img src="<%=basepath %>/resources/images/LOGO4 .png" alt="" class="img_02">
    <div class="clear_fix"></div>
  </div>
  <form action="<%=basepath %>/front/dologin" method="post">
  <div class="zh_box">
  	<%-- <input type="file" name="" style="background-image: url('<%=basepath %>/resources/images/login_icon.png');" class="fl"/> --%>
    <img src="<%=basepath %>/resources/images/login_icon.png" class="fl">
    <input class="fl" type="text" value="姓名/手机号" name="username"  onfocus="if(value == defaultValue){value='';this.style.color='#ffffff'}" onblur="if(!value){value = defaultValue;this.style.color='#ffffff'}">
  </div>
  <!-- <button class="dl_btn">登录</button> -->
  <input type="submit" name="登录" value="登录" class="dl_btn"/>
    </form>
    
  <script>
        (function (doc, win) {
          var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
              var clientWidth = docEl.clientWidth;
              if (!clientWidth) return;
              docEl.style.fontSize = 20 * (clientWidth / 375) + 'px';
            };

          if (!doc.addEventListener) return;
          win.addEventListener(resizeEvt, recalc, false);
          doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window);
</script>  
</div>
