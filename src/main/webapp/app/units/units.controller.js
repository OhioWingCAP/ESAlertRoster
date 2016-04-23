(function () {

    'use strict';

    angular
        .module('esAlertRoster')
        .controller('UnitsController',
            ["$scope", "$window", "$state", "$stateParams", "Unit",
                function ($scope, $window, $state, $stateParams, Unit) {
                    $scope.units=Unit.query();
                }]); // UnitsController

    angular
        .module('esAlertRoster')
        .controller('UnitController',
            ["$scope", "$window", "$state", "$stateParams", "Unit",
                function ($scope, $window, $state, $stateParams, Unit) {
                    $scope.unit=Unit.get({id: $stateParams.id});
                }]); // UnitsController

})(); // IIFE