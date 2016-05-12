(function () {

    'use strict';

    angular.module('esAlertRoster')
        .factory('Auth', ["$resource", function ($resource) {
            return $resource('view/api/auth/current', {}, {
                'get': {
                    method: 'GET'
                },
                'loginUrl': {
                    method: 'POST',
                    url: 'view/api/auth/loginUrl'
                },
                'logoutUrl': {
                    method: 'POST',
                    url: 'view/api/auth/logoutUrl',
                    responseType: ""
                },
                'isLoggedIn': {
                    method: 'GET',
                    action: 'view/api/auth/isLoggedIn',
                    responseType: ""
                }
            });
        }]);
})();