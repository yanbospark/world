function listUserVote($scope,$http,$location){
	$http.get(basepath + "/back/userVote/list").success(function(response) {
		console.log(response);
		$scope.userVotes = eval("("+decodeURIComponent(response.message)+")");
	});
	$scope.add=function(){
		$location.path("/userVote/edit/0");	
	}
	$scope.edit = function(id) {
		$location.path("/userVote/edit/"+id);	
	}
	$scope.del = function(id){
		$http.get(basepath + "/back/userVote/del/"+id).success(function(response){
			$scope.userVotes= eval("("+decodeURIComponent(response.message)+")");
			$scope.$apply();
		});
	}
}	


function editUserVote($scope,$http,$routeParams,$location){
	var id=$routeParams.id;
	console.log("查询id="+id);
	if(id>0){//只有在添加的时候进行请求
		$http({  
	         method:'GET',  
	         url:basepath+"/back/userVote/one?id="+id,  
		 }).success(function(response){
			 $scope.userVote=eval("("+decodeURIComponent(response.message)+")");
		 }).error(function(response){
			 console.log("未知错误："+response);
		 })
	} 
	$scope.submit = function(){
		console.log($scope.userVote);
		var url;
		if($scope.userVote.id>0) url=basepath + "/back/userVote/edit"; 
		else url=basepath + "/back/userVote/save";
		$http({
			method:"POST",
			url:url,
			data:$.param($scope.userVote)
		})
		.success(function(response){
			$scope.message=response.message;
			$location.path("/userVote");
		})
		.error(function(response){
			console.log("未知错误："+response);
		});
	}
}