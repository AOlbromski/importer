angular.module('importerApp').controller('DashboardController',
    ['$scope', '$state','Principal', 'ImportItemService', function ($scope, $state, Principal, ImportItemService, toastr ) {

        ImportItemService.getAll().then(function (data) {
            $scope.rowListForView = angular.copy(data);
        });

        $scope.delete = function (id) {
            var r = confirm("Do you want to permanently delete this item?");
            if (r == true) {
                ImportItemService.delete(id).then(function (data) {
                    toastr.success('Item deleted successively');
                    $state.reload();
                });
            } else {

            }
        };

        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });


    }]);
