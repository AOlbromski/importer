angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('password', {
                parent: 'site',
                url: '/password',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'global.menu.account.password'
                },
                templateUrl: 'scripts/app/common/auth/password/password.html',
                controller: 'PasswordController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader',
                        function ($translate,
                                  $translatePartialLoader) {
                            $translatePartialLoader.addPart('password');
                            return $translate.refresh();
                        }]
                }
            });
    });
