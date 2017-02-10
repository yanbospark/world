function listUser($scope,$http,$location){
	$http.get(basepath + "/back/user/list").success(function(response) {
		console.log(response);
		$scope.users = eval("("+decodeURIComponent(response.message)+")");
	});
	$scope.add=function(){
		$location.path("/user/edit/0");	
	}
	$scope.edit = function(id) {
		$location.path("/user/edit/"+id);	
	}
	$scope.del = function(id){
		$http.get(basepath + "/back/user/del/"+id).success(function(response){
			$scope.users= eval("("+decodeURIComponent(response.message)+")");
			$scope.$apply();
		});
	}
	$scope.search = function(){
		var username=$("input[name='username']").val();
		if(username){
			$http.get(basepath+"/back/user/byUsername/"+encodeURIComponent(encodeURIComponent(username))).success(function(response){
				$scope.users= eval("("+decodeURIComponent(response.message)+")");
				$scope.$apply();
			})
		}
	}
}	


function editUser($scope,$http,$routeParams,$location){
	var id=$routeParams.id;
	//没图片隐藏DIV
	if(id>0) $scope.show=true;
	else $scope.show=false;
	console.log("查询id="+id);
	if(id>0){//只有在添加的时候进行请求
		$http({  
	         method:'GET',  
	         url:basepath+"/back/user/one?id="+id,  
		 }).success(function(response){
			 $scope.user=eval("("+decodeURIComponent(response.message)+")");
		 }).error(function(response){
			 console.log("未知错误："+response);
		 })		
	} 
	//图片上传
	$scope.uploadImage=function(){
		var id=$("#u_id").val();
		$("#imageForm").attr("action",basepath+"/user/upload");
		var data=new FormData($('#imageForm')[0]);
		//data.append("id",id);//添加额外的参数
    	$.ajax({
    		type:"POST",
    		url:basepath+"/back/user/upload",
    		data: data,
    		cache: false,
    		processData: false,
    	    contentType: false,
    		success:function(data){
    			data=eval("("+data+")");
    			if(id>0){
    				$scope.user.img=data.message;
    			}else{
    				$scope.show=true;
    				$scope.user={img:data.message};
    				$scope.$apply();
    			} 
    			$("#showImage").attr("src",data.message+"?random="+Math.random());//防止图片缓存
    		},
    		error:function(){
    			console.log("error");
    		}
    	})
	}
//	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	$scope.submit = function(){
		debugger;
		console.log($scope.user);
		var url;
		if($scope.user.id>0) url=basepath + "/back/user/edit"; 
		else url=basepath + "/back/user/save";
		$http({
			method:"POST",
			url:url,
			data:$.param($scope.user)
		})
		.success(function(response){
			$scope.message=response.message;
			$location.path("/user");
		})
		.error(function(response){
			console.log("未知错误："+response);
		});
	}
}