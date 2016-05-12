/**
 * Created by ckovacs on 4/23/16.
 */

    var app = angular.module('esAlertRoster', [
        'ui.bootstrap',
        'ngResource',
        'ui.router'
    ]);


    app.controller("MainController",
        ["$scope", "$rootScope", "$state", "$http", "Auth", "$window",
        function ($scope, $rootScope, $state, $http, Auth, $window) {
            $scope.loggedIn = Auth.isLoggedIn();
            $scope.loggedIn.$promise.then(function(){
                if(!$scope.loggedIn == 'anonymousUser') {
                    $scope.loginUrl.$promise.then(function(){
                        $window.location.href=$scope.loginUrl.url;
                    });
                }
            });
            $scope.user = Auth.get();
            $scope.loginUrl = Auth.loginUrl({}, $window.location.href);
            $scope.loginUrl.$promise.then(function(loginUrl){
                $scope.logoutUrl = Auth.logoutUrl({}, loginUrl.url);
            });
            $scope.logout = function() {
                $window.location.href = $scope.logoutUrl.url;
            };

        }]);
    app.config(["$urlRouterProvider", function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/units');
        }]);

    app.run(['$templateCache',"$rootScope", "$state", "$location", "$window", function ($templateCache, $rootScope, $state, $location, $window) {

        }]);

