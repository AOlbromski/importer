angular.module('importerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('imports', {
                url: '/imports',
                abstract: true,
                templateUrl: 'scripts/app/common/common.html',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('global');
                        $translatePartialLoader.addPart('language');
                        return $translate.refresh();
                    }]
                }
            })
            .state('imports.all', {
                url: '/all',
                templateUrl: 'scripts/app/import/list.html',
                data: {pageTitle: 'Imports List'},
                controller: 'ImportListController',
                resolve: {
                    itemsList: function (ImportService) {
                        return ImportService.getAll().then(function (data) {
                            return data;
                        });
                    }
                }
            })
            .state('imports.new', {
                url: "/new",
                templateUrl:'scripts/app/import/new.html',
                data: { pageTitle: 'New Import' },
                controller: 'ImportNewController'

            })
            .state('imports.new.step_one', {
                url: '/step_one',
                templateUrl: 'scripts/app/import/wizard/step_one.html',
                data: { pageTitle: 'Wizard form' }
            })
            .state('imports.new.step_two', {
                url: '/step_two',
                templateUrl: 'scripts/app/import/wizard/step_two.html',
                data: { pageTitle: 'Wizard form' }
            })
            .state('imports.new.step_three', {
                url: '/step_three',
                templateUrl: 'scripts/app/import/wizard/step_three.html',
                data: { pageTitle: 'Wizard form' }
            })


    });
