angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                templateUrl: 'scripts/app/dashboard/dashboard.html',
                controller: 'DashboardController',
                data: {
                    roles: [],
                    pageTitle: 'global.DASHBOARD'
                }

            });
    });

