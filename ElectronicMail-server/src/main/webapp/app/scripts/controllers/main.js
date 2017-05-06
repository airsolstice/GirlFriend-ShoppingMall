'use strict';

/**
 * @ngdoc function
 * @name shoppingMallApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the shoppingMallApp
 */
angular.module('shoppingMallApp')
  .controller('MainCtrl', function () {
   
  });


angular.module('shoppingMallApp')
.controller("indexCtrl", function($scope,$location){
   $scope.logined = false
   $scope.adminLogined = false;
   $scope.admin= ''
    var userName = sessionStorage.getItem('userName')
    if(userName){
      $scope.rightData = "您好,"+userName
      $scope.logined= true
    }else{
    }
    var adminId = sessionStorage.getItem('adminId')
    if(adminId){
       $scope.adminLogined = true;
       $scope.admin = adminId
    }
   $scope.logout = function(){
     sessionStorage.clear()
     setTimeout('window.location.reload()',50)
   }

})


angular.module('shoppingMallApp')
  .controller('MainCtrl_new_shopping', function ($scope,$http,$location,tempDetail) {
    $scope.data = {
      current : 1,
      index_catalog:1
    };
    $scope.searchKey=''
    $scope.searchResult=''
    /*分页*/
    $scope.currentPage = 1;
    $scope.pageCount = 1;
    var locationUrl = window.location.href;
    var locationRoute = locationUrl.substring(locationUrl.lastIndexOf('/'))
    $scope.onPageChange = function() {
      if(locationRoute == '/shop_makeup' ){
        $scope.changeCatalog($scope.currentPage,1) 
      }else if(locationRoute == '/shop_hufu'){
       $scope.changeCatalog($scope.currentPage,2)  
      }else if(locationRoute == '/shop_fashion'){
        $scope.changeCatalog($scope.currentPage,3)  
      }else if(locationRoute == '/shop_accesory'){
       $scope.changeCatalog($scope.currentPage,4)
      }else if(locationRoute == '/shop_home'){
        $scope.changeCatalog($scope.currentPage,5)
      }
    };
    /*分类详情---5种商品*/
    $scope.changeCatalog = function(page,param_catalog){
      $scope.data.index_catalog = param_catalog  
      switch( $scope.data.index_catalog ){
        case 0:
            $location.path('main/main_index')
            break;
        case 1: 
            $location.path('main/shop_makeup')
            break;
        case 2: 
            $location.path('main/shop_hufu')
            break;
        case 3:
            $location.path('main/shop_fashion')
            break;
        case 4: 
            $location.path('main/shop_accesory')
            break;
        case 5:
            $location.path('main/shop_home')
            break;
        default:
            $location.path('main/main_index')
            break;
      }
      $.ajax({
        type:'GET',
        url:'http://localhost:8080/frame-study-demo/goods/catalog/page',
        data:{index:page,catalog:param_catalog},
        success:function(res){
              if(res.code == 200){
                $scope.$apply(function(){
                 $scope.goods = res.model
                 $scope.pageCount = res.totalpage;
                 $.each($scope.goods,function(i){
                    return $scope.goods[i].url = $scope.goods[i].url.split('|')
                 })
                })
               
            }else{
             swal(res.model);
            }
        }
      }) 
    }
   /* tab选项卡---新商品*/
    $scope.setCurrent =  function(param){
        $scope.data.current = param;
        $.ajax({
          type:'GET',
          url:'http://localhost:8080/frame-study-demo/goods/catalog/top10',
          data:{catalog:param},
          success:function(res){
                if(res.code == 200){
                  $scope.$apply(function(){
                   $scope.new_goods = res.model
                   $.each($scope.new_goods,function(i){
                      return $scope.new_goods[i].url = $scope.new_goods[i].url.split('|')
                   })
                  })
                 
              }else{
                swal(res.model);
              }
          }
        })
    }
    //跳转到详情页
    $scope.toDetail = function(index){
      tempDetail.tempObj = index;
      $location.path("main/detail")
    }
    //搜索商品
    $scope.searchGoods= function(){
      $location.path('main/shop_search')
      $scope.goods = []
      $.ajax({
        type:'GET',
        url:'http://localhost:8080/frame-study-demo/goods/search',
        data:{key: $scope.searchKey},
        success:function(res){
          $scope.$apply(function(){
           if(res.code == 200){
             if(res.model.length){
              swal('success',res.msg,'success')
                  $scope.goods = res.model
                  $.each($scope.goods,function(i){
                        return $scope.goods[i].url = $scope.goods[i].url.split('|')
                  })
                 
             }else{
                 $scope.searchResult = res.msg
                 swal('error',res.msg,'error')
             }
             
           }
          })
        }
      })
    }
    /*第一次加载或刷新页面*/
    $scope.setCurrent(5)
    $scope.onPageChange()
  });