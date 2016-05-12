(function () {

    'use strict';

    angular
        .module('esAlertRoster')
        .config(["$stateProvider", "$urlRouterProvider", function ($stateProvider) {
            $stateProvider
                .state('units', {
                    url: '/units',
                    templateUrl: 'app/admin/admin.html',
                    controller: 'AdminController'
                });
        }]); // EditRequestNavigation

})(); // IIFE