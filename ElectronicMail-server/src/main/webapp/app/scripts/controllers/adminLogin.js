angular.module("shoppingMallApp")
.controller("adminController",["$scope","$location",'$http',function($scope,$location,$http){
	$scope.loginData={}
	$scope.submitForm = function(){
		if($scope.adminLoginForm.$invalid){
				swal('请检查你的信息');
			}else{
					$scope.loginData.isAdmin = 1;
					var queryData = $.param( $scope.loginData);  
					$http({
						method: 'POST',
						url: 'http://localhost:8080/frame-study-demo/user/login',
						data: queryData,
						headers : {'Content-Type':'application/x-www-form-urlencoded'},  
					}).then(function successCallback(res) {
						 if(res.data.code == 200){
						 	sessionStorage.removeItem('userName')
						 	sessionStorage.removeItem('userId')
						 	sessionStorage.setItem('adminId',res.data.model.id)
							  $location.path("/adminIndex")
							setTimeout('window.location.reload()',10)
						 }else{
								swal('请检查你的登录信息')
						 }
						}, function errorCallback(res) {
							
					});
				 
			}
	}
}])
