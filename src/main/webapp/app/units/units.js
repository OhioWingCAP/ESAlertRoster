(function () {

    'use strict';

    angular
        .module('esAlertRoster')
        .config(["$stateProvider", "$urlRouterProvider", function ($stateProvider) {
            $stateProvider
                .state('units', {
                    url: '/units',
                    templateUrl: 'app/units/units.html',
                    controller: 'UnitsController'
                })
                .state('unit', {
                    url: '/units/{id}',
                    templateUrl: 'app/units/unit.html',
                    controller: 'UnitController'
            });
        }]); // EditRequestNavigation

})(); // IIFE