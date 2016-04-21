angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                parent: 'site',
                url: '/login',
                data: {
                    roles: [],
                    pageTitle: 'login.title'
                },
                templateUrl: 'scripts/app/common/auth/login/login.html',
                controller: 'LoginController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader',
                        function ($translate,
                                  $translatePartialLoader) {
                            $translatePartialLoader.addPart('login');
                            return $translate.refresh();
                        }]
                }
            });
    });
