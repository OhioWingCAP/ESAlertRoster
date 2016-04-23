/**
 * Created by ckovacs on 4/23/16.
 */

    var app = angular.module('esAlertRoster', [
        'ui.bootstrap',
        'ngResource',
        'ui.router'
    ]);


    app.controller("MainController",
            ["$scope", "$rootScope", "$state", "$http",
                function ($scope, $rootScope, $state, $http) {
                    $scope.test = 'Something to display on the screen';
                }]);
    app.config(["$urlRouterProvider", function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/units');
        }]);

    app.run(['$templateCache',"$rootScope", "$state", "$location", "$window", function ($templateCache, $rootScope, $state, $location, $window) {

        }]);

