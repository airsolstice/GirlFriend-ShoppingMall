'use strict';

/**
 * @ngdoc overview
 * @name shoppingMallApp
 * @description
 * # shoppingMallApp
 *
 * Main module of the application.
 */
angular
  .module('shoppingMallApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngSanitize',
    'ngTouch',
    'ui.router',
    'oc.lazyLoad',
    'mgcrea.ngStrap',
    'ng-pagination'
  ])


.config(function ($stateProvider,$urlRouterProvider) {
      $urlRouterProvider.when("", "/main/main_index");
      $stateProvider
          .state("main", {
              url: "/main",
              templateUrl: "views/main.html"
          })
          .state("main.main_index", {
              url: "/main_index",
              views:{
                '':{
                  templateUrl: "views/main_index.html"
                }
              }
              
          })
          .state("about", {
              url:"/about",
              templateUrl: "views/about.html"
          })
          .state("contact", {
              url:"/contact",
              views:{
                '':{
                  templateUrl: "views/contact.html"
                }
              }
          })
         .state("login",{
            url:"/login",
            templateUrl: "views/login.html",
            controller:"loginController",
            resolve:{
                   deps:["$ocLazyLoad",function($ocLazyLoad){
                     return $ocLazyLoad.load("scripts/controllers/login.js");
                   }]
                 }   
          })
         .state("adminLogin",{
            url: "/adminLogin",
            templateUrl:"views/adminLogin.html",
            resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/adminLogin.js");
               }]
             }
            })
          .state("adminIndex",{
            url: "/adminIndex",
            views:{
              '':{
                templateUrl:"views/adminIndex.html",
              }
            }
          })
          .state("adminChangePass",{
            url:"/adminChangePass",
            templateUrl:"views/adminChangePassword.html",
            resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/adminChangePass.js");
               }]
             }
          })
          .state("adminManager_goods",{
            url:"/adminManager_goods",
            templateUrl:"views/adminManager_goods.html",
             resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/adminManager_goods.js");
               }]
             }
          })
         .state("adminManager_order",{
            url:"/adminManager_order",
            templateUrl:"views/adminManager_order.html",
             resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/adminManager_order.js");
               }]
             }
        
          })
          .state("register",{
            url:"/register",
            templateUrl: "views/register.html",
            controller:"signUpController",
            resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/signUp.js");
               }]
             }
          })
          .state("registerMsg",{
            url:"/registerMsg",
            views: {
              '':{
                templateUrl: "views/registerMsg.html",
                controller:"signUpController"
              }
            }
          })
          .state("changePass",{
            url:"/changePass",
            templateUrl: "views/changePassword.html",
            controller:"changePassController",
            resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/changePassword.js");
               }]
             }
          })
          .state("changePass_Msg",{
            url:"/changePass_Msg",
            views: {
              '':{
                templateUrl: "views/changePass_Msg.html"
              }
            }
          })
          .state("main.shop_hufu",{
            url:"/shop_hufu",
            views: {
              '':{
                templateUrl: "views/shop_hufu.html"
              }
            }
          })
          .state("main.shop_makeup",{
            url:"/shop_makeup",
            views: {
              '':{
                templateUrl: "views/shop_makeup.html"
              }
            }
          })
          .state("main.shop_fashion",{
            url:"/shop_fashion",
            views: {
              '':{
                templateUrl: "views/shop_fashion.html"
              }
            }
          })
          .state("main.shop_accesory",{
            url:"/shop_accesory",
            views: {
              '':{
                templateUrl: "views/shop_accesory.html"
              }
            }
          })
          .state("main.shop_home",{
            url:"/shop_home",
            views: {
              '':{
                templateUrl: "views/shop_home.html"
              }
            }
          })
          .state("main.shop_search",{
            url:"/shop_search",
            views: {
              '':{
                templateUrl: "views/shop_search.html"
              }
            }
          })
          .state("main.detail",{
            url:"/detail",
            templateUrl: "views/detail.html",
            resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/detail.js");
               }]
             }
          })
          .state("order_list",{
            url:"/order_list",
            templateUrl: "views/order_list.html",
            resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/order.js");
               }]
             }
          })
          .state("order_success",{
            url:"/order_success",
            views:{
              '':{
                templateUrl: "views/order_success.html"
              }
            }
          })
          .state("order_manager",{
            url:"/order_manager",
            templateUrl: "views/order_manager.html",
              resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/order_manager.js");
               }]
             }
          })
          .state("adminAdd_goods",{
            url:"/adminAdd_goods",
            views:{
              '':{
                templateUrl: "views/adminAdd_goods.html"
              }
            }
          })
          .state("main.cart",{
            url:"/cart",
            templateUrl: "views/cart.html",
            resolve:{
               deps:["$ocLazyLoad",function($ocLazyLoad){
                 return $ocLazyLoad.load("scripts/controllers/cart.js");
               }]
             }
          })

  })
.provider('tempDetail', function () {
            var tempObj = {a:'1'}
            this.$get = function () {
                return tempObj;
            };
   })
.provider('orderList', function () {
            var tempObj = {a:'1'}
            this.$get = function () {
                return tempObj;
            };
   });