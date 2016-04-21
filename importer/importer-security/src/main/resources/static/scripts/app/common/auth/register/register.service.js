angular.module('importerApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {});
    });
