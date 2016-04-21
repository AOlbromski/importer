angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('logout', {
                parent: 'site',
                url: '/logout',
                data: {
                    roles: []
                },
                templateUrl: 'scripts/app/dashboard/dashboard.html',
                controller: 'LogoutController'
            });
    });
