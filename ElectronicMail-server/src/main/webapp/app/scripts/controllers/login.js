angular.module('shoppingMallApp')
.controller("loginController",function($scope,$http,$location){
	$scope.loginData = {}
	$scope.idTip=false
	$scope.pwdTip=false
	$scope.idErr=""
	$scope.pwdErr=""
	$scope.loginSubmit = function(){
		var queryData = $.param( $scope.loginData);  
		$http({
			method: 'POST',
			url: 'http://localhost:8080/frame-study-demo/user/login',
			data: queryData,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},  
		}).then(function successCallback(res) {
			 if(res.data.code == 200){
				 sessionStorage.setItem("userName", res.data.model.name);
				 sessionStorage.setItem("userId", res.data.model.id);
				 $location.path("main/main_index")
				setTimeout('window.location.reload()',10)
			 }else{
				if(res.data.model == "账号不存在"){
					$scope.idTip = true
					$scope.pwdTip= false
					$scope.idErr = res.data.model
				}else if(res.data.model=="账号或密码错误"){
					 $scope.pwdTip= true
					 $scope.idTip = false
					 $scope.pwdErr=res.data.model
				}else{
					swal('请检查你的登录信息')
				}
			 }
			}, function errorCallback(res) {
				  $location.path("login")
		});
	}
})