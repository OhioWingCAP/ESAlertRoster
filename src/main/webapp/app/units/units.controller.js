(function () {

    'use strict';

    angular
        .module('esAlertRoster')
        .controller('UnitsController',
            ["$scope", "$window", "$state", "$stateParams", "Unit",
                function ($scope, $window, $state, $stateParams, Unit) {
                    $scope.units = Unit.query({}, function (response) {
                        response.forEach(function (singleResponse) {
                            if (singleResponse.parentCharter) {
                                singleResponse.parentUnit = Unit.get({id: singleResponse.parentCharter});
                            }
                        })
                    });
                }]); // UnitsController

    angular
        .module('esAlertRoster')
        .controller('UnitController',
            ["$scope", "$window", "$state", "$stateParams", "Unit", "Member", "$timeout",
                function ($scope, $window, $state, $stateParams, Unit, Member, $timeout) {
                    $scope.memberSearch = {};
                    $scope.unit = Unit.get({id: $stateParams.id});
                    $scope.unit.$promise.then(function (unit) {
                        unit.alertRoster = [];
                        if (unit.alertRosterCapids) {
                            unit.alertRosterCapids.forEach(function (capid) {
                                unit.alertRoster.push(Member.get({id: capid}));
                            });
                        }
                    });

                    // search modals
                    $scope.showSearchModal = function (selector, id) {
                        $(selector).modal('show');
                        $timeout(function () {
                            focus(id);
                        }, 500);
                    };

                    function contains(array, item) {
                        if(array == null) {
                            return false;
                        }
                        return array.some(function(arrayItem){
                            return item.id == arrayItem.id;
                        });
                    }
                    
                    $scope.$watch('memberSearch.input', function(newValue){
                        if(newValue && newValue.length > 3) {
                            $scope.memberSearch.result = Member.query({query: newValue});
                        } else {
                            $scope.memberSearch.result = null;
                        }
                    });

                    $scope.add = function(member) {
                        if(!contains($scope.unit.alertRoster,member)) {
                            $scope.unit.alertRoster.push(member);
                            if($scope.unit.alertRosterCapids == null) {
                                $scope.unit.alertRosterCapids = [];
                            }
                            $scope.unit.alertRosterCapids.push(member.capid);
                            $scope.save();
                        }
                        $scope.resetSearch();
                        $('#searchMember').modal('hide');
                    };

                    $scope.remove = function(member) {
                        if($scope.unit.alertRosterCapids == null) {
                            $scope.unit.alertRosterCapids = [];
                        }
                        $scope.unit.alertRoster.pop(member);
                        $scope.unit.alertRosterCapids.pop(member.capid);
                        $scope.save();
                    };

                    $scope.save = function() {
                      Unit.save({id: $scope.unit.id}, $scope.unit);
                    };

                    // resest search
                    $scope.resetSearch = function () {
                        $scope.memberSearchMessage = "Your results are empty.";
                        $scope.memberSearch.result = null;
                        $scope.memberSearch.input = "";
                    };
                }]); // UnitsController

})(); // IIFE