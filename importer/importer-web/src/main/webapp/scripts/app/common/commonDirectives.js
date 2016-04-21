function sideNavigation($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element) {
            $timeout(function () {
                element.metisMenu();
            });
        }
    };
}

angular.module('ventoApp')
    .directive('sideNavigation', sideNavigation);
