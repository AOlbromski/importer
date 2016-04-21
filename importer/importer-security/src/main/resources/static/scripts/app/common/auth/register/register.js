angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('register', {
                parent: 'site',
                url: '/register',
                data: {
                    roles: [],
                    pageTitle: 'register.title'
                },
                templateUrl: 'scripts/app/common/auth/register/register.html',
                controller: 'RegisterController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('register');
                        return $translate.refresh();
                    }]
                }
            });
    });
