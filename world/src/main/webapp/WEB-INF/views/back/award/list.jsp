<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../resources/common/jsp/validation.jsp"%>
<div class="col-lg-6">
	<div class="panel panel-default">
		<div class="panel-heading">奖品列表</div>
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
							<th>奖品名称</th>
							<th>奖品数量</th>
							<th>剩余数量</th>
							<th>价格</th>
							<th align="rigth"></th>
						</tr>
					</thead>
					<tbody>
						<tr class="info" ng-repeat="award in awards">
							<td>{{award.id}}</td>
							<td>{{award.name}}</td>
							<td>{{award.amount}}</td>
							<td class="center">{{award.leftAmount}}</td>
							<td class="center">{{award.price}}</td>
							<td align="center">
								<button type="button" class="btn btn-primary btn-circle" ng-click="edit(award.id)"><i class="fa fa-list"></i></button>
								<button type="button" class="btn btn-warning btn-circle" ng-click="del(award.id)"><i class="fa fa-times"></i></button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
