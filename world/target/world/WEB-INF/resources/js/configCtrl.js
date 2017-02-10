function listConfig($scope,$http,$location){
	$http.get(basepath + "/back/config/list").success(function(response) {
		console.log(response);
		$scope.configs = eval("("+decodeURIComponent(response.message)+")");
	});
	$scope.add=function(){
		$location.path("/config/edit/0");	
	}
	$scope.edit = function(id) {
		$location.path("/config/edit/"+id);	
	}
	$scope.del = function(id){
		$http.get(basepath + "/back/config/del/"+id).success(function(response){
			$scope.configs= eval("("+decodeURIComponent(response.message)+")");
			$scope.$apply();
		});
	}
}	


function editConfig($scope,$http,$routeParams,$location){
	var id=$routeParams.id;
	console.log("查询id="+id);
	if(id>0){//只有在添加的时候进行请求
		$http({  
	         method:'GET',  
	         url:basepath+"/back/config/one?id="+id,  
		 }).success(function(response){
			 $scope.config=eval("("+decodeURIComponent(response.message)+")");
		 }).error(function(response){
			 console.log("未知错误："+response);
		 })
	} 
	$scope.submit = function(){
		console.log($scope.config);
		var url;
		if($scope.config.id>0) url=basepath + "/back/config/edit"; 
		else url=basepath + "/back/config/save";
		$http({
			method:"POST",
			url:url,
			data:$.param($scope.config)
		})
		.success(function(response){
			$scope.message=response.message;
			$location.path("/config");
		})
		.error(function(response){
			console.log("未知错误："+response);
		});
	}
}