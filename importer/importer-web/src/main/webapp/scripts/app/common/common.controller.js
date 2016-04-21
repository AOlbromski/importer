angular.module('importerApp').controller('CommonController', function ($scope, Principal, Account) {

    Account.get().$promise.then(function (account) {
        $scope.account = angular.copy(account.data);
        $scope.isAuth = Principal.isAuthenticated;
    });

    $scope.$on('reloadData', function () {
        Account.get().$promise.then(function (account) {
            $scope.account = angular.copy(account.data);
        });
    });
});
