angular.module('importerApp').directive('confirm', function ($modal, $translate, $translatePartialLoader) {
        return {
            restrict: 'A',
            scope: {
                action: '&confirm',
                question: '@confirmQuestion'
            },
            link: function (scope, element) {
                $translatePartialLoader.addPart('utils');
                $translate.refresh();
                element.on('click', function () {
                    $modal.open({
                        templateUrl: 'scripts/app/utils/confirm/confirm.modal.html',
                        scope: scope
                    }).result
                        .then(scope.action);
                });
            }
        };
    });
