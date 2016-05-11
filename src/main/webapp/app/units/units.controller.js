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
            ["$scope", "$window", "$state", "$stateParams", "Unit", "Member",
                function ($scope, $window, $state, $stateParams, Unit, Member) {
                    $scope.unit=Unit.get({id: $stateParams.id});
                    $scope.unit.$promise.then(function(unit) {
                        unit.alertRoster = [];
                        unit.alertRosterCapids.forEach(function(capid){
                            unit.alertRoster.push(Member.get({id: capid}));
                        });
                    });

                    // resest search
                    $scope.resetSearch = function () {
                        $scope.memberSearchMessage = "Your results are empty.";
                        $scope.memberssArray = [];
                        $scope.customersObject.searchCustomers = "";
                        $scope.isDisabled = true;
                        $scope.selectedResult = null;
                    };
                }]); // UnitsController

})(); // IIFE