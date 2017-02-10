function listAward($scope,$http,$location){
	$http.get(basepath + "/back/award/list").success(function(response) {
		console.log(response);
		$scope.awards = eval("("+decodeURIComponent(response.message)+")");
	});
	$scope.add=function(){
		$location.path("/award/edit/0");	
	}
	$scope.edit = function(id) {
		console.log("edit->id=" + id);
		$location.path("/award/edit/"+id);	
	}
	$scope.del = function(id){
		console.log("delete->id=" + id);
		$http.get(basepath + "/back/award/delete/"+id).success(function(response){
			$scope.awards= eval("("+decodeURIComponent(response.message)+")");
			$scope.$apply();
		});
	}
}	


function editAward($scope,$http,$routeParams,$location){
	debugger;
	var id=$routeParams.id;
	console.log("查询id="+id);
	if(id>0){//只有在添加的时候进行请求
		$http({  
	         method:'GET',  
	         url:basepath+"/back/award/one?id="+id,  
		 }).success(function(response){
			 $scope.award=eval("("+decodeURIComponent(response.message)+")");
		 }).error(function(response){
			 console.log("未知错误："+response);
		 })
	} 
//	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	$scope.submit = function(){
		console.log($scope.award);
		var url;
		if($scope.award.id>0) url=basepath + "/back/award/edit"; 
		else url=basepath + "/back/award/save";
		$http({
			method:"POST",
			url:url,
			data:$.param($scope.award)
		})
		.success(function(response){
			$scope.message=response.message;
			$location.path("/award");
		})
		.error(function(response){
			console.log("未知错误："+response);
		});
	}
	$scope.reset = function(){
		console.log("m="+m+"<>master="+$scope.master);
		$scope.award=angular.copy($scope.master);
	}
}