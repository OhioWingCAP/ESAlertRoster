(function () {

    'use strict';

    angular.module('esAlertRoster')
        .factory('Unit', ["$resource", function ($resource) {
            return $resource('view/api/units/:id', {}, {
                'query': {method: 'GET', isArray: true},
                'get': {
                    method: 'GET'
                },
                'update': {
                    method: 'PUT'
                }
            });
        }]);
})();