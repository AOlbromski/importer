angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider

            .state('imports.items', {
                url: '/items',
                templateUrl: 'scripts/app/importItem/list.html',
                data: {pageTitle: 'Imported Items'},
                controller: 'DashboardController'
            })
    });
