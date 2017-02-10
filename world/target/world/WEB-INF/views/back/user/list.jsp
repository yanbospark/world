<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../resources/common/jsp/validation.jsp"%>
<div class="col-lg-6">
	<div class="panel panel-default">
		<div class="panel-heading">员工列表</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="table-responsive">
				<!-- <input type="text" class="form-control" id="inputSuccess"> -->
				姓名：<input type="text" style="border-color: #3c763d;" name="username"
					placeholder="" />
				<button type="button" class="btn btn-info" ng-click="search()">搜索</button>
				<button type="button" class="btn btn-primary" ng-click="add()">新增</button>
				<br>
				<br>
				<table class="table">
					<thead>
						<tr class="success">
							<th>ID</th>
							<th>部门</th>
							<th>姓名</th>
							<th>微信昵称</th>
							<th>手机号</th>
							<th>身份证</th>
							<th>中奖次数</th>
							<th align="rigth"></th>
						</tr>
					</thead>
					<tbody>
						<tr class="info" ng-repeat="user in users">
							<td>{{user.id}}</td>
							<td>{{user.department}}</td>
							<td>{{user.username}}</td>
							<td>{{user.nickname}}</td>
							<td class="center">{{user.tel}}</td>
							<td class="center">{{user.idCard}}</td>
							<td class="center" align="center">{{user.winTimes}}</td>
							<td align="center">
								<button type="button" class="btn btn-primary btn-circle" ng-click="edit(user.id)"><i class="fa fa-list"></i></button>
								<button type="button" class="btn btn-warning btn-circle" ng-click="del(user.id)"><i class="fa fa-times"></i></button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
