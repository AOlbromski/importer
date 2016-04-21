angular.module('importerApp')
    .factory('Password', function ($resource) {
        return $resource('api/account/change_password', {}, {});
    });
