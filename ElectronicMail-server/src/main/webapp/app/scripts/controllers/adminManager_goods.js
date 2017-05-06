angular.module("shoppingMallApp")
.controller("adminManager_goods",function($scope,$location,$http){
	$scope.newGood = {
		name:'',
		detail: '',
		url: '',
		catalog: '',
		price:'',
		addTime:''
	}
	$scope.index_catalog = 1
	$scope.catalog = 0
	$scope.isUpdate = false;
	$scope.goodLists_catalog = 1;
	$scope.currentPage = 1;
    $scope.pageCount = 1;
	//获取所有商品
	$scope.getAllGoods=function(page,param_index){
		$scope.goodLists_catalog = param_index;
		 $.ajax({
	        type:'GET',
	        url:'http://localhost:8080/frame-study-demo/goods/catalog/page',
	        data:{index:page,catalog:param_index},
	        success:function(res){
	              if(res.code == 200){
	                $scope.$apply(function(){
	                 $scope.lists = res.model
	                 $scope.pageCount = res.totalpage;
	                 console.log($scope.pageCount)
	                 $scope.index_catalog = res.model[0].catalog
	                 $.each($scope.lists,function(i){
	                    return $scope.lists[i].url = $scope.lists[i].url.split('|')
	                 })
	                })
	               
	            }else{
	              swal(res.model)
	            }
	        }
      }) 
	}
	//删除商品
	$scope.good_del = function(index){
		var queryData = $.param({id:index.id})
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
			   $http({
				method:'POST',
				url:'http://localhost:8080/frame-study-demo/goods/delete',
				data:queryData,
				headers:  {'Content-Type':'application/x-www-form-urlencoded'}
			}).then(function successCallback(res){
				if(res.data.code == 200){
					swal("Deleted!", "删除成功", "success");
					$scope.getAllGoods(1,index.catalog)
				}else{
					 swal("Cancelled", "删除失败 :)", "error");
				}
			})
			 $scope.lists.splice(index,1)
		})	
	}
	//增加商品
	$scope.submitForm_add = function(){
		Date.prototype.format = function(format) {
				    var date = {
				       "M+": this.getMonth() + 1,
				       "d+": this.getDate(),
				       "h+": this.getHours(),
				       "m+": this.getMinutes(),
				       "s+": this.getSeconds(),
				       "q+": Math.floor((this.getMonth() + 3) / 3),
				       "S+": this.getMilliseconds()
				    };
				    if (/(y+)/i.test(format)) {
				       format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
				    }
				    for (var k in date) {
				       if (new RegExp("(" + k + ")").test(format)) {
				           format = format.replace(RegExp.$1, RegExp.$1.length == 1
				              ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
				       }
				    }
				    return format;
		}
		var addTimeStr = new Date();
		$scope.newGood.addTime = addTimeStr.format('yyyy-MM-dd h:m:s')
		$scope.newGood.url=$scope.newGood.url_1 + '|' + $scope.newGood.url_2 + '|'+$scope.newGood.url_3
		var queryData = $.param($scope.newGood);
		if($scope.addGoods.$invalid){
			swal('请检查你的信息')
		}else{
			$http({
				method: 'POST',
				url: 'http://localhost:8080/frame-study-demo/goods/add',
				data: queryData,
				headers: {'Content-Type':'application/x-www-form-urlencoded'},
			}).then(function successCallback(res){
				if(res.data.code == 200){
					swal('添加成功')
					$location.path("adminManager_goods")
				}else{
					swal('再检查一下信息呢')
				}
			},function errorCallback(){

			})
		}	
	}
	//更新商品信息
	$scope.toUpdate =function(index){
		$scope.isUpdate = true;
		$scope.updateGood_info = index ;
		$scope.updateGood_info.url_1 =  $scope.updateGood_info.url[0];
		$scope.updateGood_info.url_2 =  $scope.updateGood_info.url[1];
		$scope.updateGood_info.url_3 =  $scope.updateGood_info.url[2];
	}  
	//提交更新
	$scope.submitForm_update = function(){
		$scope.updateGood_info.url=$scope.updateGood_info.url_1 + '|' + $scope.updateGood_info.url_2 + '|'+$scope.updateGood_info.url_3
		var queryData = $.param($scope.updateGood_info)
		if($scope.updateGoods.$invalid){
			swal('请检查你的信息')
		}else{
			$http({
				method: 'POST',
				url: 'http://localhost:8080/frame-study-demo/goods/updateGoods',
				data: queryData,
				headers: {'Content-Type':'application/x-www-form-urlencoded'},
			}).then(function successCallback(res){
				if(res.data.code == 200){
					swal(res.data.msg);
					$scope.isUpdate = false;
					$scope.getAllGoods(1,1);
				}
			},function errorCallback(){

			})
		}
	}
	//返回---取消修改
	$scope.backto = function(){
		$scope.isUpdate = false;
	}
	$scope.onPageChange = function(){
		$scope.getAllGoods($scope.currentPage,$scope.index_catalog)	
 	}
	$scope.onPageChange()

})
  