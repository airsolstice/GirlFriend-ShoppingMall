  //订单管理界面
angular.module("shoppingMallApp")
.controller("order_manager",function($scope,$http){
	$scope.data={
		lists:[]
		
	}
	$scope.currentPage = 1;
	$scope.getOrderList = function(user,page){
		var _data= {
			userId: user,
			index: page
		}
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/frame-study-demo/order/getOrderPage',
			data: _data,
			success: function(res){
				console.log(res)
				if(res.code == 200){
					$scope.$apply(function(){
						$scope.data.lists = res.model
						$scope.currentPage = res.totalpage
						console.log($scope.currentPage)
					})
				}
			}
		})
	}	
	
 	 $scope.onPageChange = function(){
 	 	var userId = sessionStorage.getItem('userId')
		$scope.getOrderList(userId,$scope.currentPage);
 	 }
	$scope.order_confirm = function(index){
		$scope.data.lists[index].order_status = 1
	}
	$scope.onPageChange();

})