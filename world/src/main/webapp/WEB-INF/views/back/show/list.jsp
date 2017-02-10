<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../resources/common/jsp/validation.jsp"%>
<div class="col-lg-6">
	<div class="panel panel-default">
		<div class="panel-heading">节目列表</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="table-responsive">
				<!-- <input type="text" class="form-control" id="inputSuccess"> -->
				节目名称：<input type="text" style="border-color: #3c763d;" name="name"
					placeholder="" />
				<button type="button" class="btn btn-info">搜索</button>
				<button type="button" class="btn btn-primary" ng-click="add()">新增</button>
				{{message}}
				<br>
				<br>
				<table class="table">
					<thead>
						<tr class="success">
							<th>节目名称</th>
							<th>顺序</th>
							<th>表演人员</th>
							<th>演出状态</th>
							<th>投票状态</th>
							<th>得票数</th>
							<th>排名</th>
							<th align="rigth"></th>
						</tr>
					</thead>
					<tbody>
						<tr class="info" ng-repeat="show in shows">
							<td>{{show.name}}</td>
							<td>{{show.serial}}</td>
							<td>{{show.performers}}</td>
							<td class="center">{{show.showState}}</td>
							<td>{{show.state}}</td>
							<td>{{show.counts}}</td>
							<td class="center">{{show.rank}}</td>
							<td align="center">
								<button type="button" class="btn btn-primary btn-circle" ng-click="edit(show.id)"><i class="fa fa-list"></i></button>
								<button type="button" class="btn btn-warning btn-circle" ng-click="del(show.id)"><i class="fa fa-times"></i></button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
