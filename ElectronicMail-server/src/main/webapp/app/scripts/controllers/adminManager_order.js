angular.module("shoppingMallApp")
.controller("adminManager_order",function($scope,$http){
	$scope.data={
		lists:[]	
	}
	$scope.pageCount = 1;
	$scope.currentPage =1;
	//获取全部订单
	$scope.getOrderList = function(page){
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/frame-study-demo/order/getAllOrderPage',
			data: {index: page},
			success: function(res){
				console.log(res)
				if(res.code == 200){
					$scope.$apply(function(){
						$scope.data.lists = res.model
						$scope.pageCount = res.totalpage
						console.log($scope.pageCount)
					})
				}
			}
		})
	}	
	//删除订单
	$scope.delOrder = function(index,delIndex){
		console.log(index)
		var _data = {id: index.id}
		swal({
		   title: "确定要删除吗？",
		   text: "删除该商品后不能再找回哦！",
		   type: "warning",
		   showCancelButton: true,
		   confirmButtonColor: "#DD6B55",
		   confirmButtonText: "确定删除",
		   closeOnConfirm: false
		}, 
		function(){ 
		   $.ajax({
			type: 'get',
			url: 'http://localhost:8080/frame-study-demo/order/deleteOrder',
			data:{id:index.id},
			success:function(res){
				console.log(res)
				if(res.code == 200){
					swal('success','订单删除成功','success')
					$scope.data.lists.splice(delIndex,1)
					window.location.reload();
				}
			}
		})
		 	$scope.data.lists.splice(index,1)
		})
	}
	
	$scope.onPageChange= function(){
		$scope.getOrderList($scope.currentPage)
	}
	$scope.onPageChange()
})
   
  