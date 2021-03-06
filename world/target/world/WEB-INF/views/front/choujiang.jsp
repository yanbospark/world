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
    <title>嘟嘟牛年会盛典</title>
<link href="<%=basepath%>/resources/common/css/choujiang.css" rel="stylesheet" type="text/css">
<link href="<%=basepath%>/resources/common/css/Default.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="choujiang_box">
  <div class="banner">
    <img src="<%=basepath%>/resources/images/new1.jpg" id="u_img">
  </div>
  <div class="xx_box" id="user_info">
  </div>
</div>
<script type="text/javascript">
	var basepath='<%=basepath%>';
	var preImg=basepath+"/resources";
	var maxSerial=2;//初始化获取第一个节目信息
	var userfirst=true;//用户信息第一次加载
	
	$(function(){
		getResource("GET",basepath+"/front/home/userinfo",{},0);	
		getResource("GET",basepath+"/front/home/showinfo/"+maxSerial,{},1);
		var updateInfo=setInterval(function(){
			getResource("GET",basepath+"/front/home/userinfo",{},0);	
			getResource("GET",basepath+"/front/home/showinfo/"+maxSerial,{},1);
		},5000);
	})
		
	function getResource(type,url,data,state){
		$.ajax({
			type:type,
			url:url,
			data:data,
			dataType:"json",
			success:function(resp){
				if(state==0) userinfo(resp);
				else if(state==1) showinfo(resp);
				else if(state==2) checkStatus(resp);
				else if(state==3) changeImg(data.showId,data.score,resp);
			},
			error:function(){
				console.log("Server error!");
			}
		})
	}
	//用户信息
	function userinfo(resp){
		if(resp.code=="0"){
			var user=eval("("+decodeURIComponent(resp.message)+")");
			console.log(user);
			if(user.img){//有图片才更新
				$("#u_img").attr("src",user.img);
			}
			if(userfirst){
				$("#user_info").append("<span>ID:"+user.id+"</span><span>牛主："+user.username+"</span><span>手机号："+user.tel+"</span><p id=\"userserial\">牛号："+user.serialnumbers+"</p>");
				userfirst=false;
			}else{
				$("#userserial").text("牛号："+user.serialnumbers);
			}
		}else{
			if(userfirst) $("#user_info").append("<p>"+resp.message+"</p>")
		}
	}
	//节目信息
	function showinfo(resp){
		if(resp.code=="0"){
			var shows=eval("("+decodeURIComponent(resp.message)+")");
			for(var i=0;i<shows.length;i++){
				var show=shows[i];
				console.log(show);
				var vote_state="未开始";
				if(show.state==1) vote_state="进行中...";
				else if(show.state==2) vote_state="已结束";
				maxSerial=resp.maxSerial;//页面当前节目排序的最大值
				if($("#haveshow_"+show.id).attr("class")){
					//先清空已经投过的
					for (var k = 1; k <= 4; k++) {
						$("#show_"+show.id+"_"+k).attr("src",preImg+"/images/0"+k+".png");
						$("#show_"+show.id+"_"+k).attr("onclick","sendvote("+show.id+","+show.state+","+k+",0)");
					}
					//已经存在的做局部修改
					if(show.score>0){
						$("#show_"+show.id+"_"+show.score).attr("src",preImg+"/images/"+show.score+".png");
						$("#show_"+show.id+"_"+k).attr("onclick","sendvote("+show.id+","+show.state+","+show.score+",1)");
					}
					$("#voteshow_"+show.id).text(vote_state);
				}else{
					//页面第一次加载使用拼接形式
					var imgStr="";
					if(show.score>0){
						//投票的是亮的,没投票的是黑的
						for (var j = 1; j <= 4; j++) {
							if(show.score==j) imgStr+="<span class=\"span_img\"><img src=\""+preImg+"/images/"+j+".png\" id=\"show_"+show.id+"_"+j+"\" onclick=\"sendvote("+show.id+","+show.state+","+j+",1)\"></span>";
							else imgStr+="<span><img src=\""+preImg+"/images/0"+j+".png\" id=\"show_"+show.id+"_"+j+"\" onclick=\"sendvote("+show.id+","+show.state+","+j+",0)\"></span>";
						}
					}else{
						for (var k = 1; k <= 4; k++) {
							imgStr+="<span><img src=\""+preImg+"/images/0"+k+".png\" id=\"show_"+show.id+"_"+k+"\" onclick=\"sendvote("+show.id+","+show.state+","+k+",0)\"></span>";
						}
					}
					$("body").append("<div class=\"jm_box\" id=\"haveshow_"+show.id+"\">\
						<div class=\"fl img_box\"><img src=\""+show.teamImg+"\"> </div>\
					    <div class=\"fr\">\
					      <div class=\"zt\"><span>节&nbsp;&nbsp;&nbsp;目：</span><p>"+show.name+"</p></div>\
					      <div class=\"zt\"><span>投&nbsp;&nbsp;&nbsp;票：</span><p id=\"voteshow_"+show.id+"\">"+vote_state+"</p></div>\
					      <div class=\"dianzan\">"+imgStr+"</div></div></div></div>");
				}
			}
		}
	}

	//点击图标投票
	function sendvote(showId,votestate,score,ifVoted){
		if(votestate!=1) return;//投票非进行中
		$("#show_"+showId+"_"+1).attr("src",preImg+"/images/01.png");
		$("#show_"+showId+"_"+2).attr("src",preImg+"/images/02.png");
		$("#show_"+showId+"_"+3).attr("src",preImg+"/images/03.png");
		$("#show_"+showId+"_"+4).attr("src",preImg+"/images/04.png");
		$("#show_"+showId+"_"+score).attr("src",preImg+"/images/"+score+".png");
		if(ifVoted>0) return;//已经投过
		getResource("GET",basepath+"/front/home/vote",{showId:showId,score:score},3);
	}
	//改变投票之后的状态
	function changeImg(showId,score,resp){
		if(resp.code!=0){
			$("#show_"+showId+"_"+score).attr("src",preImg+"/images/0"+score+".png");
			alert(resp.message);
		}
	}
	
	
	
	//点击事件只能一次性提交：点击开始，然后1分钟计时开始
	var seconds=60;
	var click=0;
	//点击开始
	function start(){
		//点击开始然后直接请求接口，将开始了这个信号发到后台，让后台也开始定时统计，免得别人刷新页面这种行为
		getResource("POST",basepath+"/front/home/click",{},2);
	}
	
	function checkStatus(resp){
		if(resp.code=="1") {
			alert(resp.message);
			return;
		}
		$("body").append("<button onclick=\"doCount()\">快速点击召唤神龙</button>");
		var intervar=setInterval(function(){
			console.log(seconds);
			seconds--;
			if(seconds<0){
				clearInterval(intervar);
				//发送http请求将点击的次数上传
				getResource("POST",basepath+"/front/home/click",{count:click},3);	
			}
		},1000);
	}
	
	//点击触发事件
	function doCount(){
		if(seconds<0) return;//时间结束就停止统计
		click++;
		$("#click_count").text("点击了 "+click+" 次");
	}
</script>

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


</body>
</html>
