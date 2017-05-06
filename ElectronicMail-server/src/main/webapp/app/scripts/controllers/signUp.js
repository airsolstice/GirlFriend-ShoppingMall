angular.module('shoppingMallApp')
.controller("signUpController",function($scope,$http,$location){
		$scope.userdata = {}
		$scope.pwd2=""
		$scope.validMsg=""
		$scope.signUpSubmit = function(){
			if($scope.signUpForm.$invalid){
				swal('请检查你的信息')
			}else{
				var queryData = $.param( $scope.userdata);  
				$http({
					method: 'POST',
					url: 'http://localhost:8080/frame-study-demo/user/regist',
					data: queryData,
					headers : {'Content-Type':'application/x-www-form-urlencoded'},  
				}).then(function successCallback(res) {
					if(res.data.code == 200){
						$location.path("registerMsg")
						$scope.validMsg=res.data.msg
					}else{
						swal(res.data.model)
					}
					}, function errorCallback(res) {
						swal(res.data.model)
				});
			}
		}
	})


	.directive('compare',function(){
		var o = {};
		o.strict = 'AE';
		o.scope={
			orgText: '=compare'
		}
		o.require = 'ngModel';
		o.link = function(sco, ele, att, con){
			con.$validators.compare = function(v){
				return v == sco.orgText;
			}
			sco.$watch('orgText',function(){
				con.$validate();
			});
		}
		return o;

	})