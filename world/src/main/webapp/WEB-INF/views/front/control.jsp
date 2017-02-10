<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<!doctype html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <meta name="description" content="">
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>控制器</title>
</head>
<script type="text/javascript" src="<%=basepath %>/resources/common/js/jquery.min.js"></script>
<script type="text/javascript">
	var basepath='<%=basepath%>';
	var p=0;
	$(function(){
		$("#nextShow").click(function(){
			if(p==1) return;
			p=1;
			getResource("GET",basepath+"/front/commonShow/nextShow",{},1);
		});
		$("#change").click(function(){
			getResource("GET",basepath+"/front/commonShow/change",{type:$("input[name='type']").val()},2);
		});
		$("#doRank").click(function(){
			getResource("GET",basepath+"/front/commonShow/changeRankState",{},3);
		})
		$("#game").click(function(){
			getResource("GET",basepath+"/front/lottery/rewardGameScore",{},4);
		})
	})
	function getResource(type,url,data,state){
		$.ajax({
			type:type,
			url:url,
			data:data,
			dataType:"json",
			success:function(resp){
				if(state==1){
					p=0;
					alert("操作成功!");
				}else if(state==2){
					alert("操作成功!");
				}else if(state==3){
					alert(resp.message);
				}else if(state==4){
					alert(resp.message);
				}
			},
			error:function(){
				alert("Server error!");
			}
		})
	}
</script>
<body>

	<input type="text" name="type" placeholder="0码 1图 2名"/><input type="button" value="转换" id="change"/>
	<input type="button" value="下一个节目" id="nextShow"/><input type="button" value="排名送幸运码" id="doRank"/>
	<input type="button" value="游戏送分" id="game"/>
</body>
</html>