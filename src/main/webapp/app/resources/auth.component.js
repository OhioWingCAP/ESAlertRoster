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
                'isLoggedIn': {
                    method: 'GET',
                    url: 'view/api/auth/isLoggedIn',
                    responseType: ""
                },
                'logout' : {
                    method: 'GET',
                    url: 'view/api/auth/logout'
                }
            });
        }]);
})();