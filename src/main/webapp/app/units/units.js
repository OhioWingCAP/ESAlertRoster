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
                });
        }]); // EditRequestNavigation

})(); // IIFE