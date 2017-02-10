function listShow($scope,$http,$location){
	$http.get(basepath + "/back/show/list").success(function(response) {
		console.log(response);
		$scope.shows = eval("("+decodeURIComponent(response.message)+")");
	});
	$scope.add=function(){
		$location.path("/show/edit/0");	
	}
	$scope.edit = function(id) {
		$location.path("/show/edit/"+id);	
	}
	$scope.del = function(id){
		$http.get(basepath + "/back/show/del/"+id).success(function(response){
			$scope.shows= eval("("+decodeURIComponent(response.message)+")");
			$scope.$apply();
		});
	}
}	


function editShow($scope,$http,$routeParams,$location){
	var id=$routeParams.id;
	if(id>0) $scope.myshow=true;
	else $scope.myshow=false;
	console.log("查询id="+id);
	if(id>0){//只有在添加的时候进行请求
		$http({  
	         method:'GET',  
	         url:basepath+"/back/show/one?id="+id,  
		 }).success(function(response){
			 $scope.show=eval("("+decodeURIComponent(response.message)+")");
		 }).error(function(response){
			 console.log("未知错误："+response);
		 })
	} 
	
	//图片上传
	$scope.uploadImage=function(){
		var id=$("#u_id").val();
		debugger;
		$("#imageForm").attr("action",basepath+"/show/upload");
		var data=new FormData($('#imageForm')[0]);
		//data.append("id",id);//添加额外的参数
    	$.ajax({
    		type:"POST",
    		url:basepath+"/back/show/upload",
    		data: data,
    		cache: false,
    		processData: false,
    	    contentType: false,
    		success:function(data){
    			data=eval("("+data+")");
    			if(id>0){
    				$scope.show.teamImg=data.message;
    			}else{
    				$scope.myshow=true;
    				$scope.show={teamImg:data.message};
    				$scope.$apply();
    			} 
    			$("#showImage").attr("src",data.message+"?random="+Math.random());//防止图片缓存
    		},
    		error:function(){
    			console.log("error");
    		}
    	})
	}
	
	$scope.submit = function(){
		console.log($scope.show);
		var url;
		if($scope.show.id>0) url=basepath + "/back/show/edit"; 
		else url=basepath + "/back/show/save";
		$http({
			method:"POST",
			url:url,
			data:$.param($scope.show)
		})
		.success(function(response){
			$scope.message=response.message;
			$location.path("/show");
		})
		.error(function(response){
			console.log("未知错误："+response);
		});
	}
}