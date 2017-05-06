angular.module('shoppingMallApp')
  .controller('detail',function(tempDetail,orderList,$scope,$location,$http){
  	$scope.cli= function(){
  		$scope.good = tempDetail.tempObj
  	}
  	$scope.data = {
  		currentImg : 1
  	}
    $scope.id = 0
    $scope.buyNow = function(){
      var user = sessionStorage.getItem('userId')
      if(user){
        orderList.tempObj = $scope.good;
        console.log('我要买的', orderList.tempObj);
        $location.path("order_list")
      }else{
        swal('要登录才能购买哦')
        sessionStorage.clear()
        $location.path("login")
      }
      
    }
    $scope.addCart = function(indexId){
      var _data = {
              id: indexId,
              userId: sessionStorage.getItem('userId'),
              num: 1,
              goodsId:$scope.good.id,
      }
      var queryData = $.param(_data)
      $http({
        method:'POST',
        url:'http://localhost:8080/frame-study-demo/car/saveCar',
        data: queryData,
        headers : {'Content-Type':'application/x-www-form-urlencoded'},  
      }).then(function successCallback(res){
          console.log(res)
          if(res.data.code == 200){
            swal('success','宝贝已经加入购物车了！','success')
            $scope.id = res.data.model
          }
      })
    }
    $scope.buyCart = function(){
       var user = sessionStorage.getItem('userId')
       if(user){
          var goodId;
          if($scope.id != 0 ){
            goodId == $scope.id
          }else{
            goodId = '';
          }
           $scope.addCart(goodId);
       }else{
        swal('请先登录哦')
        sessionStorage.clear()
        $location.path('login')
       }
        
    }
  	$scope.changeBigImg = function(param){
  		$scope.data.currentImg = param 
  	}
  	$scope.cli();
  })