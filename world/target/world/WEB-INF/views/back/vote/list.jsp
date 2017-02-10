<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../resources/common/jsp/validation.jsp"%>
<div class="col-lg-6">
	<div class="panel panel-default">
		<div class="panel-heading">投票设置</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="table-responsive">
				<!-- <input type="text" class="form-control" id="inputSuccess"> -->
				奖品名称：<input type="text" style="border-color: #3c763d;" name="name"
					placeholder="" />
				<button type="button" class="btn btn-info">搜索</button>
				<button type="button" class="btn btn-primary" ng-click="add()">新增</button>
				{{message}}
				<br>
				<br>
				<table class="table">
					<thead>
						<tr class="success">
							<th>ID</th>
							<th>名称</th>
							<th>分值</th>
							<th align="rigth"></th>
						</tr>
					</thead>
					<tbody>
						<tr class="info" ng-repeat="vote in votes">
							<td>{{vote.id}}</td>
							<td>{{vote.name}}</td>
							<td>{{vote.score}}</td>
							<td align="center">
								<button type="button" class="btn btn-primary btn-circle" ng-click="edit(vote.id)"><i class="fa fa-list"></i></button>
								<button type="button" class="btn btn-warning btn-circle" ng-click="del(vote.id)"><i class="fa fa-times"></i></button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
