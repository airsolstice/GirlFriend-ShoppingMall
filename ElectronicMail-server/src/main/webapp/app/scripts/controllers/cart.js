angular.module('shoppingMallApp')
  .controller('cart',function($scope,orderList,$location,$http){
  	$scope.cartEmpty = false;
  	$scope.cart_lists=[]
    $scope.getCartData = function(){
          $.ajax({
            type: 'GET',
            url:'http://localhost:8080/frame-study-demo/car/getShoppingCarList',
            data:{
              userId: sessionStorage.getItem('userId')
            },
            success:function(res){
              console.log(res)
              if(res.code == 200){
                $scope.cart_lists= res.model;
                $.each($scope.cart_lists,function(i){
                  return $scope.cart_lists[i].url = $scope.cart_lists[i].url.split('|')
                })
                $scope.empty()
              }
            }
          })    
    }
	//判断是否有数据
  	$scope.empty = function(){
  		if($scope.cart_lists.length == 0){
		    	$scope.cartEmpty = true;
		  }
  	}
  	//全选
  	$scope.checkAll=function(){  
            for(var i in $scope.cart_lists){  
                 $scope.cart_lists[i].check=$scope.all;  
            }  
        }  
    //删除某一项
    $scope.deleteCart=function(index,delIndex){   
      console.log(index)
      var _data = {
        userId: sessionStorage.getItem('userId'),
        goodsId: index.goodsId
      }
      var queryData = $.param(_data)
          $http({
            method: 'POST',
            url:'http://localhost:8080/frame-study-demo/car/deleteCarGoodsByGdId',
            data: queryData,
            headers : {'Content-Type':'application/x-www-form-urlencoded'},  
          }).then(function successCallback(res){
            if(res.data.code == 200){
               $scope.cart_lists.splice(delIndex,1);  
            }
          })
         	$scope.empty()
    }  
    //增加商品在购物车的数量
    $scope.add=function(index){   
            $scope.cart_lists[index].num +=1 ;  
    }  
    //减少商品在购物车的数量
    $scope.reduce=function(index){   
             
             if( $scope.cart_lists[index].num <= 1){
             	 $scope.cart_lists[index].num = 1
             }else{
             	 $scope.cart_lists[index].num -=1; 
             }
            
    }  
   	//删除选中的所有商品
    $scope.deleteCheckedAll = function(){
        var _data = {
          userId: sessionStorage.getItem('userId')
        }
        var queryData = $.param(_data)
        $http({
            method: 'POST',
            url:'http://localhost:8080/frame-study-demo/car/deleteCarGoodsByGdId',
            data: queryData,
            headers : {'Content-Type':'application/x-www-form-urlencoded'},  
        }).then(function successCallback(res){
            if(res.data.code == 200){
                for(var i = 0; i < $scope.cart_lists.length; i++){
                    if( $scope.cart_lists[i].check ) {
                        $scope.cart_lists.splice($scope.cart_lists[i],1);  
                        i--;
                      }
                }
             }
          })

        $scope.empty()   
    }
    //监听数据变化
  	$scope.$watch('cart_lists',function(newValue,oldValue,scope){
  		$scope.sumTotal=0;  
        $scope.checkedNum=0;  
        $scope.checkedNum = 0;
        $scope.checkedGoods = [];
        for(var i in newValue){  
            var total=newValue[i].num*newValue[i].price; //计算出新的结果  
            $scope.cart_lists[i].totalPrice=total.toFixed(2); //保留2位小数并且把它赋值给元数据;    
            if(newValue[i].check){  
                $scope.sumTotal+=total;  
                $scope.checkedGoods.push(newValue[i])
                $scope.checkedNum++;  

            }  
            orderList.tempObj = $scope.checkedGoods;             
        }  
        $scope.all=($scope.checkedNum==newValue.length); //如果所有的都选中的话，那么全选也应该选中 
  	},true)
    //结算
    $scope.sum = function(){
      if( $scope.checkedNum == 0){
        swal('您并没有选中任何商品哦')
      }else{
        console.log('购物车要买的',orderList.tempObj)
        $location.path("order_list")
      }
    }

    
    $scope.getCartData();

  })