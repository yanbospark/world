function listVote($scope,$http,$location){
	$http.get(basepath + "/back/vote/list").success(function(response) {
		console.log(response);
		$scope.votes = eval("("+decodeURIComponent(response.message)+")");
	});
	$scope.add=function(){
		$location.path("/vote/edit/0");	
	}
	$scope.edit = function(id) {
		$location.path("/vote/edit/"+id);	
	}
	$scope.del = function(id){
		$http.get(basepath + "/back/vote/del/"+id).success(function(response){
			$scope.votes= eval("("+decodeURIComponent(response.message)+")");
			$scope.$apply();
		});
	}
}	


function editVote($scope,$http,$routeParams,$location){
	var id=$routeParams.id;
	console.log("查询id="+id);
	if(id>0){//只有在添加的时候进行请求
		$http({  
	         method:'GET',  
	         url:basepath+"/back/vote/one?id="+id,  
		 }).success(function(response){
			 $scope.vote=eval("("+decodeURIComponent(response.message)+")");
		 }).error(function(response){
			 console.log("未知错误："+response);
		 })
	} 
	$scope.submit = function(){
		console.log($scope.vote);
		var url;
		if($scope.vote.id>0) url=basepath + "/back/vote/edit"; 
		else url=basepath + "/back/vote/save";
		$http({
			method:"POST",
			url:url,
			data:$.param($scope.vote)
		})
		.success(function(response){
			$scope.message=response.message;
			$location.path("/vote");
		})
		.error(function(response){
			console.log("未知错误："+response);
		});
	}
}