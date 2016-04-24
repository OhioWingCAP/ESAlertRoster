(function () {

    'use strict';

    angular
        .module('esAlertRoster')
        .controller('UnitsController',
            ["$scope", "$window", "$state", "$stateParams", "Unit",
                function ($scope, $window, $state, $stateParams, Unit) {
                    $scope.units=Unit.query({},function(response) {
                        response.forEach(function(singleResponse){
                            if(singleResponse.parentCharter) {
                                singleResponse.parentUnit = Unit.get({id: singleResponse.parentCharter});
                            }
                        })
                    });
                }]); // UnitsController

    angular
        .module('esAlertRoster')
        .controller('UnitController',
            ["$scope", "$window", "$state", "$stateParams", "Unit",
                function ($scope, $window, $state, $stateParams, Unit) {
                    $scope.unit=Unit.get({id: $stateParams.id});
                }]); // UnitsController

})(); // IIFE