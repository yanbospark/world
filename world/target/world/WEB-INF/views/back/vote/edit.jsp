<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">投票管理</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">投票配置</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form role="form">
							<div class="form-group" ng-hide="true">
								<input type="hidden" class="form-control" id="inputSuccess" ng-model="vote.id">
							</div>
							<div class="form-group">
								<label>投票名称：</label> 
								<input type="text" class="form-control" id="inputSuccess" ng-model="vote.name">
							</div>
							<div class="form-group">
								<label>分值：</label> 
								<input type="text" class="form-control" id="inputSuccess" ng-model="vote.score">
							</div>
							<button type="submit" class="btn btn-primary btn-lg" ng-click="submit()">提交</button>
							<button type="reset" class="btn btn-warning btn-lg">重置</button>
						</form>
					</div>
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
