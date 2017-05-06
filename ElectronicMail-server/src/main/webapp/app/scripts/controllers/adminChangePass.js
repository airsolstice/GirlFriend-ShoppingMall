angular.module("shoppingMallApp")
.controller("changePassController",function($scope,$http,$location){
	$scope.passData={}
	$scope.pwd2 = ''
	$scope.passData.isAdmin = 1;
	$scope.submitForm = function(){
		var queryData = $.param($scope.passData)
		if($scope.changePassForm.$invalid){
				swal('请检查你的信息');
			}else{
				$http({
					method:'POST',
					url:'http://localhost:8080/frame-study-demo/user/forgetpwd',
					data: queryData,
					headers:{'Content-Type':'application/x-www-form-urlencoded'}
				}).then(
					function successCallback(res){
						console.log(res)
						if(res.data.code == 200){
							swal('success','管理员密码修改成功','success')
							$location.path('adminLogin')
						}else{
							swal(res.data.model)
						}
					},function errorCallback(res) {
				})
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