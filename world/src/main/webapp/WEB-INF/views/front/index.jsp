<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String basepath=(String)application.getAttribute("path");
if(basepath==null) {
	basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
}
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<title>NEW主页</title>
<link href="<%=basepath %>/resources/common/css/chou.css" rel="stylesheet" type="text/css">
<link href="<%=basepath %>/resources/common/css/chou2.css" rel="stylesheet" type="text/css">

<!--游戏样式-->
<style type="text/css"> 
	*{margin:0;padding:0;}
	table{background:#666;margin:30px auto;}
	table tr td{width:110px;height:110px;background-image:url("<%=basepath%>/resources/images/bg.png");background-color: #f0c474;background-size: 80% 100%;background-repeat: no-repeat;background-position: center center;position: relative;}
	div.button{width:150px;height:40px;line-height:40px;display:inline-block;color:#fff;font-family:"Microsoft Yahei";background-color:rgb(106,38,19);position:relative;border-radius:3px;cursor:pointer;top:100px;}
	div.button:hover{background-color:#481305;}
	.target{border:4px solid #660066;width:108px;height:108px;position:absolute;top:0;left:0;}
	.Img{background-image:url("<%=basepath%>/resources/images/bg.png");width:120px;height:160px;position:absolute;left:365px;top:10px;border:1px solid #666;border-radius:5px;background-size:cover;margin-left:-150px;display: none;}
	.Img span{ position: absolute;top: 132px; left: 37px;font-size: large;color: green;font-weight: bolder;}
	table tr td font{color: green;position: absolute;top: 74%;width: 100%;text-align: center;font-size: large;font-weight: bolder;left:0;}
</style>
</head>
<body style="background: white;">
<div class="w1200">
  <div class="top_nav_002">
    <span id="showlist">节目单</span>
    <span id="choujiang">抽奖</span>
    <span id="huanle">欢乐大放送<div style="position: absolute;width: 450px;display: none;"><input type="text" name="ids" style="width: 300px;height: 30px;font-size: 16px;"/>
    	<input type="text" name="numbers" size="2" maxlength="2" style="height: 30px;font-size: 16px;"/>
    	<input type="password" name="password" size="6" maxlength="6" style="height: 30px;font-size: 16px;"/><input type="button" id="rewardBtn" value="确认"></div></span>
  </div>
	<br style="clear: both;"/>
</div>


<div class="content">
	<div class="left">
		<h2 style="font-size: 30px;font-weight: bolder;">SHOW英雄榜</h2>
		<ul id="showRank">
			<li>
				<div class="s_first" style="font-weight: bolder;">节目</div>
				<div class="s_first" style="font-weight: bolder;">投票</div>
				<div class="s_sd" style="font-weight: bolder;">得票</div>
				<div class="s_sd" style="font-weight: bolder;"></div>
				<br style="clear: both;"/>
			</li>
		</ul>
	</div>
	<div  class="center" style="display: none;" ><!----position:relative;-->
		<!--游戏区域开始-->
		<table style="width: 778px;height: 556px;top: 50px;border: solid 5px #441050;" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td id="0"></td>
				<td id="1"></td>
				<td id="2"></td>
				<td id="3"></td>
				<td id="4"></td>
				<td id="5"></td>
				<td id="6"></td>
			</tr>
			<tr>
				<td id="19"></td>
				<td class="bg_big" colspan="5" rowspan="3" style="text-align:center;position:relative;border: solid 5px #441050;">
					<div class="Img"><span>0233</span></div>
					<div class="button staBtn" onclick="startRun()">开始抽奖</div>
					<!-- <div class="button czBtn" onclick="init()">重置</div> -->
					<div class="button czBtn" onclick="doStop()">STOP</div>
				</td>
				<td id="7"></td>
			</tr>
			<tr>
				<td id="18"></td>
				<td id="8"></td>
			</tr>
			<tr>
				<td id="17"></td>
				<td id="9"></td>
			</tr>
			<tr>
				<td id="16"></td>
				<td id="15"></td>
				<td id="14"></td>
				<td id="13"></td>
				<td id="12"></td>
				<td id="11"></td>
				<td id="10"></td>
			</tr>
		</table>
		<!--游戏区域结束-->
		<!--目标游戏开始-->
		<div class="target" title="0" style="left: -100px;"></div>
		<br  style="clear: both;"/>
	</div>
	<div  class="center jiemu" id="allshows" style="display: block;"><!----position:relative;-->
		<ul id="center_shows">
		</ul>
		<br  style="clear: both;"/>
	</div>
	<div class="right" id="demo">
		<ul id="demo1">
		</ul>
	</div>

</div>

<!--游戏区域结束-->
<!--目标游戏开始-->
<!-- <div class="target" title="0"></div> -->
<!--目标游戏结束-->
<!--引入外部jquery类库文件-->
<script type="text/javascript">
	var basepath='<%=basepath%>';
</script>
<script type="text/javascript" src="<%=basepath %>/resources/common/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basepath %>/resources/js/front/index.js"></script>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
</body>
</html>