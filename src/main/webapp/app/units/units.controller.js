(function () {

    'use strict';

    angular
        .module('esAlertRoster')
        .controller('UnitsController',
            ["$scope", "$window", "$state", "$stateParams", "Unit",
                function ($scope, $window, $state, $stateParams, Unit) {
                    $scope.units=Unit.query();
                }]); // UnitsController

})(); // IIFE