	var n=0;
	var imagelen=0;
	var images=[];
	var st=0;//隐藏状态
	$(function(){
		$(".top_nav_002 span").click(function(){
			if($(this).attr("id")=="showlist"){
				doStop();
				$(".center").hide();
				$("#allshows").show();
			}else if($(this).attr("id")=="choujiang"){
				$(".center").show();
				$("table tr td font").empty();
				init();
				$("#allshows").hide();
			}
		})
		$("#huanle").dblclick(function(){
			if(st==0){
				st=1;
				$(this).children("div").css("display","block");
			}else{
				st=0;
				$(this).children("div").css("display","none");
			}
		})
		$("#rewardBtn").click(function(){
			var ids=$("input[name='ids']").val();
			var password=$("input[name='password']").val();
			var numbers=$("input[name='numbers']").val();
			if(!ids||!password||!numbers) {
				alert("请输入必要数据");
				return;
			}
			getResource("GET",basepath+"/front/lottery/rewardSerial",{ids:encodeURIComponent(ids),password:password,numbers:numbers},5);
		})
		//双击查看并且上传中奖号
		$(".Img").dblclick(function(){
			var winNumber=$(this).children("span").text();
			var reg = new RegExp("^[0-9]*$");
			console.log(winNumber.length);
			if(!reg.test(winNumber)){//非数字就是人名
				getResource("GET",basepath+"/front/commonShow/addWinTimes",{username:encodeURIComponent(winNumber)},6);
			}else{//数字
				if(winNumber=="0000") return; 
				getResource("GET",basepath+"/front/commonShow/username",{serial:winNumber},4);
			}
		})
		getResource("GET",basepath+"/front/commonShow/teamShow",{},2);
		getResource("GET",basepath+"/front/commonUser/userShow",{},3);
		var interval=setInterval(function(){
			getResource("GET",basepath+"/front/commonShow/teamShow",{},2);
			getResource("GET",basepath+"/front/commonUser/userShow",{},3);
		},3000);
	})
	//初始状态
	function init(){
		$('div.target').stop(true);
		getResource("GET",basepath+"/front/commonShow/imgShow",{},1);
	}
	//通用ajax请求
	function getResource(type,url,data,state){
		$.ajax({
			type:type,
			url:url,
			data:data,
			dataType:"json",
			success:function(resp){
				if(state==1) fillImgs(resp);
				else if(state==2) fillShows(resp);
				else if(state==3) fillUsers(resp);
				else if(state==4) printWinner(resp);
				else if(state==5) rewardSerial(resp);
			},
			error:function(){
				console.log("Server error!");
			}
		})
	}
	//初始化填充图片
	var type="0";//0是中奖码,1是图片
	function fillImgs(resp){
		type=resp.type;
		if(type=="2"){
			images=decodeURIComponent(resp.images);
			images=images.substring(1,images.length-1);
			images=images.split(", ");
		}else{
			images=resp.images;
		}
		imagelen=images.length;
		var less=imagelen%20;
		if(less!=0){//不是整数个，得填充非人物图片
			for (var i = 0; i < 20-less; i++) {
				if(type=="1") images[imagelen+i]=basepath+"/resources/images/bg.png";
				else images[imagelen+i]="0000";
			}
		}
		imagelen=images.length;
		console.log("len="+imagelen+" images="+images);
		if(type=="1"){
			for (var i = 0; i < 20; i++) {
				$("#"+i).css("background-size","cover");
				$("#"+i).css("background-image","url("+images[i]+")");
			}
		}else{
			for (var i = 0; i < 20; i++) {
				$("#"+i).append("<font>"+images[i]+"</font>")
			}
		} 
		moveToNext(0);
	}
	
	function moveToNext(i){
		var $target = $('div.target');//代表找到div.target的元素
		var elem=$('#'+(i%20));
		var offset = elem.offset();
		$target.animate({top:offset.top-2,left:offset.left-2},5,function(){
			//图片显示逻辑
			if(i!=0){
				var bigCycle=parseInt(i/imagelen);//循环倍数
				var index=0;
				if(bigCycle<1){
					if(i>=20){
						index=i;
						if(type=="1") elem.css("background-image","url("+images[index]+")");
						else elem.empty().append("<font>"+images[index]+"</font>");
					}
				}else if(bigCycle==1){
					index=i-imagelen;
					if(type=="1") elem.css("background-image","url("+images[index]+")");
					else elem.empty().append("<font>"+images[index]+"</font>");
				}else if(bigCycle>1){
					index=i-bigCycle*imagelen;
					if(type=="1") elem.css("background-image","url("+images[index]+")");
					else elem.empty().append("<font>"+images[index]+"</font>");
				}
				//注入被选中图片的ID
				$target.attr("title",i%20);
			}
		});
		n = i;
	}
	//每次启动都重新加载数据
	function startRun(){
		init();
		$(".Img").css("display","none");
		var randomNumber =10000;
		var m = n;
		for(var i=m;i<randomNumber+m;i++){
			moveToNext(i);
		}
	}
	//停止
	function doStop(){
		$('div.target').stop(true);
		var id=$('div.target').attr("title");
		if(type=="1"){
			var img=$("#"+id).css("background-image");
			$(".bg_big").css("background-image",img);
		}else{
			var winSerial=$("#"+id).text();
			$(".Img span").text(winSerial);
			$(".Img").css("display","block");
		}
	}
	
	//填充节目信息
	var newShows=[];
	function fillShows(resp){
		debugger;
		newShows=eval("("+decodeURIComponent(resp.message)+")");
		for (var i = 0; i < newShows.length; i++) {
			//填充左侧
			var show=newShows[i];
			var elem=$("#new_"+show.id);
			var divElem=elem.children("div");
			var voteState=changeStyle(0,show.state);
			var counts=show.counts;
			//已经存在的节目就做更新数据操作
			if(divElem.length){
				var first_div=divElem.eq(1);
				if(first_div.text()!=voteState){
					first_div.fadeOut("fast");
					first_div.fadeIn(2000);
					first_div.text(voteState);
				}
				var second_div=divElem.eq(2);
				if(second_div.text()!=counts){
					second_div.fadeOut("fast");
					$("#up_"+show.id).fadeOut("slow");
					if(parseInt(second_div.text())>counts){
						$("#up_"+show.id).attr("src",basepath+"/resources/images/down.png");
					}else{
						$("#up_"+show.id).attr("src",basepath+"/resources/images/up.png");
					}
					$("#up_"+show.id).fadeIn("slow");
					second_div.text(counts);
					second_div.fadeIn(2000);
				}
			}else{
				$("#showRank").append("<li id=\"new_"+show.id+"\"><div class=\"s_first\">"+show.name+"</div><div class=\"s_first\">"+voteState+"</div><div class=\"s_sd\">"+counts+"</div>"+
						"<div class=\"s_sd\"><img id=\"up_"+show.id+"\" src=\"\" style=\"display:none;margin-top: 10px;width:30%;\"/></div><br style=\"clear: both;\"/></li>");
			}
		}
		
		//填充中间：先清空再填充
		var centerElems=$("#center_shows");
		centerElems.empty();
		for (var i = 0; i < newShows.length; i++) {
			var centerShow=newShows[i];
			var showState=changeStyle(1,centerShow.showState);
			var voteState=changeStyle(0,centerShow.state);
			var time="";
			if(centerShow.showState==1) time="开始时间："+centerShow.startTime;
			else if(centerShow.showState==2) time="结束时间："+centerShow.endTime;
			centerElems.append("<li><div class=\"img\"><img src=\""+centerShow.teamImg+"\"/></div>\
					<div class=\"con\"><h4 style=\"font-weight:bolder;\">"+centerShow.department+":"+centerShow.name+"</h4><p style=\"font-weight:bolder;\">出演人员:"+centerShow.performers+"</p><p><font color=\"green\" style=\"font-weight:bolder;font-size:20px;\">"+showState+"&nbsp;&nbsp;投票"+voteState+"</font></p><p style=\"font-weight:bolder;\">"+time+"</p></div><br style=\"clear: both;\"/></li>");
		}
	}
	//填充右侧用户信息
	function fillUsers(resp){
		var users=eval("("+decodeURIComponent(resp.message)+")");
		var u_elem=$(".right ul");
		u_elem.empty();
		u_elem.append("<li style=\"height:40px;font-size:30px;font-weight:bolder;\">牛牛英雄榜</li>");
		for (var i = 0; i < users.length; i++) {
			var u_one=users[i];
			u_elem.append("<li><font color=\"green\" style=\"font-weight:bold;\">"+u_one.username+"</font>有<font color=\"green\" style=\"font-weight:bold;\">"+u_one.numbers+"</font>个中奖码中<font color=\"green\" style=\"font-weight:bold;\">"+u_one.winTimes+"</font>次</li>");
		}
	}
	function changeStyle(v,p){
		if(v==0){
			if(p==0) return "未开始";
			else if(p==1) return "进行中...";
			else if(p==2) return "已结束";
		}else if(v==1){
			if(p==0) return "节目未开始";
			else if(p==1) return "节目进行中...";
			else if(p==2) return "节目已结束";
		}
	}
	//显示中奖人名字
	function printWinner(resp){
		$(".Img span").text(decodeURIComponent(resp.message));
	}
	
	function rewardSerial(resp){
		$("input[name='ids']").val("");
		$("input[name='password']").val("");
		$("input[name='numbers']").val("");
		alert(resp.message);
	}