angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('activate', {
                parent: 'site',
                url: '/activate?key',
                data: {
                    roles: [],
                    pageTitle: 'activate.title'
                },
                templateUrl: 'scripts/app/common/auth/activate/activate.html',
                controller: 'ActivationController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('activate');
                        return $translate.refresh();
                    }]
                }
            });
    });

