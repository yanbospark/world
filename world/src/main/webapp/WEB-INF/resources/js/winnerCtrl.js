function listWinner($scope,$http,$location){
	$http.get(basepath + "/back/winner/list").success(function(response) {
		console.log(response);
		$scope.winners = eval("("+decodeURIComponent(response.message)+")");
	});
	$scope.add=function(){
		$location.path("/winner/edit/0");	
	}
	$scope.edit = function(id) {
		$location.path("/winner/edit/"+id);	
	}
	$scope.del = function(id){
		$http.get(basepath + "/back/winner/del/"+id).success(function(response){
			$scope.winners= eval("("+decodeURIComponent(response.message)+")");
			$scope.$apply();
		});
	}
}	


function editWinner($scope,$http,$routeParams,$location){
	var id=$routeParams.id;
	console.log("查询id="+id);
	if(id>0){//只有在添加的时候进行请求
		$http({  
	         method:'GET',  
	         url:basepath+"/back/winner/one?id="+id,  
		 }).success(function(response){
			 $scope.winner=eval("("+decodeURIComponent(response.message)+")");
		 }).error(function(response){
			 console.log("未知错误："+response);
		 })
	} 
	$scope.submit = function(){
		console.log($scope.winner);
		var url;
		if($scope.winner.id>0) url=basepath + "/back/winner/edit"; 
		else url=basepath + "/back/winner/save";
		$http({
			method:"POST",
			url:url,
			data:$.param($scope.winner)
		})
		.success(function(response){
			$scope.message=response.message;
			$location.path("/winner");
		})
		.error(function(response){
			console.log("未知错误："+response);
		});
	}
}