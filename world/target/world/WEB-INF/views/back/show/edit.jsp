<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">节目管理</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">节目配置</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form method="post" enctype="multipart/form-data" id="imageForm">	
							<input type="file" name="image" id="u_image" style="width: 15%;float: left;"/>
							<input type="hidden" name="id" value="{{show.id}}" id="u_id"/>
							<input type="button" name="上传" value="上传"  ng-click="uploadImage()" style="float:left;"/>
						</form>
						<br><br><br>
						<form role="form">
							<div class="form-group" ng-hide="true">
								<input type="hidden" class="form-control" id="inputSuccess" ng-model="show.id">
							</div>
							<div class="form-group" ng-show="myshow">
								<label>图片：</label> 
								<input type="hidden" class="form-control" id="realImage" ng-model="show.teamImg">
								<img alt="" src="{{show.teamImg}}" id="showImage" height="200px"/>
							</div> 
							<div class="form-group">
								<label>节目名称：</label> 
								<input type="text" class="form-control" id="inputSuccess" ng-model="show.name">
							</div>
							<div class="form-group">
								<label>部门</label> 
								<select class="form-control" ng-model="show.department">
									<option value="总经办">总经办</option>
									<option value="研发部">研发部</option>
									<option value="市场部">市场部</option>
									<option value="产品部">产品部</option>
									<option value="行政部">行政部</option>
									<option value="人事部">人事部</option>
									<option value="增值运营部">增值运营部</option>
									<option value="济南分公司">济南分公司</option>
									<option value="行政/人事部">行政/人事部</option>
									<option value="海外拓展部">海外拓展部</option>
								</select>
							</div>
							<div class="form-group">
								<label>表演人员：</label> 
								<input type="text" class="form-control" id="inputSuccess" ng-model="show.performers">
							</div>
							<div class="form-group">
								<label>人员ID串：</label> 
								<input type="text" class="form-control" id="inputSuccess" ng-model="show.userIds" placeholder="多个id请以英文,号隔开">
							</div>
							<div class="form-group">
								<label>顺序：</label> 
								<input type="text" class="form-control" id="inputSuccess" ng-model="show.serial">
							</div>
							<div class="form-group">
								<label>基数：</label> 
								<input type="text" class="form-control" id="inputSuccess" ng-model="show.base">
							</div>
							<div class="form-group">
								<label>演出状态：</label> 
								<select class="form-control" ng-model="show.showState">
									<option value="0">未开始</option>
									<option value="1">进行中</option>
									<option value="2">已结束</option>
								</select>
							</div>
							<div class="form-group">
								<label>投票状态：</label> 
								<select class="form-control" ng-model="show.state">
									<option value="0">未开始</option>
									<option value="1">进行中</option>
									<option value="2">已结束</option>
								</select>
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
