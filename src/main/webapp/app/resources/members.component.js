(function () {

    'use strict';

    angular.module('esAlertRoster')
        .factory('Member', ["$resource", function ($resource) {
            return $resource('view/api/members/:id', {}, {
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