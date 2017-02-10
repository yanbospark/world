<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../resources/common/jsp/validation_front.jsp" %>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
  /* reset */
*,*:before,*:after {-webkit-box-sizing:border-box; box-sizing:border-box;}
*{margin:0;padding:0;font-weight:normal;}
html,body,h1,h2,h3,h4,h5,h6,div,dl,dt,dd,ul,ol,li,p,blockquote,pre,hr,figure,table,caption,th,td,form,fieldset,legend,input,button,textarea,menu{margin:0;padding:0;-webkit-tap-highlight-color:rgba(255,255,255,0);}
header,footer,section,article,aside,nav,hgroup,address,figure,figcaption,menu,details{display:block;}
table{border-collapse:collapse;border-spacing:0;table-layout:fixed;}
caption,th{text-align:left;font-weight:normal;}
html,body,fieldset,img,iframe,abbr,button,input{border:0;}
i,cite,em,var,address,dfn{font-style:normal;}
[hidefocus],summary{outline:0;}
ul,ol,li{list-style:none;}
h1,h2,h3,h4,h5,h6,small{font-size:100%;}
sup,sub{font-size:83%;}
pre,code,kbd,samp{font-family:inherit;}
q:before,q:after{content:none;}
textarea{overflow:auto;resize:none;}
label,summary{cursor:default;}
a,button{cursor:pointer;}
h1,h2,h3,h4,h5,h6,em,strong,b{/*font-weight:bold;*/}
del,ins,u,s,a,a:hover{text-decoration:none;}
body,textarea,input,button,select,keygen,legend{font:1.2rem/1.14 arial,\5b8b\4f53;color:#333;outline:0;}
body{background:#fff;}
a,a:hover{color:#333;}
input,img{vertical-align:middle;}
input,button,select,textarea{outline:none;border-radius:0;}
input[type="search"]{-webkit-appearance:none;} 
input::-webkit-search-decoration,input::-webkit-search-cancel-button{display:none;}
* html,* html body{background-image:url(about:blank);background-attachment:fixed;}
/*直接加类的浮动闭合*/
.fix:after{content:".";display:block;height:0;clear:both;visibility:hidden;}
.fix{display:inline-block;}
* html .fix{height:1%;}
.fix{display:block;}
/*加div浮动闭合*/
.clear{height:0; clear:both;}

/*公用头部样式*/
html{font-size:14px;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;}
input[type='text']{color:#c9c9c9;}



/* 重置 */

a,img,button,input,textarea{ border: 0;text-decoration:none;}
body, h1, h2, h3, h4, h5, h6, p, blockquote, dl, dt, dd, ul, ol, li, pre, form, fieldset, legend, button, input, textarea, th, td, figure, select,b{margin: 0; padding: 0; font-weight:normal; list-style:none; }
img{ display:block;}
html {
  height: 100%;
  width: 100%;
  font-size: 20px;
  outline: 0;
  -webkit-text-size-adjust:none;
}
body {
  height: 100%;
  margin: 0;
  -webkit-user-select: none;
  position: relative;
  background-color:#f2f2f2;
}

/*公用样式*/
*{font-weight: normal;font-family: SourceHanSansCN,PingFangSC-Light,PingFangSC-Regular,San Francisco, Hiragino Sans GB, WenQuanYi Micro Hei, sans-serif;}
.fl{float: left;}
.fr{float: right;}
.left{margin-left: 0.6rem;}
.right{margin-right: 0.6rem;}
.por_r{ position:relative;}
.por_a{ position:absolute;}
.por_f{position: fixed;}
.clear_fix{ clear:both;}

/*2个游戏*/
.bg_box{position: fixed;width: 100%;overflow: hidden;}
.bg_box > img{width: 100%;}
.game_sm{width: 100%;overflow: hidden;position: fixed;bottom: 7.25rem;}
.game_sm li{
  width: 13.8rem;
  border-radius: 0.15rem;
  background-color:rgba(0,0,0,0.3);
  font-size: 0.6rem;
  color: #ffffff;
  text-align: left;
  line-height: 2.02;
  margin:0 auto;
  margin-bottom: 0.5rem;
  overflow: hidden;
  padding:0.5rem 0.5rem;

}
.game_sm li h3{font-size: 0.9rem;line-height: 1.33;}
.btn_boxs{width: 100%;overflow: hidden;padding:0 2.15rem;position:fixed;bottom: 1.35rem;}
.btn_boxs > a{display:block;width: 9.55rem;height: 3.5rem;margin-left: 40px;}
.btn_boxs > a img{width: 100%;height: 100%;}

</style>
<script src="<%=basepath %>/resources/common/js/jquery.min.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <meta name="description" content="">
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>嘟嘟牛游戏报名</title>

</head>
<body>
<div class="bg_box">
  <img src="<%=basepath %>/resources/images/bg_big.png" alt="" class="bg">
  <ul class="game_sm">
    <li>
      <h3>游戏规则：</h3>
      <p>1.这是一段长又短的游戏说明长度真的刚刚好。</p>
      <p>2.超过了这么多字容易产生文字换行效果很丑。</p>
      <p>3.这是一段非常长的游戏说明长度一定不合适看着很不舒服。</p>
    </li>
  </ul>
  <div class="btn_boxs">
    <a class="btn_01 fl"><img src="<%=basepath %>/resources/images/btn_01.png" id="join"></a>
  </div>
</div>
<script type="text/javascript">
	$(function(){
		$("#join").click(function(){
			getResource("GET","<%=basepath%>/front/lottery/gamer",{},1);
		})
		var interval=setInterval(function(){
			getResource("GET","<%=basepath%>/front/lottery/allGamer",{},2);
		},3000);
	})
	function getResource(type,url,data,state){
		$.ajax({
			type:type,
			url:url,
			data:data,
			dataType:"json",
			success:function(resp){
				if(state==1){
					alert(resp.message);
				}else if(state==2){
					var gamers=decodeURIComponent(resp.message);
					$(".game_sm ").empty();
					$(".game_sm").append("<li><h3>游戏规则：</h3>\
						      <p>1.这是一段长又短的游戏说明长度真的刚刚好。</p>\
						      <p>2.超过了这么多字容易产生文字换行效果很丑。</p>\
						      <p>3.这是一段非常长的游戏说明长度一定不合适看着很不舒服。</p></li>");
					$(".game_sm").append("<li id=\"names\"><p>报名人员："+gamers+"</p></li>");
				}
			},
			error:function(){
				console.log("Server error!");
			}
		})
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
