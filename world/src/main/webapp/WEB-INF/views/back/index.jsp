<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="../../resources/common/jsp/header.jsp" %>--%>
<%@ include file="../../resources/common/jsp/validation.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>年会首页</title>

    <link href="<%=basepath%>/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basepath%>/resources/sb-admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="<%=basepath%>/resources/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="<%=basepath%>/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="<%=basepath%>/resources/sb-admin/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="<%=basepath%>/resources/sb-admin/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
    <script src="<%=basepath%>/resources/common/js/angular.min.js"></script>
	<script src="<%=basepath%>/resources/common/js/angular.route.js"></script>
</head>

<body>

    <div id="wrapper" ng-app="indexApp">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#/">嘟嘟牛年会后台主页</a>
            </div>
            <!-- /.navbar-header -->
            
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="#/config"><i class="fa fa-table fa-fw"></i> 系统配置</a>
                        </li>
                        <li>
                            <a href="#/award"><i class="fa fa-table fa-fw"></i> 奖品列表</a>
                        </li>
                        <li>
                            <a href="#/user"><i class="fa fa-edit fa-fw"></i> 用户管理</a>
                        </li>
                        <li>
                            <a href="#/show"><i class="fa fa-edit fa-fw"></i>节目管理</a>
                        </li>
                        <li>
                            <a href="#/winner"><i class="fa fa-edit fa-fw"></i>中奖记录</a>
                        </li>
                        <li>
                            <a href="#/vote"><i class="fa fa-edit fa-fw"></i> 投票策略</a>
                        </li>
                        <li>
                            <a href="#/userVote"><i class="fa fa-edit fa-fw"></i>投票记录</a>
                        </li>
                        <li>
                            <a href="#/"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="panels-wells.html">Panels and Wells</a>
                                </li>
                                <li>
                                    <a href="buttons.html">Buttons</a>
                                </li>	
                                <li>
                                    <a href="notifications.html">Notifications</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div id="page-wrapper" ng-view>
        </div>
        
    </div>
    <script src="<%=basepath%>/resources/sb-admin/vendor/jquery/jquery.min.js"></script>
    <script src="<%=basepath%>/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basepath%>/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>
    <script src="<%=basepath%>/resources/sb-admin/dist/js/sb-admin-2.js"></script>
    <script src="<%=basepath%>/resources/sb-admin/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="<%=basepath%>/resources/sb-admin/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="<%=basepath%>/resources/sb-admin/vendor/datatables-responsive/dataTables.responsive.js"></script>
 	<script type="text/javascript">
 	var basepath='<%=basepath%>';
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });	
    </script>
	<script src="<%=basepath%>/resources/js/index.js"></script>
	<script src="<%=basepath%>/resources/js/awardCtrl.js"></script>
	<script src="<%=basepath%>/resources/js/userCtrl.js"></script>
	<script src="<%=basepath%>/resources/js/configCtrl.js"></script>
	<script src="<%=basepath%>/resources/js/winnerCtrl.js"></script>
	<script src="<%=basepath%>/resources/js/showCtrl.js"></script>
	<script src="<%=basepath%>/resources/js/voteCtrl.js"></script>
	<script src="<%=basepath%>/resources/js/userVoteCtrl.js"></script>
</body>
</html>
